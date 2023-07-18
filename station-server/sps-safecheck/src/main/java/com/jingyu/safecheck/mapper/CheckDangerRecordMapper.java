package com.jingyu.safecheck.mapper;

import java.util.List;

import com.jingyu.safecheck.domain.CheckDangerRecord;

/**
 * 安全隐患检查登记Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public interface CheckDangerRecordMapper 
{
    /**
     * 查询安全隐患检查登记
     * 
     * @param id 安全隐患检查登记主键
     * @return 安全隐患检查登记
     */
    public CheckDangerRecord selectCheckDangerRecordById(Long id);

    /**
     * 查询安全隐患检查登记列表
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 安全隐患检查登记集合
     */
    public List<CheckDangerRecord> selectCheckDangerRecordList(CheckDangerRecord checkDangerRecord);

    /**
     * 新增安全隐患检查登记
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 结果
     */
    public int insertCheckDangerRecord(CheckDangerRecord checkDangerRecord);

    /**
     * 修改安全隐患检查登记
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 结果
     */
    public int updateCheckDangerRecord(CheckDangerRecord checkDangerRecord);

    /**
     * 删除安全隐患检查登记
     * 
     * @param id 安全隐患检查登记主键
     * @return 结果
     */
    public int deleteCheckDangerRecordById(Long id);

    /**
     * 批量删除安全隐患检查登记
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckDangerRecordByIds(Long[] ids);
}
