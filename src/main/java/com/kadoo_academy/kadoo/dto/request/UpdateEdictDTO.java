package com.kadoo_academy.kadoo.dto.request;

import java.time.LocalDate;
import java.util.List;

public record UpdateEdictDTO(
        String title,
        String description,
        String organizer,
        String contact,
        String location,
        LocalDate startDate,
        LocalDate endDate,
        String file,
        List<String> categories
) {
}
