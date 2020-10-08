package com.github.zuul.enhance.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ConfigDto {

    private List<String> filterList;

    private List<String> whiteList;

    private List<String> blackList;

    private Map<Integer, Integer> routeRateLimitMap;

    private long version;

}
