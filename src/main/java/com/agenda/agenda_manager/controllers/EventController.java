package com.agenda.agenda_manager.controllers;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;
import com.agenda.agenda_manager.services.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable String id) {
        try {
            EventResponseDTO event = eventService.getEventById(id);
            return ResponseEntity.ok(Map.of("event", event));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody EventCreateDTO eventCreateDTO) {
        return ResponseEntity.status(201).body(Map.of("name", eventCreateDTO.getEventName(),
                                                      "description", eventCreateDTO.getDescription()));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEvent(@RequestParam String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.status(204).build();
    }
}
