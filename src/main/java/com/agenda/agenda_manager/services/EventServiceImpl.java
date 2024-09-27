package com.agenda.agenda_manager.services;

import com.agenda.agenda_manager.controllers.dtos.*;
import com.agenda.agenda_manager.services.mappers.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;
    private EventCreateDTO eventCreateDTO = new EventCreateDTO();
    private List<EventCreateDTO> eventCreateList = new ArrayList<>();

    public String sortId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.substring(0, 8);
    }

    @Override
    public List<EventResponseDTO> getAllEvents() {
        List<EventViewDTO> events = eventMapper.toEvents(eventCreateList);
        return eventMapper.toEventResponses(List.of(events), getEventCreate());
    }

    @Override
    public EventCreateDTO getEventCreate() {
        return this.eventCreateDTO;
    }

    @Override
    public Optional<EventCreateDTO> searchEvent(String id) {
        Optional<EventCreateDTO> eventListDTOOptional;
        eventListDTOOptional = eventCreateList.stream().filter(event -> event.getId().equalsIgnoreCase(id)).findFirst();
        return eventListDTOOptional;
    }

    @Override
    public EventCreateDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description) {
        String id = sortId();
        if (searchEvent(id).isPresent())
            throw new RuntimeException("event already exist");

        eventCreateDTO.setId(id);
        eventCreateDTO.setStartDate(startDate);
        eventCreateDTO.setEndDate(endDate);
        eventCreateDTO.setStartTime(startTime);
        eventCreateDTO.setEndTime(endTime);
        eventCreateDTO.setActiveEvent(true);

        EventRegisterDTO eventRegisterDTO = new EventRegisterDTO();
        eventRegisterDTO.setEventName(name);
        eventRegisterDTO.setDescription(description);

        eventCreateDTO.setEventRegisterDTO(eventRegisterDTO);

        this.eventCreateList.add(eventCreateDTO);
        return eventCreateDTO;
    }

    @Override
    public boolean controlActiveEvent(String id, boolean isActive) {
        try {
            if (searchEvent(id).isPresent()) {
                EventCreateDTO eventListDTO = getEventById(id);
                eventListDTO.setActiveEvent(isActive);
                return eventListDTO.isActiveEvent();
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("event doesn't exist");
        }
        return false;
    }

    @Override
    public void deleteEvent(String id) {
        eventCreateList.stream()
                .filter(eventListDTO -> !eventListDTO.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    @Override
    public EventCreateDTO getEventById(String id) {
       return searchEvent(id).orElseThrow(() -> new RuntimeException("event doesn't exist"));
    }
}
