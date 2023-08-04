package com.jingyu.web.controller.polices;

import com.jingyu.common.annotation.Log;
import com.jingyu.common.core.controller.BaseController;
import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.common.core.page.TableDataInfo;
import com.jingyu.common.enums.BusinessType;
import com.jingyu.common.utils.encryption_decryption.SensitiveNewsHander;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.polices.domain.PoliceInformation;
import com.jingyu.polices.service.IPoliceInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 警员基本信息Controller
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@RestController
@RequestMapping("/polices/information")
public class PoliceInformationController extends BaseController
{
    @Resource(name = "policeInformationServiceImpl")
    private IPoliceInformationService policeInformationService;

    /**
     * 查询警员基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('polices:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(PoliceInformation policeInformation)
    {
        startPage();
        List<PoliceInformation> list = policeInformationService.selectPoliceInformationList(policeInformation);
        return getDataTable(list);
    }

    /**
     * 导出警员基本信息列表
     */
    @PreAuthorize("@ss.hasPermi('polices:information:export')")
    @Log(title = "警员基本信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PoliceInformation policeInformation)
    {
        List<PoliceInformation> list = policeInformationService.selectPoliceInformationList(policeInformation);
        ExcelUtil<PoliceInformation> util = new ExcelUtil<PoliceInformation>(PoliceInformation.class);
        util.exportExcel(response, (List<PoliceInformation>)toAjaxEncrypt(list), "警员基本信息数据");
    }

    /**
     * 获取警员基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return toAjax(policeInformationService.selectPoliceInformationById(id));
    }


    /**
     * 获取警员基本信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('polices:information:query')")
    @GetMapping(value = "/getPoliceInformationByIdCard/{idCard}")
    public AjaxResult getPoliceInformationByIdCard(@PathVariable("idCard") String idCard)
    {
        PoliceInformation policeInformation = policeInformationService.selectPoliceInformationByIdCard(idCard);
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult.put("data",policeInformation);
    }

    /**
     * 新增警员基本信息
     */
    @PreAuthorize("@ss.hasPermi('polices:information:add')")
    @Log(title = "警员基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PoliceInformation policeInformation)
    {
        SensitiveNewsHander.revertEncryptAttrs(policeInformation);
        return toAjax(policeInformationService.insertPoliceInformation(policeInformation));
    }

    /**
     * 修改警员基本信息
     */
    @PreAuthorize("@ss.hasPermi('polices:information:edit')")
    @Log(title = "警员基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PoliceInformation policeInformation)
    {
        PoliceInformation oldPoliceInformation = policeInformationService.selectPoliceInformationById(policeInformation.getId());
        SensitiveNewsHander.revertNotEditAttrs(policeInformation,oldPoliceInformation);
        return toAjax(policeInformationService.updatePoliceInformation(policeInformation));
    }

    /**
     * 删除警员基本信息
     */
    @PreAuthorize("@ss.hasPermi('polices:information:remove')")
    @Log(title = "警员基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(policeInformationService.deletePoliceInformationByIds(ids));
    }
}
