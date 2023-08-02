package com.jingyu.community.mapper;

import java.util.List;
import com.jingyu.community.domain.Community;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 小区（村）基本信息Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-03
 */
@Mapper
public interface CommunityMapper
{
    /**
     * 查询小区（村）基本信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 小区（村）基本信息
     */
    public Community selectSqCommunityById(Long id);

    /**
     * 查询小区（村）基本信息列表
     * 
     * @param community 小区（村）基本信息
     * @return 小区（村）基本信息集合
     */
    public List<Community> selectSqCommunityList(Community community);

    /**
     * 新增小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    public int insertSqCommunity(Community community);

    /**
     * 修改小区（村）基本信息
     * 
     * @param community 小区（村）基本信息
     * @return 结果
     */
    public int updateSqCommunity(Community community);

    /**
     * 删除小区（村）基本信息
     * 
     * @param id 小区（村）基本信息主键
     * @return 结果
     */
    public int deleteSqCommunityById(Long id);

    /**
     * 批量删除小区（村）基本信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSqCommunityByIds(Long[] ids);

    int countByCondition(Community community);

}
