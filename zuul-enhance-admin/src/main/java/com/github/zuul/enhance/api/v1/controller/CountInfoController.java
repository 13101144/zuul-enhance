package com.github.zuul.enhance.api.v1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.zuul.enhance.dto.CountInfoDto;
import com.github.zuul.enhance.dto.IDS;
import com.github.zuul.enhance.dto.RateLimitDto;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.CountInfo;
import com.github.zuul.enhance.entity.RateLimit;
import com.github.zuul.enhance.service.RateLimitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class CountInfoController {

    private static final Logger log = LoggerFactory.getLogger(RateLimitController.class);

    @Autowired
    private RateLimitService rateLimitService;



}
