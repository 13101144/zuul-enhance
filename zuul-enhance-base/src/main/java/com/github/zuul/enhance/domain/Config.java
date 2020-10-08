package com.github.zuul.enhance.domain;

import lombok.Data;

import java.util.Map;


public interface Config {

    String getName();

    Object getConfig();

}
