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
import com.jingyu.person.domain.PersonFcous;
import com.jingyu.person.service.IPersonFcousService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 重点关注人口Controller
 * 
 * @author jingyu
 * @date 2023-08-01
 */
@RestController
@RequestMapping("/person/fcous")
public class PersonFcousController extends BaseController
{
    @Autowired
    private IPersonFcousService personFcousService;

    /**
     * 查询重点关注人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonFcous personFcous)
    {
        startPage();
        List<PersonFcous> list = personFcousService.selectPersonFcousList(personFcous);
        return getDataTable(list);
    }

    /**
     * 导出重点关注人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:export')")
    @Log(title = "重点关注人口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonFcous personFcous)
    {
        List<PersonFcous> list = personFcousService.selectPersonFcousList(personFcous);
        ExcelUtil<PersonFcous> util = new ExcelUtil<PersonFcous>(PersonFcous.class);
        util.exportExcel(response, list, "重点关注人口数据");
    }

    /**
     * 获取重点关注人口详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personFcousService.selectPersonFcousById(id));
    }

    /**
     * 新增重点关注人口
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:add')")
    @Log(title = "重点关注人口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonFcous personFcous)
    {
        return toAjax(personFcousService.insertPersonFcous(personFcous));
    }

    /**
     * 修改重点关注人口
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:edit')")
    @Log(title = "重点关注人口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonFcous personFcous)
    {
        return toAjax(personFcousService.updatePersonFcous(personFcous));
    }

    /**
     * 删除重点关注人口
     */
    @PreAuthorize("@ss.hasPermi('person:fcous:remove')")
    @Log(title = "重点关注人口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personFcousService.deletePersonFcousByIds(ids));
    }
}
