package com.agenda.agenda_manager.services;
import com.agenda.agenda_manager.controllers.dtos.EventListDTO;
import com.agenda.agenda_manager.controllers.dtos.EventResultDTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<EventResultDTO> getAllEvents(List<List<EventListDTO>> eventListDTOToResultList);

    List<EventListDTO> getEventList();

    Optional<EventListDTO> searchEvent(String id);

    EventListDTO getEventById(String id);

    EventListDTO addEvent(Date startDate, Date endDate, Time startTime, Time endTime, String name, String description);

    boolean controlActiveEvent(String id, boolean isActive);

    void deleteEvent(String id);
}
