package com.jingyu.safecheck.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.safecheck.domain.CheckItemDict;
import com.jingyu.safecheck.service.ICheckItemDictService;
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
 * 安全隐患检查项字典Controller
 * 
 * @author ruoyi
 * @date 2023-06-29
 */
@RestController
@RequestMapping("/safecheck/checkitemdict")
public class CheckItemDictController extends BaseController
{
    @Autowired
    private ICheckItemDictService checkItemDictService;

    /**
     * 查询安全隐患检查项字典列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckItemDict checkItemDict)
    {
        startPage();
        List<CheckItemDict> list = checkItemDictService.selectCheckItemDictList(checkItemDict);
        return getDataTable(list);
    }

    /**
     * 导出安全隐患检查项字典列表
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:export')")
    @Log(title = "安全隐患检查项字典", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckItemDict checkItemDict)
    {
        List<CheckItemDict> list = checkItemDictService.selectCheckItemDictList(checkItemDict);
        ExcelUtil<CheckItemDict> util = new ExcelUtil<CheckItemDict>(CheckItemDict.class);
        util.exportExcel(response, list, "安全隐患检查项字典数据");
    }

    /**
     * 获取安全隐患检查项字典详细信息
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(checkItemDictService.selectCheckItemDictById(id));
    }

    /**
     * 新增安全隐患检查项字典
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:add')")
    @Log(title = "安全隐患检查项字典", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckItemDict checkItemDict)
    {
        return toAjax(checkItemDictService.insertCheckItemDict(checkItemDict));
    }

    /**
     * 修改安全隐患检查项字典
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:edit')")
    @Log(title = "安全隐患检查项字典", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckItemDict checkItemDict)
    {
        return toAjax(checkItemDictService.updateCheckItemDict(checkItemDict));
    }

    /**
     * 删除安全隐患检查项字典
     */
    @PreAuthorize("@ss.hasPermi('safecheck:checkitemdict:remove')")
    @Log(title = "安全隐患检查项字典", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(checkItemDictService.deleteCheckItemDictByIds(ids));
    }
}
