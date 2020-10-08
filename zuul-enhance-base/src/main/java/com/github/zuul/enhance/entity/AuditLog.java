package com.github.zuul.enhance.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "audit_log")
public class AuditLog extends BaseEntity {

    private String userName;

    private String op;

    private String beforeValue;

    private String afterValue;

}
