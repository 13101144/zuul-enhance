package com.github.zuul.enhance.filter.log;


import cn.hutool.core.date.DateUtil;
import com.github.zuul.enhance.service.StatisticService;
import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class StatisticFilter extends ZuulFilter {

    private final String dateFormat = "yyyy-MM-dd HH";
    private static final String STATISTICS_KEY = "zuul.STATISTICS";

    private StatisticService statisticService;

    public StatisticFilter(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return statisticService.statisticEnabled();
    }

    @Override
    public Object run() {
        String date = DateUtil.format(new Date(), dateFormat);
        String key = date + STATISTICS_KEY;
        statisticService.increment(key);
        return null;
    }
}
