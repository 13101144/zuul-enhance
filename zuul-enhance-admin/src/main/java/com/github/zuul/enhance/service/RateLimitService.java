package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.RateLimit;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RateLimitService extends IService<RateLimit> {

    List<RateLimit> listActive();

}
