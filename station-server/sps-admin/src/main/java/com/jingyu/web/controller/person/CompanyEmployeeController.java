package com.jingyu.web.controller.person;

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
import com.jingyu.person.domain.CompanyEmployee;
import com.jingyu.person.service.ICompanyEmployeeService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 单位员工Controller
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@RestController
@RequestMapping("/company/employee")
public class CompanyEmployeeController extends BaseController
{
    @Autowired
    private ICompanyEmployeeService companyEmployeeService;

    /**
     * 查询单位员工列表
     */
    @PreAuthorize("@ss.hasPermi('company:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompanyEmployee companyEmployee)
    {
        startPage();
        List<CompanyEmployee> list = companyEmployeeService.selectCompanyEmployeeList(companyEmployee);
        return getDataTable(list);
    }

    /**
     * 导出单位员工列表
     */
    @PreAuthorize("@ss.hasPermi('company:employee:export')")
    @Log(title = "单位员工", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompanyEmployee companyEmployee)
    {
        List<CompanyEmployee> list = companyEmployeeService.selectCompanyEmployeeList(companyEmployee);
        ExcelUtil<CompanyEmployee> util = new ExcelUtil<CompanyEmployee>(CompanyEmployee.class);
        util.exportExcel(response, list, "单位员工数据");
    }

    /**
     * 获取单位员工详细信息
     */
    @PreAuthorize("@ss.hasPermi('company:employee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(companyEmployeeService.selectCompanyEmployeeById(id));
    }

    /**
     * 新增单位员工
     */
    @PreAuthorize("@ss.hasPermi('company:employee:add')")
    @Log(title = "单位员工", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompanyEmployee companyEmployee)
    {
        return toAjax(companyEmployeeService.insertCompanyEmployee(companyEmployee));
    }

    /**
     * 修改单位员工
     */
    @PreAuthorize("@ss.hasPermi('company:employee:edit')")
    @Log(title = "单位员工", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompanyEmployee companyEmployee)
    {
        return toAjax(companyEmployeeService.updateCompanyEmployee(companyEmployee));
    }

    /**
     * 删除单位员工
     */
    @PreAuthorize("@ss.hasPermi('company:employee:remove')")
    @Log(title = "单位员工", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(companyEmployeeService.deleteCompanyEmployeeByIds(ids));
    }
}
