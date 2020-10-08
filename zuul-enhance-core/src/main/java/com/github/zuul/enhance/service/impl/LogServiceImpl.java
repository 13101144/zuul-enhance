package com.github.zuul.enhance.service.impl;

import com.github.zuul.enhance.service.LogService;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    @Override
    public void logRequest(RequestContext ctx) {

    }

    @Override
    public void logResponse(RequestContext ctx) {

    }

    @Override
    public void logRoute(RequestContext ctx) {

    }

    @Override
    public void logCollectSpan() {

    }

    @Override
    public boolean logEnabled() {
        return false;
    }
}
