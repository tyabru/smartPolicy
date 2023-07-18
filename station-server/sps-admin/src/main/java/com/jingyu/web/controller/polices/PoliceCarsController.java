package com.jingyu.web.controller.polices;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.polices.domain.PoliceCars;
import com.jingyu.polices.service.IPoliceCarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 警用车辆Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/polices/cars")
public class PoliceCarsController extends BaseController
{
    @Resource(name = "policeCarsServiceImpl")
    private IPoliceCarsService policeCarsService;

    /**
     * 查询警用车辆列表
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceCars policeCars)
    {
        startPage();
        List<PoliceCars> list = policeCarsService.selectPoliceCarsList(policeCars);
        return getDataTable(list);
    }

    /**
     * 导出警用车辆列表
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:export')")
    @Log(title = "警用车辆", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceCars policeCars)
    {
        List<PoliceCars> list = policeCarsService.selectPoliceCarsList(policeCars);
        ExcelUtil<PoliceCars> util = new ExcelUtil<PoliceCars>(PoliceCars.class);
        util.exportExcel(response, list, "警用车辆数据");
    }

    /**
     * 获取警用车辆详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(policeCarsService.selectPoliceCarsById(id));
    }

    /**
     * 新增警用车辆
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:add')")
    @Log(title = "警用车辆", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceCars policeCars)
    {
        return toAjax(policeCarsService.insertPoliceCars(policeCars));
    }

    /**
     * 修改警用车辆
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:edit')")
    @Log(title = "警用车辆", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceCars policeCars)
    {
        return toAjax(policeCarsService.updatePoliceCars(policeCars));
    }

    /**
     * 删除警用车辆
     */
    @PreAuthorize("@ss.hasPermi('polices:cars:remove')")
    @Log(title = "警用车辆", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeCarsService.deletePoliceCarsByIds(ids));
    }
}
