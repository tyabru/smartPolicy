package com.jingyu.qunfangqunzhi.service;

import java.util.List;

import com.jingyu.qunfangqunzhi.domain.AllocateRectifyContent;

/**
 * 下发任务修改Service接口
 *
 * @author jingyu
 * @date 2023-07-26
 */
public interface IAllocateRectifyContentService
{
    /**
     * 查询下发任务修改
     *
     * @param id 下发任务修改主键
     * @return 下发任务修改
     */
    public AllocateRectifyContent selectAllocateRectifyContentById(Long id);

    /**
     * 查询下发任务修改列表
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 下发任务修改集合
     */
    public List<AllocateRectifyContent> selectAllocateRectifyContentList(AllocateRectifyContent allocateRectifyContent);

    /**
     * 新增下发任务修改
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 结果
     */
    public int insertAllocateRectifyContent(AllocateRectifyContent allocateRectifyContent);

    /**
     * 修改下发任务修改
     *
     * @param allocateRectifyContent 下发任务修改
     * @return 结果
     */
    public int updateAllocateRectifyContent(AllocateRectifyContent allocateRectifyContent);

    /**
     * 批量删除下发任务修改
     *
     * @param ids 需要删除的下发任务修改主键集合
     * @return 结果
     */
    public int deleteAllocateRectifyContentByIds(Long[] ids);

    /**
     * 删除下发任务修改信息
     *
     * @param id 下发任务修改主键
     * @return 结果
     */
    public int deleteAllocateRectifyContentById(Long id);


}
