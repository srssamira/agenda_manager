package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.response.EventResponseDTO;

import java.util.Optional;

public interface EventService {

    public Optional<EventCreateDTO> searchVehicle(String id);

    public EventResponseDTO getEventById(String id);

    public EventCreateDTO addEvent(String id, String nameEvent, String description);

    public boolean cancelEvent(String id);

    public void deleteEvent(String id);
}
