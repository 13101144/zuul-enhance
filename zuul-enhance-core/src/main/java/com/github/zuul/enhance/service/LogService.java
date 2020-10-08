package com.github.zuul.enhance.service;

import com.netflix.zuul.context.RequestContext;

public interface LogService {

    /**
     * 记录请求日志
     * @param ctx
     * @return void
     *
     * */
    void logRequest(RequestContext ctx);

    /**
     * 记录响应日志
     * @param ctx
     * @return void
     *
     * */
    void logResponse(RequestContext ctx);

    /**
     * 记录路由信息
     * @param ctx
     */
    void logRoute(RequestContext ctx);

    /**
     * 记录调用链路信息
     */
    void logCollectSpan();

    /**
     * 是否使能网关日志
     * @return
     */
    boolean logEnabled();

}
