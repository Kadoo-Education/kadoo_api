package com.kadoo_academy.kadoo.dto.response;

import com.kadoo_academy.kadoo.models.enums.EdictStatusEnum;

import java.time.LocalDate;
import java.util.ArrayList;

public record EdictAttachUserDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        EdictStatusEnum status,
        ArrayList<String> categories
) {
}
