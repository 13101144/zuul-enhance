package com.github.zuul.enhance.service.impl;

import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.Config;
import com.github.zuul.enhance.domain.Configs;
import com.github.zuul.enhance.helper.IpUtils;
import com.github.zuul.enhance.listener.RefreshListner;
import com.github.zuul.enhance.name.Named;
import com.github.zuul.enhance.service.WhiteListService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class WhiteListServiceImpl implements WhiteListService {

    AtomicReference<List<String>> whiteList = new AtomicReference<>();

    @Override
    public boolean checkWhiteList(String ip) {
        if (!IpUtils.match(whiteList.get(), ip)) {
            return true;
        }
        return false;
    }

    @Override
    public void refresh(Configs configs) {
        Config config = configs.getConfig(name());
        if (config != null) {
            List<String> ipList = (List<String>)config.getConfig();
            whiteList.compareAndSet(whiteList.get(), ipList);
        }
    }


    @Override
    public String name() {
        return NameConstant.WHITE_LIST;
    }
}
