package com.jingyu.qunfangqunzhi.service;

import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;


import java.util.HashMap;
import java.util.List;

public interface IStatisticsService {


    List<StatisticsEntity> countTimesOfGridStuff(HashMap<String,Object> params);

    List<StatisticsEntity> countTimesOfGridStuffLeader(HashMap<String,Object> params);

    List<StatisticsEntity> countTimesOfCommunity(HashMap<String,Object> params);

    List<StatisticsEntity> countTimesOfPeople(HashMap<String,Object> params);
}
