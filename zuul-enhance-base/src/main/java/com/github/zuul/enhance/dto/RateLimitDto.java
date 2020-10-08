package com.github.zuul.enhance.dto;

import lombok.Data;

@Data
public class RateLimitDto {

    private Integer ratelimitId;

    private String routeId;

    private Integer rate;

}
