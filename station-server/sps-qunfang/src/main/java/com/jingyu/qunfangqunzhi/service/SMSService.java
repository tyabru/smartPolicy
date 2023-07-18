package com.jingyu.qunfangqunzhi.service;


import com.jingyu.common.core.redis.RedisCache;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.qunfangqunzhi.constant.QFConstants;
import com.jingyu.qunfangqunzhi.util.CountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class SMSService {
    /**
     *
     * @param phoneNumber
     * @return 返回短信验证码
     */
    @Autowired
    private RedisCache redisCache;
    public String generateSMSCaptcha(String phoneNumber){
        CountUtil.validPhoneNumber(phoneNumber);
        Object cachePhoneNumber = redisCache.getCacheObject(QFConstants.CODE_CACHE_TIME_LIMIT_PREFIX+ phoneNumber);
        if(StringUtils.isNotNull(cachePhoneNumber)){
            throw new RuntimeException("请求短信验证码频繁");
        }
        String SMSCode = CountUtil.generateSMSCaptcha(phoneNumber);
        //设置时间校验缓存1min
        redisCache.setCacheObject(QFConstants.CODE_CACHE_TIME_LIMIT_PREFIX+phoneNumber, SMSCode, QFConstants.CODE_TIME_LIMIT, TimeUnit.SECONDS);
        //设置短信验证码有效期5min
        redisCache.setCacheObject(QFConstants.CODE_CACHE_PREFIX+phoneNumber, SMSCode, QFConstants.CODE_CONTENT_TIME_LIMIT, TimeUnit.SECONDS);
        return SMSCode;
    }
}
