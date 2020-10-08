package com.github.zuul.enhance.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.zuul.enhance.entity.AuditLog;
import com.github.zuul.enhance.mapper.AuditLogMapper;
import com.github.zuul.enhance.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuditLogServiceImpl extends ServiceImpl<AuditLogMapper, AuditLog> implements AuditLogService {

    @Autowired
    private AuditLogMapper auditLogMapper;

    @Override
    public List<AuditLog> selectPage(int page, int size) {
        IPage<AuditLog> userPage = new Page<>(page, size);
        userPage = auditLogMapper.selectPage(userPage, null);
        List<AuditLog> list = userPage.getRecords();
        return list;
    }
}
