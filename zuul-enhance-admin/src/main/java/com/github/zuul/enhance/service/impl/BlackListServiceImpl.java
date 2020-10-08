package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.github.zuul.enhance.entity.BlackList;
import com.github.zuul.enhance.mapper.BlackListMapper;
import com.github.zuul.enhance.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListServiceImpl extends ServiceImpl<BlackListMapper, BlackList> implements BlackListService {

    @Autowired
    private BlackListMapper filterInfoMapper;

    @Override
    public List<BlackList> listActive() {
        return filterInfoMapper.listActive();
    }
}
