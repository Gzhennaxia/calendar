package com.example.calendar.mapper;

import com.example.calendar.entity.Event;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EventMapper {

    public List<Event> selectList(@Param("eventIds") List<String> eventIds);

    Integer insertList(List<Event> list);
}
