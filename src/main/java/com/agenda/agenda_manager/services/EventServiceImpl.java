package com.agenda.agenda_manager.services;

import com.agenda.agenda_manager.controllers.dtos.EventRegisterDTO;
import com.agenda.agenda_manager.controllers.dtos.EventListDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResultDTO;
import com.agenda.agenda_manager.services.mappers.EventMapper;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventMapper eventMapper;
    private List<EventListDTO> eventListDTO = new ArrayList<>();

    public String sortId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.substring(0, 8);
    }

    @Override
    public List<EventResultDTO> getAllEvents() {
        List<List<EventListDTO>> eventListDTOToResultList = new ArrayList<>();
        eventListDTOToResultList.add(eventListDTO);
        return eventMapper.toEventResult(eventListDTOToResultList);
    }

    @Override
    public List<EventListDTO> getEventList() {
        return eventListDTO;
    }

    @Override
    public Optional<EventListDTO> searchEvent(String id) {
        Optional<EventListDTO> eventListDTOOptional;
        eventListDTOOptional = eventListDTO.stream().filter(event -> event.getId().equalsIgnoreCase(id)).findFirst();
        return eventListDTOOptional;
    }

    @Override
    public EventListDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description) {
        String id = sortId();
        if (searchEvent(id).isPresent())
            throw new RuntimeException("event already exist");

        EventListDTO eventListDTO = new EventListDTO();
        eventListDTO.setId(id);
        eventListDTO.setStartDate(startDate);
        eventListDTO.setEndDate(endDate);
        eventListDTO.setStartTime(startTime);
        eventListDTO.setEndTime(endTime);
        eventListDTO.setActiveEvent(true);

        EventRegisterDTO eventRegisterDTO = new EventRegisterDTO();
        eventRegisterDTO.setEventName(name);
        eventRegisterDTO.setDescription(description);

        eventListDTO.setEventRegisterDTO(eventRegisterDTO);

        this.eventListDTO.add(eventListDTO);
        return eventListDTO;
    }

    @Override
    public boolean controlActiveEvent(String id, boolean isActive) {
        try {
            if (searchEvent(id).isPresent()) {
                EventListDTO eventListDTO = getEventById(id);
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
        eventListDTO.stream()
                .filter(eventListDTO -> !eventListDTO.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    @Override
    public EventListDTO getEventById(String id) {
       return searchEvent(id).orElseThrow(() -> new RuntimeException("event doesn't exist"));
    }
}
