package com.github.zuul.enhance.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CountInfoDto {

    private Date date;

    private Integer counter;

    private Integer gatewayId;
}
