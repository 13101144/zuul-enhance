package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.zuul.enhance.entity.Statistic;
import com.github.zuul.enhance.mapper.StatisticMapper;
import com.github.zuul.enhance.service.StatisticService;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl extends ServiceImpl<StatisticMapper, Statistic> implements StatisticService {

}
