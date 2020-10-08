package com.github.zuul.enhance.dto;

import lombok.Data;

@Data
public class RouteDto {

    private Integer routeId;

    private Long serviceId;

    private String serviceCode;

    private String serviceName;

    private String path;

    private String url;

    private Integer stripPrefix;

    private String sensitiveHeaders;
}
