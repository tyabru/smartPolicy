package com.jingyu.safecheck.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.safecheck.mapper.CheckRectifyResultReviewRecordMapper;
import com.jingyu.safecheck.domain.CheckRectifyResultReviewRecord;
import com.jingyu.safecheck.service.ICheckRectifyResultReviewRecordService;

/**
 * 整改结果审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
@Service
public class CheckRectifyResultReviewRecordServiceImpl implements ICheckRectifyResultReviewRecordService 
{
    @Autowired
    private CheckRectifyResultReviewRecordMapper checkRectifyResultReviewRecordMapper;

    /**
     * 查询整改结果审核
     * 
     * @param id 整改结果审核主键
     * @return 整改结果审核
     */
    @Override
    public CheckRectifyResultReviewRecord selectCheckRectifyResultReviewRecordById(Long id)
    {
        return checkRectifyResultReviewRecordMapper.selectCheckRectifyResultReviewRecordById(id);
    }

    /**
     * 查询整改结果审核列表
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 整改结果审核
     */
    @Override
    public List<CheckRectifyResultReviewRecord> selectCheckRectifyResultReviewRecordList(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        return checkRectifyResultReviewRecordMapper.selectCheckRectifyResultReviewRecordList(checkRectifyResultReviewRecord);
    }

    /**
     * 新增整改结果审核
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 结果
     */
    @Override
    public int insertCheckRectifyResultReviewRecord(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        return checkRectifyResultReviewRecordMapper.insertCheckRectifyResultReviewRecord(checkRectifyResultReviewRecord);
    }

    /**
     * 修改整改结果审核
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 结果
     */
    @Override
    public int updateCheckRectifyResultReviewRecord(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord)
    {
        return checkRectifyResultReviewRecordMapper.updateCheckRectifyResultReviewRecord(checkRectifyResultReviewRecord);
    }

    /**
     * 批量删除整改结果审核
     * 
     * @param ids 需要删除的整改结果审核主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyResultReviewRecordByIds(Long[] ids)
    {
        return checkRectifyResultReviewRecordMapper.deleteCheckRectifyResultReviewRecordByIds(ids);
    }

    /**
     * 删除整改结果审核信息
     * 
     * @param id 整改结果审核主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyResultReviewRecordById(Long id)
    {
        return checkRectifyResultReviewRecordMapper.deleteCheckRectifyResultReviewRecordById(id);
    }
}
