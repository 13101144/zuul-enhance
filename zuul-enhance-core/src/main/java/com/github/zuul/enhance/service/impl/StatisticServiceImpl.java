package com.github.zuul.enhance.service.impl;

import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.Config;
import com.github.zuul.enhance.domain.Configs;
import com.github.zuul.enhance.listener.RefreshListner;
import com.github.zuul.enhance.name.Named;
import com.github.zuul.enhance.service.StatisticService;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.LongAdder;

@Component
public class StatisticServiceImpl implements StatisticService, RefreshListner, Named {

    private Map<String, LongAdder> map = Maps.newConcurrentMap();

    private AtomicBoolean statisticEnabled = new AtomicBoolean(false);

    @Override
    public void increment(String counterName) {

        LongAdder longAdder = map.get(counterName);

        if (longAdder == null) {
            map.putIfAbsent(counterName, new LongAdder());
            longAdder = map.get(counterName);
        }

        longAdder.increment();

    }

    @Override
    public long getCount(String counterName) {
        LongAdder longAdder = map.get(counterName);
        return longAdder == null ? 0 : longAdder.sum();
    }

    @Override
    public boolean statisticEnabled() {
        return statisticEnabled.get();
    }

    public void setStatisticEnabled(boolean statisticEnabled) {
        this.statisticEnabled.getAndSet(statisticEnabled);
    }

    @Override
    public void refresh(Configs configs) {
        Config config = configs.getConfig(name());
        Boolean statisticEnabled = (Boolean)config.getConfig();
        boolean oldValue = this.statisticEnabled.get();
        this.statisticEnabled.compareAndSet(oldValue,statisticEnabled);
    }

    @Override
    public String name() {
        return NameConstant.STATISTIC;
    }
}
