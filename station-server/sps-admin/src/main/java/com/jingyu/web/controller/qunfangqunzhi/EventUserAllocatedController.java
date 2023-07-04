package com.jingyu.web.controller.qunfangqunzhi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.qunfangqunzhi.service.IEventUserAllocatedService;
import com.jingyu.qunfangqunzhi.util.MyIdUtil;
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

    /**
     * 查询事件分配列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventUserAllocated eventUserAllocated)
    {
        startPage();
        List<EventUserAllocated> list = eventUserAllocatedService.selectEventUserAllocatedList(eventUserAllocated);
        return getDataTable(list);
    }

    /**
     * 导出事件分配列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:export')")
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
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eventUserAllocatedService.selectEventUserAllocatedById(id));
    }



    @GetMapping("/downloadEvent/{userIds}/{eventId}")
    public AjaxResult downloadEvent(@PathVariable("userIds") Long[] userIds ,@PathVariable("eventId")Long eventId){
        Date date = new Date();
        List<EventUserAllocated>  list = new ArrayList<>();
        for(Long userId:userIds){
            EventUserAllocated eventUserAllocated = new EventUserAllocated();
            eventUserAllocated.setAllocateUserId(SecurityUtils.getUserId());
            eventUserAllocated.setUserId(userId);
            eventUserAllocated.setId(MyIdUtil.getRandomId());
            eventUserAllocated.setEventId(eventId);
            eventUserAllocated.setStatus("0");
            list.add(eventUserAllocated);
        }
        eventUserAllocatedService.insertBatchEventUserAllocated(list);
        return AjaxResult.success("下发事件成功");

    }

    /**
     * 新增事件分配
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:add')")
    @Log(title = "事件分配", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventUserAllocated eventUserAllocated)
    {
        return toAjax(eventUserAllocatedService.insertEventUserAllocated(eventUserAllocated));
    }

    /**
     * 修改事件分配
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:edit')")
    @Log(title = "事件分配", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventUserAllocated eventUserAllocated)
    {
        return toAjax(eventUserAllocatedService.updateEventUserAllocated(eventUserAllocated));
    }

    /**
     * 删除事件分配
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:allocated:remove')")
    @Log(title = "事件分配", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventUserAllocatedService.deleteEventUserAllocatedByIds(ids));
    }
}
