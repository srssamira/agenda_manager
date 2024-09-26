package com.agenda.agenda_manager.controllers;

import com.agenda.agenda_manager.controllers.dtos.EventListDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResultDTO;
import com.agenda.agenda_manager.services.EventService;
import jdk.jfr.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<EventResultDTO>> getAllEvents() {
        List<EventResultDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        try {
            EventListDTO event = eventService.getEventById(id);
            return ResponseEntity.ok(Map.of("event", event));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventListDTO eventList) {
        eventService.addEvent(eventList.getStartDate(),
                eventList.getEndDate(),
                eventList.getStartTime(),
                eventList.getEndTime(),
                eventList.getEventRegisterDTO().getEventName(),
                eventList.getEventRegisterDTO().getDescription());

        return ResponseEntity.status(201).body(Map.of("event", eventList));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("{id}/isActive")
    public ResponseEntity<?> updateControlActiveEvent(@PathVariable String id, @RequestParam boolean isActive) {
        try {
            boolean controlActivity = eventService.controlActiveEvent(id, isActive);
            return ResponseEntity.ok(controlActivity);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
