package com.kadoo_academy.kadoo.dto.request;

public record CreateUserDTO(String name,
                            String email,
                            String password,
                            String role,
                            String cpf
        ) {
    }
