package com.jingyu.framework.qunfang.QFsecurity.pwd;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 手机号——短信验证码登录身份认证
 */
public class PhoneNumberPwdAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public PhoneNumberPwdAuthenticationProvider(UserDetailsService userDetailsService){
        setUserDetailsService(userDetailsService);
    }

    /**
     * 重写 authenticate方法，实现身份验证逻辑。
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneNumberPwdAuthenticationToken authenticationToken =  (PhoneNumberPwdAuthenticationToken) authentication;
        String phoneNumber = (String) authenticationToken.getPrincipal();
        // 委托 UserDetailsService 查找系统用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(phoneNumber);
        // 鉴权成功，返回一个拥有鉴权的 AbstractAuthenticationToken
        PhoneNumberPwdAuthenticationToken phoneNumberPwdAuthenticationToken = new PhoneNumberPwdAuthenticationToken(userDetails, userDetails.getAuthorities());
        phoneNumberPwdAuthenticationToken.setDetails(authenticationToken.getDetails());
        System.out.println(authentication.getDetails()+"看看");
        return phoneNumberPwdAuthenticationToken;
    }

    /**
     * 重写supports方法，指定此 AuthenticationProvider 仅支持PhoneNumberPwdAuthenticationToken验证;
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneNumberPwdAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}

