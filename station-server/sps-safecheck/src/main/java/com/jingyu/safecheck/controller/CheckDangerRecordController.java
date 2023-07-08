package com.jingyu.safecheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckDangerRecord;
import com.jingyu.safecheck.service.ICheckDangerRecordService;
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
 * 安全隐患检查登记Controller
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/safecheck/checkdanger")
public class CheckDangerRecordController extends BaseController
{
    @Autowired
    private ICheckDangerRecordService checkDangerRecordService;

    /**
     * 查询安全隐患检查登记列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckDangerRecord checkDangerRecord)
    {
        startPage();
        List<CheckDangerRecord> list = checkDangerRecordService.selectCheckDangerRecordList(checkDangerRecord);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患检查登记列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:export')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckDangerRecord checkDangerRecord)
    {
        List<CheckDangerRecord> list = checkDangerRecordService.selectCheckDangerRecordList(checkDangerRecord);
        ExcelUtil<CheckDangerRecord> util = new ExcelUtil<CheckDangerRecord>(CheckDangerRecord.class);
        util.exportExcel(response, list, "安全隐患检查登记数据");
    }

    /**
     * 获取安全隐患检查登记详细信息
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkDangerRecordService.selectCheckDangerRecordById(id));
    }

    /**
     * 新增安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:add')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckDangerRecord checkDangerRecord)
    {
        return toAjax(checkDangerRecordService.insertCheckDangerRecord(checkDangerRecord));
    }

    /**
     * 修改安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:edit')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckDangerRecord checkDangerRecord)
    {
        return toAjax(checkDangerRecordService.updateCheckDangerRecord(checkDangerRecord));
    }

    /**
     * 删除安全隐患检查登记
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkdanger:remove')")
    @Log(title = "安全隐患检查登记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkDangerRecordService.deleteCheckDangerRecordByIds(ids));
    }
}
