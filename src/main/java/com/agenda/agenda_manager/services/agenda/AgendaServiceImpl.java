package com.agenda.agenda_manager.services.agenda;

import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaResponseDTO;
import com.agenda.agenda_manager.controllers.dtos.event.EventCreateDTO;
import com.agenda.agenda_manager.services.event.EventService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AgendaServiceImpl implements AgendaService {

    private EventService eventService;

    @Override
    public AgendaCreateDTO createAgenda(Date startDate, Date endDate, Time startTime, Time endTime, String nameEvent, String description) {
        AgendaCreateDTO agendaCreateDTO = new AgendaCreateDTO();
        agendaCreateDTO.setStartDate(startDate);
        agendaCreateDTO.setEndDate(endDate);
        agendaCreateDTO.setStartTime(startTime);
        agendaCreateDTO.setEndTime(endTime);

        EventCreateDTO newEvent = eventService.addEvent(nameEvent, description);

        agendaCreateDTO.setEvent(newEvent);

        return agendaCreateDTO;
    }


}
