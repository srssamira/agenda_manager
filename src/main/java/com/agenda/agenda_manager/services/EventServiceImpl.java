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
    private final EventCreateDTO eventCreateDTO = new EventCreateDTO();
    private final List<EventCreateDTO> eventCreateList = new ArrayList<>();
    private final List<EventViewDTO> events = new ArrayList<>();

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
    public Optional<EventViewDTO> searchEvent(String id) {
        List<EventViewDTO> events = eventMapper.toEvents(eventCreateList);
        return events.stream().filter(event -> event.getId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public EventCreateDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description) {

        EventCreateDTO newEventDTO = new EventCreateDTO();

        String id = sortId();
        if (searchEvent(id).isPresent())
            throw new RuntimeException("event already exist");

        newEventDTO.setId(id);
        newEventDTO.setStartDate(startDate);
        newEventDTO.setEndDate(endDate);
        newEventDTO.setStartTime(startTime);
        newEventDTO.setEndTime(endTime);

        EventRegisterDTO eventRegisterDTO = new EventRegisterDTO();
        eventRegisterDTO.setEventName(name);
        eventRegisterDTO.setDescription(description);

        newEventDTO.setEventRegisterDTO(eventRegisterDTO);

        this.eventCreateList.add(newEventDTO);
        return newEventDTO;
    }

    public List<EventViewDTO> cancelEvent(String id) {
        Optional<EventViewDTO> optionalEvent = searchEvent(id);

        if (optionalEvent.isPresent()) {
            EventViewDTO event = optionalEvent.get();
            event.setActiveEvent(false);

            this.events.removeIf(eventViewDTO -> eventViewDTO.getId().equalsIgnoreCase(id));
            this.events.add(event);

            return events;

        } else {
            throw new RuntimeException("event not found");
        }
    }

    @Override
    public void deleteEvent(String id) {
        eventCreateList.stream()
                .filter(eventListDTO -> !eventListDTO.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    @Override
    public EventViewDTO getEventById(String id) {
        return searchEvent(id).orElseThrow(() -> new RuntimeException("event doesn't exist"));
    }
}
