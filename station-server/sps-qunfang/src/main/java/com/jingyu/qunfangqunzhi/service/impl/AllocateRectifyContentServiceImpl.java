package com.jingyu.qunfangqunzhi.service.impl;

import java.util.Date;
import java.util.List;

import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.qunfangqunzhi.domain.AllocateRectifyContent;
import com.jingyu.qunfangqunzhi.service.IAllocateRectifyContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.qunfangqunzhi.mapper.AllocateRectifyContentMapper;

/**
 * 下发任务修改Service业务层处理
 *
 * @author jingyu
 * @date 2023-07-26
 */
@Service
public class AllocateRectifyContentServiceImpl implements IAllocateRectifyContentService
{
    @Autowired
    private AllocateRectifyContentMapper allocateRectifyContentMapper;

    /**
     * 查询下发任务修改
     *
     * @param id 下发任务修改主键
     * @return 下发任务修改
     */
    @Override
    public AllocateRectifyContent selectAllocateRectifyContentById(Long id)
    {
        return allocateRectifyContentMapper.selectAllocateRectifyContentById(id);
    }

    /**
     * 查询下发任务修改列表
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 下发任务修改
     */
    @Override
    public List<AllocateRectifyContent> selectAllocateRectifyContentList(AllocateRectifyContent allocateRectifyContent)
    {
        return allocateRectifyContentMapper.selectAllocateRectifyContentList(allocateRectifyContent);
    }

    /**
     * 新增下发任务修改
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 结果
     */
    @Override
    public int insertAllocateRectifyContent(AllocateRectifyContent allocateRectifyContent)
    {

        allocateRectifyContent.setSendUserId(SecurityUtils.getUserId());
        allocateRectifyContent.setSendTime(new Date());
        return allocateRectifyContentMapper.insertAllocateRectifyContent(allocateRectifyContent);
    }

    /**
     * 修改下发任务修改
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 结果
     */
    @Override
    public int updateAllocateRectifyContent(AllocateRectifyContent allocateRectifyContent)
    {
        return allocateRectifyContentMapper.updateAllocateRectifyContent(allocateRectifyContent);
    }

    /**
     * 批量删除下发任务修改
     *
     * @param ids 需要删除的下发任务修改主键
     * @return 结果
     */
    @Override
    public int deleteAllocateRectifyContentByIds(Long[] ids)
    {
        return allocateRectifyContentMapper.deleteAllocateRectifyContentByIds(ids);
    }

    /**
     * 删除下发任务修改信息
     *
     * @param id 下发任务修改主键
     * @return 结果
     */
    @Override
    public int deleteAllocateRectifyContentById(Long id)
    {
        return allocateRectifyContentMapper.deleteAllocateRectifyContentById(id);
    }
}
