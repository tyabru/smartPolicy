package com.jingyu.polices.service;

import java.util.List;
import com.jingyu.polices.domain.PoliceCars;

/**
 * 警用车辆Service接口
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
public interface IPoliceCarsService 
{
    /**
     * 查询警用车辆
     * 
     * @param id 警用车辆主键
     * @return 警用车辆
     */
    public PoliceCars selectPoliceCarsById(Long id);

    /**
     * 查询警用车辆列表
     * 
     * @param policeCars 警用车辆
     * @return 警用车辆集合
     */
    public List<PoliceCars> selectPoliceCarsList(PoliceCars policeCars);

    /**
     * 新增警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    public int insertPoliceCars(PoliceCars policeCars);

    /**
     * 修改警用车辆
     * 
     * @param policeCars 警用车辆
     * @return 结果
     */
    public int updatePoliceCars(PoliceCars policeCars);

    /**
     * 批量删除警用车辆
     * 
     * @param ids 需要删除的警用车辆主键集合
     * @return 结果
     */
    public int deletePoliceCarsByIds(Long[] ids);

    /**
     * 删除警用车辆信息
     * 
     * @param id 警用车辆主键
     * @return 结果
     */
    public int deletePoliceCarsById(Long id);

    /**
     * 查询警用车辆列表
     *
     * @param licenseNumber 车牌号
     * @return 警用车辆集合
     */
    public PoliceCars getPoliceCarByLicenseNumber(String licenseNumber);

    /**
     * 查询警用车辆列表
     *
     * @param carCode 车辆编码
     * @return 警用车辆集合
     */
    public PoliceCars getPoliceCarByCarCode(String carCode);

    /**
     * 查询警用车辆列表
     *
     * @param equipmentNumber 车载设备编码
     * @return 警用车辆集合
     */
    public PoliceCars getPoliceCarByEquipmentNumber(String equipmentNumber);
}
