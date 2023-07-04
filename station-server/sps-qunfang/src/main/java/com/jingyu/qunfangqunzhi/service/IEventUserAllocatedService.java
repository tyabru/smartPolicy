package com.jingyu.qunfangqunzhi.service;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;

/**
 * 事件分配Service接口
 *
 * @author jingyu
 * @date 2023-07-03
 */
public interface IEventUserAllocatedService
{
    /**
     * 查询事件分配
     *
     * @param id 事件分配主键
     * @return 事件分配
     */
    public EventUserAllocated selectEventUserAllocatedById(Long id);

    /**
     * 查询事件分配列表
     *
     * @param eventUserAllocated 事件分配
     * @return 事件分配集合
     */
    public List<EventUserAllocated> selectEventUserAllocatedList(EventUserAllocated eventUserAllocated);

    /**
     * 新增事件分配
     *
     * @param eventUserAllocated 事件分配
     * @return 结果
     */
    public int insertEventUserAllocated(EventUserAllocated eventUserAllocated);

    /**
     * 修改事件分配
     *
     * @param eventUserAllocated 事件分配
     * @return 结果
     */
    public int updateEventUserAllocated(EventUserAllocated eventUserAllocated);

    /**
     * 批量删除事件分配
     *
     * @param ids 需要删除的事件分配主键集合
     * @return 结果
     */
    public int deleteEventUserAllocatedByIds(Long[] ids);

    /**
     * 删除事件分配信息
     *
     * @param id 事件分配主键
     * @return 结果
     */
    public int deleteEventUserAllocatedById(Long id);

    /**
     * 批量插入下发事件
     */
    public int insertBatchEventUserAllocated(List<EventUserAllocated> list);
}
