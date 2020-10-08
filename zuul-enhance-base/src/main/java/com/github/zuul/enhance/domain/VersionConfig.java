package com.github.zuul.enhance.domain;

public class VersionConfig implements Config{

    private String name;

    private Integer config;

    public VersionConfig(){

    }

    public VersionConfig(String name, Integer config) {
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

    public void setConfig(Integer config) {
        this.config = config;
    }
}
