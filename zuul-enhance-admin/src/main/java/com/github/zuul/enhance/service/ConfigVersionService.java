package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.ConfigVersion;

public interface ConfigVersionService extends IService<ConfigVersion> {

    Integer getMaxVersion();
}
