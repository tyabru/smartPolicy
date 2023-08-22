package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.domain.entity.SysUser;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.domain.CommonUsersApplication;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;
import com.jingyu.qunfangqunzhi.service.ICommonUsersApplicationService;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import com.jingyu.qunfangqunzhi.service.IEventUserAllocatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.security.util.SecurityConstants;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 普通用户信息Controller
 *
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/qf/commonUsers")
public class CommonUsersController extends BaseController
{
    @Autowired
    private ICommonUsersService commonUsersService;


    @Autowired
    private IEventUserAllocatedService allocatedService;

    @Autowired
    private ICommonUsersApplicationService applicationService;


    /**
     * 查询普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommonUser commonUser)
    {
        startPage();
        List<CommonUser> list = commonUsersService.selectCommonUsersList(commonUser);
        return getDataTable(list);
    }


    /**
     * 获取网格员列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:list')")
    @GetMapping("/listGridStaff")
    public TableDataInfo listGridStaff(CommonUser user, Long eventID)
    {
        System.out.println(user);
        System.out.println("eventID:"+eventID);
        startPage();
        List<CommonUser> list = commonUsersService.selectCommonUsersList(user);
        EventUserAllocated allocated = new EventUserAllocated();
        allocated.setEventId(eventID);
        List<EventUserAllocated> eventUserAllocateds = allocatedService.selectEventUserAllocatedList(allocated);
        List<Long> userIDs = eventUserAllocateds.stream().filter(eventUserAllocated ->
                    !eventUserAllocated.getStatus().equals(QFConstants.AllocatedEventStatus.CANCELED.getValue()))
                    .map(eventUserAllocated -> eventUserAllocated.getUserId()).collect(Collectors.toList());
        //过滤掉角色非网格员长用户
        for(Iterator<CommonUser> iterator = list.iterator(); iterator.hasNext();){
            CommonUser commonUser = iterator.next();
            //如果已经下发了，直接跳过到下一个用户
            if(userIDs.contains(commonUser.getUserId())){
                iterator.remove();
                continue;
            };
            List<String> rolesList = Arrays.asList(commonUser.getUserType().split(","));
           //过滤掉非网格员角色
            if(!rolesList.contains(CommonUserConstants.CommonUserType.WANGGEYUANZHANG.getValue())&&!rolesList.contains(CommonUserConstants.CommonUserType.WANGGEYUAN.getValue())){
                iterator.remove();
            }
        }
        TableDataInfo dataTable = getDataTable(list);
        dataTable.setTotal(list.size());
        return dataTable;
    }



    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:edit')")
    @Log(title = "普通用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody CommonUser user)
    {
        user.setUpdateBy(getUsername());
        return toAjax(commonUsersService.updateCommonUsers(user));
    }


    /**
     * 导出普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:export')")
    @Log(title = "普通用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommonUser commonUser)
    {
        List<CommonUser> list = commonUsersService.selectCommonUsersList(commonUser);
        ExcelUtil<CommonUser> util = new ExcelUtil<CommonUser>(CommonUser.class);
        util.exportExcel(response, list, "普通用户信息数据");
    }

    /**
     * 获取普通用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commonUsersService.selectCommonUsersByUserId(userId));
    }

    /**
     * 新增普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:add')")
    @Log(title = "普通用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.insertCommonUsers(commonUser));
    }

    /**
     * 修改普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:edit')")
    @Log(title = "普通用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.updateCommonUsers(commonUser));
    }

    /**
     * 处理网格员申请
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:edit')")
    @Log(title = "处理网格员申请", businessType = BusinessType.UPDATE)
    @PostMapping("/handleApplication")
    public AjaxResult handleApplication(@RequestBody CommonUser commonUser,Long applicationId,boolean flag)
    {
        //批准请求
        if(flag){
            commonUsersService.handleUserApplication(commonUser, applicationId);
        }else{
            CommonUsersApplication application = new CommonUsersApplication();
            application.setId(applicationId);
            application.setStatus(CommonUserConstants.CommonUserApplicationStatus.REJECT.getValue());
            applicationService.updateCommonUsersApplication(application);
        }

        return AjaxResult.success("申请信息处理成功");
    }

    /**
     * 删除普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:CommonUsers:remove')")
    @Log(title = "普通用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(commonUsersService.deleteCommonUsersByUserIds(userIds));
    }


}
