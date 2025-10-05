package com.kadoo_academy.kadoo.dto.response;

public record EventDTO(
        Long id,
        String type,
        String mode,
        String format,
        String meetingLink,
        String address
) {
}
