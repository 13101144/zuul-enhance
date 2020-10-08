package com.github.zuul.enhance.api.v1.controller;


import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.*;
import com.github.zuul.enhance.dto.ConfigDto;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.*;
import com.github.zuul.enhance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class ConfigController {

    @Autowired
    private FilterInfoService filterInfoService;

    @Autowired
    private RateLimitService rateLimitService;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private WhiteListService whiteListService;

    @Autowired
    private ConfigVersionService configVersionService;

    @GetMapping("/configs")
    public ResponseEntity getAllConfig() {

//        ConfigDto configDto = new ConfigDto();
//        List<FilterInfo> filterList = filterInfoService.listActive();
//        List<RateLimit> rateLimitList = rateLimitService.listActive();
//        List<WhiteList> whiteLists = whiteListService.listActive();
//        List<BlackList> blackLists = blackListService.listActive();
//        Integer configVersion = configVersionService.getMaxVersion();
//
//        List<String> filterInfoList = new ArrayList<String>();
//        for (FilterInfo filterInfo : filterList) {
//            filterInfoList.add(filterInfo.getFilterName());
//        }
//        configDto.setFilterList(filterInfoList);
//
//        List<String> whiteIpList = new ArrayList<>();
//        for (WhiteList whiteList : whiteLists) {
//            whiteIpList.add(whiteList.getIpAddress());
//        }
//        configDto.setWhiteList(whiteIpList);
//
//        List<String> blackIpList = new ArrayList<>();
//        for (BlackList blackList : blackLists) {
//            blackIpList.add(blackList.getIpAddress());
//        }
//        configDto.setWhiteList(blackIpList);
//
//        HashMap<Integer, Integer> rateLimitMap = new HashMap<>();
//        for (RateLimit rateLimit : rateLimitList) {
//            rateLimitMap.put(rateLimit.getRouteId(), rateLimit.getRate());
//        }
//        configDto.setRouteRateLimitMap(rateLimitMap);
//
//        if (configVersion == null) {
//            configVersion = 0;
//        }
//        configDto.setVersion(configVersion);
//
        List<Config> configList = new ArrayList<>();
        List<FilterInfo> filterList = filterInfoService.list();
        List<RateLimit> rateLimitList = rateLimitService.listActive();
        List<WhiteList> whiteLists = whiteListService.listActive();
        List<BlackList> blackLists = blackListService.listActive();
        Integer configVersion = configVersionService.getMaxVersion();

        Map<String,Boolean> filterMap = new HashMap<String,Boolean>();
        for (FilterInfo filterInfo : filterList) {
            int status = filterInfo.getStatus();
            if (status == 1) {
                filterMap.put(filterInfo.getFilterName(), true);
            } else {
                filterMap.put(filterInfo.getFilterName(), false);
            }
        }

        FilterConfig filterConfig = new FilterConfig(NameConstant.FILTER_INFO, filterMap);
        configList.add(filterConfig);

        List<String> blackIpList = new ArrayList<String>();
        for (BlackList blackList : blackLists) {
            blackIpList.add(blackList.getIpAddress());
        }

        IpListConfig blackListConfig = new IpListConfig(NameConstant.BLACK_LIST, blackIpList);
        configList.add(blackListConfig);

        List<String> whiteIpList = new ArrayList<String>();
        for (WhiteList whiteList : whiteLists) {
            whiteIpList.add(whiteList.getIpAddress());
        }

        IpListConfig whiteListConfig = new IpListConfig(NameConstant.WHITE_LIST, whiteIpList);
        configList.add(whiteListConfig);

        HashMap<String, Integer> rateLimitMap = new HashMap<>();
        for (RateLimit rateLimit : rateLimitList) {
           rateLimitMap.put(rateLimit.getRouteId(), rateLimit.getRate());
        }

        RateLimitConfig rateLimitConfig = new RateLimitConfig(NameConstant.RATE_LIMIT, rateLimitMap);
        configList.add(rateLimitConfig);

        if (configVersion == null) {
            configVersion = 0;
        }

        VersionConfig versionConfig = new VersionConfig(NameConstant.CONFIG_VERSION,configVersion);
        configList.add(versionConfig);
        return ResponseEntity.okayWithData(configList);
    }
}
