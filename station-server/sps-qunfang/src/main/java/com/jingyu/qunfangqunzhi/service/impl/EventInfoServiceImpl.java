package com.jingyu.qunfangqunzhi.service.impl;

import java.util.Date;
import java.util.List;

import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.annotation.DataScope;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.util.MyIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.jingyu.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;
import com.jingyu.qunfangqunzhi.mapper.EventInfoMapper;
import com.jingyu.qunfangqunzhi.domain.EventInfo;
import com.jingyu.qunfangqunzhi.service.IEventInfoService;

/**
 * 上报事件管理Service业务层处理
 *
 * @author jingyu
 * @date 2023-07-03
 */
@Service
public class EventInfoServiceImpl implements IEventInfoService
{
    @Autowired
    private EventInfoMapper eventInfoMapper;

    /**
     * 查询上报事件管理
     *
     * @param id 上报事件管理主键
     * @return 上报事件管理
     */
    @Override
    public EventInfo selectEventInfoById(Long id)
    {
        return eventInfoMapper.selectEventInfoById(id);
    }

    /**
     * 查询上报事件管理列表
     *
     * @param eventInfo 上报事件管理
     * @return 上报事件管理
     */
    @Override
    @DataScope(deptAlias = "c")
    public List<EventInfo> selectEventInfoList(EventInfo eventInfo)
    {
        return eventInfoMapper.selectEventInfoList(eventInfo);
    }

    /**
     * 新增上报事件管理
     *
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertEventInfo(EventInfo eventInfo)
    {
        eventInfo.setId(MyIdUtil.getRandomId());
        eventInfo.setUploadUserId(SecurityUtils.getUserId());
        eventInfo.setUploadTime(new Date());
        eventInfo.setStatus(QFConstants.EventStatus.UNCONFIRMED.getValue());
        int rows = eventInfoMapper.insertEventInfo(eventInfo);
        insertEventUserAllocated(eventInfo);
        return rows;
    }

    /**
     * 修改上报事件管理
     *
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateEventInfo(EventInfo eventInfo)
    {
        eventInfoMapper.deleteEventUserAllocatedByUserId(eventInfo.getId());
        insertEventUserAllocated(eventInfo);
        return eventInfoMapper.updateEventInfo(eventInfo);
    }

    /**
     * 批量删除上报事件管理
     *
     * @param ids 需要删除的上报事件管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEventInfoByIds(Long[] ids)
    {
        eventInfoMapper.deleteEventUserAllocatedByUserIds(ids);
        return eventInfoMapper.deleteEventInfoByIds(ids);
    }

    /**
     * 删除上报事件管理信息
     *
     * @param id 上报事件管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteEventInfoById(Long id)
    {
        eventInfoMapper.deleteEventUserAllocatedByUserId(id);
        return eventInfoMapper.deleteEventInfoById(id);
    }

    @Override
    public Long selectSuperiorDeptIdById(Long userId) {
        return eventInfoMapper.selectSuperiorDeptIdById(userId);
    }

    /**
     * 新增${subTable.functionName}信息
     *
     * @param eventInfo 上报事件管理对象
     */
    public void insertEventUserAllocated(EventInfo eventInfo)
    {
        List<EventUserAllocated> eventUserAllocatedList = eventInfo.getEventUserAllocatedList();
        Long id = eventInfo.getId();
        if (StringUtils.isNotNull(eventUserAllocatedList))
        {
            List<EventUserAllocated> list = new ArrayList<EventUserAllocated>();
            for (EventUserAllocated eventUserAllocated : eventUserAllocatedList)
            {
                eventUserAllocated.setUserId(id);
                list.add(eventUserAllocated);
            }
            if (list.size() > 0)
            {
                eventInfoMapper.batchEventUserAllocated(list);
            }
        }
    }
}
