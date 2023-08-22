package com.jingyu.safecheck.service.impl;

import java.util.List;

import com.jingyu.common.annotation.DataScope;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;
import com.jingyu.safecheck.mapper.CheckRectifyNoticeDistributeRecordMapper;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 整改通知书下发Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@Service
public class CheckRectifyNoticeDistributeRecordServiceImpl implements ICheckRectifyNoticeDistributeRecordService
{
    @Autowired
    private CheckRectifyNoticeDistributeRecordMapper checkRectifyNoticeDistributeRecordMapper;

    /**
     * 查询整改通知书下发
     * 
     * @param id 整改通知书下发主键
     * @return 整改通知书下发
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public CheckRectifyNoticeDistributeRecord selectCheckRectifyNoticeDistributeRecordById(Long id)
    {
        return checkRectifyNoticeDistributeRecordMapper.selectCheckRectifyNoticeDistributeRecordById(id);
    }

    /**
     * 查询整改通知书下发列表
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 整改通知书下发
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<CheckRectifyNoticeDistributeRecord> selectCheckRectifyNoticeDistributeRecordList(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return checkRectifyNoticeDistributeRecordMapper.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);
    }

    /**
     * 新增整改通知书下发
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 结果
     */
    @Override
    public int insertCheckRectifyNoticeDistributeRecord(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        checkRectifyNoticeDistributeRecord.setUserId(SecurityUtils.getUserId());
        checkRectifyNoticeDistributeRecord.setDeptId(SecurityUtils.getDeptId());
        return checkRectifyNoticeDistributeRecordMapper.insertCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord);
    }

    /**
     * 修改整改通知书下发
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 结果
     */
    @Override
    public int updateCheckRectifyNoticeDistributeRecord(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord)
    {
        return checkRectifyNoticeDistributeRecordMapper.updateCheckRectifyNoticeDistributeRecord(checkRectifyNoticeDistributeRecord);
    }

    /**
     * 批量删除整改通知书下发
     * 
     * @param ids 需要删除的整改通知书下发主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyNoticeDistributeRecordByIds(Long[] ids)
    {
        return checkRectifyNoticeDistributeRecordMapper.deleteCheckRectifyNoticeDistributeRecordByIds(ids);
    }

    /**
     * 删除整改通知书下发信息
     * 
     * @param id 整改通知书下发主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyNoticeDistributeRecordById(Long id)
    {
        return checkRectifyNoticeDistributeRecordMapper.deleteCheckRectifyNoticeDistributeRecordById(id);
    }
}
