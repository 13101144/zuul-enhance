package com.github.zuul.enhance.service.impl;

import com.github.zuul.enhance.constant.NameConstant;
import com.github.zuul.enhance.domain.Config;
import com.github.zuul.enhance.domain.Configs;
import com.github.zuul.enhance.listener.RefreshListner;
import com.github.zuul.enhance.name.Named;
import com.github.zuul.enhance.service.RateLimitService;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

@Component
public class RateLimitServiceImpl implements RateLimitService, RefreshListner, Named {

    public static final Logger LOGGER = LoggerFactory.getLogger(RateLimitServiceImpl.class);

    private static Map<String, RateLimiter> routlimitMap = Maps.newConcurrentMap();

    private static final int DEFAULTLIMIT = 100;

    @Override
    public void rateLimit(RequestContext ctx, String routeid) {

        if (routeid == null) {
            URL routeHost = ctx.getRouteHost();
            if (routeHost != null) {
                String url = routeHost.toString();
                routlimitMap.putIfAbsent(url, RateLimiter.create(DEFAULTLIMIT));
            }
        }

        RateLimiter rateLimiter = routlimitMap.get(routeid);
        LOGGER.info("routeid:"+routeid+"  rate:"+rateLimiter.getRate());

        if (!rateLimiter.tryAcquire()) {
            HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
            try {
                ctx.getResponse().setContentType(MediaType.TEXT_PLAIN_VALUE);
                ctx.getResponse().setStatus(httpStatus.value());
                ctx.getResponse().getWriter().append(httpStatus.getReasonPhrase());
                ctx.setSendZuulResponse(false);
            } catch (IOException e) {

                LOGGER.error(">runRateLimit 报错:"+e.getMessage());
            }
        }

    }

    @Override
    public void refresh(Configs configs) {
        Config config = configs.getConfig(name());
        Map<String,Integer> rateLimitMap = (Map<String,Integer>)config.getConfig();

        Map<String, RateLimiter> routlimitMapNew = Maps.newConcurrentMap();

        for ( Map.Entry entry:rateLimitMap.entrySet()) {
            routlimitMapNew.put((String) entry.getKey(), RateLimiter.create((Integer)entry.getValue()));
        }

        routlimitMap = routlimitMapNew;
    }

    @Override
    public String name() {
        return NameConstant.RATE_LIMIT;
    }
}
