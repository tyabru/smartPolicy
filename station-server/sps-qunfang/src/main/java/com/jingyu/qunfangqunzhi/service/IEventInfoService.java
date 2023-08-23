package com.jingyu.qunfangqunzhi.service;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.EventInfo;

/**
 * 上报事件管理Service接口
<<<<<<< HEAD
 * 
 * @author jingyu
 * @date 2023-07-03
 */
public interface IEventInfoService 
{
    /**
     * 查询上报事件管理
     * 
=======
 *
 * @author jingyu
 * @date 2023-07-03
 */
public interface IEventInfoService
{
    /**
     * 查询上报事件管理
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param id 上报事件管理主键
     * @return 上报事件管理
     */
    public EventInfo selectEventInfoById(Long id);

    /**
     * 查询上报事件管理列表
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param eventInfo 上报事件管理
     * @return 上报事件管理集合
     */
    public List<EventInfo> selectEventInfoList(EventInfo eventInfo);

    /**
     * 新增上报事件管理
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    public int insertEventInfo(EventInfo eventInfo);

    /**
     * 修改上报事件管理
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param eventInfo 上报事件管理
     * @return 结果
     */
    public int updateEventInfo(EventInfo eventInfo);

    /**
     * 批量删除上报事件管理
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param ids 需要删除的上报事件管理主键集合
     * @return 结果
     */
    public int deleteEventInfoByIds(Long[] ids);

    /**
     * 删除上报事件管理信息
<<<<<<< HEAD
     * 
=======
     *
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
     * @param id 上报事件管理主键
     * @return 结果
     */
    public int deleteEventInfoById(Long id);
<<<<<<< HEAD
=======


    public Long selectSuperiorDeptIdById(Long userId);
>>>>>>> fc1e3ed2add3bb98c2a2514c47a486213c442590
}
