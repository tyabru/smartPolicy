package com.jingyu.qunfangqunzhi.util;

import cn.hutool.core.util.IdUtil;

public class MyIdUtil {
    public static Long getRandomId(){
        long id = IdUtil.getSnowflake().nextId();
        System.out.println("随机id"+id);
        return Long.valueOf(String.valueOf(id).substring(8));
    }
}
