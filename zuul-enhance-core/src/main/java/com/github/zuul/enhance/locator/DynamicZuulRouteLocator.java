package com.github.zuul.enhance.locator;

import com.github.zuul.enhance.entity.ServiceRoute;
import com.github.zuul.enhance.locator.repository.ServiceRouteRepository;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

public class DynamicZuulRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    private ZuulProperties properties;

    private ServiceRouteRepository serviceRouteRepository;

    private ApplicationEventPublisher publisher;

    public static LinkedHashMap<String, ZuulProperties.ZuulRoute> cacheRoutesMap = null;

    public DynamicZuulRouteLocator(String servletPath, ZuulProperties properties, ServiceRouteRepository serviceRouteRepository, ApplicationEventPublisher publisher) {
        super(servletPath, properties);
        this.properties = properties;
        this.serviceRouteRepository = serviceRouteRepository;
        this.publisher = publisher;
    }

    @Override
    public Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<String, ZuulProperties.ZuulRoute>();

        /**
         * Step1: 从application.properties中加载路由信息
         */
        routesMap.putAll(super.locateRoutes());

        /**
         * Step2: 从Cache加载Load路由信息
         */
        if (cacheRoutesMap != null) {
            routesMap.putAll(cacheRoutesMap);
        } else {

            Map<String, ZuulProperties.ZuulRoute> routeMapFromDB = getRouteMapFromDB();
            cacheRoutesMap = new LinkedHashMap<>();
            cacheRoutesMap.putAll(routeMapFromDB);
            routesMap.putAll(routeMapFromDB);
        }

        /**
         * Step3: 根据routesMap优化输出路由映射信息
         */
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();

        for (Map.Entry<String, ZuulProperties.ZuulRoute> entry : routesMap.entrySet()) {
            String path = entry.getKey();
            // Prepend with slash if not already present.
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, entry.getValue());

        }
        return values;

    }

    private Map<String, ZuulProperties.ZuulRoute> getRouteMapFromDB() {
        Map<String, ZuulProperties.ZuulRoute> routes = new LinkedHashMap<>();
        List<ServiceRoute> dbRoutes = serviceRouteRepository.listRoutes();
        dbRoutes.forEach(route -> {
            ZuulProperties.ZuulRoute zuulRoute = loadZuulRoute(route);
            routes.put(zuulRoute.getId(), zuulRoute);
        });
        return routes;
    }

    private ZuulProperties.ZuulRoute loadZuulRoute(ServiceRoute route) {
        ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute();
        zuulRoute.setId(route.getRouteId());
        zuulRoute.setServiceId(route.getServiceId());
        zuulRoute.setUrl(route.getUrl());
        zuulRoute.setPath(route.getPath());
        zuulRoute.setRetryable(route.getRetryable());
        // 平滑升级，避免stripPrefix为null的情况
        boolean stripPrefix = route.getStripPrefix() == null || route.getStripPrefix();
        zuulRoute.setStripPrefix(stripPrefix);
        if (route.getSensitiveHeaders() != null) {
            Set<String> sensitiveHeaders = new HashSet<>(Arrays.asList(route.getSensitiveHeaders().split(",")));
            zuulRoute.setSensitiveHeaders(sensitiveHeaders);
        }
        return zuulRoute;
    }

    @Override
    public void refresh() {
        super.doRefresh();
        // 发布本地刷新事件, 更新相关本地缓存, 解决动态加载完,新路由映射无效的问题
        publisher.publishEvent(new RoutesRefreshedEvent(this));
    }
}
