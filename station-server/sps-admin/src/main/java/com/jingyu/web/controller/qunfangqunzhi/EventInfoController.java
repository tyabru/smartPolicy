package com.jingyu.web.controller.qunfangqunzhi;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.jingyu.qunfangqunzhi.domain.EventInfo;
import com.jingyu.qunfangqunzhi.service.IEventInfoService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 上报事件管理Controller
 *
 * @author jingyu
 * @date 2023-07-03
 */
@RestController
@RequestMapping("/qf/threatmanagement")
public class EventInfoController extends BaseController
{
    @Autowired
    private IEventInfoService eventInfoService;

    /**
     * 查询上报事件管理列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:list')")
    @GetMapping("/list")
    public TableDataInfo list(EventInfo eventInfo)
    {
        startPage();
        List<EventInfo> list = eventInfoService.selectEventInfoList(eventInfo);
        return getDataTable(list);
    }

    /**
     * 导出上报事件管理列表
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:export')")
    @Log(title = "上报事件管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, EventInfo eventInfo)
    {
        List<EventInfo> list = eventInfoService.selectEventInfoList(eventInfo);
        ExcelUtil<EventInfo> util = new ExcelUtil<EventInfo>(EventInfo.class);
        util.exportExcel(response, list, "上报事件管理数据");
    }

    /**
     * 获取上报事件管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(eventInfoService.selectEventInfoById(id));
    }

    /**
     * 新增上报事件管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:add')")
    @Log(title = "上报事件管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EventInfo eventInfo)
    {

        return toAjax(eventInfoService.insertEventInfo(eventInfo));
    }

    /**
     * 修改上报事件管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:edit')")
    @Log(title = "上报事件管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EventInfo eventInfo)
    {
        return toAjax(eventInfoService.updateEventInfo(eventInfo));
    }

    /**
     * 删除上报事件管理
     */
    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:threatmanagement:remove')")
    @Log(title = "上报事件管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(eventInfoService.deleteEventInfoByIds(ids));
    }



}
