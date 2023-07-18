package com.jingyu.safecheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckRectifyResultReviewRecord;
import com.jingyu.safecheck.service.ICheckRectifyResultReviewRecordService;
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
 * 整改结果审核Controller
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/safecheck/rectifyresultreview")
public class CheckRectifyResultReviewRecordController extends BaseController
{
    @Autowired
    private ICheckRectifyResultReviewRecordService checkRectifyResultReviewRecordService;

    /**
     * 查询整改结果审核列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        startPage();
        List<CheckRectifyResultReviewRecord> list = checkRectifyResultReviewRecordService.selectCheckRectifyResultReviewRecordList(checkRectifyResultReviewRecord);
        return getDataTable(list);
    }

    /**
     * 导出整改结果审核列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:export')")
    @Log(title = "整改结果审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        List<CheckRectifyResultReviewRecord> list = checkRectifyResultReviewRecordService.selectCheckRectifyResultReviewRecordList(checkRectifyResultReviewRecord);
        ExcelUtil<CheckRectifyResultReviewRecord> util = new ExcelUtil<CheckRectifyResultReviewRecord>(CheckRectifyResultReviewRecord.class);
        util.exportExcel(response, list, "整改结果审核数据");
    }

    /**
     * 获取整改结果审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkRectifyResultReviewRecordService.selectCheckRectifyResultReviewRecordById(id));
    }

    /**
     * 新增整改结果审核
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:add')")
    @Log(title = "整改结果审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        return toAjax(checkRectifyResultReviewRecordService.insertCheckRectifyResultReviewRecord(checkRectifyResultReviewRecord));
    }

    /**
     * 修改整改结果审核
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:edit')")
    @Log(title = "整改结果审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        return toAjax(checkRectifyResultReviewRecordService.updateCheckRectifyResultReviewRecord(checkRectifyResultReviewRecord));
    }

    /**
     * 删除整改结果审核
     */
    @PreAuthorize("@ss.hasPermi('safecheck:rectifyresultreview:remove')")
    @Log(title = "整改结果审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkRectifyResultReviewRecordService.deleteCheckRectifyResultReviewRecordByIds(ids));
    }
}
