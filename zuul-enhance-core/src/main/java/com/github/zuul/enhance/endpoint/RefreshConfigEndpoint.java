package com.github.zuul.enhance.endpoint;

import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.web.bind.annotation.PutMapping;

public class RefreshConfigEndpoint {

    private ContextRefresher contextRefresher;

    public RefreshConfigEndpoint(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @PutMapping({"/config-refresh"})
    public void refresh() {
       this.contextRefresher.refresh();
    }
}
