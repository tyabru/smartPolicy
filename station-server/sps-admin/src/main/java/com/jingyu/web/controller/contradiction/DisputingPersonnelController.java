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
import com.jingyu.contradiction.domain.DisputingPersonnel;
import com.jingyu.contradiction.service.IDisputingPersonnelService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 参与人员信息Controller
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/contradiction/personnel")
public class DisputingPersonnelController extends BaseController
{
    @Autowired
    private IDisputingPersonnelService disputingPersonnelService;

    /**
     * 查询参与人员信息列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:list')")
    @GetMapping("/list")
    public TableDataInfo list(DisputingPersonnel disputingPersonnel)
    {
        startPage();
        List<DisputingPersonnel> list = disputingPersonnelService.selectDisputingPersonnelList(disputingPersonnel);
        return getDataTable(list);
    }

    /**
     * 导出参与人员信息列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:export')")
    @Log(title = "参与人员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DisputingPersonnel disputingPersonnel)
    {
        List<DisputingPersonnel> list = disputingPersonnelService.selectDisputingPersonnelList(disputingPersonnel);
        ExcelUtil<DisputingPersonnel> util = new ExcelUtil<DisputingPersonnel>(DisputingPersonnel.class);
        util.exportExcel(response, list, "参与人员信息数据");
    }

    /**
     * 获取参与人员信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return toAjax(disputingPersonnelService.selectDisputingPersonnelById(id));
    }

    /**
     * 获取参与人员信息详细信息
     */
    @GetMapping( "getContradictionPersonByCaseCode/{caseCode}")
    public TableDataInfo getContradictionPersonByCaseCode(@PathVariable("caseCode") String caseCode)
    {
        List<DisputingPersonnel> list = disputingPersonnelService.getContradictionPersonByCaseCode(caseCode);
        return getDataTable(list);
    }

    /**
     * 新增参与人员信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:add')")
    @Log(title = "参与人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DisputingPersonnel disputingPersonnel)
    {
        return toAjax(disputingPersonnelService.insertDisputingPersonnel(disputingPersonnel));
    }

    /**
     * 修改参与人员信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:edit')")
    @Log(title = "参与人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DisputingPersonnel disputingPersonnel)
    {
        return toAjax(disputingPersonnelService.updateDisputingPersonnel(disputingPersonnel));
    }

    /**
     * 删除参与人员信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:personnel:remove')")
    @Log(title = "参与人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(disputingPersonnelService.deleteDisputingPersonnelByIds(ids));
    }
}
