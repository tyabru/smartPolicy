package com.jingyu.safecheck.mapper;

import java.util.List;

import com.jingyu.safecheck.domain.CheckRectifyResultReviewRecord;

/**
 * 整改结果审核Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public interface CheckRectifyResultReviewRecordMapper 
{
    /**
     * 查询整改结果审核
     * 
     * @param id 整改结果审核主键
     * @return 整改结果审核
     */
    public CheckRectifyResultReviewRecord selectCheckRectifyResultReviewRecordById(Long id);

    /**
     * 查询整改结果审核列表
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 整改结果审核集合
     */
    public List<CheckRectifyResultReviewRecord> selectCheckRectifyResultReviewRecordList(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord);

    /**
     * 新增整改结果审核
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 结果
     */
    public int insertCheckRectifyResultReviewRecord(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord);

    /**
     * 修改整改结果审核
     * 
     * @param checkRectifyResultReviewRecord 整改结果审核
     * @return 结果
     */
    public int updateCheckRectifyResultReviewRecord(CheckRectifyResultReviewRecord checkRectifyResultReviewRecord);

    /**
     * 删除整改结果审核
     * 
     * @param id 整改结果审核主键
     * @return 结果
     */
    public int deleteCheckRectifyResultReviewRecordById(Long id);

    /**
     * 批量删除整改结果审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckRectifyResultReviewRecordByIds(Long[] ids);
}
