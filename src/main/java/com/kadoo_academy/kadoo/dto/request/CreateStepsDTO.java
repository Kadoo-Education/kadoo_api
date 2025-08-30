package com.kadoo_academy.kadoo.dto.request;

import java.time.LocalDateTime;

public record CreateStepsDTO(
        String title,
        String description,
        String time,
        LocalDateTime date,
        String type,
        String format,
        String adress,
        String meetinglink,
        String activityTitle,
        LocalDateTime dueDate,
        String pdf
) {
}
