package com.github.zuul.enhance.filter.auth;

import com.github.zuul.enhance.utils.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenFilter extends ZuulFilter {


    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.setResponseBody("Auth fail, token not exsit");
            return null;
        }

        //jwt验证，获取userId
        try {
             String userId = (String) JwtUtil.parseJWT(token).get("userId");
            //token值有问题拒绝请求
            if (StringUtils.isEmpty(userId)) {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                context.setResponseBody("Auth fail, useid not exsit");
                return null;
            } else if (JwtUtil.isExpiration(token)) {
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                context.setResponseBody("token expired");
                return null;
            }

        }
        catch (Exception e) {

            context.setSendZuulResponse(false);
            context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            context.setResponseBody("token auth fail");
        }
        return null;
    }

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotEmpty(token) && token.startsWith("Bearer ")) {
            token = token.replace("Bearer ", "");
        }
        return token;
    }
}
