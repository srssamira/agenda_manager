package com.agenda.agenda_manager.controllers;

import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.EventViewDTO;
import com.agenda.agenda_manager.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/agenda")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<EventResponseDTO>> getAllEvents() {
        List<EventResponseDTO> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        try {
            EventViewDTO event = eventService.getEventById(id);
            return ResponseEntity.ok(Map.of("event", event));
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

    @DeleteMapping
    public ResponseEntity<?> deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/events/{id}")
    public ResponseEntity<?> updateControlActiveEvent(@PathVariable String id) {
        try {
            List<EventViewDTO> controlActivity = eventService.cancelEvent(id);
            return ResponseEntity.ok(controlActivity);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
