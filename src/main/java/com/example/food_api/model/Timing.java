package com.example.food_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "timings")
public class Timing {

    @Id
    @JsonIgnore
    private String mongoId;

    private Long id;
    private String days;
    private String time;
    private boolean closed;
    private String holidayReason;
    private String notice;

    public Timing() {
    }

    public Timing(Long id, String days, String time,
                   boolean closed, String holidayReason, String notice) {
        this.id = id;
        this.days = days;
        this.time = time;
        this.closed = closed;
        this.holidayReason = holidayReason;
        this.notice = notice;
    }

    public String getMongoId() {
        return mongoId;
    }

    public void setMongoId(String mongoId) {
        this.mongoId = mongoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getHolidayReason() {
        return holidayReason;
    }

    public void setHolidayReason(String holidayReason) {
        this.holidayReason = holidayReason;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}