package com.agenda.agenda_manager.controllers.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EventRegisterDTO {
    @NotNull(message = "required field")
    @Size(min = 5, message = "can't be empty")
    private String eventName;

    @NotNull(message = "required field")
    @Size(min = 5, message = "can't be empty")
    private String description;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
