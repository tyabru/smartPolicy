package com.jingyu.safecheck.mapper;

import java.util.List;

import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;

/**
 * 整改通知书下发Mapper接口
 * 
 * @author ruoyi
 * @date 2023-06-27
 */
public interface CheckRectifyNoticeDistributeRecordMapper 
{
    /**
     * 查询整改通知书下发
     * 
     * @param id 整改通知书下发主键
     * @return 整改通知书下发
     */
    public CheckRectifyNoticeDistributeRecord selectCheckRectifyNoticeDistributeRecordById(Long id);

    /**
     * 查询整改通知书下发列表
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 整改通知书下发集合
     */
    public List<CheckRectifyNoticeDistributeRecord> selectCheckRectifyNoticeDistributeRecordList(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord);

    /**
     * 新增整改通知书下发
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 结果
     */
    public int insertCheckRectifyNoticeDistributeRecord(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord);

    /**
     * 修改整改通知书下发
     * 
     * @param checkRectifyNoticeDistributeRecord 整改通知书下发
     * @return 结果
     */
    public int updateCheckRectifyNoticeDistributeRecord(CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord);

    /**
     * 删除整改通知书下发
     * 
     * @param id 整改通知书下发主键
     * @return 结果
     */
    public int deleteCheckRectifyNoticeDistributeRecordById(Long id);

    /**
     * 批量删除整改通知书下发
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckRectifyNoticeDistributeRecordByIds(Long[] ids);
}
