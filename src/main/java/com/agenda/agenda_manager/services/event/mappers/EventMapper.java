package com.agenda.agenda_manager.services.event.mappers;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;
import com.agenda.agenda_manager.services.event.EventService;

public class EventMapper {

    EventService eventService;

    public EventResponseDTO toEventResponse(EventCreateDTO eventCreateDTO) {
            EventResponseDTO eventResponseDTO = new EventResponseDTO();
            eventResponseDTO.setId(eventCreateDTO.getId());
            eventResponseDTO.setNameEvent(eventCreateDTO.getEventName());
            eventResponseDTO.setDescription(eventCreateDTO.getDescription());
            eventResponseDTO.setActiveEvent(true);
        return eventResponseDTO;
    }

}

