package com.kadoo_academy.kadoo.dto.request;

import java.util.List;

public record CreateMentorProfileDTO(
    String name,
    String email,
    String password,
    String cpf,
    List<String> area,
    String description,
    String linkedin
) {
}
