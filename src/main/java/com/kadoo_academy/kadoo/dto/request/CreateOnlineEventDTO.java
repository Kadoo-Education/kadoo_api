package com.kadoo_academy.kadoo.dto.request;

import java.time.LocalDate;

public record CreateOnlineEventDTO(
        String title,
        String description,
        LocalDate date,
        String meetingLink,
        String mode,
        String format,
        Long edictId
) {
}
