package com.agenda.agenda_manager.controllers.dtos.agenda;
import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;

import java.sql.Time;
import java.util.Date;

public class AgendaCreateDTO {
    private Date startDate;
    private Date endDate;
    private Time startTime;
    private Time endTime;
    private EventCreateDTO event;

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

    public EventCreateDTO getEvent() {
        return event;
    }

    public void setEvent(EventCreateDTO event) {
        this.event = event;
    }
}
