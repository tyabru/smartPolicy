package com.jingyu.web.controller.equipment;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.equipment.domain.PoliceEquipment;
import com.jingyu.equipment.service.IPoliceEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 警用装备配发领取记录Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/equipment/equipment")
public class PoliceEquipmentController extends BaseController
{
    @Resource(name = "policeEquipmentServiceImpl")
    private IPoliceEquipmentService policeEquipmentService;

    /**
     * 查询警用装备配发领取记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceEquipment policeEquipment)
    {
        startPage();
        List<PoliceEquipment> list = policeEquipmentService.selectPoliceEquipmentList(policeEquipment);
        return getDataTable(list);
    }

    /**
     * 导出警用装备配发领取记录列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:export')")
    @Log(title = "警用装备配发领取记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceEquipment policeEquipment)
    {
        List<PoliceEquipment> list = policeEquipmentService.selectPoliceEquipmentList(policeEquipment);
        ExcelUtil<PoliceEquipment> util = new ExcelUtil<PoliceEquipment>(PoliceEquipment.class);
        util.exportExcel(response, list, "警用装备配发领取记录数据");
    }

    /**
     * 获取警用装备配发领取记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeEquipmentService.selectPoliceEquipmentById(id));
    }

    /**
     * 新增警用装备配发领取记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:add')")
    @Log(title = "警用装备配发领取记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceEquipment policeEquipment)
    {
        return toAjax(policeEquipmentService.insertPoliceEquipment(policeEquipment));
    }

    /**
     * 修改警用装备配发领取记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:edit')")
    @Log(title = "警用装备配发领取记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceEquipment policeEquipment)
    {
        return toAjax(policeEquipmentService.updatePoliceEquipment(policeEquipment));
    }

    /**
     * 删除警用装备配发领取记录
     */
    @PreAuthorize("@ss.hasPermi('equipment:equipment:remove')")
    @Log(title = "警用装备配发领取记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeEquipmentService.deletePoliceEquipmentByIds(ids));
    }
}
