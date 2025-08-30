package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public record EdictDTO(
        Long id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String organizer,
        String file,
        String status,
        ArrayList<String> categories
) {
}
