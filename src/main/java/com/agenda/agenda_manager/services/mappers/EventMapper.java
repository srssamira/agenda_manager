package com.agenda.agenda_manager.services.mappers;

import com.agenda.agenda_manager.controllers.dtos.EventRegisterDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventViewDTO;
import com.agenda.agenda_manager.services.EventService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    EventService eventService;

    public EventViewDTO toEvents(EventCreateDTO eventCreateDTO) {
        EventViewDTO events = new EventViewDTO();
        events.setId(eventCreateDTO.getId());
        events.setDescription(eventCreateDTO.getEventRegisterDTO().getDescription());
        events.setName(eventCreateDTO.getEventRegisterDTO().getEventName());
        events.setStartTime(eventCreateDTO.getStartTime());
        events.setActiveEvent(eventCreateDTO.isActiveEvent());

        return events;
    }

    public List<EventViewDTO> toEvents(List<EventCreateDTO> eventListDTOList) {
        return eventListDTOList.stream().map(this::toEvents).collect(Collectors.toList());
    }

    public EventResponseDTO toEventResponses(List<EventViewDTO> events, Date date) {
        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setEventosList(events);
        eventResponseDTO.setStartDate(date);
        return eventResponseDTO;
    }
}

