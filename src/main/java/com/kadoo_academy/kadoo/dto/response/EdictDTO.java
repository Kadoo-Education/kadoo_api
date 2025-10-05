package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record EdictDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String organizer,
        String contact,
        String file,
        String status,
        String location,
        ArrayList<String> categories
) {
}
