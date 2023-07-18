package com.jingyu.safecheck.utils;

import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;
import com.jingyu.safecheck.mapper.CheckRectifyNoticeDistributeRecordMapper;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    private ICheckRectifyNoticeDistributeRecordService checkRectifyNoticeDistributeRecordService;
    private CheckRectifyNoticeDistributeRecord checkRectifyNoticeDistributeRecord;

    private CheckRectifyNoticeDistributeRecordMapper checkRectifyNoticeDistributeRecordMapper;
    //3.添加定时任务
    @Scheduled(cron = "* * * * 1 ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws Exception {
        List<CheckRectifyNoticeDistributeRecord> list = checkRectifyNoticeDistributeRecordService.selectCheckRectifyNoticeDistributeRecordList(checkRectifyNoticeDistributeRecord);

        // 获取当前时间
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        date = calendar.getTime();

        for(int i=0;i<list.size();i++){
            if((date.compareTo(list.get(i).getCheckDate())>=0)&&(list.get(i).getNoticeStatus().equals("0"))){
                System.out.println(list.get(i).getPhoneNumber());
//                List<String> mobiles = new ArrayList<>();
//                mobiles.add("15*******13");
//                String s= getSplitString(mobiles);
//                调用阿里云短信接口
                AliCloudSendSmsUtil.sendSMS(list.get(i).getPhoneNumber().toString(), list.get(i).getCheckedUnitDirector());
//                调用华为云短信接口
//                HuaWeiCloudSendSms.sendSms((list.get(i).getPhoneNumber().toString()));
                list.get(i).setNoticeStatus("1");
//                System.out.println(list.get(i));
                checkRectifyNoticeDistributeRecordService.updateCheckRectifyNoticeDistributeRecord(list.get(i));
//                checkRectifyNoticeDistributeRecordMapper.updateCheckRectifyNoticeDistributeRecord(list.get(i));

            }
        }

//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}