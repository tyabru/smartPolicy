package com.jingyu.framework.qunfang.QFsecurity.pwd;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * 自定义手机号密码登录token验证
 */
public class PhoneNumberPwdAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 存储手机号码
     */
    private final Object principal;

    /**
     * 存储密码
     */
    private final Object credentials;

    /**
     * 构建一个没有鉴权的构造函数
     */
    public PhoneNumberPwdAuthenticationToken(Object principal,Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * 构建一个拥有鉴权的构造函数
     */
    public PhoneNumberPwdAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities, Object credentials) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }

}
