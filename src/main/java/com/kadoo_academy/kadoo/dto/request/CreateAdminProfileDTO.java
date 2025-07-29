package com.kadoo_academy.kadoo.dto.request;

public record CreateAdminProfileDTO(
        String name,
        String email,
        String password,
        String cpf
) {
}
