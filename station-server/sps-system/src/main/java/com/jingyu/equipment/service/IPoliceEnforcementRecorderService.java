package com.jingyu.equipment.service;

import com.jingyu.equipment.domain.PoliceEnforcementRecorder;

import java.util.List;

/**
 * 执法仪记录仪Service接口
 * 
 * @author jiatongbo
 * @date 2023-07-13
 */
public interface IPoliceEnforcementRecorderService 
{
    /**
     * 查询执法仪记录仪
     * 
     * @param id 执法仪记录仪主键
     * @return 执法仪记录仪
     */
    public PoliceEnforcementRecorder selectPoliceEnforcementRecorderById(Long id);

    /**
     * 查询执法仪记录仪列表
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 执法仪记录仪集合
     */
    public List<PoliceEnforcementRecorder> selectPoliceEnforcementRecorderList(PoliceEnforcementRecorder policeEnforcementRecorder);

    /**
     * 新增执法仪记录仪
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 结果
     */
    public int insertPoliceEnforcementRecorder(PoliceEnforcementRecorder policeEnforcementRecorder);

    /**
     * 修改执法仪记录仪
     * 
     * @param policeEnforcementRecorder 执法仪记录仪
     * @return 结果
     */
    public int updatePoliceEnforcementRecorder(PoliceEnforcementRecorder policeEnforcementRecorder);

    /**
     * 批量删除执法仪记录仪
     * 
     * @param ids 需要删除的执法仪记录仪主键集合
     * @return 结果
     */
    public int deletePoliceEnforcementRecorderByIds(Long[] ids);

    /**
     * 删除执法仪记录仪信息
     * 
     * @param id 执法仪记录仪主键
     * @return 结果
     */
    public int deletePoliceEnforcementRecorderById(Long id);
}
