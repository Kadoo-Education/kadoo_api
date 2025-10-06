package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDate;

public record StepDetailsDTO(
        Long id,
        String title,
        String description,
        LocalDate date,
        String status,
        EventDTO event
) {}