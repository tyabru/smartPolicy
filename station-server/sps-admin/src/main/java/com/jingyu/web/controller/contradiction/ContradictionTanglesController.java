package com.jingyu.web.controller.contradiction;

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
import com.jingyu.contradiction.domain.ContradictionTangles;
import com.jingyu.contradiction.service.IContradictionTanglesService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 矛盾纠纷基本信息Controller
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/contradiction/contradiction")
public class ContradictionTanglesController extends BaseController
{
    @Autowired
    private IContradictionTanglesService contradictionTanglesService;

    /**
     * 查询矛盾纠纷基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:list')")
    @GetMapping("/list")
    public TableDataInfo list(ContradictionTangles contradictionTangles)
    {
        startPage();
        List<ContradictionTangles> list = contradictionTanglesService.selectContradictionTanglesList(contradictionTangles);
        return getDataTable(list);
    }

    /**
     * 导出矛盾纠纷基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:export')")
    @Log(title = "矛盾纠纷基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ContradictionTangles contradictionTangles)
    {
        List<ContradictionTangles> list = contradictionTanglesService.selectContradictionTanglesList(contradictionTangles);
        ExcelUtil<ContradictionTangles> util = new ExcelUtil<ContradictionTangles>(ContradictionTangles.class);
        util.exportExcel(response, list, "矛盾纠纷基本信息数据");
    }

    /**
     * 获取矛盾纠纷基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(contradictionTanglesService.selectContradictionTanglesById(id));
    }

    /**
     * 获取矛盾纠纷基本信息详细信息
     */
    @GetMapping("getContradictionByCaseCode/{caseCode}")
    public AjaxResult getContradictionByCaseCode(@PathVariable("caseCode") String caseCode)
    {
        ContradictionTangles contradictionByCaseCode = contradictionTanglesService.getContradictionByCaseCode(caseCode);
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult.put("data",contradictionByCaseCode);
    }

    /**
     * 新增矛盾纠纷基本信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:add')")
    @Log(title = "矛盾纠纷基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ContradictionTangles contradictionTangles)
    {
        return toAjax(contradictionTanglesService.insertContradictionTangles(contradictionTangles));
    }

    /**
     * 修改矛盾纠纷基本信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:edit')")
    @Log(title = "矛盾纠纷基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ContradictionTangles contradictionTangles)
    {
        return toAjax(contradictionTanglesService.updateContradictionTangles(contradictionTangles));
    }

    /**
     * 删除矛盾纠纷基本信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:contradiction:remove')")
    @Log(title = "矛盾纠纷基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(contradictionTanglesService.deleteContradictionTanglesByIds(ids));
    }
}
