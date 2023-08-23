package com.jingyu.qunfangqunzhi.mapper;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.EventInfo;
import com.jingyu.qunfangqunzhi.domain.EventUserAllocated;

/**
 * 上报事件管理Mapper接口
 *
 * @author jingyu
 * @date 2023-07-03
 */
public interface EventInfoMapper
{
    /**
     * 查询上报事件管理
     *
     * @param id 上报事件管理主键
     * @return 上报事件管理
     */
    public EventInfo selectEventInfoById(Long id);

    /**
     * 查询上报事件管理列表
     *
     * @param eventInfo 上报事件管理
     * @return 上报事件管理集合
     */
    public List<EventInfo> selectEventInfoList(EventInfo eventInfo);

    /**
     * 新增上报事件管理
     *
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    public int insertEventInfo(EventInfo eventInfo);

    /**
     * 修改上报事件管理
     *
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    public int updateEventInfo(EventInfo eventInfo);

    /**
     * 删除上报事件管理
     *
     * @param id 上报事件管理主键
     * @return 结果
     */
    public int deleteEventInfoById(Long id);

    /**
     * 批量删除上报事件管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventInfoByIds(Long[] ids);

    /**
     * 批量删除${subTable.functionName}
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEventUserAllocatedByUserIds(Long[] ids);

    /**
     * 批量新增${subTable.functionName}
     *
     * @param eventUserAllocatedList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchEventUserAllocated(List<EventUserAllocated> eventUserAllocatedList);


    /**
     * 通过上报事件管理主键删除${subTable.functionName}信息
     *
     * @param id 上报事件管理ID
     * @return 结果
     */
    public int deleteEventUserAllocatedByUserId(Long id);


    public Long selectSuperiorDeptIdById(Long userId);
}
