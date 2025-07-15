package com.kadoo_academy.kadoo.dto.response;

import com.kadoo_academy.kadoo.models.enums.UserEnum;

import java.util.Date;

public record ListUsersDTO(Long id, String name, String email,
                          Boolean active, Date createdAt) {

}
