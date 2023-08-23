package com.jingyu.framework.qunfang.service;


import com.jingyu.common.core.redis.RedisCache;
import com.jingyu.common.utils.MessageUtils;
import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.framework.manager.AsyncManager;
import com.jingyu.framework.manager.factory.AsyncFactory;
import com.jingyu.qunfangqunzhi.constant.CommonUserConstants;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.domain.CommonUser;
import com.jingyu.qunfangqunzhi.domain.CommonUserRegisterBody;
import com.jingyu.qunfangqunzhi.service.ICommonUsersService;
import com.jingyu.qunfangqunzhi.util.CountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class CommonUserRegisterService {

    private static final Logger log = LoggerFactory.getLogger(CommonUserRegisterService.class);


    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ICommonUsersService usersService;

    /**
     * 注册
     */
    public String register(CommonUserRegisterBody registerBody)
    {
        String msg = "";
        String username = registerBody.getUsername();
        String phoneNumber = registerBody.getPhoneNumber();
        String password = registerBody.getPassword();
        String SMSCode = registerBody.getSMSCode();
        System.out.println(SMSCode+"验证码");

        CountUtil.validPhoneNumber(phoneNumber);
        CountUtil.validSMSCode(SMSCode);
        CountUtil.validPassWord(password);
        Object cacheObject = redisCache.getCacheObject(QFConstants.CODE_CACHE_PREFIX + phoneNumber);
        if(StringUtils.isNull(cacheObject)){
            return "验证码已过期";
        }
        String trueSMSCode = (String) cacheObject;
        if(!trueSMSCode.equals(registerBody.getSMSCode())){
            return "请输入正确的验证码";
        }
        if(StringUtils.isNotNull(usersService.selectCommonUserByPhoneNumber(phoneNumber))){
            return "该手机号已被注册";
        }
        CommonUser commonUser = new CommonUser();
        commonUser.setUserType(CommonUserConstants.CommonUserType.COMMON_PERSON.name());
        commonUser.setCreateTime(new Date());
        commonUser.setUserName(phoneNumber);
        commonUser.setDelFlag(CommonUserConstants.CommonUserStatus.NORMAL.getValue());
        commonUser.setPassword(SecurityUtils.encryptPassword(password));
        commonUser.setPhonenumber(phoneNumber);
        try {
            usersService.insertCommonUsers(commonUser);
        }catch (Exception e) {
            log.info(e.getMessage());
            return "注册过程出错";
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, CommonUserConstants.REGISTER, MessageUtils.message("qunfangqunzhi.user.register.success")));
        return msg;
    }

}
