package com.jingyu.web.controller.equipment;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.equipment.domain.PoliceEnforcementRecorder;
import com.jingyu.equipment.service.IPoliceEnforcementRecorderService;
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
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 执法仪记录仪Controller
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/polices/PoliceEnforcementRecorder")
public class PoliceEnforcementRecorderController extends BaseController
{
    @Autowired
    private IPoliceEnforcementRecorderService policeEnforcementRecorderService;

    /**
     * 查询执法仪记录仪列表
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        startPage();
        List<PoliceEnforcementRecorder> list = policeEnforcementRecorderService.selectPoliceEnforcementRecorderList(policeEnforcementRecorder);
        return getDataTable(list);
    }

    /**
     * 导出执法仪记录仪列表
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:export')")
    @Log(title = "执法仪记录仪", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        List<PoliceEnforcementRecorder> list = policeEnforcementRecorderService.selectPoliceEnforcementRecorderList(policeEnforcementRecorder);
        ExcelUtil<PoliceEnforcementRecorder> util = new ExcelUtil<PoliceEnforcementRecorder>(PoliceEnforcementRecorder.class);
        util.exportExcel(response, list, "执法仪记录仪数据");
    }

    /**
     * 获取执法仪记录仪详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeEnforcementRecorderService.selectPoliceEnforcementRecorderById(id));
    }

    /**
     * 新增执法仪记录仪
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:add')")
    @Log(title = "执法仪记录仪", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        return toAjax(policeEnforcementRecorderService.insertPoliceEnforcementRecorder(policeEnforcementRecorder));
    }

    /**
     * 修改执法仪记录仪
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:edit')")
    @Log(title = "执法仪记录仪", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        return toAjax(policeEnforcementRecorderService.updatePoliceEnforcementRecorder(policeEnforcementRecorder));
    }

    /**
     * 删除执法仪记录仪
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceEnforcementRecorder:remove')")
    @Log(title = "执法仪记录仪", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeEnforcementRecorderService.deletePoliceEnforcementRecorderByIds(ids));
    }
}
