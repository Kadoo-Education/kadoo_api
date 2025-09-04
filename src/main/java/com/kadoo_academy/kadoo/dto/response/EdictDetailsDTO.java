package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record EdictDetailsDTO(
        Long id,
        String title,
        String description,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String organizer,
        String contact,
        String file,
        String status,
        String location,
        ArrayList<String> categories,
        List<StepDTO> steps
) {
}
