package com.jingyu.qunfangqunzhi.mapper;

import com.jingyu.qunfangqunzhi.domain.CommonUser;

import java.util.List;


/**
 * 普通用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2023-06-27
 */
public interface CommonUsersMapper
{
    /**
     * 查询普通用户信息
     *
     * @param userId 普通用户信息主键
     * @return 普通用户信息
     */
    public CommonUser selectCommonUsersByUserId(Long userId);

    /**
     * 查询普通用户信息列表
     *
     * @param commonUser 普通用户信息
     * @return 普通用户信息集合
     */
    public List<CommonUser> selectCommonUsersList(CommonUser commonUser);

    /**
     * 新增普通用户信息
     *
     * @param commonUser 普通用户信息
     * @return 结果
     */
    public int insertCommonUsers(CommonUser commonUser);

    /**
     * 修改普通用户信息
     *
     * @param commonUser 普通用户信息
     * @return 结果
     */
    public int updateCommonUsers(CommonUser commonUser);

    /**
     * 删除普通用户信息
     *
     * @param userId 普通用户信息主键
     * @return 结果
     */
    public int deleteCommonUsersByUserId(Long userId);

    /**
     * 批量删除普通用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommonUsersByUserIds(Long[] userIds);


    public CommonUser selectCommonUsersByPhoneNumber(String phoneNumber);

}
