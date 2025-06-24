package com.kadoo_academy.kadoo.dto.Response;

import java.util.Date;

public record ResponseEdictDto(Long id, String title, String description,String linkDoc, Date startDate, Date endDate ,Boolean active) {
}
