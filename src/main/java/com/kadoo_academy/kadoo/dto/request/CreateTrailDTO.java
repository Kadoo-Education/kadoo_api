package com.kadoo_academy.kadoo.dto.request;

public record CreateTrailDTO(
        String title,
        String type,
        String date,
        String mode,
        String address,
        String time,
        String link,
        String activityTitle,
        String activityDescription,
        String activityUrl
) {
}
