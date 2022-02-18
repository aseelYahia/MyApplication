package com.example.myapplication;

import java.util.Date;

public class Event {
    Date date;
    String eventName;

    public Event(Date date, String eventName) {
        this.date = date;
        this.eventName = eventName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
