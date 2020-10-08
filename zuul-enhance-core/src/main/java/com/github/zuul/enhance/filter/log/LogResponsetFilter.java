package com.github.zuul.enhance.filter.log;


import com.github.zuul.enhance.service.LogService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class LogResponsetFilter extends ZuulFilter {

    protected LogService logService;

    public LogResponsetFilter(LogService logService) {
        this.logService = logService;
    }

    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return logService.logEnabled();
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        logService.logResponse(context);
        return true;
    }
}
