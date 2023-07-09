package com.jingyu.qunfangqunzhi.service;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.CommonUsersApplication;

/**
 * 申请管理Service接口
 * 
 * @author jingyu
 * @date 2023-07-05
 */
public interface ICommonUsersApplicationService 
{
    /**
     * 查询申请管理
     * 
     * @param id 申请管理主键
     * @return 申请管理
     */
    public CommonUsersApplication selectCommonUsersApplicationById(Long id);

    /**
     * 查询申请管理列表
     * 
     * @param commonUsersApplication 申请管理
     * @return 申请管理集合
     */
    public List<CommonUsersApplication> selectCommonUsersApplicationList(CommonUsersApplication commonUsersApplication);

    /**
     * 新增申请管理
     * 
     * @param commonUsersApplication 申请管理
     * @return 结果
     */
    public int insertCommonUsersApplication(CommonUsersApplication commonUsersApplication);

    /**
     * 修改申请管理
     * 
     * @param commonUsersApplication 申请管理
     * @return 结果
     */
    public int updateCommonUsersApplication(CommonUsersApplication commonUsersApplication);

    /**
     * 批量删除申请管理
     * 
     * @param ids 需要删除的申请管理主键集合
     * @return 结果
     */
    public int deleteCommonUsersApplicationByIds(Long[] ids);

    /**
     * 删除申请管理信息
     * 
     * @param id 申请管理主键
     * @return 结果
     */
    public int deleteCommonUsersApplicationById(Long id);
}
