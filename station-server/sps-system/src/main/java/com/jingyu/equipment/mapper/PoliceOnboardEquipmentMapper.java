package com.jingyu.equipment.mapper;

import com.jingyu.equipment.domain.PoliceOnboardEquipment;

import java.util.List;

/**
 * 车载设备Mapper接口
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
public interface PoliceOnboardEquipmentMapper 
{
    /**
     * 查询车载设备
     * 
     * @param id 车载设备主键
     * @return 车载设备
     */
    public PoliceOnboardEquipment selectPoliceOnboardEquipmentById(Long id);

    /**
     * 查询车载设备列表
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 车载设备集合
     */
    public List<PoliceOnboardEquipment> selectPoliceOnboardEquipmentList(PoliceOnboardEquipment policeOnboardEquipment);

    /**
     * 新增车载设备
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 结果
     */
    public int insertPoliceOnboardEquipment(PoliceOnboardEquipment policeOnboardEquipment);

    /**
     * 修改车载设备
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 结果
     */
    public int updatePoliceOnboardEquipment(PoliceOnboardEquipment policeOnboardEquipment);

    /**
     * 删除车载设备
     * 
     * @param id 车载设备主键
     * @return 结果
     */
    public int deletePoliceOnboardEquipmentById(Long id);

    /**
     * 批量删除车载设备
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePoliceOnboardEquipmentByIds(Long[] ids);
}
