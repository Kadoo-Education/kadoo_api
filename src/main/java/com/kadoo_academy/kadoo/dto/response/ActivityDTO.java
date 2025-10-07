package com.kadoo_academy.kadoo.dto.response;

import java.time.LocalDate;

public record ActivityDTO(
        LocalDate dueDate,
        String file
) {}