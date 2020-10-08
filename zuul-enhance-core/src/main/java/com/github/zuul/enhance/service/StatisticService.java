package com.github.zuul.enhance.service;

public interface StatisticService {

    void increment(String counterName);

    long getCount(String counterName);

    boolean statisticEnabled();

}
