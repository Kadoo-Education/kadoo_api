package com.kadoo_academy.kadoo.dto.request;

import com.kadoo_academy.kadoo.models.enums.ModeEnum;

import java.time.LocalDate;

public record CreateInPersonEventDTO(
    String title,
    String description,
    LocalDate date,
    String address,
    String mode,
    String format,
    Long edictId
) {
}
