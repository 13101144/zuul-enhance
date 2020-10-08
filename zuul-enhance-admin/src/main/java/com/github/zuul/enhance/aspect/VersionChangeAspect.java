package com.github.zuul.enhance.aspect;


import com.github.zuul.enhance.service.ConfigVersionService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.github.zuul.enhance.entity.ConfigVersion;

@Component
public class VersionChangeAspect {

    @Autowired
    private ConfigVersionService configVersionService;

    @Pointcut("@annotation(com.github.zuul.enhance.annotation.VersionChange)")
    private void versionChange() {

    }

    @After(value = "versionChange()")
    public void after(JoinPoint joinPoint){

        int version = configVersionService.getMaxVersion();

        ConfigVersion configVersion = new ConfigVersion();
        configVersion.setVersion(version);

        configVersionService.save(configVersion);

    }

}
