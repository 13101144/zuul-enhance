package com.github.zuul.enhance.locator.repository;

import com.github.zuul.enhance.entity.ServiceRoute;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ServiceRouteRepository {

    private JdbcTemplate jdbcTemplate;

    public ServiceRouteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ServiceRoute> listRoutes() {
        String sql = "select * from service_route";
        return jdbcTemplate.queryForList(sql, ServiceRoute.class);
    }


}
