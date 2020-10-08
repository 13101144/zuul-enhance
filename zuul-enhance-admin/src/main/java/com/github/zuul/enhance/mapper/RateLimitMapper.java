package com.github.zuul.enhance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuul.enhance.entity.RateLimit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RateLimitMapper extends BaseMapper<RateLimit> {

    @Select("select * from rate_limit where status = 1")
    public List<RateLimit> listActive();

}
