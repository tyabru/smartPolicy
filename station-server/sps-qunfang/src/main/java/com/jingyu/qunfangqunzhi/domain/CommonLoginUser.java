package com.jingyu.qunfangqunzhi.domain;

import com.jingyu.common.core.domain.model.LoginUser;

import java.util.Set;

public class CommonLoginUser extends LoginUser {


    /**
     * 用户ID
     */
    private Long userId;


    /**
     * 辖区ID
     */
    private Long areaId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 权限列表
     */
    private Set<String> permissions;
    /**
     * 用户信息
     */
    private CommonUser commonUser;


    public CommonLoginUser(Long userId, Long areaId, CommonUser commonUser, Set<String> permissions)
    {
        this.userId = userId;
        this.areaId = areaId;
        this.commonUser = commonUser;
        this.permissions = permissions;
    }

    public CommonLoginUser() {
    }

    @Override
    public String getUsername() {
        return commonUser.getPhonenumber();
    }


}
