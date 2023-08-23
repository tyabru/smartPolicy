package com.jingyu.safecheck.service.impl;

import java.util.List;

import com.jingyu.common.annotation.DataScope;
import com.jingyu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.safecheck.mapper.CheckRectifyNoticeDistributeReviewMapper;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeReview;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeReviewService;

/**
 * 整改通知书下发审核Service业务层处理
 * 
 * @author jingyu
 * @date 2023-07-22
 */
@Service
public class CheckRectifyNoticeDistributeReviewServiceImpl implements ICheckRectifyNoticeDistributeReviewService 
{
    @Autowired
    private CheckRectifyNoticeDistributeReviewMapper checkRectifyNoticeDistributeReviewMapper;

    /**
     * 查询整改通知书下发审核
     * 
     * @param id 整改通知书下发审核主键
     * @return 整改通知书下发审核
     */
    @Override
    public CheckRectifyNoticeDistributeReview selectCheckRectifyNoticeDistributeReviewById(Long id)
    {
        return checkRectifyNoticeDistributeReviewMapper.selectCheckRectifyNoticeDistributeReviewById(id);
    }

    /**
     * 查询整改通知书下发审核列表
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 整改通知书下发审核
     */
    @Override
    public List<CheckRectifyNoticeDistributeReview> selectCheckRectifyNoticeDistributeReviewList(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview)
    {
        return checkRectifyNoticeDistributeReviewMapper.selectCheckRectifyNoticeDistributeReviewList(checkRectifyNoticeDistributeReview);
    }

    /**
     * 新增整改通知书下发审核
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 结果
     */
    @Override
    public int insertCheckRectifyNoticeDistributeReview(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview)
    {
        checkRectifyNoticeDistributeReview.setUserId(SecurityUtils.getUserId());
        checkRectifyNoticeDistributeReview.setDeptId(SecurityUtils.getDeptId());
        return checkRectifyNoticeDistributeReviewMapper.insertCheckRectifyNoticeDistributeReview(checkRectifyNoticeDistributeReview);
    }

    /**
     * 修改整改通知书下发审核
     * 
     * @param checkRectifyNoticeDistributeReview 整改通知书下发审核
     * @return 结果
     */
    @Override
    public int updateCheckRectifyNoticeDistributeReview(CheckRectifyNoticeDistributeReview checkRectifyNoticeDistributeReview)
    {
        return checkRectifyNoticeDistributeReviewMapper.updateCheckRectifyNoticeDistributeReview(checkRectifyNoticeDistributeReview);
    }

    /**
     * 批量删除整改通知书下发审核
     * 
     * @param ids 需要删除的整改通知书下发审核主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyNoticeDistributeReviewByIds(Long[] ids)
    {
        return checkRectifyNoticeDistributeReviewMapper.deleteCheckRectifyNoticeDistributeReviewByIds(ids);
    }

    /**
     * 删除整改通知书下发审核信息
     * 
     * @param id 整改通知书下发审核主键
     * @return 结果
     */
    @Override
    public int deleteCheckRectifyNoticeDistributeReviewById(Long id)
    {
        return checkRectifyNoticeDistributeReviewMapper.deleteCheckRectifyNoticeDistributeReviewById(id);
    }
}
