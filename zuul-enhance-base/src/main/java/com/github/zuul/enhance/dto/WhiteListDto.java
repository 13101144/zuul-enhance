package com.github.zuul.enhance.dto;

import lombok.Data;

@Data
public class WhiteListDto {

    private Integer whitelistId;

    private String ipAddress;

    private Integer status;

    private String desc;

}
