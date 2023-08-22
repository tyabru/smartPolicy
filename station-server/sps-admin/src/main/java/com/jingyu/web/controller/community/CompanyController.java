package com.jingyu.web.controller.community;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.constant.DeptConstants;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.community.domain.Company;
import com.jingyu.community.service.ICompanyService;
import com.jingyu.system.service.ISysDeptService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 单位基本信息Controller
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@RestController
@RequestMapping("/community/company")
public class CompanyController extends BaseController
{
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private ISysDeptService deptService;

    /**
     * 查询单位基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(Company company)
    {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('community:community:list')")
    @GetMapping("/listByRole")
    public AjaxResult listByRole(Company company)
    {
        val loginUser = SecurityUtils.getLoginUser();
        Long deptId = loginUser.getDeptId();
        String ids = deptService.queryChildIdsByTypeAndDeptId(deptId, DeptConstants.POLICE_COMMUNITY_AREA);
        if(StringUtils.isEmpty(ids)) {
            return AjaxResult.success(new ArrayList<>());
        }
        company.getParams().put("sqIds", ids.split(","));
        List<Company> list = companyService.selectCompanyList(company);
        return AjaxResult.success(list);
    }

    /**
     * 导出单位基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('community:company:export')")
    @Log(title = "单位基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Company company)
    {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        util.exportExcel(response, list, "单位基本信息数据");
    }

    /**
     * 获取单位基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('community:company:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return toAjax(companyService.selectCompanyById(id));
    }

    /**
     * 新增单位基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:company:add')")
    @Log(title = "单位基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改单位基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:company:edit')")
    @Log(title = "单位基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Company company)
    {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除单位基本信息
     */
    @PreAuthorize("@ss.hasPermi('community:company:remove')")
    @Log(title = "单位基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(companyService.deleteCompanyByIds(ids));
    }
}
