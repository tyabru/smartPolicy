package com.jingyu.framework.qunfang.service;


import com.jingyu.common.constant.Constants;
import com.jingyu.common.core.redis.RedisCache;
import com.jingyu.common.exception.ServiceException;
import com.jingyu.common.exception.user.UserPasswordNotMatchException;
import com.jingyu.common.utils.DateUtils;
import com.jingyu.common.utils.MessageUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.ip.IpUtils;
import com.jingyu.framework.manager.AsyncManager;
import com.jingyu.framework.manager.factory.AsyncFactory;
import com.jingyu.framework.qunfang.QFsecurity.pwd.PhoneNumberPwdAuthenticationToken;
import com.jingyu.framework.qunfang.QFsecurity.sms.SmsCodeAuthenticationToken;
import com.jingyu.framework.security.context.AuthenticationContextHolder;
import com.jingyu.framework.web.service.TokenService;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.domain.CommonLoginUser;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.domain.CommonUserLoginBody;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import com.jingyu.qunfangqunzhi.util.CountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CommonUserLoginService {
    @Autowired
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private ICommonUsersService commonUsersService;

    @Autowired
    private TokenService tokenService;

    public String login(CommonUserLoginBody loginBody){
        String loginType = loginBody.getLoginType();
        String phoneNumber = loginBody.getPhoneNumber();
        CountUtil.validPhoneNumber(phoneNumber);
        System.out.println(loginBody);
        // 用户验证
        Authentication authentication = null;
        //短信验证码方式
        if(CommonUserConstants.LoginType.SMSCODE.name().equals(loginType)){
            Object cacheObject = redisCache.getCacheObject(QFConstants.CODE_CACHE_PREFIX + phoneNumber);
            if(StringUtils.isNull(cacheObject)){
                return "验证码已过期!";
            }
            String trueSMSCode = (String) cacheObject;
            String SMSCode = loginBody.getSMSCode();
            CountUtil.validSMSCode(SMSCode);
            if(!trueSMSCode.equals(SMSCode)){
                return "验证码不匹配!";
            }
            redisCache.deleteObject(QFConstants.CODE_CACHE_PREFIX + phoneNumber);
            SmsCodeAuthenticationToken smsCodeAuthenticationToken = new SmsCodeAuthenticationToken(phoneNumber);
            authentication = authenticationManager.authenticate(smsCodeAuthenticationToken);
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNumber, Constants.LOGIN_SUCCESS, MessageUtils.message("qunfangqunzhi.user.login.success")));
            CommonLoginUser commonLoginUser = (CommonLoginUser)authentication.getPrincipal();
            recordLoginInfo(commonLoginUser.getUserId());
            return tokenService.createToken(commonLoginUser);
        }else
        //手机号+密码验证
        {
            try
            {
                String password = loginBody.getPassword();
                PhoneNumberPwdAuthenticationToken authenticationToken = new PhoneNumberPwdAuthenticationToken(phoneNumber, password);
                AuthenticationContextHolder.setContext(authenticationToken);
                // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
                authentication = authenticationManager.authenticate(authenticationToken);
            }
            catch (Exception e)
            {
                if (e instanceof BadCredentialsException)
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNumber, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                    throw new UserPasswordNotMatchException();
                }
                else
                {
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNumber, Constants.LOGIN_FAIL, e.getMessage()));
                    throw new ServiceException(e.getMessage());
                }
            }
            finally
            {
                AuthenticationContextHolder.clearContext();
            }
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(phoneNumber, Constants.LOGIN_SUCCESS, MessageUtils.message("qunfangqunzhi.user.login.success")));
            CommonLoginUser commonLoginUser = (CommonLoginUser)authentication.getPrincipal();
            recordLoginInfo(commonLoginUser.getUserId());
            return tokenService.createToken(commonLoginUser);
        }

    }


    public void recordLoginInfo(Long userId)
    {
        CommonUser commonUser = new CommonUser();
        commonUser.setUserId(userId);
        commonUser.setLoginIp(IpUtils.getIpAddr());
        commonUser.setLoginDate(DateUtils.getNowDate());
        commonUsersService.updateCommonUsers(commonUser);
    }
}
