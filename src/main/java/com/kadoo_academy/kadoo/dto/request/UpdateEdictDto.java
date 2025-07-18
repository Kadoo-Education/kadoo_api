package com.kadoo_academy.kadoo.dto.request;

import java.util.Date;

public record UpdateEdictDto(String title, String description,String linkDoc,Date startDate,Date endDate, Boolean active) {
}
