package com.kadoo_academy.kadoo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record ActivityDTO(
        LocalDate dueDate,
        String file
) {}