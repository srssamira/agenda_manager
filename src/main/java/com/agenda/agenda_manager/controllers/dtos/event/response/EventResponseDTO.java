package com.agenda.agenda_manager.controllers.dtos.event.response;

import java.sql.Time;

public class EventResponseDTO {
    String id;
    String nameEvent;
    String description;
    Time startTime;
    boolean activeEvent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public boolean activeEvent() {
        return activeEvent;
    }

    public void setActiveEvent(boolean activeEvent) {
        this.activeEvent = activeEvent;
    }
}
