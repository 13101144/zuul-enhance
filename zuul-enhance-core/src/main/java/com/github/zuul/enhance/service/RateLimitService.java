package com.github.zuul.enhance.service;

import com.netflix.zuul.context.RequestContext;

public interface RateLimitService {

    public void rateLimit(RequestContext ctx, String routeid) ;

}
