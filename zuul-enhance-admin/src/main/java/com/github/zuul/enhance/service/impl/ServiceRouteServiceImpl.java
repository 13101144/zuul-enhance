package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.ServiceRoute;
import com.github.zuul.enhance.mapper.ServiceRouteMapper;
import com.github.zuul.enhance.service.ServiceRouteService;
import org.springframework.stereotype.Service;

@Service
public class ServiceRouteServiceImpl extends ServiceImpl<ServiceRouteMapper, ServiceRoute> implements ServiceRouteService {

}
