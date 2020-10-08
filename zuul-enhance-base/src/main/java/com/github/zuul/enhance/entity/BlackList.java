package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "black_list")
public class BlackList extends BaseEntity {

    private String ipAddress;

    private String description;

    private Integer status;

}
