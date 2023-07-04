package com.jingyu.qunfangqunzhi.service.impl;

import java.util.List;

import com.jingyu.common.annotation.DataScope;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.mapper.CommonUsersMapper;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 普通用户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2023-06-27
 */
@Service
public class CommonUsersServiceImpl implements ICommonUsersService
{
    @Autowired
    private CommonUsersMapper commonUsersMapper;

    /**
     * 查询普通用户信息
     *
     * @param userId 普通用户信息主键
     * @return 普通用户信息
     */
    @Override
    public CommonUser selectCommonUsersByUserId(Long userId)
    {
        return commonUsersMapper.selectCommonUsersByUserId(userId);
    }

    /**
     * 查询普通用户信息列表
     *
     * @param commonUser 普通用户信息
     * @return 普通用户信息
     */
    @Override
    @DataScope(deptAlias = "c")
    public List<CommonUser> selectCommonUsersList(CommonUser commonUser)
    {
        return commonUsersMapper.selectCommonUsersList(commonUser);
    }

    /**
     * 新增普通用户信息
     *
     * @param commonUser 普通用户信息
     * @return 结果
     */
    @Override
    public int insertCommonUsers(CommonUser commonUser)
    {
        commonUser.setCreateTime(DateUtils.getNowDate());
        return commonUsersMapper.insertCommonUsers(commonUser);
    }

    /**
     * 修改普通用户信息
     *
     * @param commonUser 普通用户信息
     * @return 结果
     */
    @Override
    public int updateCommonUsers(CommonUser commonUser)
    {
        commonUser.setUpdateTime(DateUtils.getNowDate());
        return commonUsersMapper.updateCommonUsers(commonUser);
    }

    /**
     * 批量删除普通用户信息
     *
     * @param userIds 需要删除的普通用户信息主键
     * @return 结果
     */
    @Override
    public int deleteCommonUsersByUserIds(Long[] userIds)
    {
        return commonUsersMapper.deleteCommonUsersByUserIds(userIds);
    }

    /**
     * 删除普通用户信息信息
     *
     * @param userId 普通用户信息主键
     * @return 结果
     */
    @Override
    public int deleteCommonUsersByUserId(Long userId)
    {
        return commonUsersMapper.deleteCommonUsersByUserId(userId);
    }

    @Override
    public CommonUser selectCommonUserByPhoneNumber(String phoneNumber) {
        return commonUsersMapper.selectCommonUsersByPhoneNumber(phoneNumber);
    }
}
