package com.jingyu.equipment.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.equipment.mapper.PoliceEquipmentClaimReturnMapper;
import com.jingyu.equipment.domain.PoliceEquipmentClaimReturn;
import com.jingyu.equipment.service.IPoliceEquipmentClaimReturnService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 警用设备管理Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-06-27
 */
@Service
public class PoliceEquipmentClaimReturnServiceImpl implements IPoliceEquipmentClaimReturnService 
{
    @Autowired
    private PoliceEquipmentClaimReturnMapper policeEquipmentClaimReturnMapper;

    /**
     * 查询警用设备管理
     * 
     * @param id 警用设备管理主键
     * @return 警用设备管理
     */
    @Override
    public PoliceEquipmentClaimReturn selectPoliceEquipmentClaimReturnById(Long id)
    {
        return policeEquipmentClaimReturnMapper.selectPoliceEquipmentClaimReturnById(id);
    }

    /**
     * 查询警用设备管理列表
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 警用设备管理
     */
    @Override
    public List<PoliceEquipmentClaimReturn> selectPoliceEquipmentClaimReturnList(PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        return policeEquipmentClaimReturnMapper.selectPoliceEquipmentClaimReturnList(policeEquipmentClaimReturn);
    }

    /**
     * 新增警用设备管理
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPoliceEquipmentClaimReturn(PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        return policeEquipmentClaimReturnMapper.insertPoliceEquipmentClaimReturn(policeEquipmentClaimReturn);
    }

    /**
     * 修改警用设备管理
     * 
     * @param policeEquipmentClaimReturn 警用设备管理
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePoliceEquipmentClaimReturn(PoliceEquipmentClaimReturn policeEquipmentClaimReturn)
    {
        return policeEquipmentClaimReturnMapper.updatePoliceEquipmentClaimReturn(policeEquipmentClaimReturn);
    }

    /**
     * 批量删除警用设备管理
     * 
     * @param ids 需要删除的警用设备管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceEquipmentClaimReturnByIds(Long[] ids)
    {
        return policeEquipmentClaimReturnMapper.deletePoliceEquipmentClaimReturnByIds(ids);
    }

    /**
     * 删除警用设备管理信息
     * 
     * @param id 警用设备管理主键
     * @return 结果
     */
    @Override
    public int deletePoliceEquipmentClaimReturnById(Long id)
    {
        return policeEquipmentClaimReturnMapper.deletePoliceEquipmentClaimReturnById(id);
    }
}
