package com.agenda.agenda_manager.services.mappers;

import com.agenda.agenda_manager.controllers.dtos.EventResultDTO;
import com.agenda.agenda_manager.controllers.dtos.EventListDTO;
import com.agenda.agenda_manager.services.EventService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventMapper {

    EventService eventService;

    public List<EventResultDTO> toEventResult(List<List<EventListDTO>> eventListDTO) {
        List<EventResultDTO> eventResultList = new ArrayList<>();

        for (List<EventListDTO> eventList : eventListDTO) {
            EventResultDTO eventResultDTO = new EventResultDTO();

            List<Date> dateList = eventList.stream()
                    .map(EventListDTO::getStartDate)
                    .toList();

            eventResultDTO.setStartDates(dateList);
            eventResultDTO.setEvents(eventList);

            eventResultList.add(eventResultDTO);
        }
        return eventResultList;
    }
}

