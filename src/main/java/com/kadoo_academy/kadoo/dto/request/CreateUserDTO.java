package com.kadoo_academy.kadoo.dto.request;

import com.kadoo_academy.kadoo.models.enums.UserEnum;

public record CreateUserDTO(Long id, String name, String email, String password, UserEnum type) {
}
