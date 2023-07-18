package com.jingyu.community.service.impl;

import java.util.List;

import com.jingyu.common.config.RuoYiConfig;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.file.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityWuyeMapper;
import com.jingyu.community.domain.CommunityWuye;
import com.jingyu.community.service.ICommunityWuyeService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物业信息管理Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-14
 */
@Service
public class CommunityWuyeServiceImpl implements ICommunityWuyeService 
{
    @Autowired
    private CommunityWuyeMapper communityWuyeMapper;

    /**
     * 查询物业信息管理
     * 
     * @param id 物业信息管理主键
     * @return 物业信息管理
     */
    @Override
    public CommunityWuye selectCommunityWuyeById(Long id)
    {
        return communityWuyeMapper.selectCommunityWuyeById(id);
    }

    /**
     * 查询物业信息管理列表
     * 
     * @param communityWuye 物业信息管理
     * @return 物业信息管理
     */
    @Override
    public List<CommunityWuye> selectCommunityWuyeList(CommunityWuye communityWuye)
    {
        return communityWuyeMapper.selectCommunityWuyeList(communityWuye);
    }

    /**
     * 新增物业信息管理
     * 
     * @param communityWuye 物业信息管理
     * @return 结果
     */
    @Override
    public int insertCommunityWuye(CommunityWuye communityWuye)
    {
        try {
            communityWuye.setCreateBy(SecurityUtils.getUsername());
            communityWuye.setCreateTime(DateUtils.getNowDate());
            return communityWuyeMapper.insertCommunityWuye(communityWuye);
        } catch (Exception e) {
            if(StringUtils.isNotEmpty(communityWuye.getFaceImgUrl())){
                FileUtils.deleteFileByProfileUrl(communityWuye.getFaceImgUrl());
            }
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改物业信息管理
     * 
     * @param communityWuye 物业信息管理
     * @return 结果
     */
    @Override
    public int updateCommunityWuye(CommunityWuye communityWuye)
    {
        try {
            communityWuye.setUpdateTime(DateUtils.getNowDate());
            communityWuye.setUpdateBy(SecurityUtils.getUsername());
            if(StringUtils.isNotEmpty(communityWuye.getFaceImgUrl())){
                CommunityWuye old = selectCommunityWuyeById(communityWuye.getId());
                if(!communityWuye.getFaceImgUrl().equals(old.getFaceImgUrl())) {
                    FileUtils.deleteFileByProfileUrl(old.getFaceImgUrl());
                }
            }
            return communityWuyeMapper.updateCommunityWuye(communityWuye);
        } catch (Exception e) {
            if(StringUtils.isNotEmpty(communityWuye.getFaceImgUrl())){
                CommunityWuye old = selectCommunityWuyeById(communityWuye.getId());
                if(!communityWuye.getFaceImgUrl().equals(old.getFaceImgUrl())) {
                    FileUtils.deleteFileByProfileUrl(communityWuye.getFaceImgUrl());
                }
            }
            throw new RuntimeException(e);
        }

    }

    /**
     * 批量删除物业信息管理
     * 
     * @param ids 需要删除的物业信息管理主键
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteCommunityWuyeByIds(Long[] ids)
    {
        int i = 0;
        for (Long id : ids) {
            i += deleteCommunityWuyeById(id);
        }
        return i;
    }

    /**
     * 删除物业信息管理信息
     * 
     * @param id 物业信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCommunityWuyeById(Long id)
    {
        CommunityWuye wuye = selectCommunityWuyeById(id);
        if(StringUtils.isNotEmpty(wuye.getFaceImgUrl())) {
            FileUtils.deleteFileByProfileUrl(wuye.getFaceImgUrl());
        }
        return communityWuyeMapper.deleteCommunityWuyeById(id);
    }
}
