package com.example.calendar.entity;

import com.google.api.services.calendar.model.CalendarListEntry;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.SelectKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
public class Calendar {

    private Integer id;

    private String calendarId;

    private String name;

    private Date createDate;

    private Date updateDate;

    public Calendar(String calendarId, String name) {
        this.calendarId = calendarId;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calendar calendar = (Calendar) o;
        return Objects.equals(id, calendar.id) &&
                Objects.equals(calendarId, calendar.calendarId) &&
                Objects.equals(name, calendar.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, calendarId, name);
    }

    public static List<Calendar> convert2Calendar(List<CalendarListEntry> list) {
        ArrayList<Calendar> calendars = new ArrayList<>();
        for (CalendarListEntry entry : list) {
            calendars.add(convert2Calendar(entry));
        }
        return calendars;
    }

    public static Calendar convert2Calendar(CalendarListEntry entry) {
        return new Calendar(entry.getId(), entry.getSummary());
    }
}
