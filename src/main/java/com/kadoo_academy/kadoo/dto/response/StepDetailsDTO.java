package com.kadoo_academy.kadoo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StepDetailsDTO(
        Long id,
        String title,
        String description,
        LocalDate date,
        String status,
        String type,
        EventDTO event,
        ActivityDTO activity
) {}