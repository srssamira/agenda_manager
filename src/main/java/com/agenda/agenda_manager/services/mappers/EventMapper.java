package com.agenda.agenda_manager.services.mappers;

import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventViewDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {

    public EventViewDTO toEvents(EventCreateDTO eventListDTO) {
        EventViewDTO events = new EventViewDTO();
        events.setId(eventListDTO.getId());
        events.setDescription(eventListDTO.getEventRegisterDTO().getDescription());
        events.setName(eventListDTO.getEventRegisterDTO().getEventName());
        events.setStartTime(eventListDTO.getStartTime());
        events.setActiveEvent(events.isActiveEvent());

        return events;
    }

    public List<EventViewDTO> toEvents(List<EventCreateDTO> eventListDTOList) {
        return eventListDTOList.stream().map(this::toEvents).collect(Collectors.toList());
    }

    public List<EventResponseDTO> toEventResponses(List<List<EventViewDTO>> events, EventCreateDTO eventCreateDTO) {
        List<EventResponseDTO> eventResponseDTOList = new ArrayList<>();
        for (List<EventViewDTO> eventViewList : events) {
            EventResponseDTO eventResponseDTO = new EventResponseDTO();

            Date date = eventCreateDTO.getStartDate();
            eventResponseDTO.setEventosList(eventViewList);
            eventResponseDTO.setStartDate(date);

            eventResponseDTOList.add(eventResponseDTO);
        }
        return eventResponseDTOList;
    }
}

