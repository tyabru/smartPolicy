package com.jingyu.qunfangqunzhi.constant;


/**
 * 群防群治系统常量类
 */
public class QFConstants {

    /**
     * 检验手机号格式失败错误信息
     */
    public static final String INVALID_PHONE_NUMBER = "手机号格式异常，请检查！";


    /**
     * 手机号为空错误信息
     */
    public static final String EMPTY_PHONE_NUMBER = "手机号格式异常，请检查！";

    /**
     * 检验短信验证码格式错误信息
     */
    public static final String INVALID_SMS_CODE = "验证码格式异常，请检查！";

    /**
     * 检验短信验证码为空
     */
    public static final String EMPTY_SMS_CODE = "验证码为空，请检查！";

    /**
     * 密码不符合规范
     */
    public static final String BAD_PASSWORD = "密码格式不符合规范！";


    /**
     * 返回验证码map的key值
     */
    public static  final String SMS_KEY = "SMSCode";

    /**
     * 注册验证码redis缓存前缀,用于验证是否重复请求
     */
    public  static final  String CODE_CACHE_TIME_LIMIT_PREFIX = "SMSTimeCache";

    /**
     * 注册验证码redis缓存前缀,用于保存验证码内容
     */
    public  static final  String CODE_CACHE_PREFIX = "SMSContentCache";

    /**
     * 重复提交缓存请求时间 60s
     */
    public static final int CODE_TIME_LIMIT = 60;

    /**
     * 短信内容有效期 300s
     */
    public static final int CODE_CONTENT_TIME_LIMIT = 60*5;

    /**
     * 事件下发人员类型
     */
    public enum AllocateUserType{
        SYSTEM_USER("管理员"),
        COMMON_USER("网格员长");
        private final String value;
        AllocateUserType(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
    }

    /**
     *  下发事件处置状态
     */
    public enum AllocatedEventStatus{
        UNCONFIRMED("0"),
        CONFIRMED("1"),
        CANCELED("2"),
        COMPLETED("3");
        private final String value;
        AllocatedEventStatus(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
    }

    /**
     *  事件处置状态
     */
    public enum EventStatus{
        UNCONFIRMED("0"),
        CONFIRMED("1"),
        COMPLETED("2");
        private final String value;
        EventStatus(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
    }

    /**
     *  事件处置类型,是否为处置
     */
    public enum EventDealType{
        DEAL("1"),
        ALLOCATE("0");
        private final String value;
        EventDealType(String value){
            this.value = value;
        }
        public String getValue(){
            return this.value;
        }
    }
}
