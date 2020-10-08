package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.WhiteList;

import java.util.List;

public interface WhiteListService extends IService<WhiteList> {

    List<WhiteList> listActive();
}
