package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDate;
import java.util.ArrayList;

public record EdictAttachUserDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String status,
        ArrayList<String> categories
) {
}
