package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "rate_limit")
public class RateLimit extends BaseEntity{

    private String routeId;

    private Integer rate;

    private Integer status;

}
