package com.jingyu.safecheck.utils;

import com.jingyu.common.utils.SecurityUtils;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeRecord;
import com.jingyu.safecheck.domain.CheckRectifyNoticeDistributeReview;
import com.jingyu.safecheck.mapper.CheckRectifyNoticeDistributeRecordMapper;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeRecordService;
import com.jingyu.safecheck.service.ICheckRectifyNoticeDistributeReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    private ICheckRectifyNoticeDistributeReviewService checkRectifyNoticeDistributeReviewService;
    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() throws Exception {
        List<CheckRectifyNoticeDistributeReview> list = checkRectifyNoticeDistributeReviewService.selectCheckRectifyNoticeDistributeReviewList(new CheckRectifyNoticeDistributeReview());

        // 获取当前时间
        Date date1 = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date1);
        // 把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(Calendar.DATE, 1);
        // 这个时间就是日期往后推一天的结果
        Date date2 = calendar.getTime();

        for(int i=0;i<list.size();i++){
            if((date2.compareTo(list.get(i).getFinishDate())>=0)&&(list.get(i).getNoticeStatus().equals("0"))&&(list.get(i).getFinishStatus().equals("1"))){
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
                checkRectifyNoticeDistributeReviewService.updateCheckRectifyNoticeDistributeReview(list.get(i));
//                checkRectifyNoticeDistributeRecordMapper.updateCheckRectifyNoticeDistributeRecord(list.get(i));
            }
            if((date1.compareTo(list.get(i).getFinishDate())>=0)&&(list.get(i).getFinishStatus().equals("1"))){
                list.get(i).setFinishStatus("3");
                checkRectifyNoticeDistributeReviewService.updateCheckRectifyNoticeDistributeReview(list.get(i));
            }
        }

//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}