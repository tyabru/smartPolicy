package com.jingyu.polices.service;

import java.util.List;
import com.jingyu.polices.domain.PoliceWorkforceManagements;

/**
 * 排班管理Service接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface IPoliceWorkforceManagementsService 
{
    /**
     * 查询排班管理
     * 
     * @param id 排班管理主键
     * @return 排班管理
     */
    public PoliceWorkforceManagements selectPoliceWorkforceManagementsById(Long id);

    /**
     * 查询排班管理
     *
     * @param date 排班日期
     * @return 排班管理
     */
    public PoliceWorkforceManagements selectPoliceWorkforceManagementsByDutyDate(String date);

    /**
     * 查询排班管理列表
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 排班管理集合
     */
    public List<PoliceWorkforceManagements> selectPoliceWorkforceManagementsList(PoliceWorkforceManagements policeWorkforceManagements);

    /**
     * 新增排班管理
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 结果
     */
    public int insertPoliceWorkforceManagements(PoliceWorkforceManagements policeWorkforceManagements);

    /**
     * 修改排班管理
     * 
     * @param policeWorkforceManagements 排班管理
     * @return 结果
     */
    public int updatePoliceWorkforceManagements(PoliceWorkforceManagements policeWorkforceManagements);

    /**
     * 批量删除排班管理
     * 
     * @param ids 需要删除的排班管理主键集合
     * @return 结果
     */
    public int deletePoliceWorkforceManagementsByIds(Long[] ids);

    /**
     * 删除排班管理信息
     * 
     * @param id 排班管理主键
     * @return 结果
     */
    public int deletePoliceWorkforceManagementsById(Long id);

}
