package com.jingyu.equipment.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.equipment.mapper.PoliceEquipmentMapper;
import com.jingyu.equipment.domain.PoliceEquipment;
import com.jingyu.equipment.service.IPoliceEquipmentService;

/**
 * 警用装备配发领取记录Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceEquipmentServiceImpl implements IPoliceEquipmentService 
{
    @Autowired
    private PoliceEquipmentMapper policeEquipmentMapper;

    /**
     * 查询警用装备配发领取记录
     * 
     * @param id 警用装备配发领取记录主键
     * @return 警用装备配发领取记录
     */
    @Override
    public PoliceEquipment selectPoliceEquipmentById(Long id)
    {
        return policeEquipmentMapper.selectPoliceEquipmentById(id);
    }

    /**
     * 查询警用装备配发领取记录列表
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 警用装备配发领取记录
     */
    @Override
    public List<PoliceEquipment> selectPoliceEquipmentList(PoliceEquipment policeEquipment)
    {
        return policeEquipmentMapper.selectPoliceEquipmentList(policeEquipment);
    }

    /**
     * 新增警用装备配发领取记录
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 结果
     */
    @Override
    public int insertPoliceEquipment(PoliceEquipment policeEquipment)
    {
        return policeEquipmentMapper.insertPoliceEquipment(policeEquipment);
    }

    /**
     * 修改警用装备配发领取记录
     * 
     * @param policeEquipment 警用装备配发领取记录
     * @return 结果
     */
    @Override
    public int updatePoliceEquipment(PoliceEquipment policeEquipment)
    {
        return policeEquipmentMapper.updatePoliceEquipment(policeEquipment);
    }

    /**
     * 批量删除警用装备配发领取记录
     * 
     * @param ids 需要删除的警用装备配发领取记录主键
     * @return 结果
     */
    @Override
    public int deletePoliceEquipmentByIds(Long[] ids)
    {
        return policeEquipmentMapper.deletePoliceEquipmentByIds(ids);
    }

    /**
     * 删除警用装备配发领取记录信息
     * 
     * @param id 警用装备配发领取记录主键
     * @return 结果
     */
    @Override
    public int deletePoliceEquipmentById(Long id)
    {
        return policeEquipmentMapper.deletePoliceEquipmentById(id);
    }
}
