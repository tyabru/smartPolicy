package com.jingyu.community.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityDetailMapper;
import com.jingyu.community.domain.CommunityDetail;
import com.jingyu.community.service.ICommunityDetailService;

/**
 * 小区/村基本信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-07
 */
@Service
public class CommunityDetailServiceImpl implements ICommunityDetailService 
{
    @Autowired
    private CommunityDetailMapper communityDetailMapper;

    /**
     * 查询小区/村基本信息
     * 
     * @param id 小区/村基本信息主键
     * @return 小区/村基本信息
     */
    @Override
    public CommunityDetail selectCommunityDetailById(Long id)
    {
        return communityDetailMapper.selectCommunityDetailById(id);
    }

    /**
     * 查询小区/村基本信息列表
     * 
     * @param communityDetail 小区/村基本信息
     * @return 小区/村基本信息
     */
    @Override
    public List<CommunityDetail> selectCommunityDetailList(CommunityDetail communityDetail)
    {
        return communityDetailMapper.selectCommunityDetailList(communityDetail);
    }

    /**
     * 新增小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    @Override
    public int insertCommunityDetail(CommunityDetail communityDetail)
    {
        communityDetail.setCreateTime(DateUtils.getNowDate());
        return communityDetailMapper.insertCommunityDetail(communityDetail);
    }

    /**
     * 修改小区/村基本信息
     * 
     * @param communityDetail 小区/村基本信息
     * @return 结果
     */
    @Override
    public int updateCommunityDetail(CommunityDetail communityDetail)
    {
        communityDetail.setUpdateTime(DateUtils.getNowDate());
        return communityDetailMapper.updateCommunityDetail(communityDetail);
    }

    /**
     * 批量删除小区/村基本信息
     * 
     * @param ids 需要删除的小区/村基本信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityDetailByIds(Long[] ids)
    {
        return communityDetailMapper.deleteCommunityDetailByIds(ids);
    }

    /**
     * 删除小区/村基本信息信息
     * 
     * @param id 小区/村基本信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityDetailById(Long id)
    {
        return communityDetailMapper.deleteCommunityDetailById(id);
    }

    @Override
    public void deleteByVillageId(Long id) {
        communityDetailMapper.deleteByVillageId(id);
    }
}
