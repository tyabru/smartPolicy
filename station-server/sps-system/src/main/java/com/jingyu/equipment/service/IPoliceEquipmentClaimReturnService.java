package com.jingyu.equipment.service;

import java.util.List;
import com.jingyu.equipment.domain.PoliceEquipmentClaimReturn;

/**
 * 警用设备管理Service接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface IPoliceEquipmentClaimReturnService 
{
    /**
     * 查询警用设备管理
     * 
     * @param id 警用设备管理主键
     * @return 警用设备管理
     */
    public PoliceEquipmentClaimReturn selectPoliceEquipmentClaimReturnById(Long id);

    /**
     * 查询警用设备管理列表
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 警用设备管理集合
     */
    public List<PoliceEquipmentClaimReturn> selectPoliceEquipmentClaimReturnList(PoliceEquipmentClaimReturn policeEquipmentClaimReturn);

    /**
     * 新增警用设备管理
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 结果
     */
    public int insertPoliceEquipmentClaimReturn(PoliceEquipmentClaimReturn policeEquipmentClaimReturn);

    /**
     * 修改警用设备管理
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 结果
     */
    public int updatePoliceEquipmentClaimReturn(PoliceEquipmentClaimReturn policeEquipmentClaimReturn);

    /**
     * 批量删除警用设备管理
     * 
     * @param ids 需要删除的警用设备管理主键集合
     * @return 结果
     */
    public int deletePoliceEquipmentClaimReturnByIds(Long[] ids);

    /**
     * 删除警用设备管理信息
     * 
     * @param id 警用设备管理主键
     * @return 结果
     */
    public int deletePoliceEquipmentClaimReturnById(Long id);
}
