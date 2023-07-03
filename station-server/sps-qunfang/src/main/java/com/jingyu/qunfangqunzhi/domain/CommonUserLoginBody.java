package com.jingyu.qunfangqunzhi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 移动端普通用户登录信息
 */
public class CommonUserLoginBody {

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码唯一标识
     */
    private String uuid;

    /**
     * 短信验证码
     */
    @JsonProperty("SMSCode")
    private String SMSCode;


    /**
     * 登录方式
     */
    private String loginType;


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSMSCode() {
        return SMSCode;
    }

    @JsonProperty("SMSCode")
    public void setSMSCode(String SMSCode) {
        this.SMSCode = SMSCode;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public String toString() {
        return "CommonUserLoginBody{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                ", SMSCode='" + SMSCode + '\'' +
                ", loginType='" + loginType + '\'' +
                '}';
    }
}
