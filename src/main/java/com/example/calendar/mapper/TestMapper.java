package com.example.calendar.mapper;

import com.example.calendar.entity.Test;
import org.apache.ibatis.annotations.Select;

public interface TestMapper {

    @Select("select * from test where id = #{id}")
    public Test selectById(Integer id);

}
