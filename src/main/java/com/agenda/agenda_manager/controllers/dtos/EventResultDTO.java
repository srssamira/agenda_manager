package com.agenda.agenda_manager.controllers.dtos;

import java.util.Date;
import java.util.List;

public class EventResultDTO {
    List<Date> startDates;
    List<EventListDTO> events;

    public List<Date> getStartDates() {
        return startDates;
    }

    public void setStartDates(List<Date> startDates) {
        this.startDates = startDates;
    }

    public List<EventListDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventListDTO> events) {
        this.events = events;
    }
}
