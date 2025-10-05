package com.kadoo_academy.kadoo.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateStepsDTO(
        String title,
        String description,
        LocalDate date,
        String format,
        String mode,
        String address,
        String meetingLink,
        LocalDate dueDate,
        String activityFile
) {
}
