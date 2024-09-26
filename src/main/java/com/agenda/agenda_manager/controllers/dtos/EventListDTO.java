package com.agenda.agenda_manager.controllers.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Time;
import java.util.Date;

public class EventListDTO {
    @JsonIgnore
    private String id;
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    @JsonIgnore
    private boolean activeEvent;
    private EventRegisterDTO eventRegisterDTO;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActiveEvent() {
        return activeEvent;
    }

    public void setActiveEvent(boolean activeEvent) {
        this.activeEvent = activeEvent;
    }

    public EventRegisterDTO getEventRegisterDTO() {
        return eventRegisterDTO;
    }

    public void setEventRegisterDTO(EventRegisterDTO eventRegisterDTO) {
        this.eventRegisterDTO = eventRegisterDTO;
    }
}
