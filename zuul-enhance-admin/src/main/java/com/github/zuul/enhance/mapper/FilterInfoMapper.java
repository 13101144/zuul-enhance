package com.github.zuul.enhance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuul.enhance.entity.FilterInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FilterInfoMapper extends BaseMapper<FilterInfo> {

    @Select("select * from filter_info where status = 1")
    List<FilterInfo> listActive();
}
