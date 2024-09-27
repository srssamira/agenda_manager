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

    private final List<EventCreateDTO> eventCreateList = new ArrayList<>();

    public String sortId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.substring(0, 8);
    }

    @Override
    public Optional<EventCreateDTO> searchEventCreated(String id) {
        return eventCreateList.stream()
                .filter(event -> event.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public Optional<EventViewDTO> searchEvent(String id) {
        List<EventViewDTO> events = eventMapper.toEvents(eventCreateList);
        return events.stream()
                .filter(event -> event.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public EventResponseDTO getEventById(String id) {
        Optional<EventCreateDTO> eventOptional = eventCreateList.stream()
                .filter(event -> event.getId().equalsIgnoreCase(id))
                .findFirst();

        if (eventOptional.isPresent()) {
            EventCreateDTO event = eventOptional.get();
            List<EventViewDTO> eventViewList = List.of(eventMapper.toEvents(event));
            return eventMapper.toEventResponses(eventViewList, event.getStartDate());
        } else {
            throw new RuntimeException("event not found");
        }
    }

    @Override
    public List<EventResponseDTO> getAllEvents(Optional<Integer> dayOptional, Optional<Boolean> activeOptional) {
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date startOfDay = calendar.getTime();

        List<EventCreateDTO> filteredEvents = eventCreateList;

        if (dayOptional.isPresent()) {
            int days = dayOptional.get();
            calendar.add(Calendar.DAY_OF_YEAR, days);
            Date targetDate = calendar.getTime();

            if (days >= 0) {
                filteredEvents = filteredEvents.stream()
                        .filter(event -> !event.getStartDate().before(startOfDay) && event.getStartDate().before(targetDate))
                        .collect(Collectors.toList());
            } else {
                filteredEvents = filteredEvents.stream()
                        .filter(event -> event.getStartDate().before(startOfDay) && event.getStartDate().after(targetDate))
                        .collect(Collectors.toList());
            }
        } else {
            filteredEvents = filteredEvents.stream()
                    .filter(event -> !event.getStartDate().before(startOfDay))
                    .collect(Collectors.toList());
        }

        if (activeOptional.isPresent()) {
            boolean isActive = activeOptional.get();
            filteredEvents = filteredEvents.stream()
                    .filter(event -> event.isActiveEvent() == isActive)
                    .collect(Collectors.toList());
        }

        List<EventViewDTO> events = eventMapper.toEvents(filteredEvents);
        return List.of(eventMapper.toEventResponses(events, today));
    }

    @Override
    public EventCreateDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description) {
        EventCreateDTO newEventDTO = new EventCreateDTO();
        String id = sortId();

        if (searchEvent(id).isPresent()) throw new RuntimeException("event already exist");

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

    @Override
    public List<EventCreateDTO> cancelEvent(String id) {
        Optional<EventCreateDTO> optionalEvent = searchEventCreated(id);

        if (optionalEvent.isPresent()) {
            EventCreateDTO event = optionalEvent.get();
            event.setActiveEvent(false);
            this.eventCreateList.removeIf(eventViewDTO -> eventViewDTO.getId().equalsIgnoreCase(id));
            this.eventCreateList.add(event);
            return eventCreateList;
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
}
