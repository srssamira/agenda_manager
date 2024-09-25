package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;
import com.agenda.agenda_manager.services.event.mappers.EventMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    EventMapper eventMapper = new EventMapper();
    List<EventCreateDTO> eventCreateList = new ArrayList<>();

    public String sortId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.substring(0, 8);
    }

    @Override
    public Optional<EventCreateDTO> searchEvent(String id) {
        return eventCreateList.stream().filter(eventCreateDTO -> eventCreateDTO.getId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public EventCreateDTO addEvent(String nameEvent, String description) {
        String id = sortId();
        if (searchEvent(id).isPresent())
            throw new RuntimeException("event already exist");

        EventCreateDTO eventCreateDTO = new EventCreateDTO();
        eventCreateDTO.setId(id);
        eventCreateDTO.setEventName(nameEvent);
        eventCreateDTO.setDescription(description);

        return eventCreateDTO;
    }

    @Override
    public boolean cancelEvent(String id) {
        return searchEvent(id).isEmpty();
    }

    @Override
    public void deleteEvent(String id) {
        eventCreateList.stream().filter(eventCreateDTO -> !eventCreateDTO.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(String id) {
        EventCreateDTO eventCreate = searchEvent(id).orElseThrow(() -> new RuntimeException("event doesn't exist"));
        return eventMapper.toEventResponse(eventCreate);
    }
}
