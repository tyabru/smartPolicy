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
import com.jingyu.person.domain.PersonFlow;
import com.jingyu.person.service.IPersonFlowService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 流动人口Controller
 * 
 * @author jingyu
 * @date 2023-07-21
 */
@RestController
@RequestMapping("/person/flow")
public class PersonFlowController extends BaseController
{
    @Autowired
    private IPersonFlowService personFlowService;

    /**
     * 查询流动人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:flow:list')")
    @GetMapping("/list")
    public TableDataInfo list(PersonFlow personFlow)
    {
        startPage();
        List<PersonFlow> list = personFlowService.selectPersonFlowList(personFlow);
        return getDataTable(list);
    }

    /**
     * 导出流动人口列表
     */
    @PreAuthorize("@ss.hasPermi('person:flow:export')")
    @Log(title = "流动人口", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PersonFlow personFlow)
    {
        List<PersonFlow> list = personFlowService.selectPersonFlowList(personFlow);
        ExcelUtil<PersonFlow> util = new ExcelUtil<PersonFlow>(PersonFlow.class);
        util.exportExcel(response, list, "流动人口数据");
    }

    /**
     * 获取流动人口详细信息
     */
    @PreAuthorize("@ss.hasPermi('person:flow:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(personFlowService.selectPersonFlowById(id));
    }

    /**
     * 新增流动人口
     */
    @PreAuthorize("@ss.hasPermi('person:flow:add')")
    @Log(title = "流动人口", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonFlow personFlow)
    {
        return toAjax(personFlowService.insertPersonFlow(personFlow));
    }

    /**
     * 修改流动人口
     */
    @PreAuthorize("@ss.hasPermi('person:flow:edit')")
    @Log(title = "流动人口", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonFlow personFlow)
    {
        return toAjax(personFlowService.updatePersonFlow(personFlow));
    }

    /**
     * 删除流动人口
     */
    @PreAuthorize("@ss.hasPermi('person:flow:remove')")
    @Log(title = "流动人口", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(personFlowService.deletePersonFlowByIds(ids));
    }
}
