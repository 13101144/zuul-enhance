package com.github.zuul.enhance.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "service_route")
public class ServiceRoute extends BaseEntity {

    private String routeId;
    /**
     * 路由名称
     */
    private String routeName;

    /**
     * 服务ID
     */
    private String serviceId;

    /**
     * 服务名
     */
    private String serviceName;

    /**
     * 路径
     */
    private String path;

    /**
     * 完整路径
     */
    private String url;

    /**
     * 忽略前缀
     */
    private Boolean stripPrefix;

    /**
     * false-不重试 true-重试
     */
    private Boolean retryable;

    /**
     * 敏感Header设置
     */
    private String sensitiveHeaders;

    /**
     * 路由描述
     */
    private String routeDesc;

}
