package com.jingyu.community.service.impl;

import java.io.IOException;
import java.util.List;

import com.jingyu.common.config.RuoYiConfig;
import com.jingyu.common.constant.Constants;
import com.jingyu.common.exception.base.BaseException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.file.FileUploadUtils;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.community.domain.CommunityDesc;
import com.jingyu.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CompanyDescMapper;
import com.jingyu.community.domain.CompanyDesc;
import com.jingyu.community.service.ICompanyDescService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 单位详情附件Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-12
 */
@Service
@RequiredArgsConstructor
public class CompanyDescServiceImpl implements ICompanyDescService 
{

    private final CompanyDescMapper companyDescMapper;
    private final ISysDictDataService dictDataService;

    private final String file_base_dir = "company";
    private final String group_type_dict_key = "sq_group_company";
    private final String group_type_dict_label = "单位信息相关文件";

    /**
     * 查询单位详情附件
     * 
     * @param id 单位详情附件主键
     * @return 单位详情附件
     */
    @Override
    public CompanyDesc selectCompanyDescById(Long id)
    {
        return companyDescMapper.selectCompanyDescById(id);
    }

    /**
     * 查询单位详情附件列表
     * 
     * @param companyDesc 单位详情附件
     * @return 单位详情附件
     */
    @Override
    public List<CompanyDesc> selectCompanyDescList(CompanyDesc companyDesc)
    {
        return companyDescMapper.selectCompanyDescList(companyDesc);
    }

    /**
     * 新增单位详情附件
     * 
     * @param companyDesc 单位详情附件
     * @return 结果
     */
    @Override
    public Long insertCompanyDesc(CompanyDesc companyDesc) throws Exception {
        String fileUrl = null;
        try {
            companyDesc.setCreateTime(DateUtils.getNowDate());
            companyDesc.setCreateBy(SecurityUtils.getUsername());
            MultipartFile file = companyDesc.getFile();
            if(file == null || file.isEmpty()) {
                throw new BaseException("上传文件为空！");
            }
            fileUrl = FileUploadUtils.upload(
                    RuoYiConfig.getCustomPath(file_base_dir), file);
            companyDesc.setFileUrl(fileUrl);
            dictDataService.checkDictIsExists(
                    group_type_dict_key,
                    companyDesc.getGroupTitle(),
                    group_type_dict_label,null);
            companyDescMapper.insertCompanyDesc(companyDesc);
        } catch (Exception e) {
            deleteFileByDescUrl(fileUrl);
            throw e;
        }
        return companyDesc.getId();
    }

    /**
     * 修改单位详情附件
     * 
     * @param companyDesc 单位详情附件
     * @return 结果
     */
    @Override
    public Long updateCompanyDesc(CompanyDesc companyDesc) throws Exception {
        String fileUrl = null;
        try {
            // 上传新文件
            MultipartFile file = companyDesc.getFile();
            if(file != null && !file.isEmpty()) {
                CompanyDesc oldDesc = selectCompanyDescById(companyDesc.getId());
                if(oldDesc == null || oldDesc.getId() == null) {
                    return insertCompanyDesc(companyDesc);
                }
                // 删除旧文件
                deleteFileByDescUrl(oldDesc.getFileUrl());
                companyDesc.setUpdateBy(SecurityUtils.getUsername());

                fileUrl = FileUploadUtils.upload(
                        RuoYiConfig.getCustomPath(file_base_dir), file);
                companyDesc.setFileUrl(fileUrl);
            }
            dictDataService.checkDictIsExists(
                    group_type_dict_key,
                    companyDesc.getGroupTitle(),
                    group_type_dict_label,null);
            companyDescMapper.updateCompanyDesc(companyDesc);
        } catch (Exception e) {
            deleteFileByDescUrl(fileUrl);
            throw e;
        }
        return companyDesc.getId();
    }

    @Override
    public void updateCompanyIdById(long id, Long companyId) {
        companyDescMapper.updateCompanyIdById(id, companyId);
    }

    /**
     * 批量删除单位详情附件
     * 
     * @param ids 需要删除的单位详情附件主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDescByIds(Long[] ids)
    {
        for (Long id : ids) {
            deleteCompanyDescById(id, false);
        }
        return ids.length;
    }

    /**
     * 删除单位详情附件信息
     * 
     * @param id 单位详情附件主键
     * @return 结果
     */
    @Override
    public int deleteCompanyDescById(Long id, boolean onlyNotDefined)
    {
        CompanyDesc desc = companyDescMapper.selectCompanyDescById(id);
        if(onlyNotDefined && desc.getCompanyId() != -1) {
            return 0;
        }
        deleteFileByDescUrl(desc.getFileUrl());
        return companyDescMapper.deleteCompanyDescById(id);
    }

    @Override
    public void deleteDescByCompanyId(Long id) {
        List<CompanyDesc> descList = companyDescMapper.selectCompanyDescList(new CompanyDesc(id));
        for (CompanyDesc desc : descList) {
            deleteFileByDescUrl(desc.getFileUrl());
            companyDescMapper.deleteCompanyDescById(id);
        }
    }

    private void deleteFileByDescUrl(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }

        String realPath = fileUrl.replace(Constants.RESOURCE_PREFIX + "/", "");
        System.out.println("删除文件："+ RuoYiConfig.getProfile() + realPath);
        FileUtils.deleteFile(RuoYiConfig.getProfile() + realPath);
    }
}
