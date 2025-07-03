package com.kadoo_academy.kadoo.dto.Response;

import com.kadoo_academy.kadoo.models.enums.UserEnum;

public record ResponseStudentsDto(Long id, UserEnum type) {
}
