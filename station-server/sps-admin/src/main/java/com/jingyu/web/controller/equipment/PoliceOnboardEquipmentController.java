package com.jingyu.web.controller.equipment;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.equipment.domain.PoliceOnboardEquipment;
import com.jingyu.equipment.service.IPoliceOnboardEquipmentService;
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
 * 车载设备Controller
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@RestController
@RequestMapping("/polices/PoliceOnboardEquipment")
public class PoliceOnboardEquipmentController extends BaseController
{
    @Autowired
    private IPoliceOnboardEquipmentService policeOnboardEquipmentService;

    /**
     * 查询车载设备列表
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceOnboardEquipment policeOnboardEquipment)
    {
        startPage();
        List<PoliceOnboardEquipment> list = policeOnboardEquipmentService.selectPoliceOnboardEquipmentList(policeOnboardEquipment);
        return getDataTable(list);
    }

    /**
     * 导出车载设备列表
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:export')")
    @Log(title = "车载设备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceOnboardEquipment policeOnboardEquipment)
    {
        List<PoliceOnboardEquipment> list = policeOnboardEquipmentService.selectPoliceOnboardEquipmentList(policeOnboardEquipment);
        ExcelUtil<PoliceOnboardEquipment> util = new ExcelUtil<PoliceOnboardEquipment>(PoliceOnboardEquipment.class);
        util.exportExcel(response, list, "车载设备数据");
    }

    /**
     * 获取车载设备详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeOnboardEquipmentService.selectPoliceOnboardEquipmentById(id));
    }

    /**
     * 新增车载设备
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:add')")
    @Log(title = "车载设备", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceOnboardEquipment policeOnboardEquipment)
    {
        return toAjax(policeOnboardEquipmentService.insertPoliceOnboardEquipment(policeOnboardEquipment));
    }

    /**
     * 修改车载设备
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:edit')")
    @Log(title = "车载设备", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceOnboardEquipment policeOnboardEquipment)
    {
        return toAjax(policeOnboardEquipmentService.updatePoliceOnboardEquipment(policeOnboardEquipment));
    }

    /**
     * 删除车载设备
     */
    @PreAuthorize("@ss.hasPermi('polices:PoliceOnboardEquipment:remove')")
    @Log(title = "车载设备", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeOnboardEquipmentService.deletePoliceOnboardEquipmentByIds(ids));
    }
}
