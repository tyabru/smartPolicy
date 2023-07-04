package com.jingyu.qunfangqunzhi.mapper;

import java.util.List;
import com.jingyu.qunfangqunzhi.domain.CommonUser;

/**
 * 普通用户信息Mapper接口
 * 
 * @author jingyu
 * @date 2023-07-03
 */
public interface CommonUserMapper 
{
    /**
     * 查询普通用户信息
     * 
     * @param userId 普通用户信息主键
     * @return 普通用户信息
     */
    public CommonUser selectCommonUserByUserId(Long userId);

    /**
     * 查询普通用户信息列表
     * 
     * @param commonUser 普通用户信息
     * @return 普通用户信息集合
     */
    public List<CommonUser> selectCommonUserList(CommonUser commonUser);

    /**
     * 新增普通用户信息
     * 
     * @param commonUser 普通用户信息
     * @return 结果
     */
    public int insertCommonUser(CommonUser commonUser);

    /**
     * 修改普通用户信息
     * 
     * @param commonUser 普通用户信息
     * @return 结果
     */
    public int updateCommonUser(CommonUser commonUser);

    /**
     * 删除普通用户信息
     * 
     * @param userId 普通用户信息主键
     * @return 结果
     */
    public int deleteCommonUserByUserId(Long userId);

    /**
     * 批量删除普通用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommonUserByUserIds(Long[] userIds);
}
