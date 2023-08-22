package com.jingyu.equipment.mapper;

import java.util.List;
import com.jingyu.equipment.domain.PoliceEquipment;

/**
 * 警用装备Mapper接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface PoliceEquipmentMapper 
{
    /**
     * 查询警用装备配发领取记录
     * 
     * @param id 警用装备配发领取记录主键
     * @return 警用装备配发领取记录
     */
    public PoliceEquipment selectPoliceEquipmentById(Long id);

    /**
     * 查询警用装备配发领取记录列表
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 警用装备配发领取记录集合
     */
    public List<PoliceEquipment> selectPoliceEquipmentList(PoliceEquipment policeEquipment);

    /**
     * 新增警用装备配发领取记录
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 结果
     */
    public int insertPoliceEquipment(PoliceEquipment policeEquipment);

    /**
     * 修改警用装备配发领取记录
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 结果
     */
    public int updatePoliceEquipment(PoliceEquipment policeEquipment);

    /**
     * 删除警用装备配发领取记录
     * 
     * @param id 警用装备配发领取记录主键
     * @return 结果
     */
    public int deletePoliceEquipmentById(Long id);

    /**
     * 批量删除警用装备配发领取记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePoliceEquipmentByIds(Long[] ids);

    /**
     * 查询警用装备
     *
     * @param equipmentNumber 装备编码
     * @return 警用装备配
     */
    public PoliceEquipment getPoliceEquipmentByEquipmentNumber(String equipmentNumber);
}
