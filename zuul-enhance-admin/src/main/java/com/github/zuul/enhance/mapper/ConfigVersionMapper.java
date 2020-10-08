package com.github.zuul.enhance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuul.enhance.entity.ConfigVersion;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConfigVersionMapper extends BaseMapper<ConfigVersion> {

    @Select("select max(version) from config_version")
    Integer getMaxVersion();
}
