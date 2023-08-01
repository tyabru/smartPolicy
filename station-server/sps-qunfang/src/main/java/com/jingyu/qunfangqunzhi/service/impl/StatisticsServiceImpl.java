package com.jingyu.qunfangqunzhi.service.impl;

import com.jingyu.common.annotation.DataScope;
import com.jingyu.common.utils.StringUtils;
import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.domain.StatisticsParams;
import com.jingyu.qunfangqunzhi.mapper.StatisticsMapper;
import com.jingyu.qunfangqunzhi.service.IStatisticsService;
import com.jingyu.qunfangqunzhi.util.QFDateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticsServiceImpl implements IStatisticsService {


    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    @DataScope(deptAlias = "d")
    public List<StatisticsEntity> countTimesOfGridStuff(StatisticsParams params) {
        System.out.println(params.getParams());
        handleParams(params.getParams());
        return statisticsMapper.countTimesOfGridStuff(params);
    }

    @Override
    @DataScope(deptAlias = "d")
    public List<StatisticsEntity> countTimesOfGridStuffLeader(StatisticsParams params) {
        handleParams(params.getParams());
        return statisticsMapper.countTimesOfGridStuffLeader(params);
    }

    @Override
    @DataScope(deptAlias = "a")
    public List<StatisticsEntity> countTimesOfCommunity(StatisticsParams params) {
        handleParams(params.getParams());
        return statisticsMapper.countTimesOfCommunity(params);
    }

    @Override
    @DataScope(deptAlias = "a")
    public List<StatisticsEntity> countTimesOfPeople(StatisticsParams params) {
        handleParams(params.getParams());
        return statisticsMapper.countTimesOfPeople(params);
    }




    @Override
    @DataScope(deptAlias = "a")
    public List<StatisticsEntity> countNumberOfUnconfirmedEvents(StatisticsParams params) {
        return statisticsMapper.countNumberOfUnconfirmedEvents(params);
    }

    /**
     * 根据参数统一处理params参数
     */
    public void handleParams(Map<String, Object> params){
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
