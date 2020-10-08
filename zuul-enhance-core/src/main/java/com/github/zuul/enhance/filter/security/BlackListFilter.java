package com.github.zuul.enhance.filter.security;

import com.github.zuul.enhance.service.BlackListService;
import com.github.zuul.enhance.utils.RequestContextUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
public class BlackListFilter extends ZuulFilter {

    private BlackListService blackListService;

    public BlackListFilter(BlackListService blackListService) {
        this.blackListService = blackListService;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String realIp = RequestContextUtils.getRealIp(ctx);
        if (blackListService.checkBlackList(realIp)) {
            ctx.setResponseStatusCode(401);
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("IpAddr is forbidden!");
        }
        return null;
    }
}
