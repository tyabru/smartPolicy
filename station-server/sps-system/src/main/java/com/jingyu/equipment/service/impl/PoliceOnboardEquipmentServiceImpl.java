package com.jingyu.equipment.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.equipment.domain.PoliceOnboardEquipment;
import com.jingyu.equipment.mapper.PoliceOnboardEquipmentMapper;
import com.jingyu.equipment.service.IPoliceOnboardEquipmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 车载设备Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@Service
public class PoliceOnboardEquipmentServiceImpl implements IPoliceOnboardEquipmentService
{
    @Resource
    private PoliceOnboardEquipmentMapper policeOnboardEquipmentMapper;

    /**
     * 查询车载设备
     * 
     * @param id 车载设备主键
     * @return 车载设备
     */
    @Override
    public PoliceOnboardEquipment selectPoliceOnboardEquipmentById(Long id)
    {
        return policeOnboardEquipmentMapper.selectPoliceOnboardEquipmentById(id);
    }

    /**
     * 查询车载设备列表
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 车载设备
     */
    @Override
    public List<PoliceOnboardEquipment> selectPoliceOnboardEquipmentList(PoliceOnboardEquipment policeOnboardEquipment)
    {
        return policeOnboardEquipmentMapper.selectPoliceOnboardEquipmentList(policeOnboardEquipment);
    }

    /**
     * 新增车载设备
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 结果
     */
    @Override
    public int insertPoliceOnboardEquipment(PoliceOnboardEquipment policeOnboardEquipment)
    {
        policeOnboardEquipment.setCreateTime(DateUtils.getNowDate());
        return policeOnboardEquipmentMapper.insertPoliceOnboardEquipment(policeOnboardEquipment);
    }

    /**
     * 修改车载设备
     * 
     * @param policeOnboardEquipment 车载设备
     * @return 结果
     */
    @Override
    public int updatePoliceOnboardEquipment(PoliceOnboardEquipment policeOnboardEquipment)
    {
        policeOnboardEquipment.setUpdateTime(DateUtils.getNowDate());
        return policeOnboardEquipmentMapper.updatePoliceOnboardEquipment(policeOnboardEquipment);
    }

    /**
     * 批量删除车载设备
     * 
     * @param ids 需要删除的车载设备主键
     * @return 结果
     */
    @Override
    public int deletePoliceOnboardEquipmentByIds(Long[] ids)
    {
        return policeOnboardEquipmentMapper.deletePoliceOnboardEquipmentByIds(ids);
    }

    /**
     * 删除车载设备信息
     * 
     * @param id 车载设备主键
     * @return 结果
     */
    @Override
    public int deletePoliceOnboardEquipmentById(Long id)
    {
        return policeOnboardEquipmentMapper.deletePoliceOnboardEquipmentById(id);
    }
}
