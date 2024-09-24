package com.agenda.agenda_manager.controllers.dtos.agenda;

import com.agenda.agenda_manager.controllers.dtos.event.response.EventResponseDTO;

import java.util.Date;
import java.util.List;

public class AgendaResponseDTO {
    Date startDate;
    List<EventResponseDTO> events;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<EventResponseDTO> getEvents() {
        return events;
    }

    public void setEvents(List<EventResponseDTO> events) {
        this.events = events;
    }
}
