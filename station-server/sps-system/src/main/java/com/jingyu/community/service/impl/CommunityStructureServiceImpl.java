package com.jingyu.community.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.community.mapper.CommunityStructureMapper;
import com.jingyu.community.domain.CommunityStructure;
import com.jingyu.community.service.ICommunityStructureService;

/**
 * 小区房屋结构和地址信息Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-17
 */
@Service
public class CommunityStructureServiceImpl implements ICommunityStructureService 
{
    @Autowired
    private CommunityStructureMapper communityStructureMapper;

    /**
     * 查询小区房屋结构和地址信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 小区房屋结构和地址信息
     */
    @Override
    public CommunityStructure selectCommunityStructureById(Long id)
    {
        return communityStructureMapper.selectCommunityStructureById(id);
    }

    /**
     * 查询小区房屋结构和地址信息列表
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 小区房屋结构和地址信息
     */
    @Override
    public List<CommunityStructure> selectCommunityStructureList(CommunityStructure communityStructure)
    {
        return communityStructureMapper.selectCommunityStructureList(communityStructure);
    }

    /**
     * 新增小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    @Override
    public int insertCommunityStructure(CommunityStructure communityStructure)
    {
        return communityStructureMapper.insertCommunityStructure(communityStructure);
    }

    /**
     * 修改小区房屋结构和地址信息
     * 
     * @param communityStructure 小区房屋结构和地址信息
     * @return 结果
     */
    @Override
    public int updateCommunityStructure(CommunityStructure communityStructure)
    {
        return communityStructureMapper.updateCommunityStructure(communityStructure);
    }

    /**
     * 批量删除小区房屋结构和地址信息
     * 
     * @param ids 需要删除的小区房屋结构和地址信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityStructureByIds(Long[] ids)
    {
        return communityStructureMapper.deleteCommunityStructureByIds(ids);
    }

    /**
     * 删除小区房屋结构和地址信息信息
     * 
     * @param id 小区房屋结构和地址信息主键
     * @return 结果
     */
    @Override
    public int deleteCommunityStructureById(Long id)
    {
        return communityStructureMapper.deleteCommunityStructureById(id);
    }
}
