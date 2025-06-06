package com.kadoo_academy.kadoo.dto;

import com.kadoo_academy.kadoo.models.enums.UserEnum;

import java.util.Date;

public record ListUsersDTO(Long id, String name, String email, UserEnum type, Boolean active, Date createdAt, Date updatedAt) {

}
