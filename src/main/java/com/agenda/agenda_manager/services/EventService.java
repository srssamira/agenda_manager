package com.agenda.agenda_manager.services;
import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventResponseDTO> getAllEvents();

    EventCreateDTO getEventCreate();

    Optional<EventCreateDTO> searchEvent(String id);

    EventCreateDTO getEventById(String id);

    EventCreateDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description);

    boolean controlActiveEvent(String id, boolean isActive);

    void deleteEvent(String id);
}
