package com.agenda.agenda_manager.services.event;

import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.event.response.EventResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.event.response.StartTimeDTO;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    List<EventCreateDTO> eventCreateList = new ArrayList<>();

    public String sortId() {
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        return id.substring(0, 8);
    }

    public Time updateEventStartTime(String id, StartTimeDTO startTimeDTO) {
        Time parsedStartTime = Time.valueOf(startTimeDTO.getStartTime());
        return parsedStartTime;
    }

    @Override
    public Optional<EventCreateDTO> searchVehicle(String id) {
        return eventCreateList.stream().filter(eventCreateDTO -> eventCreateDTO.getId().equalsIgnoreCase(id)).findFirst();
    }

    @Override
    public EventCreateDTO addEvent(String id, String nameEvent, String description) {
        if (searchVehicle(id).isPresent())
            throw new RuntimeException("event already exist");

        EventCreateDTO eventCreateDTO = new EventCreateDTO();
        eventCreateDTO.setId(sortId());
        eventCreateDTO.setEventName(nameEvent);
        eventCreateDTO.setDescription(description);

        return eventCreateDTO;
    }

    @Override
    public boolean cancelEvent(String id) {
        if (searchVehicle(id).isPresent())
            return false;
        return true;
    }

    @Override
    public void deleteEvent(String id) {
        eventCreateList.stream().filter(eventCreateDTO -> !eventCreateDTO.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
    }

    @Override
    public EventResponseDTO getEventById(String id) {
        if (searchVehicle(id).isEmpty()) {
            throw new RuntimeException("event doesn't exist");
        }

        EventResponseDTO eventResponseDTO = new EventResponseDTO();
        eventResponseDTO.setId(id);
        eventResponseDTO.setNameEvent(searchVehicle(id).get().getEventName());
        eventResponseDTO.setDescription(searchVehicle(id).get().getDescription());
        eventResponseDTO.setActiveEvent(!cancelEvent(id));
        eventResponseDTO.setStartTime(null);

        return eventResponseDTO;
    }
}
