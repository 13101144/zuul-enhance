package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.BlackList;

import java.util.List;

public interface BlackListService extends IService<BlackList> {

    List<BlackList> listActive();
}
