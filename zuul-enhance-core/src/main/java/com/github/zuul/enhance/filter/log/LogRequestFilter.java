package com.github.zuul.enhance.filter.log;

import com.github.zuul.enhance.service.LogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class LogRequestFilter extends ZuulFilter {

    protected LogService logService;

    public LogRequestFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        return logService.logEnabled();
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        logService.logRequest(context);
        return null;
    }
}
