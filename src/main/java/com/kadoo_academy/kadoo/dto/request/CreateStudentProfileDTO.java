package com.kadoo_academy.kadoo.dto.request;


public record CreateStudentProfileDTO(
        String name,
        String email,
        String password,
        String cpf,
        String birthDate
) {
}
