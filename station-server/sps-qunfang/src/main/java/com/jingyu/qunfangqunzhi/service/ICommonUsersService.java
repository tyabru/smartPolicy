package com.jingyu.qunfangqunzhi.service;

import com.jingyu.qunfangqunzhi.domain.CommonUser;

import java.util.List;


/**
 * 普通用户信息Service接口
 *
 * @author ruoyi
 * @date 2023-06-27
 */
public interface ICommonUsersService
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
     * 批量删除普通用户信息
     *
     * @param userIds 需要删除的普通用户信息主键集合
     * @return 结果
     */
    public int deleteCommonUsersByUserIds(Long[] userIds);

    /**
     * 删除普通用户信息信息
     *
     * @param userId 普通用户信息主键
     * @return 结果
     */
    public int deleteCommonUsersByUserId(Long userId);


    public CommonUser selectCommonUserByPhoneNumber(String phoneNumber);
}
