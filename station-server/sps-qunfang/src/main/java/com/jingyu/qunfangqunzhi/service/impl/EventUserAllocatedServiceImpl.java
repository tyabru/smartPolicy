package com.jingyu.qunfangqunzhi.service.impl;

import java.util.List;

import com.jingyu.qunfangqunzhi.domain.EventInfo;
import com.jingyu.qunfangqunzhi.mapper.EventInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jingyu.qunfangqunzhi.mapper.EventUserAllocatedMapper;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;
import com.jingyu.qunfangqunzhi.service.IEventUserAllocatedService;

/**
 * 事件分配Service业务层处理
 *
 * @author jingyu
 * @date 2023-07-03
 */
@Service
public class EventUserAllocatedServiceImpl implements IEventUserAllocatedService
{
    @Autowired
    private EventUserAllocatedMapper eventUserAllocatedMapper;

    @Autowired
    private EventInfoMapper eventInfoMapper;

    /**
     * 查询事件分配
     *
     * @param id 事件分配主键
     * @return 事件分配
     */
    @Override
    public EventUserAllocated selectEventUserAllocatedById(Long id)
    {
        return eventUserAllocatedMapper.selectEventUserAllocatedById(id);
    }

    /**
     * 查询事件分配列表
     *
     * @param eventUserAllocated 事件分配
     * @return 事件分配
     */
    @Override
    public List<EventUserAllocated> selectEventUserAllocatedList(EventUserAllocated eventUserAllocated)
    {
        return eventUserAllocatedMapper.selectEventUserAllocatedList(eventUserAllocated);
    }

    /**
     * 新增事件分配
     *
     * @param eventUserAllocated 事件分配
     * @return 结果
     */
    @Override
    public int insertEventUserAllocated(EventUserAllocated eventUserAllocated)
    {
        return eventUserAllocatedMapper.insertEventUserAllocated(eventUserAllocated);
    }

    /**
     * 修改事件分配
     *
     * @param eventUserAllocated 事件分配
     * @return 结果
     */
    @Override
    public int updateEventUserAllocated(EventUserAllocated eventUserAllocated)
    {
        return eventUserAllocatedMapper.updateEventUserAllocated(eventUserAllocated);
    }

    /**
     * 批量删除事件分配
     *
     * @param ids 需要删除的事件分配主键
     * @return 结果
     */
    @Override
    public int deleteEventUserAllocatedByIds(Long[] ids)
    {
        return eventUserAllocatedMapper.deleteEventUserAllocatedByIds(ids);
    }

    /**
     * 删除事件分配信息
     *
     * @param id 事件分配主键
     * @return 结果
     */
    @Override
    public int deleteEventUserAllocatedById(Long id)
    {
        return eventUserAllocatedMapper.deleteEventUserAllocatedById(id);
    }

    /**
     * 批量下发
     *
     */

    @Override
    public int insertBatchEventUserAllocated(List<EventUserAllocated> list) {
        return eventInfoMapper.batchEventUserAllocated(list);
    }
}
