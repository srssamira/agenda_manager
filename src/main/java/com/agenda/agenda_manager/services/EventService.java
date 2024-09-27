package com.agenda.agenda_manager.services;
import com.agenda.agenda_manager.controllers.dtos.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.EventViewDTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventResponseDTO> getAllEvents();

    EventCreateDTO getEventCreate();

    Optional<EventViewDTO> searchEvent(String id);

    EventViewDTO getEventById(String id);

    EventCreateDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description);

    List<EventViewDTO> cancelEvent(String id);

    void deleteEvent(String id);
}
