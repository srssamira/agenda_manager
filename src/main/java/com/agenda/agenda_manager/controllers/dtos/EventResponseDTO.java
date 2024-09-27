package com.agenda.agenda_manager.controllers.dtos;

import java.util.Date;
import java.util.List;

public class EventResponseDTO {
    private Date startDate;
    private List<EventViewDTO> eventosList;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<EventViewDTO> getEventosList() {
        return eventosList;
    }

    public void setEventosList(List<EventViewDTO> eventosList) {
        this.eventosList = eventosList;
    }
}
