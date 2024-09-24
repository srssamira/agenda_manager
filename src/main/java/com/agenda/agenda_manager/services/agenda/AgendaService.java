package com.agenda.agenda_manager.services.agenda;

import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaCreateDTO;
import com.agenda.agenda_manager.controllers.dtos.agenda.AgendaResponseDTO;

import java.util.List;

public interface AgendaService {
    public List<AgendaResponseDTO> getAllAgendas();

    public List<AgendaCreateDTO> addAgenda(AgendaCreateDTO agenda);
}
