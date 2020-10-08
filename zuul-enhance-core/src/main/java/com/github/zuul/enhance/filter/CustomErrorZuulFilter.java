package com.github.zuul.enhance.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class CustomErrorZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ERROR_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SEND_ERROR_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("sendErrorFilter.ran");
            ctx.setResponseStatusCode(500);
            ctx.setResponseBody("forward service error");
            ZuulException exception = this.findZuulException(ctx.getThrowable());
            HttpServletRequest request = ctx.getRequest();
            request.setAttribute("javax.servlet.error.status_code", exception.nStatusCode);
            request.setAttribute("javax.servlet.error.exception", exception);
            if (StringUtils.hasText(exception.errorCause)) {
                request.setAttribute("javax.servlet.error.message", exception.errorCause);
            }
        } catch (Exception var4) {
            ReflectionUtils.rethrowRuntimeException(var4);
        }

        return null;
    }

    ZuulException findZuulException(Throwable throwable) {
        if (throwable.getCause() instanceof ZuulRuntimeException) {
            return (ZuulException)throwable.getCause().getCause();
        } else if (throwable.getCause() instanceof ZuulException) {
            return (ZuulException)throwable.getCause();
        } else {
            return throwable instanceof ZuulException ? (ZuulException)throwable : new ZuulException(throwable, 500, (String)null);
        }
    }
}
