package com.kadoo_academy.kadoo.dto;

import java.util.Date;

public record CreateEdictDto(Long id, String title, String description, String linkDoc,Date startDate,Date endDate ) {
}
