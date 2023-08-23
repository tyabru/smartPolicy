package com.jingyu.qunfangqunzhi.mapper;

import java.util.List;

import com.jingyu.qunfangqunzhi.domain.AllocateRectifyContent;

/**
 * 下发任务修改Mapper接口
 *
 * @author jingyu
 * @date 2023-07-26
 */
public interface AllocateRectifyContentMapper
{
    /**
     * 查询下发任务修改
     *
     * @param id 下发任务修改主键
     * @return 下发任务修改
     */
    public AllocateRectifyContent selectAllocateRectifyContentById(Long id);


    public List<AllocateRectifyContent> selectAllocateRectifyContentByAllocateId(Long id);

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
     * 删除下发任务修改
     *
     * @param id 下发任务修改主键
     * @return 结果
     */
    public int deleteAllocateRectifyContentById(Long id);

    /**
     * 批量删除下发任务修改
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAllocateRectifyContentByIds(Long[] ids);
}
