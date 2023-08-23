package com.jingyu.qunfangqunzhi.mapper;

import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.domain.StatisticsParams;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface StatisticsMapper {


    List<StatisticsEntity> countTimesOfGridStuff(StatisticsParams params);


    List<StatisticsEntity> countTimesOfGridStuffLeader(StatisticsParams params);


    List<StatisticsEntity> countTimesOfCommunity(StatisticsParams params);


    List<StatisticsEntity> countTimesOfPeople(StatisticsParams params);


    List<StatisticsEntity> countNumberOfUnconfirmedEvents(StatisticsParams params);

}
