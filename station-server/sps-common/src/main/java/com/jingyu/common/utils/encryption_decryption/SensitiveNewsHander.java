package com.jingyu.common.utils.encryption_decryption;

import com.jingyu.common.utils.StringReplaceUtil;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.common.utils.sign.AESUtil;
import com.jingyu.common.utils.spring.SpringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SensitiveNewsHander {

    public static <T> T parseRequestParams(T params) {
        SwitchChange switchChange = SpringUtils.getBean(SwitchChange.class);
        Class<?> cls = params.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                SensitiveNew deviceAttr = field.getAnnotation(SensitiveNew.class);
                if(deviceAttr != null) {
                    String getterDncrypt = deviceAttr.getterDncrypt();
                    String setterEncrypt = deviceAttr.setterEncrypt();
                    Method getter = cls.getDeclaredMethod(getterDncrypt);
                    Method setter = cls.getDeclaredMethod(setterEncrypt, String.class);
                    String value = (String)(getter.invoke(params));
                    if(true) {
                        value = AESUtil.decrypt(value);
                    }
                    if (switchChange.getSwitchChange()) {
                        String encryptMethodName = deviceAttr.EncryptMethod();
                        String resultValue = encryptMethod(encryptMethodName, value);
                        setter.invoke(params, resultValue);
                    } else {
                        setter.invoke(params, deviceAttr.notEncryptByResponse()? value: AESUtil.encrypt(value));
                    }
                }
            }
            return params;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("参数获取失败！"+ e.getMessage() + "&&& data: " + params);
        }
    }

    public static <T> T revertNotEditAttrs(T source, T old) {
        Class<?> cls = source.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                SensitiveNew deviceAttr = field.getAnnotation(SensitiveNew.class);
                if(deviceAttr != null) {
                    String getterDncrypt = deviceAttr.getterDncrypt();
                    String setterEncrypt = deviceAttr.setterEncrypt();
                    Method getter = cls.getDeclaredMethod(getterDncrypt);
                    Method setter = cls.getDeclaredMethod(setterEncrypt, String.class);
                    String newValue = (String)(getter.invoke(source));
                    String oldValue = (String)(getter.invoke(old));
                    if(newValue != null && newValue.contains("*") && StringUtils.isNotEmpty(oldValue)) {
                        setter.invoke(source, oldValue);
                    }
                }
            }
            return source;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("参数获取失败！"+ e.getMessage() + "&&& data: " + source);
        }
    }

    public static <T> T revertEncryptAttrs(T source) {
        Class<?> cls = source.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field field : fields) {
                SensitiveNew deviceAttr = field.getAnnotation(SensitiveNew.class);
                if(deviceAttr != null) {
                    String getterDncrypt = deviceAttr.getterDncrypt();
                    String setterEncrypt = deviceAttr.setterEncrypt();
                    Method getter = cls.getDeclaredMethod(getterDncrypt);
                    Method setter = cls.getDeclaredMethod(setterEncrypt, String.class);
                    if (deviceAttr.neeEecrypt()) {
                        setter.invoke(source, AESUtil.encrypt((String) getter.invoke(source)));
                    }
                }
            }
            return source;
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("参数获取失败！"+ e.getMessage() + "&&& data: " + source);
        }
    }

    public static String encryptMethod(String MethodName, String value) {
        if(StringUtils.isNotEmpty(MethodName)) {
            try {
                SensitiveNewsHander instance = new SensitiveNewsHander();
                Method jm = SensitiveNewsHander.class.getDeclaredMethod(MethodName, String.class);
                return (String)jm.invoke(instance, value);
            } catch (NoSuchMethodException | InvocationTargetException  | IllegalAccessException e) {
                e.printStackTrace();
                return StringReplaceUtil.mgxxJM(value);
            }
        }
        return StringReplaceUtil.mgxxJM(value);
    }

    public String nameEncrypt(String value) {
        int length = StringUtils.isEmpty(value)? 0 :value.length();
        if(StringUtils.isEmpty(value) || length == 1) {
            return value;
        } else if (length == 2) {
            return value.charAt(0) + "*";
        } else if (length == 3) {
            return value.charAt(0) + "*" + value.charAt(length-1);
        } else if (length <= 5) {
            return value.substring(0, 2) + "**";
        } else {
            String replaceStr = value.substring(2, length-2);
            return value.replace(replaceStr, "***");
        }
    }

}
