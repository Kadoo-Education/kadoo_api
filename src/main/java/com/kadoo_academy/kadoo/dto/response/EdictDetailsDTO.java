package com.kadoo_academy.kadoo.dto.response;

import com.kadoo_academy.kadoo.models.enums.EdictStatusEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record EdictDetailsDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        String organizer,
        String contact,
        String file,
        EdictStatusEnum status,
        String location,
        ArrayList<String> categories,
        List<StepDTO> steps
) {
}
