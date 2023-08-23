package com.jingyu.polices.service;

import java.util.List;
import com.jingyu.polices.domain.PoliceDutyGroups;

/**
 * 警员分组详情Service接口
 * 
 * @author jiatongbo
 * @date 2023-07-04
 */
public interface IPoliceDutyGroupsService 
{
    /**
     * 查询警员分组详情
     * 
     * @param id 警员分组详情主键
     * @return 警员分组详情
     */
    public PoliceDutyGroups selectPoliceDutyGroupsById(Long id);

    /**
     * 查询警员分组详情列表
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 警员分组详情集合
     */
    public List<PoliceDutyGroups> selectPoliceDutyGroupsList(PoliceDutyGroups policeDutyGroups);

    /**
     * 新增警员分组详情
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 结果
     */
    public int insertPoliceDutyGroups(PoliceDutyGroups policeDutyGroups);

    /**
     * 修改警员分组详情
     * 
     * @param policeDutyGroups 警员分组详情
     * @return 结果
     */
    public int updatePoliceDutyGroups(PoliceDutyGroups policeDutyGroups);

    /**
     * 批量删除警员分组详情
     * 
     * @param ids 需要删除的警员分组详情主键集合
     * @return 结果
     */
    public int deletePoliceDutyGroupsByIds(Long[] ids);

    /**
     * 删除警员分组详情信息
     * 
     * @param id 警员分组详情主键
     * @return 结果
     */
    public int deletePoliceDutyGroupsById(Long id);
}
