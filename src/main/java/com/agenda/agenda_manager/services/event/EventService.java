package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.dtos.AgendaDTO;
import com.agenda.agenda_manager.controllers.dtos.EventDTO;

import java.util.List;

public interface EventService {
    public List<EventDTO> getAllEvents();

    public EventDTO addEvent(AgendaDTO agendaDTO);
}
