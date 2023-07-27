package com.jingyu.qunfangqunzhi.mapper;

import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface StatisticsMapper {


    List<StatisticsEntity> countTimesOfGridStuff(@Param("params")HashMap<String,Object> params);


    List<StatisticsEntity> countTimesOfGridStuffLeader(@Param("params")HashMap<String,Object> params);


    List<StatisticsEntity> countTimesOfCommunity(@Param("params")HashMap<String,Object> params);


    List<StatisticsEntity> countTimesOfPeople(@Param("params")HashMap<String,Object> params);

}
