package com.agenda.agenda_manager.controllers.dtos;

import java.sql.Time;

public class EventViewDTO {
    private String id;
    private String name;
    private String description;
    private Time startTime;
    private boolean activeEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActiveEvent() {
        return activeEvent;
    }

    public void setActiveEvent(boolean activeEvent) {
        this.activeEvent = activeEvent;
    }
}
