package com.jingyu.safecheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckPlaceDict;
import com.jingyu.safecheck.service.ICheckPlaceDictService;
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
 * 安全隐患检查场所字典Controller
 * 
 * @author ruoyi
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/safecheck/checkplacedict")
public class CheckPlaceDictController extends BaseController
{
    @Autowired
    private ICheckPlaceDictService checkPlaceDictService;

    /**
     * 查询安全隐患检查场所字典列表
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckPlaceDict checkPlaceDict)
    {
        startPage();
        List<CheckPlaceDict> list = checkPlaceDictService.selectCheckPlaceDictList(checkPlaceDict);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患检查场所字典列表
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:export')")
    @Log(title = "安全隐患检查场所字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckPlaceDict checkPlaceDict)
    {
        List<CheckPlaceDict> list = checkPlaceDictService.selectCheckPlaceDictList(checkPlaceDict);
        ExcelUtil<CheckPlaceDict> util = new ExcelUtil<CheckPlaceDict>(CheckPlaceDict.class);
        util.exportExcel(response, list, "安全隐患检查场所字典数据");
    }

    /**
     * 获取安全隐患检查场所字典详细信息
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkPlaceDictService.selectCheckPlaceDictById(id));
    }

    /**
     * 新增安全隐患检查场所字典
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:add')")
    @Log(title = "安全隐患检查场所字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckPlaceDict checkPlaceDict)
    {
        return toAjax(checkPlaceDictService.insertCheckPlaceDict(checkPlaceDict));
    }

    /**
     * 修改安全隐患检查场所字典
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:edit')")
    @Log(title = "安全隐患检查场所字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckPlaceDict checkPlaceDict)
    {
        return toAjax(checkPlaceDictService.updateCheckPlaceDict(checkPlaceDict));
    }

    /**
     * 删除安全隐患检查场所字典
     */
//    @PreAuthorize("@ss.hasPermi('safecheck:checkplacedict:remove')")
    @Log(title = "安全隐患检查场所字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkPlaceDictService.deleteCheckPlaceDictByIds(ids));
    }
}
