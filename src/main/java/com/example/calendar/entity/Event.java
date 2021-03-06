package com.example.calendar.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Event {

    private Integer id;

    private String eventId;

    private String title;

    private Date startTime;

    private Date endTime;

    private String description;

    private String linkUrl;

    private Date createDate;

    private Date updateDate;

    private Integer source;

    private Integer score;

    private String scoreNote;

    private Integer delFlag;

    private Integer status;

    public Event(com.google.api.services.calendar.model.Event event) {
        this.eventId = event.getId();
        this.title = event.getSummary();
        this.description = event.getDescription();
        this.linkUrl = event.getHtmlLink();
        this.source = 1; // 换成字典表数据 todo
        this.delFlag = 0; // 换成字典表数据 todo
        this.status = 0; // 换成字典表数据 todo
        if (event.getStart() != null) {
            this.createDate = new Date(event.getCreated().getValue());
        }
        if (event.getUpdated() != null) {
            this.updateDate = new Date(event.getUpdated().getValue());
        }
        if (event.getStart() != null) {
            if (event.getStart().getDateTime() == null) {
                this.startTime = new Date(event.getStart().getDate().getValue());
            } else {
                this.startTime = new Date(event.getStart().getDateTime().getValue());
            }
        }
        if (event.getEnd() != null) {
            if (event.getEnd().getDateTime() == null) {
                this.endTime = new Date(event.getEnd().getDate().getValue());
            } else {
                this.endTime = new Date(event.getEnd().getDateTime().getValue());
            }
        }
    }
}
