package com.github.zuul.enhance.domain;

import java.util.List;

public class IpListConfig implements Config {

    private String name;

    private List<String> config;

    public IpListConfig(){}

    public IpListConfig(String name, List<String> config) {
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

    public void setIpList(List<String> config) {
        this.config = config;
    }
}
