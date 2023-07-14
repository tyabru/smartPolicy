package com.jingyu.community.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityWuyeMapper;
import com.jingyu.community.domain.CommunityWuye;
import com.jingyu.community.service.ICommunityWuyeService;

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
        communityWuye.setCreateTime(DateUtils.getNowDate());
        return communityWuyeMapper.insertCommunityWuye(communityWuye);
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
        communityWuye.setUpdateTime(DateUtils.getNowDate());
        return communityWuyeMapper.updateCommunityWuye(communityWuye);
    }

    /**
     * 批量删除物业信息管理
     * 
     * @param ids 需要删除的物业信息管理主键
     * @return 结果
     */
    @Override
    public int deleteCommunityWuyeByIds(Long[] ids)
    {
        return communityWuyeMapper.deleteCommunityWuyeByIds(ids);
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
        return communityWuyeMapper.deleteCommunityWuyeById(id);
    }
}
