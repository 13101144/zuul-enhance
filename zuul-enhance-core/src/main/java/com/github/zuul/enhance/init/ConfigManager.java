package com.github.zuul.enhance.init;

import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.Config;
import com.github.zuul.enhance.domain.Configs;
import com.github.zuul.enhance.service.BlackListService;
import com.github.zuul.enhance.service.RateLimitService;
import com.github.zuul.enhance.service.WhiteListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConfigManager {

    private Configs lastConfigs;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private WhiteListService whiteListService;

    public synchronized void apply(Configs configs) {
        if (configs == null) {
            return;
        }

        if (lastConfigs == null) {
            lastConfigs = configs;
            doApply(configs);
            return;

        }

        Config lastVersionConfig = lastConfigs.getConfig("version");
        Config versionConfig = configs.getConfig("version");
        int lastVersion = (Integer) lastVersionConfig.getConfig();
        int version = (Integer) versionConfig.getConfig();

        if (version <= lastVersion) {
            return;
        }
    }

    private synchronized void doApply(Configs configs) {
        blackListService.refresh(configs);
        whiteListService.refresh(configs);
    }
}
