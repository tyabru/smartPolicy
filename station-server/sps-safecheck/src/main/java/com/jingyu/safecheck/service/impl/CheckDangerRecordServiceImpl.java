package com.jingyu.safecheck.service.impl;

import java.util.List;

import com.jingyu.common.annotation.DataScope;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.safecheck.domain.CheckDangerRecord;
import com.jingyu.safecheck.mapper.CheckDangerRecordMapper;
import com.jingyu.safecheck.service.ICheckDangerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 安全隐患检查登记Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@Service
public class CheckDangerRecordServiceImpl implements ICheckDangerRecordService
{
    @Autowired
    private CheckDangerRecordMapper checkDangerRecordMapper;

    /**
     * 查询安全隐患检查登记
     * 
     * @param id 安全隐患检查登记主键
     * @return 安全隐患检查登记
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public CheckDangerRecord selectCheckDangerRecordById(Long id)
    {
        return checkDangerRecordMapper.selectCheckDangerRecordById(id);
    }

    /**
     * 查询安全隐患检查登记列表
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 安全隐患检查登记
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CheckDangerRecord> selectCheckDangerRecordList(CheckDangerRecord checkDangerRecord)
    {
        return checkDangerRecordMapper.selectCheckDangerRecordList(checkDangerRecord);
    }

    /**
     * 新增安全隐患检查登记
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 结果
     */
    @Override
    public int insertCheckDangerRecord(CheckDangerRecord checkDangerRecord)
    {
//        checkDangerRecord.setCreateBy(SecurityUtils.getUsername());
        checkDangerRecord.setUserId(SecurityUtils.getUserId());
        checkDangerRecord.setDeptId(SecurityUtils.getDeptId());
        return checkDangerRecordMapper.insertCheckDangerRecord(checkDangerRecord);
    }

    /**
     * 修改安全隐患检查登记
     * 
     * @param checkDangerRecord 安全隐患检查登记
     * @return 结果
     */
    @Override
    public int updateCheckDangerRecord(CheckDangerRecord checkDangerRecord)
    {
        return checkDangerRecordMapper.updateCheckDangerRecord(checkDangerRecord);
    }

    /**
     * 批量删除安全隐患检查登记
     * 
     * @param ids 需要删除的安全隐患检查登记主键
     * @return 结果
     */
    @Override
    public int deleteCheckDangerRecordByIds(Long[] ids)
    {
        return checkDangerRecordMapper.deleteCheckDangerRecordByIds(ids);
    }

    /**
     * 删除安全隐患检查登记信息
     * 
     * @param id 安全隐患检查登记主键
     * @return 结果
     */
    @Override
    public int deleteCheckDangerRecordById(Long id)
    {
        return checkDangerRecordMapper.deleteCheckDangerRecordById(id);
    }
}
