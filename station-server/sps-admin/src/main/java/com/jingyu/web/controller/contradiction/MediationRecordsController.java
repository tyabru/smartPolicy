package com.jingyu.web.controller.contradiction;

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
import com.jingyu.contradiction.domain.MediationRecords;
import com.jingyu.contradiction.service.IMediationRecordsService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 纠纷调解记录Controller
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/contradiction/MediationRecords")
public class MediationRecordsController extends BaseController
{
    @Autowired
    private IMediationRecordsService mediationRecordsService;

    /**
     * 查询纠纷调解记录列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:list')")
    @GetMapping("/list")
    public TableDataInfo list(MediationRecords mediationRecords)
    {
        startPage();
        List<MediationRecords> list = mediationRecordsService.selectMediationRecordsList(mediationRecords);
        return getDataTable(list);
    }

    /**
     * 导出纠纷调解记录列表
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:export')")
    @Log(title = "纠纷调解记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MediationRecords mediationRecords)
    {
        List<MediationRecords> list = mediationRecordsService.selectMediationRecordsList(mediationRecords);
        ExcelUtil<MediationRecords> util = new ExcelUtil<MediationRecords>(MediationRecords.class);
        util.exportExcel(response, list, "纠纷调解记录数据");
    }

    /**
     * 获取纠纷调解记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(mediationRecordsService.selectMediationRecordsById(id));
    }

    /**
     * 新增纠纷调解记录
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:add')")
    @Log(title = "纠纷调解记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MediationRecords mediationRecords)
    {
        return toAjax(mediationRecordsService.insertMediationRecords(mediationRecords));
    }

    /**
     * 修改纠纷调解记录
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:edit')")
    @Log(title = "纠纷调解记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MediationRecords mediationRecords)
    {
        return toAjax(mediationRecordsService.updateMediationRecords(mediationRecords));
    }

    /**
     * 删除纠纷调解记录
     */
    @PreAuthorize("@ss.hasPermi('contradiction:MediationRecords:remove')")
    @Log(title = "纠纷调解记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mediationRecordsService.deleteMediationRecordsByIds(ids));
    }
}
