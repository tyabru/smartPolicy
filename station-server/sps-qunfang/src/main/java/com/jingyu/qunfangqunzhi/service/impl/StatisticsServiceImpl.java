package com.jingyu.qunfangqunzhi.service.impl;

import com.jingyu.common.utils.StringUtils;
import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.mapper.StatisticsMapper;
import com.jingyu.qunfangqunzhi.service.IStatisticsService;
import com.jingyu.qunfangqunzhi.util.QFDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class StatisticsServiceImpl implements IStatisticsService {


    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public List<StatisticsEntity> countTimesOfGridStuff(HashMap<String, Object> params) {
        handleParams(params);
        return statisticsMapper.countTimesOfGridStuff(params);
    }

    @Override
    public List<StatisticsEntity> countTimesOfGridStuffLeader(HashMap<String, Object> params) {
        handleParams(params);
        return statisticsMapper.countTimesOfGridStuffLeader(params);
    }

    @Override
    public List<StatisticsEntity> countTimesOfCommunity(HashMap<String, Object> params) {
        handleParams(params);
        return statisticsMapper.countTimesOfCommunity(params);
    }

    @Override
    public List<StatisticsEntity> countTimesOfPeople(HashMap<String, Object> params) {
        handleParams(params);
        return statisticsMapper.countTimesOfPeople(params);
    }


    /**
     * 根据参数统一处理params参数
     */
    public void handleParams(HashMap<String, Object> params){
        Integer tag = (Integer) params.get("tag");
        if(StringUtils.isNull(tag)){
            return;
        }
        if(tag == 4){
            return;
        }
        if(tag == 0 ){
            String beginTime = QFDateUtil.getDateStr(7);
            String endTime = QFDateUtil.getDateStr(0);
            params.put("beginTime",beginTime);
            params.put("endTime", endTime);
        }else if(tag == 1){
            String beginTime = QFDateUtil.getDateStr(30);
            String endTime = QFDateUtil.getDateStr(0);
            params.put("beginTime",beginTime);
            params.put("endTime", endTime);
        }else if(tag == 2){
            String beginTime = QFDateUtil.getDateStr(90);
            String endTime = QFDateUtil.getDateStr(0);
            params.put("beginTime",beginTime);
            params.put("endTime", endTime);
        }else if(tag == 3){
            String beginTime = QFDateUtil.getDateStr(365);
            String endTime = QFDateUtil.getDateStr(0);
            params.put("beginTime",beginTime);
            params.put("endTime", endTime);
        }
    }
}
