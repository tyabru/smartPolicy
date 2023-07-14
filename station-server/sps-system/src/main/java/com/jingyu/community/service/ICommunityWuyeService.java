package com.jingyu.community.service;

import java.util.List;
import com.jingyu.community.domain.CommunityWuye;

/**
 * 物业信息管理Service接口
 * 
 * @author jingyu
 * @date 2023-07-14
 */
public interface ICommunityWuyeService 
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
     * 批量删除物业信息管理
     * 
     * @param ids 需要删除的物业信息管理主键集合
     * @return 结果
     */
    public int deleteCommunityWuyeByIds(Long[] ids);

    /**
     * 删除物业信息管理信息
     * 
     * @param id 物业信息管理主键
     * @return 结果
     */
    public int deleteCommunityWuyeById(Long id);
}
