package com.github.zuul.enhance.service.impl;

import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.Config;
import com.github.zuul.enhance.domain.Configs;
import com.github.zuul.enhance.helper.IpUtils;
import com.github.zuul.enhance.service.BlackListService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class BlackListServiceImpl implements BlackListService{

    private AtomicReference<List<String>> blackList = new AtomicReference<>();

    @Override
    public boolean checkBlackList(String ip) {
        List<String> list = blackList.get();
        if (IpUtils.match(blackList.get(), ip)) {
            return true;
        }
        return false;
    }

    @Override
    public void refresh(Configs configs) {
        Config config = configs.getConfig(name());
        if (config != null) {
            List<String> ipList = (List<String>)config.getConfig();
            blackList.compareAndSet(blackList.get(), ipList);
        }

    }

    @Override
    public String name() {
        return NameConstant.BLACK_LIST;
    }
}
