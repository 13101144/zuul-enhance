package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.FilterInfo;

import java.util.List;

public interface FilterInfoService extends IService<FilterInfo> {

    List<FilterInfo> listActive();
}
