package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDate;

public record StepDTO(
        Long id,
        String title,
        String description,
        LocalDate date,
        String format,
        String mode,
        String meetingLink,
        String address,
        LocalDate dueDate,
        String activityFile
) {
}
