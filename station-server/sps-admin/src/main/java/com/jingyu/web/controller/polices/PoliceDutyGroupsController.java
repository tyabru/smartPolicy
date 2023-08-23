package com.jingyu.web.controller.polices;

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
import com.jingyu.polices.domain.PoliceDutyGroups;
import com.jingyu.polices.service.IPoliceDutyGroupsService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 警员分组详情Controller
 * 
 * @author jiatongbo
 * @date 2023-07-04
 */
@RestController
@RequestMapping("/polices/polices")
public class PoliceDutyGroupsController extends BaseController
{
    @Autowired
    private IPoliceDutyGroupsService policeDutyGroupsService;

    /**
     * 查询警员分组详情列表
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceDutyGroups policeDutyGroups)
    {
        startPage();
        List<PoliceDutyGroups> list = policeDutyGroupsService.selectPoliceDutyGroupsList(policeDutyGroups);
        return getDataTable(list);
    }

    /**
     * 导出警员分组详情列表
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:export')")
    @Log(title = "警员分组详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceDutyGroups policeDutyGroups)
    {
        List<PoliceDutyGroups> list = policeDutyGroupsService.selectPoliceDutyGroupsList(policeDutyGroups);
        ExcelUtil<PoliceDutyGroups> util = new ExcelUtil<PoliceDutyGroups>(PoliceDutyGroups.class);
        util.exportExcel(response, list, "警员分组详情数据");
    }

    /**
     * 获取警员分组详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeDutyGroupsService.selectPoliceDutyGroupsById(id));
    }

    /**
     * 新增警员分组详情
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:add')")
    @Log(title = "警员分组详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceDutyGroups policeDutyGroups)
    {
        return toAjax(policeDutyGroupsService.insertPoliceDutyGroups(policeDutyGroups));
    }

    /**
     * 修改警员分组详情
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:edit')")
    @Log(title = "警员分组详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceDutyGroups policeDutyGroups)
    {
        return toAjax(policeDutyGroupsService.updatePoliceDutyGroups(policeDutyGroups));
    }

    /**
     * 删除警员分组详情
     */
    @PreAuthorize("@ss.hasPermi('polices:polices:remove')")
    @Log(title = "警员分组详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeDutyGroupsService.deletePoliceDutyGroupsByIds(ids));
    }
}
