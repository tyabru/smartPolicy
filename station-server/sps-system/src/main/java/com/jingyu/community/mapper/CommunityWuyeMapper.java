package com.jingyu.community.mapper;

import java.util.List;
import com.jingyu.community.domain.CommunityWuye;
import org.apache.ibatis.annotations.Mapper;

/**
 * 物业信息管理Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-14
 */
@Mapper
public interface CommunityWuyeMapper 
{
    /**
     * 查询物业信息管理
     * 
     * @param id 物业信息管理主键
     * @return 物业信息管理
     */
    public CommunityWuye selectCommunityWuyeById(Long id);

    /**
     * 查询物业信息管理列表
     * 
     * @param communityWuye 物业信息管理
     * @return 物业信息管理集合
     */
    public List<CommunityWuye> selectCommunityWuyeList(CommunityWuye communityWuye);

    /**
     * 新增物业信息管理
     * 
     * @param communityWuye 物业信息管理
     * @return 结果
     */
    public int insertCommunityWuye(CommunityWuye communityWuye);

    /**
     * 修改物业信息管理
     * 
     * @param communityWuye 物业信息管理
     * @return 结果
     */
    public int updateCommunityWuye(CommunityWuye communityWuye);

    /**
     * 删除物业信息管理
     * 
     * @param id 物业信息管理主键
     * @return 结果
     */
    public int deleteCommunityWuyeById(Long id);

    /**
     * 批量删除物业信息管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommunityWuyeByIds(Long[] ids);
}
