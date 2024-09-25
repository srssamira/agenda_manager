package com.agenda.agenda_manager.services.event.mappers;

import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;
import com.agenda.agenda_manager.services.event.EventService;

import java.util.Optional;

public class EventMapper {

    EventService eventService;

    public EventResponseDTO toEventResponse(EventCreateDTO eventCreateDTO) {
            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setId(eventCreateDTO.getId());
            eventResponseDTO.setNameEvent(eventCreateDTO.getEventName());
            eventResponseDTO.setDescription(eventCreateDTO.getDescription());
            eventResponseDTO.setActiveEvent(!eventService.cancelEvent(eventResponseDTO.getId()));

        return eventResponseDTO;
    }

    public EventCreateDTO cloneEventDTO(String id, String name, String description) {
        return new EventCreateDTO();
    }
}

