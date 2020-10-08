package com.github.zuul.enhance.filter.log;


import com.github.zuul.enhance.service.LogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class LogRouteFilter extends ZuulFilter {

    protected LogService logService;

    public LogRouteFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 11;
    }

    @Override
    public boolean shouldFilter() {
        return logService.logEnabled();
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        logService.logRoute(context);
        return null;
    }
}
