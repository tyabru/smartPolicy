package com.jingyu.equipment.service;

import java.util.List;
import com.jingyu.equipment.domain.PoliceEquipment;

/**
 * 警用装备Service接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface IPoliceEquipmentService 
{
    /**
     * 查询警用装备
     * 
     * @param id 警用装备
     * @return 警用装备
     */
    public PoliceEquipment selectPoliceEquipmentById(Long id);

    /**
     * 查询警用装备
     * 
     * @param policeEquipment 警用装备
     * @return 警用装备配发领取记录集合
     */
    public List<PoliceEquipment> selectPoliceEquipmentList(PoliceEquipment policeEquipment);

    /**
     * 新增警用装备配发领取记录
     * 
     * @param policeEquipment 警用装备
     * @return 结果
     */
    public int insertPoliceEquipment(PoliceEquipment policeEquipment);

    /**
     * 修改警用装备
     * 
     * @param policeEquipment 警用装备
     * @return 结果
     */
    public int updatePoliceEquipment(PoliceEquipment policeEquipment);

    /**
     * 批量删除警用装备
     * 
     * @param ids 需要删除的警用装备
     * @return 结果
     */
    public int deletePoliceEquipmentByIds(Long[] ids);

    /**
     * 删除警用装备信息
     * 
     * @param id 警用装备主键
     * @return 结果
     */
    public int deletePoliceEquipmentById(Long id);

    /**
     * 查询警用装备
     *
     * @param equipmentNumber 装备编码
     * @return 警用装备配
     */
    public PoliceEquipment getPoliceEquipmentByEquipmentNumber(String equipmentNumber);
}
