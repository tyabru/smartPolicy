package com.jingyu.safecheck.mapper;

import java.util.List;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeReview;

/**
 * 整改通知书下发审核Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-22
 */
public interface CheckRectifyNoticeDistributeReviewMapper 
{
    /**
     * 查询整改通知书下发审核
     * 
     * @param id 整改通知书下发审核主键
     * @return 整改通知书下发审核
     */
    public CheckRectifyNoticeDistributeReview selectCheckRectifyNoticeDistributeReviewById(Long id);

    /**
     * 查询整改通知书下发审核列表
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 整改通知书下发审核集合
     */
    public List<CheckRectifyNoticeDistributeReview> selectCheckRectifyNoticeDistributeReviewList(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview);

    /**
     * 新增整改通知书下发审核
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 结果
     */
    public int insertCheckRectifyNoticeDistributeReview(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview);

    /**
     * 修改整改通知书下发审核
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 结果
     */
    public int updateCheckRectifyNoticeDistributeReview(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview);

    /**
     * 删除整改通知书下发审核
     * 
     * @param id 整改通知书下发审核主键
     * @return 结果
     */
    public int deleteCheckRectifyNoticeDistributeReviewById(Long id);

    /**
     * 批量删除整改通知书下发审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCheckRectifyNoticeDistributeReviewByIds(Long[] ids);
}
