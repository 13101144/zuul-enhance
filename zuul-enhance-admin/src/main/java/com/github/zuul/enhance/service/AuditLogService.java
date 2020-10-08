package com.github.zuul.enhance.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.zuul.enhance.entity.AuditLog;
import java.util.List;


public interface AuditLogService extends IService<AuditLog> {

    List<AuditLog> selectPage(int page, int size);

}
