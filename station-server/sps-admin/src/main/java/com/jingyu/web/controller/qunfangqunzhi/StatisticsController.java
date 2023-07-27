package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/qf/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;


    @PostMapping("/gridStuff")
    public AjaxResult countTimesOfGridStuff(@RequestBody HashMap<String,Object> params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfGridStuff(params);
        return AjaxResult.success(statisticsEntities);
    }



    @PostMapping("/gridStuffLeader")
    public AjaxResult countTimesOfGridStuffLeader(@RequestBody HashMap<String,Object> params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfGridStuffLeader(params);
        return AjaxResult.success(statisticsEntities);
    }


    @PostMapping("/community")
    public AjaxResult countTimesOfCommunity(@RequestBody HashMap<String,Object> params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfCommunity(params);
        return AjaxResult.success(statisticsEntities);
    }


    @PostMapping("/people")
    public AjaxResult countTimesOfPeople(@RequestBody HashMap<String,Object> params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfPeople(params);
        return AjaxResult.success(statisticsEntities);
    }
}
