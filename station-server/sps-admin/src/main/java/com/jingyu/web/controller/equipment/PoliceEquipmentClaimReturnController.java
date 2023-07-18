package com.jingyu.web.controller.equipment;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.equipment.domain.PoliceEquipmentClaimReturn;
import com.jingyu.equipment.service.IPoliceEquipmentClaimReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 警用设备管理Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/equipment/claimReturn")
public class PoliceEquipmentClaimReturnController extends BaseController
{
    @Resource(name = "policeEquipmentClaimReturnServiceImpl")
    private IPoliceEquipmentClaimReturnService policeEquipmentClaimReturnService;

    /**
     * 查询警用设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        startPage();
        List<PoliceEquipmentClaimReturn> list = policeEquipmentClaimReturnService.selectPoliceEquipmentClaimReturnList(policeEquipmentClaimReturn);
        return getDataTable(list);
    }

    /**
     * 导出警用设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:export')")
    @Log(title = "警用设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        List<PoliceEquipmentClaimReturn> list = policeEquipmentClaimReturnService.selectPoliceEquipmentClaimReturnList(policeEquipmentClaimReturn);
        ExcelUtil<PoliceEquipmentClaimReturn> util = new ExcelUtil<PoliceEquipmentClaimReturn>(PoliceEquipmentClaimReturn.class);
        util.exportExcel(response, list, "警用设备管理数据");
    }

    /**
     * 获取警用设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeEquipmentClaimReturnService.selectPoliceEquipmentClaimReturnById(id));
    }

    /**
     * 新增警用设备管理
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:add')")
    @Log(title = "警用设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        return toAjax(policeEquipmentClaimReturnService.insertPoliceEquipmentClaimReturn(policeEquipmentClaimReturn));
    }

    /**
     * 修改警用设备管理
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:edit')")
    @Log(title = "警用设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        return toAjax(policeEquipmentClaimReturnService.updatePoliceEquipmentClaimReturn(policeEquipmentClaimReturn));
    }

    /**
     * 删除警用设备管理
     */
    @PreAuthorize("@ss.hasPermi('equipment:claimReturn:remove')")
    @Log(title = "警用设备管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeEquipmentClaimReturnService.deletePoliceEquipmentClaimReturnByIds(ids));
    }
}
