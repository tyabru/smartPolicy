package com.jingyu.common.utils;

/*
* 字符串工具类
* */
public class StringReplaceUtil {

    /*
    * 敏感信息加密
    */
    public static String mgxxJM(String str) {
        String idCard = "";
        if (str == null || str =="") {
            idCard = "";
        } else {
            if (str.length() > 4) {
                StringBuilder builder = new StringBuilder(str);
                if (str.length()>17){
                    idCard = builder.replace(2, str.length() - 2, "**************").toString();
                } else {
                    idCard = builder.replace(2, str.length() - 2, "*******").toString();
                }
            } else {
                return str;
            }

        }
        return idCard;
    }

    /*
     * 计算出生日期
     */
    public static String idCardToBirthday(String idCard) {
        String birthday = idCard.substring(6,10) + "-" + idCard.substring(10,12) + "-" + idCard.substring(12,14);
        return birthday;
    }
}
