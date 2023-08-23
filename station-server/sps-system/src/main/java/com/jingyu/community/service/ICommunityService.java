package com.jingyu.community.service;

import java.util.List;
import com.jingyu.community.domain.Community;

/**
 * 小区（村）基本信息Service接口
 * 
 * @author jingyu
 * @date 2023-07-03
 */
public interface ICommunityService
{
    /**
     * 查询小区（村）基本信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 小区（村）基本信息
     */
    Community selectSqCommunityById(Long id);

    /**
     * 查询小区（村）基本信息列表
     * 
     * @param community 小区（村）基本信息
     * @return 小区（村）基本信息集合
     */
    List<Community> selectSqCommunityList(Community community);

    /**
     * 新增小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    int insertSqCommunity(Community community);

    /**
     * 修改小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    int updateSqCommunity(Community community);

    /**
     * 批量删除小区（村）基本信息
     * 
     * @param ids 需要删除的小区（村）基本信息主键集合
     * @return 结果
     */
    int deleteSqCommunityByIds(Long[] ids);

    /**
     * 删除小区（村）基本信息信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 结果
     */
    int deleteSqCommunityById(Long id);

    boolean codeIsExists(String code);

}
