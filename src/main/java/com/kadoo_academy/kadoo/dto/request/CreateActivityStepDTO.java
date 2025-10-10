package com.kadoo_academy.kadoo.dto.request;

import java.time.LocalDate;

public record CreateActivityStepDTO(
        String title,
        String description,
        LocalDate date,
        LocalDate dueDate,
        String file,
        Long edictId
) {
}
