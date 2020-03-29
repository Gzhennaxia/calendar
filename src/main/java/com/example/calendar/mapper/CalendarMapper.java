package com.example.calendar.mapper;

import com.example.calendar.entity.Calendar;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface CalendarMapper {

    @Select("SELECT seq FROM sqlite_sequence WHERE `name` = 'calendar'")
    Integer selectId();

//    @SelectKey(statement = "select seq from sqlite_sequence where name = calendar", keyProperty = "id", before = true, resultType = int.class)
    Integer insertList(List<Calendar> list);

    @Select("select * from calendar")
    public List<Calendar> selectList();

    public List<Calendar> selectByList(List<Calendar> list);
}
