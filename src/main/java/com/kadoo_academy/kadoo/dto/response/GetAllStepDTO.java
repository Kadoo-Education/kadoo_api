package com.kadoo_academy.kadoo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record GetAllStepDTO(
        Long id,
        String title,
        String description,
        LocalDate date,
        String status,
        String kind,          // "event" | "activity"
        EventDTO event,       // presente quando kind == "event"
        ActivityDTO activity  // presente quando kind == "activity"
) {}
