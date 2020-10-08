package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 白名单
 */
@Data
@TableName("white_list")
public class WhiteList extends BaseEntity {

    private String ipAddress;

    private String description;

    private Integer status;

}

