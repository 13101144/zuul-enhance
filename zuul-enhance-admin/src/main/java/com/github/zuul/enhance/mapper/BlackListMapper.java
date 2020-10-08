package com.github.zuul.enhance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.zuul.enhance.entity.BlackList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlackListMapper extends BaseMapper<BlackList> {

    @Select("select * from black_list where status = 1")
    List<BlackList> listActive();

}
