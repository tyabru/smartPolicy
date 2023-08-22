package com.jingyu.web.controller.community;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jingyu.common.exception.file.InvalidExtensionException;
import com.jingyu.community.domain.CommunityDesc;
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
import com.jingyu.community.domain.CompanyDesc;
import com.jingyu.community.service.ICompanyDescService;
import com.jingyu.common.utils.poi.ExcelUtil;
import com.jingyu.common.core.page.TableDataInfo;

/**
 * 单位详情附件Controller
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@RestController
@RequestMapping("/company/desc")
public class CompanyDescController extends BaseController
{
    @Autowired
    private ICompanyDescService companyDescService;

    @GetMapping("{companyId}/list")
    public AjaxResult getDescListByCompanyId(@PathVariable Long companyId) {
        CompanyDesc desc = new CompanyDesc(companyId);
        return AjaxResult.success(companyDescService.selectCompanyDescList(desc));
    }

    @PostMapping("uploadFile")
    public AjaxResult uploadGroup(CompanyDesc desc) {
        Long id;
        try {
            if(desc.getId() != null) {
                companyDescService.updateCompanyDesc(desc);
                id = desc.getId();
            }else {
                id = companyDescService.insertCompanyDesc(desc);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return AjaxResult.success("保存成功", id);
    }

    @DeleteMapping("/{fileId}")
    public AjaxResult deleteById(@PathVariable Long fileId, HttpServletRequest request) {
        // 是否只删除未关联社区的数据 1 是 0 否
        String onlyNotDefined = request.getHeader("only-not-defined");
        companyDescService.deleteCompanyDescById(fileId, "1".equals(onlyNotDefined));
        return AjaxResult.success();
    }
}
