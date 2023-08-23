package com.jingyu.web.controller.qunfangqunzhi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.common.core.domain.entity.SysUser;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.domain.EventInfo;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import com.jingyu.qunfangqunzhi.service.IEventInfoService;
import com.jingyu.qunfangqunzhi.service.IEventUserAllocatedService;
import com.jingyu.qunfangqunzhi.util.MyIdUtil;
import com.jingyu.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;

import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 事件分配Controller
 *
 * @author jingyu
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/qf/allocated")
public class EventUserAllocatedController extends BaseController
{
    @Autowired
    private IEventUserAllocatedService eventUserAllocatedService;

    @Autowired
    private IEventInfoService eventInfoService;

    @Autowired
    private ICommonUsersService usersService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询事件分配列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping("/list")
    public TableDataInfo list(EventUserAllocated eventUserAllocated)
    {
        startPage();
        List<EventUserAllocated> list = eventUserAllocatedService.selectEventUserAllocatedList(eventUserAllocated);
        for(EventUserAllocated item:list){
            Long userId = item.getUserId();
            CommonUser commonUser = usersService.selectCommonUsersByUserId(userId);
            eventUserAllocated.getParams().put("userName", commonUser.getUserName());
            eventUserAllocated.getParams().put("realName", commonUser.getRealName());

        }
        return getDataTable(list);
    }

    /**
     * 导出事件分配列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "事件分配", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EventUserAllocated eventUserAllocated)
    {
        List<EventUserAllocated> list = eventUserAllocatedService.selectEventUserAllocatedList(eventUserAllocated);
        ExcelUtil<EventUserAllocated> util = new ExcelUtil<EventUserAllocated>(EventUserAllocated.class);
        util.exportExcel(response, list, "事件分配数据");
    }

    /**
     * 获取事件分配详细信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        EventUserAllocated eventUserAllocated = eventUserAllocatedService.selectEventUserAllocatedById(id);
        Long userId = eventUserAllocated.getUserId();
        if(eventUserAllocated.getDealFlag().equals(QFConstants.EventDealType.ALLOCATE.getValue())){
            CommonUser commonUser = usersService.selectCommonUsersByUserId(userId);
            eventUserAllocated.getParams().put("userName", commonUser.getUserName());
            eventUserAllocated.getParams().put("realName", commonUser.getRealName());
            eventUserAllocated.getParams().put("userType", commonUser.getUserType());
        }else {
            SysUser sysUser = sysUserService.selectUserById(userId);
            eventUserAllocated.getParams().put("userName", sysUser.getUserName());
        }

        return success(eventUserAllocated);
    }


    /**
     * 下发事件
     * @param userIds
     * @param eventId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping("/downloadEvent/{userIds}/{eventId}")
    public AjaxResult downloadEvent(@PathVariable("userIds") Long[] userIds ,@PathVariable("eventId")Long eventId){
        Date date = new Date();
        List<EventUserAllocated>  list = new ArrayList<>();
        for(Long userId:userIds){
            EventUserAllocated eventUserAllocated = new EventUserAllocated();
            eventUserAllocated.setAllocateUserId(SecurityUtils.getUserId());
            eventUserAllocated.setAllocateTime(new Date());
            eventUserAllocated.setUserId(userId);
            eventUserAllocated.setId(MyIdUtil.getRandomId());
            eventUserAllocated.setEventId(eventId);
            eventUserAllocated.setStatus(QFConstants.AllocatedEventStatus.UNCONFIRMED.getValue());
            eventUserAllocated.setAllocatedUserType(QFConstants.AllocateUserType.SYSTEM_USER.getValue());
            eventUserAllocated.setAllocateTime(date);
            list.add(eventUserAllocated);
        }
        eventUserAllocatedService.insertBatchEventUserAllocated(list);
        EventInfo eventInfo = new EventInfo();
        eventInfo.setId(eventId);
        eventInfo.setStatus(QFConstants.EventStatus.CONFIRMED.getValue());
        eventInfoService.updateEventInfo(eventInfo);
        return AjaxResult.success("下发事件成功");
    }


    /**
     * 取消下发事件
     * @return
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping("/cancelEvent/{ids}")
    public AjaxResult cancelEvent(@PathVariable("ids") Long[] ids){
        for(Long id : ids){
            EventUserAllocated alterAllocated = new EventUserAllocated();
            alterAllocated.setId(id);
            alterAllocated.setStatus(QFConstants.AllocatedEventStatus.CANCELED.getValue());
            System.out.println(id+"777");
            eventUserAllocatedService.updateEventUserAllocated(alterAllocated);
        }
        return AjaxResult.success("取消成功");

    }

    /**
     * 处置事件
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "事件分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventUserAllocated eventUserAllocated)
    {
        eventUserAllocated.setAllocateTime(new Date());
        eventUserAllocated.setUserType(QFConstants.AllocateUserType.SYSTEM_USER.getValue());
        eventUserAllocated.setAllocateUserId(SecurityUtils.getUserId());
        eventUserAllocated.setUserId(SecurityUtils.getUserId());
        eventUserAllocated.setStatus(QFConstants.AllocatedEventStatus.COMPLETED.getValue());
        eventUserAllocated.setAllocatedUserType(QFConstants.AllocateUserType.SYSTEM_USER.getValue());
        eventUserAllocated.setDealFlag(QFConstants.EventDealType.DEAL.getValue());
        eventUserAllocated.setUpdateTime(new Date());
        eventUserAllocatedService.insertEventUserAllocated(eventUserAllocated);
        Long eventId = eventUserAllocated.getEventId();
        EventInfo eventInfo = new EventInfo();
        eventInfo.setId(eventId);
        eventInfo.setStatus(QFConstants.EventStatus.CONFIRMED.getValue());
        return toAjax(eventInfoService.updateEventInfo(eventInfo));
    }

    /**
     * 修改事件分配
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "事件分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventUserAllocated eventUserAllocated)
    {
        return toAjax(eventUserAllocatedService.updateEventUserAllocated(eventUserAllocated));
    }

    /**
     * 删除事件分配
     */

    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @Log(title = "事件分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventUserAllocatedService.deleteEventUserAllocatedByIds(ids));
    }
}
