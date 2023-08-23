package com.jingyu.qunfangqunzhi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QFDateUtil {
    /**
     * 获取n天前的的日期字符串
     * @param pastDays
     * @return
     */
    public  static String getDateStr(int pastDays){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - pastDays);
        Date beginDay = c.getTime();
        return sdf.format(beginDay);
    }
}
