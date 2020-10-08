package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "count_info")
public class CountInfo implements Serializable {

    private Date date;

    private String gatewayId;

    private Integer counter;
}
