package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.EventController;
import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventResponseDTO;

import java.util.List;
import java.util.Optional;

public interface EventService {

    Optional<EventCreateDTO> searchEvent(String id);

    EventResponseDTO getEventById(String id);

    EventCreateDTO addEvent(String nameEvent, String description);

    boolean cancelEvent(String id);

    void deleteEvent(String id);
}
