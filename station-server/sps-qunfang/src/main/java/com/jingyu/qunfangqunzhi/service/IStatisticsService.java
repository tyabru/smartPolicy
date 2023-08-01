package com.jingyu.qunfangqunzhi.service;

import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.domain.StatisticsParams;


import java.util.HashMap;
import java.util.List;

public interface IStatisticsService {


    List<StatisticsEntity> countTimesOfGridStuff(StatisticsParams params);

    List<StatisticsEntity> countTimesOfGridStuffLeader(StatisticsParams params);

    List<StatisticsEntity> countTimesOfCommunity(StatisticsParams params);

    List<StatisticsEntity> countTimesOfPeople(StatisticsParams params);

    List<StatisticsEntity> countNumberOfUnconfirmedEvents(StatisticsParams params);
}
