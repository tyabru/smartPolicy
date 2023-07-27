package com.jingyu.qunfangqunzhi.mapper;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;

/**
 * 事件分配Mapper接口
 *
 * @author jingyu
 * @date 2023-07-03
 */
public interface EventUserAllocatedMapper
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
     * 删除事件分配
     *
     * @param id 事件分配主键
     * @return 结果
     */
    public int deleteEventUserAllocatedById(Long id);

    /**
     * 批量删除事件分配
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventUserAllocatedByIds(Long[] ids);

    /**
     * 根据事件id取消已下发分配任务
     * @param eventId
     * @return
     */
    public int cancelByEventId(Long eventId);
}
