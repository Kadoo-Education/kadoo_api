package com.kadoo_academy.kadoo.dto.request;

import java.util.ArrayList;
import java.util.Date;

public record CreateEdictDto(String title, String description, String linkDoc,
                             Date startDate, Date endDate, ArrayList<String> tags) {
}
