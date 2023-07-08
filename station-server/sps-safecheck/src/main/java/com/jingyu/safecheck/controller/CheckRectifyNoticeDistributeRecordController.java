package com.jingyu.safecheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeRecordService;
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
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 整改通知书下发Controller
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/safecheck/rectifynoticedistribute")
public class CheckRectifyNoticeDistributeRecordController extends BaseController
{
    @Autowired
    private ICheckRectifyNoticeDistributeRecordService checkRectifyNoticeDistributeRecordService;

    /**
     * 查询整改通知书下发列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        startPage();
        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);
        return getDataTable(list);
    }

    /**
     * 导出整改通知书下发列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:export')")
    @Log(title = "整改通知书下发", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);
        ExcelUtil<CheckRectifyNoticeDistributeRecord> util = new ExcelUtil<CheckRectifyNoticeDistributeRecord>(CheckRectifyNoticeDistributeRecord.class);
        util.exportExcel(response, list, "整改通知书下发数据");
    }

    /**
     * 获取整改通知书下发详细信息
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordById(id));
    }

    /**
     * 新增整改通知书下发
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:add')")
    @Log(title = "整改通知书下发", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.insertCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord));
    }

    /**
     * 修改整改通知书下发
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:edit')")
    @Log(title = "整改通知书下发", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.updateCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord));
    }

    /**
     * 删除整改通知书下发
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifynoticedistribute:remove')")
    @Log(title = "整改通知书下发", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkRectifyNoticeDistributeRecordService.deleteCheckRectifyNoticeDistributeRecordByIds(ids));
    }
}
