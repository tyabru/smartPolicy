package com.jingyu.web.controller.polices;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.polices.domain.PoliceWorkforceManagements;
import com.jingyu.polices.service.IPoliceWorkforceManagementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 排班管理Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/polices/managements")
public class PoliceWorkforceManagementsController extends BaseController
{
    @Resource(name = "policeWorkforceManagementsServiceImpl")
    private IPoliceWorkforceManagementsService policeWorkforceManagementsService;

    /**
     * 查询排班管理列表
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceWorkforceManagements policeWorkforceManagements)
    {
        startPage();
        List<PoliceWorkforceManagements> list = policeWorkforceManagementsService.selectPoliceWorkforceManagementsList(policeWorkforceManagements);
        return getDataTable(list);
    }

    /**
     * 导出排班管理列表
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:export')")
    @Log(title = "排班管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceWorkforceManagements policeWorkforceManagements)
    {
        List<PoliceWorkforceManagements> list = policeWorkforceManagementsService.selectPoliceWorkforceManagementsList(policeWorkforceManagements);
        ExcelUtil<PoliceWorkforceManagements> util = new ExcelUtil<PoliceWorkforceManagements>(PoliceWorkforceManagements.class);
        util.exportExcel(response, list, "排班管理数据");
    }

    /**
     * 获取排班管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeWorkforceManagementsService.selectPoliceWorkforceManagementsById(id));
    }

    /**
     * 获取排班管理详细信息
     */
    @GetMapping("/getManagementsByDutyDate/{date}")
    public AjaxResult getManagementsByDutyDate(@PathVariable("date") String date)
    {
        return success(policeWorkforceManagementsService.selectPoliceWorkforceManagementsByDutyDate(date));
    }

    /**
     * 新增排班管理
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:add')")
    @Log(title = "排班管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceWorkforceManagements policeWorkforceManagements)
    {
        return toAjax(policeWorkforceManagementsService.insertPoliceWorkforceManagements(policeWorkforceManagements));
    }

    /**
     * 修改排班管理
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:edit')")
    @Log(title = "排班管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceWorkforceManagements policeWorkforceManagements)
    {
        return toAjax(policeWorkforceManagementsService.updatePoliceWorkforceManagements(policeWorkforceManagements));
    }

    /**
     * 删除排班管理
     */
    @PreAuthorize("@ss.hasPermi('polices:managements:remove')")
    @Log(title = "排班管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeWorkforceManagementsService.deletePoliceWorkforceManagementsByIds(ids));
    }
}
