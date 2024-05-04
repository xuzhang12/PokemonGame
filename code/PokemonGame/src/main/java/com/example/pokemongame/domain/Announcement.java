package com.example.pokemongame.domain;

import java.util.Date;

/**
 * 公告类
 */
public class Announcement {
    private Date day;//发布日期
    private String message;//公告消息

    public Announcement() {
    }

    public Announcement(Date day, String message) {
        this.day = day;
        this.message = message;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "day=" + day +
                ", message='" + message + '\'' +
                '}';
    }
}
