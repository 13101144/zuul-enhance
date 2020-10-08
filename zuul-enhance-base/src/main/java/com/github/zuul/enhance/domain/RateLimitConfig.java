package com.github.zuul.enhance.domain;

import java.util.List;
import java.util.Map;

public class RateLimitConfig implements Config{

    private String name;

    private Map<String,Integer> config;

    public RateLimitConfig(){}

    public RateLimitConfig(String name, Map<String,Integer> config) {
        this.name = name;
        this.config = config;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getConfig() {
        return config;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRateLimitMap(Map<String, Integer> config) {
        this.config = config;
    }
}
