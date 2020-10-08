package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.CountInfo;
import com.github.zuul.enhance.mapper.CountInfoMapper;
import com.github.zuul.enhance.service.CountInfoService;
import org.springframework.stereotype.Service;

@Service
public class CountInfoServiceImpl extends ServiceImpl<CountInfoMapper, CountInfo> implements CountInfoService {

}
