package com.kadoo_academy.kadoo.dto.response;

import java.util.ArrayList;
import java.util.Date;

public record ResponseEdictDto(Long id, String title, String category, String description, String linkDoc, Date startDate, Date endDate, Boolean active,
                               ArrayList<String> tag) {
}
