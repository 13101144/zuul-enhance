package com.github.zuul.enhance.domain;

import java.util.List;
import java.util.Map;

public class FilterConfig implements Config{

    private String name;

    private Map<String,Boolean> config;

    public FilterConfig(){}

    public FilterConfig(String name, Map<String,Boolean> config) {
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

    public void setFilterList(Map<String,Boolean> config) {
        this.config = config;
    }
}
