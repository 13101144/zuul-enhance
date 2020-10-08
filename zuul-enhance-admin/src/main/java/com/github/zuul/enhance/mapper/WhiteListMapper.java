package com.github.zuul.enhance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuul.enhance.entity.WhiteList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WhiteListMapper extends BaseMapper<WhiteList> {

    @Select("select * from white_list where status = 1")
    List<WhiteList> listActive();
}
