package com.github.zuul.enhance.dto;

import lombok.Data;

@Data
public class BlackListDto {

    private Integer blacklistId;

    private String ipAddress;

    private Integer status;

    private String desc;
}
