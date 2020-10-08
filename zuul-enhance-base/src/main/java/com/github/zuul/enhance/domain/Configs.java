package com.github.zuul.enhance.domain;

import com.github.zuul.enhance.dto.ConfigDto;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Configs {

    Map<String, Config> configMap = new HashMap<>();

    public Config getConfig(String name) {
        return configMap.get(name);
    }

    public void addConfig(String key, Config config) {
        configMap.put(key, config);
    }


}
