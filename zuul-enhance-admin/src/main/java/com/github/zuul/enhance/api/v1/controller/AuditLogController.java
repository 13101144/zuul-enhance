package com.github.zuul.enhance.api.v1.controller;


import com.github.zuul.enhance.dto.ResponseEntity;
import com.github.zuul.enhance.entity.AuditLog;
import com.github.zuul.enhance.service.AuditLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class AuditLogController {

    private AuditLogService auditLogService;

    @GetMapping("/audit/list")
    public ResponseEntity listAuditLog(int page, int size) {
        List<AuditLog> list = auditLogService.selectPage(page, size);
        return  ResponseEntity.okayWithData(list);
    }

}
