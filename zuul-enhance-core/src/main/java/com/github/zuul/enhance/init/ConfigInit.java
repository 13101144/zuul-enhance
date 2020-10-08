package com.github.zuul.enhance.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.*;
import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.RateLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(value = 10)
public class ConfigInit implements ApplicationRunner
{
    @Value("${app.gateway.name}")
    private String name;

    @Value("${app.gateway.version}")
    private String version;

    @Value("${app.gateway.adminUrl}")
    private String adminUrl;

    @Autowired
    ConfigManager configManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 1. 拉取配置
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity responseEntity = restTemplate.getForObject(adminUrl+"v1/configs", ResponseEntity.class);
        // ConfigDto configDto = (ConfigDto) responseEntity.getData();
        ObjectMapper objectMapper = new ObjectMapper();
        List list = objectMapper.convertValue(responseEntity.getData(), List.class);
        Configs configs = new Configs();
        for (Object object : list) {
            LinkedHashMap map = (LinkedHashMap)object;
            Map.Entry<String, String> entry = (Map.Entry<String, String>)map.entrySet().iterator().next();

            if (NameConstant.FILTER_INFO.equals(entry.getValue())){
                FilterConfig config = objectMapper.convertValue(map, FilterConfig.class);
                configs.addConfig(config.getName(),config);
            }
            if (NameConstant.BLACK_LIST.equals(entry.getValue())){
                IpListConfig config = objectMapper.convertValue(map, IpListConfig.class);
                configs.addConfig(config.getName(),config);
            }
            if (NameConstant.WHITE_LIST.equals(entry.getValue())){
                IpListConfig config = objectMapper.convertValue(map, IpListConfig.class);
                configs.addConfig(config.getName(),config);
            }
            if (NameConstant.RATE_LIMIT.equals(entry.getValue())){
                RateLimitConfig config = objectMapper.convertValue(map, RateLimitConfig.class);
                configs.addConfig(config.getName(),config);
            }

        }
        configManager.apply(configs);
    }
}
