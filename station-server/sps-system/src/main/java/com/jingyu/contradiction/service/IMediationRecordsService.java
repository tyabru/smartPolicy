package com.jingyu.contradiction.service;

import java.util.List;
import com.jingyu.contradiction.domain.MediationRecords;

/**
 * 纠纷调解记录Service接口
 * 
 * @author jiatongbo
 * @date 2023-08-15
 */
public interface IMediationRecordsService 
{
    /**
     * 查询纠纷调解记录
     * 
     * @param id 纠纷调解记录主键
     * @return 纠纷调解记录
     */
    public MediationRecords selectMediationRecordsById(Long id);

    /**
     * 查询纠纷调解记录列表
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 纠纷调解记录集合
     */
    public List<MediationRecords> selectMediationRecordsList(MediationRecords mediationRecords);

    /**
     * 新增纠纷调解记录
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 结果
     */
    public int insertMediationRecords(MediationRecords mediationRecords);

    /**
     * 修改纠纷调解记录
     * 
     * @param mediationRecords 纠纷调解记录
     * @return 结果
     */
    public int updateMediationRecords(MediationRecords mediationRecords);

    /**
     * 批量删除纠纷调解记录
     * 
     * @param ids 需要删除的纠纷调解记录主键集合
     * @return 结果
     */
    public int deleteMediationRecordsByIds(Long[] ids);

    /**
     * 删除纠纷调解记录信息
     * 
     * @param id 纠纷调解记录主键
     * @return 结果
     */
    public int deleteMediationRecordsById(Long id);
}
