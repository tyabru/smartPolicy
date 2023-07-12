package com.jingyu.community.service.impl;

import com.jingyu.common.config.RuoYiConfig;
import com.jingyu.common.constant.Constants;
import com.jingyu.common.exception.base.BaseException;
import com.jingyu.common.exception.file.InvalidExtensionException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.file.FileUploadUtils;
import com.jingyu.common.utils.file.FileUtils;
import com.jingyu.community.domain.CommunityDesc;
import com.jingyu.community.mapper.CommunityDescMapper;
import com.jingyu.community.service.ICommunityDescService;
import com.jingyu.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommunityDescServiceImpl implements ICommunityDescService {

    private final CommunityDescMapper communityDescMapper;
    private final ISysDictDataService dictDataService;

    private final String group_type_dict_key = "sq_group_community";
    private final String group_type_dict_label = "小区（村）相关文件";

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     */
    @Override
    public CommunityDesc selectCommunityDescById(Long id)
    {
        return communityDescMapper.selectSqCommunityDescById(id);
    }

    /**
     * 查询社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理列表
     *
     * @param CommunityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     */
    @Override
    public List<CommunityDesc> selectCommunityDescList(CommunityDesc CommunityDesc)
    {
        return communityDescMapper.selectSqCommunityDescList(CommunityDesc);
    }

    /**
     * 新增社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param communityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    @Override
    @Transactional
    public Long insertCommunityDesc(CommunityDesc communityDesc) throws InvalidExtensionException, IOException {
        String fileUrl = null;
        try {
            communityDesc.setCreateTime(DateUtils.getNowDate());
            communityDesc.setCreateBy(SecurityUtils.getUsername());
            MultipartFile file = communityDesc.getFile();
            if(file == null || file.isEmpty()) {
                throw new BaseException("上传文件为空！");
            }
            FileUploadUtils.assertAllowed(file, null);
            fileUrl = FileUploadUtils.upload(file);
            communityDesc.setFileUrl(fileUrl);
            dictDataService.checkDictIsExists(
                    group_type_dict_key,
                    communityDesc.getGroupTitle(),
                    group_type_dict_label,null);
            communityDescMapper.insertSqCommunityDesc(communityDesc);
        } catch (Exception e) {
            deleteFileByDescUrl(fileUrl);
            throw e;
        }
        return communityDesc.getId();
    }

    /**
     * 修改社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param communityDesc 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     * @return 结果
     */
    @Override
    @Transactional
    public Long updateCommunityDesc(CommunityDesc communityDesc) throws IOException, InvalidExtensionException {
        String fileUrl = null;
        try {
            CommunityDesc oldDesc = selectCommunityDescById(communityDesc.getId());
            if(oldDesc == null || oldDesc.getId() == null) {
                return insertCommunityDesc(communityDesc);
            }
            // 删除旧文件
            deleteFileByDescUrl(oldDesc.getFileUrl());
            communityDesc.setCreateBy(SecurityUtils.getUsername());
            communityDesc.setCreateTime(DateUtils.getNowDate());
            // 上传新文件
            MultipartFile file = communityDesc.getFile();
            if(file == null || file.isEmpty()) {
                throw new BaseException("上传文件为空！");
            }
            FileUploadUtils.assertAllowed(file, null);
            fileUrl = FileUploadUtils.upload(file);
            communityDesc.setFileUrl(fileUrl);
            dictDataService.checkDictIsExists(
                    group_type_dict_key,
                    communityDesc.getGroupTitle(),
                    group_type_dict_label,null);
            communityDescMapper.updateSqCommunityDesc(communityDesc);
        } catch (Exception e) {
            deleteFileByDescUrl(fileUrl);
            throw e;
        }
        return communityDesc.getId();
    }

    /**
     * 批量删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理
     *
     * @param ids 需要删除的社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCommunityDescByIds(Long[] ids)
    {
        for (Long id : ids) {
            deleteCommunityDescById(id, false);
        }
        return ids.length;
    }

    /**
     * 删除社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理信息
     *
     * @param id 社区详情附件-描述社区组成部分如平面图、重点场所照片或相关文件等相关信息，可通过group分组管理主键
     * @return 结果
     */
    @Override
    public int deleteCommunityDescById(Long id, boolean onlyNotDefined)
    {
        CommunityDesc desc = communityDescMapper.selectSqCommunityDescById(id);
        if(onlyNotDefined && desc.getCommunityId() != -1) {
            return 0;
        }
        deleteFileByDescUrl(desc.getFileUrl());
        return communityDescMapper.deleteSqCommunityDescById(id);
    }

    @Override
    public void updateCommunityId(int id, Long communityId) {
        communityDescMapper.updateCommunityId(id, communityId);
    }

    @Override
    public void deleteByCommunityId(Long id) {
        List<CommunityDesc> descList = communityDescMapper.selectSqCommunityDescList(new CommunityDesc(id));
        for (CommunityDesc desc : descList) {
            deleteFileByDescUrl(desc.getFileUrl());
            communityDescMapper.deleteByCommunityId(id);
        }
    }

    private void deleteFileByDescUrl(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            return;
        }
        String realPath = fileUrl.replace(Constants.RESOURCE_PREFIX + "/", "");
        FileUtils.deleteFile(RuoYiConfig.getProfile() + realPath);
    }

}
