package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.zuul.enhance.entity.ConfigVersion;
import com.github.zuul.enhance.mapper.ConfigVersionMapper;
import com.github.zuul.enhance.service.ConfigVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigVersionServiceImpl extends ServiceImpl<ConfigVersionMapper, ConfigVersion> implements ConfigVersionService {

    @Autowired
    private ConfigVersionMapper configVersionMapper;

    @Override
    public Integer getMaxVersion() {
        return configVersionMapper.getMaxVersion();
    }
}
