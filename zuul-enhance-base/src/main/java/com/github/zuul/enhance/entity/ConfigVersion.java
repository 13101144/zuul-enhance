package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "config_version")
public class ConfigVersion extends BaseEntity {

    private Integer version;
}
