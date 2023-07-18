package com.jingyu.qunfangqunzhi.util;

import com.jingyu.qunfangqunzhi.constant.QFConstants;

import java.util.regex.Pattern;


public class CountUtil {



    /**
     * 根据手机号生成短信验证码
     */
    public static String generateSMSCaptcha(String phoneNumber){
        return "123456";

    }
    /**
     * 检查手机号码格式是否合法
     */
    public static void validPhoneNumber(String phoneNumber){
        if ((phoneNumber != null) && (!phoneNumber.isEmpty())) {
            if(!Pattern.matches("^1[3-9]\\d{9}$", phoneNumber)){
                throw new RuntimeException(QFConstants.INVALID_PHONE_NUMBER);
            }
        }else{
            throw new RuntimeException(QFConstants.EMPTY_PHONE_NUMBER);
        }
    }

    /**
     * 检查验证码格式是否合法
     */
    public static void validSMSCode(String SMSCode){
        if ((SMSCode!= null) && (!SMSCode.isEmpty())) {
            if(!Pattern.matches("^\\d{6}$", SMSCode)){
                throw new RuntimeException(QFConstants.INVALID_SMS_CODE);
            }
        }else{
            throw new RuntimeException(QFConstants.EMPTY_SMS_CODE);
        }
    }
    /**
     * 验证密码是否符合规范
     */
    public static void validPassWord(String password){
        if (password.length()<=8 || password.length() >=20){
            throw new RuntimeException(QFConstants.BAD_PASSWORD);
        }
    }
}
