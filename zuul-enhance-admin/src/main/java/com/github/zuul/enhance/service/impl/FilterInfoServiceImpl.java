package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.FilterInfo;
import com.github.zuul.enhance.mapper.FilterInfoMapper;
import com.github.zuul.enhance.service.FilterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterInfoServiceImpl extends ServiceImpl<FilterInfoMapper, FilterInfo> implements FilterInfoService {

    @Autowired
    private FilterInfoMapper filterInfoMapper;

    @Override
    public List<FilterInfo> listActive() {
        return filterInfoMapper.listActive();
    }
}
