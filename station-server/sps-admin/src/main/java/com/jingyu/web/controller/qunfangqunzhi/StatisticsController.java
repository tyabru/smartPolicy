package com.jingyu.web.controller.qunfangqunzhi;

import com.jingyu.common.core.domain.AjaxResult;
import com.jingyu.qunfangqunzhi.domain.StatisticsEntity;
import com.jingyu.qunfangqunzhi.domain.StatisticsParams;
import com.jingyu.qunfangqunzhi.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/qf/statistics")
public class StatisticsController {

    @Autowired
    private IStatisticsService statisticsService;


    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:statistics')")
    @PostMapping("/gridStuff")
    public AjaxResult countTimesOfGridStuff(@RequestBody StatisticsParams params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfGridStuff(params);
        return AjaxResult.success(statisticsEntities);
    }


    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:statistics')")
    @PostMapping("/gridStuffLeader")
    public AjaxResult countTimesOfGridStuffLeader(@RequestBody StatisticsParams params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfGridStuffLeader(params);
        return AjaxResult.success(statisticsEntities);
    }

    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:statistics')")
    @PostMapping("/community")
    public AjaxResult countTimesOfCommunity(@RequestBody StatisticsParams params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfCommunity(params);
        return AjaxResult.success(statisticsEntities);
    }

    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:statistics')")
    @PostMapping("/people")
    public AjaxResult countTimesOfPeople(@RequestBody StatisticsParams params){
        List<StatisticsEntity> statisticsEntities = statisticsService.countTimesOfPeople(params);
        return AjaxResult.success(statisticsEntities);
    }

    @PreAuthorize("@ss.hasPermi('qunfangqunzhi:statistics')")
    @GetMapping("/unconfirmedEvents")
    public AjaxResult countTimesOfPeople(){
        List<StatisticsEntity> statisticsEntities = statisticsService.countNumberOfUnconfirmedEvents(new StatisticsParams());
        return AjaxResult.success(statisticsEntities);
    }
}
