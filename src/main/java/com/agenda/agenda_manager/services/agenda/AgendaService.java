package com.agenda.agenda_manager.services.agenda;

import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaResponseDTO;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface AgendaService {

    AgendaCreateDTO createAgenda(Date startDate, Date endDate, Time startTime, Time endTime, String nameEvent, String description);
}
