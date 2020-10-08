package com.github.zuul.enhance.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "statistic")
public class Statistic extends BaseEntity {

    private Date date;

    private Integer sum;

}
