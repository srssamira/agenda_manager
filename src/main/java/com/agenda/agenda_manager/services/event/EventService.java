package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;

import java.util.Optional;

public interface EventService {

    Optional<EventCreateDTO> searchEvent(String id);

    EventResponseDTO getEventById(String id);

    EventCreateDTO addEvent(String nameEvent, String description);

    boolean controlActiveEvent(String id, boolean isActive);

    void deleteEvent(String id);
}
