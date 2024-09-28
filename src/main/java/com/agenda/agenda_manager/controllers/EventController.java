package com.agenda.agenda_manager.controllers;

import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.EventViewDTO;
import com.agenda.agenda_manager.services.EventService;
import com.agenda.agenda_manager.services.mappers.EventMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventMapper eventMapper;

    @GetMapping("/events")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents(
            @RequestParam(value = "days", required = false) Integer days,
            @RequestParam(value = "actives", required = false) Boolean actives) {

        List<EventResponseDTO> events = eventService.getAllEvents(Optional.ofNullable(days), Optional.ofNullable(actives));
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        try {
            EventResponseDTO event = eventService.getEventById(id);
            return ResponseEntity.ok(event);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDTO eventList) {
        eventService.addEvent(eventList.getStartDate(),
                eventList.getEndDate(),
                eventList.getStartTime(),
                eventList.getEndTime(),
                eventList.getEventRegisterDTO().getEventName(),
                eventList.getEventRegisterDTO().getDescription());

        return ResponseEntity.status(201).body(Map.of("event", eventList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/events/{id}/cancel")
    public ResponseEntity<?> cancelEvent(@PathVariable String id) {
        try {
            List<EventCreateDTO> cancelEvent = eventService.cancelEvent(id);
            List<EventViewDTO> updateEventViewList = eventMapper.toEvents(cancelEvent);
            return ResponseEntity.ok(updateEventViewList);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
