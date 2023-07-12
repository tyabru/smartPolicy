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
import com.jingyu.person.domain.PersonResident;
import com.jingyu.person.service.IPersonResidentService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 社区常驻人口Controller
 * 
 * @author jingyu
 * @date 2023-07-10
 */
@RestController
@RequestMapping("/person/resident")
public class PersonResidentController extends BaseController
{
    @Autowired
    private IPersonResidentService personResidentService;

    /**
     * 查询社区常驻人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:resident:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonResident personResident)
    {
        startPage();
        List<PersonResident> list = personResidentService.selectPersonResidentList(personResident);
        return getDataTable(list);
    }

    /**
     * 导出社区常驻人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:resident:export')")
    @Log(title = "社区常驻人口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonResident personResident)
    {
        List<PersonResident> list = personResidentService.selectPersonResidentList(personResident);
        ExcelUtil<PersonResident> util = new ExcelUtil<PersonResident>(PersonResident.class);
        util.exportExcel(response, list, "社区常驻人口数据");
    }

    /**
     * 获取社区常驻人口详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:resident:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personResidentService.selectPersonResidentById(id));
    }

    /**
     * 新增社区常驻人口
     */
    @PreAuthorize("@ss.hasPermi('person:resident:add')")
    @Log(title = "社区常驻人口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonResident personResident)
    {
        return toAjax(personResidentService.insertPersonResident(personResident));
    }

    /**
     * 修改社区常驻人口
     */
    @PreAuthorize("@ss.hasPermi('person:resident:edit')")
    @Log(title = "社区常驻人口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonResident personResident)
    {
        return toAjax(personResidentService.updatePersonResident(personResident));
    }

    /**
     * 删除社区常驻人口
     */
    @PreAuthorize("@ss.hasPermi('person:resident:remove')")
    @Log(title = "社区常驻人口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personResidentService.deletePersonResidentByIds(ids));
    }
}
