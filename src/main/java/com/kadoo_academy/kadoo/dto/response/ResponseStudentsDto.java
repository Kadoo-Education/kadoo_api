package com.kadoo_academy.kadoo.dto.response;

import com.kadoo_academy.kadoo.models.enums.UserEnum;

public record ResponseStudentsDto(Long id, UserEnum type) {
}
