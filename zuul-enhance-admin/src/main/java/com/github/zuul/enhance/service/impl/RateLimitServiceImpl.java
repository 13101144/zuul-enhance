package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.RateLimit;
import com.github.zuul.enhance.mapper.RateLimitMapper;
import com.github.zuul.enhance.service.RateLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RateLimitServiceImpl extends ServiceImpl<RateLimitMapper, RateLimit> implements RateLimitService {

    @Autowired
    private RateLimitMapper rateLimitMapper;

    @Override
    public List<RateLimit> listActive() {
        return rateLimitMapper.listActive();
    }

}
