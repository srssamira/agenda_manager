package com.agenda.agenda_manager.services.agenda;

import com.agenda.agenda_manager.controllers.dtos.AgendaDTO;

import java.util.List;

public interface AgendaService {
    public List<AgendaDTO> getAllAgendas();

    public AgendaDTO addAgenda(AgendaDTO agenda);
}
