package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.domain.entity.SysUser;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;
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


    /**
     * 查询普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:list')")
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
        List<Long> userIDs = eventUserAllocateds.stream().map(eventUserAllocated -> eventUserAllocated.getUserId()).collect(Collectors.toList());
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
            if(!rolesList.contains(CommonUserConstants.CommonUserType.WANGGEYUANZHANG.getValue())){
                iterator.remove();
            }
        }
        return getDataTable(list);
    }

    /**
     * 导出普通用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:export')")
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
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commonUsersService.selectCommonUsersByUserId(userId));
    }

    /**
     * 新增普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:add')")
    @Log(title = "普通用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.insertCommonUsers(commonUser));
    }

    /**
     * 修改普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:edit')")
    @Log(title = "普通用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonUser commonUser)
    {
        return toAjax(commonUsersService.updateCommonUsers(commonUser));
    }

    /**
     * 删除普通用户信息
     */
    @PreAuthorize("@ss.hasPermi('system:users:remove')")
    @Log(title = "普通用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(commonUsersService.deleteCommonUsersByUserIds(userIds));
    }
}
