package com.jingyu.equipment.service;

import com.jingyu.equipment.domain.PoliceOnboardEquipment;

import java.util.List;

/**
 * 车载设备Service接口
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
public interface IPoliceOnboardEquipmentService 
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
     * 批量删除车载设备
     * 
     * @param ids 需要删除的车载设备主键集合
     * @return 结果
     */
    public int deletePoliceOnboardEquipmentByIds(Long[] ids);

    /**
     * 删除车载设备信息
     * 
     * @param id 车载设备主键
     * @return 结果
     */
    public int deletePoliceOnboardEquipmentById(Long id);

    /**
     * 查询车载设备列表
     *
     * @param deviceCode 车备编码
     * @return 车载设备
     */
    public PoliceOnboardEquipment getPoliceOnboardEquipmentByDeviceCode(String deviceCode);
}
