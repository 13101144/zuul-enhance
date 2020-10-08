package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.WhiteList;
import com.github.zuul.enhance.mapper.WhiteListMapper;
import com.github.zuul.enhance.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WhiteListServiceImpl extends ServiceImpl<WhiteListMapper, WhiteList> implements WhiteListService {

    @Autowired
    private WhiteListMapper whiteListMapper;

    @Override
    public List<WhiteList> listActive() {
        return whiteListMapper.listActive();
    }
}
