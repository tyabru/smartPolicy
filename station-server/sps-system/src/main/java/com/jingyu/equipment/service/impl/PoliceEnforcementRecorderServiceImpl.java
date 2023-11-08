package com.jingyu.equipment.service.impl;

import java.util.List;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.equipment.domain.PoliceEnforcementRecorder;
import com.jingyu.equipment.mapper.PoliceEnforcementRecorderMapper;
import com.jingyu.equipment.service.IPoliceEnforcementRecorderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 执法仪记录仪Service业务层处理
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
@Service
public class PoliceEnforcementRecorderServiceImpl implements IPoliceEnforcementRecorderService
{
    @Resource
    private PoliceEnforcementRecorderMapper policeEnforcementRecorderMapper;

    /**
     * 查询执法仪记录仪
     * 
     * @param id 执法仪记录仪主键
     * @return 执法仪记录仪
     */
    @Override
    public PoliceEnforcementRecorder selectPoliceEnforcementRecorderById(Long id)
    {
        return policeEnforcementRecorderMapper.selectPoliceEnforcementRecorderById(id);
    }

    /**
     * 查询执法仪记录仪列表
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 执法仪记录仪
     */
    @Override
    public List<PoliceEnforcementRecorder> selectPoliceEnforcementRecorderList(PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        return policeEnforcementRecorderMapper.selectPoliceEnforcementRecorderList(policeEnforcementRecorder);
    }

    /**
     * 新增执法仪记录仪
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 结果
     */
    @Override
    @Transactional
    public int insertPoliceEnforcementRecorder(PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        policeEnforcementRecorder.setCreateTime(DateUtils.getNowDate());
        return policeEnforcementRecorderMapper.insertPoliceEnforcementRecorder(policeEnforcementRecorder);
    }

    /**
     * 修改执法仪记录仪
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 结果
     */
    @Override
    @Transactional
    public int updatePoliceEnforcementRecorder(PoliceEnforcementRecorder policeEnforcementRecorder)
    {
        policeEnforcementRecorder.setUpdateTime(DateUtils.getNowDate());
        return policeEnforcementRecorderMapper.updatePoliceEnforcementRecorder(policeEnforcementRecorder);
    }

    /**
     * 批量删除执法仪记录仪
     * 
     * @param ids 需要删除的执法仪记录仪主键
     * @return 结果
     */
    @Override
    public int deletePoliceEnforcementRecorderByIds(Long[] ids)
    {
        return policeEnforcementRecorderMapper.deletePoliceEnforcementRecorderByIds(ids);
    }

    /**
     * 删除执法仪记录仪信息
     * 
     * @param id 执法仪记录仪主键
     * @return 结果
     */
    @Override
    public int deletePoliceEnforcementRecorderById(Long id)
    {
        return policeEnforcementRecorderMapper.deletePoliceEnforcementRecorderById(id);
    }

    /**
     * 查询执法仪记录仪
     *
     * @param deviceCode 设备编码
     * @return 执法仪记录仪
     */
    @Override
    public PoliceEnforcementRecorder getPoliceEnforcementRecorderByDeviceCode(String deviceCode) {
        return policeEnforcementRecorderMapper.getPoliceEnforcementRecorderByDeviceCode(deviceCode);
    }
}
