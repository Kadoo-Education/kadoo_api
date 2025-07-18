package com.kadoo_academy.kadoo.dto.request;

import java.util.List;

public record CreateEnterpriseProfileDTO(
        String name,
        String email,
        String password,
        String cpf,
        String cnpj
) {
}
