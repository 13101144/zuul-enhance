package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "filter_info" )
public class FilterInfo extends BaseEntity {

    private String filterName;

    private Integer status;

    private String description;

}
