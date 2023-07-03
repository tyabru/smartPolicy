package com.jingyu.qunfangqunzhi.constant;

/**
 * 普通用户常量类
 */
public class CommonUserConstants {

    public enum CommonUserType{
        //普通用户
        COMMON_PERSON,
        //网格员
        WANGGEYUAN,
    }

    public enum CommonUserStatus{
        NORMAL("0"),
        DISABLE("1"),
        DELETED("2");

        private final String value;
        CommonUserStatus(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }

    }

    public enum LoginType{
        PASSWORD,
        SMSCODE,
    }

    public static final String REGISTER = "普通用户注册";
}
