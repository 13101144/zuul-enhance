package com.github.zuul.enhance.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class XForwardedForFilter extends ZuulFilter {

    private static final String HTTP_X_FORWARDED_FOR = "X-Forwarded-For";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        /**

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getHeader("X-Real-IP");;
        ctx.getZuulRequestHeaders().put("X-Forwarded-For", remoteAddr);
         */
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String remoteAddr = request.getRemoteAddr();
        ctx.getZuulRequestHeaders().put("X-Forwarded-For", remoteAddr);
        return null;
    }
}
