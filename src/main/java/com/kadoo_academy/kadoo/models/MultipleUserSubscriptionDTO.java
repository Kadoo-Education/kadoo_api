package com.kadoo_academy.kadoo.models;

import lombok.Data;

import java.util.List;

@Data
public class MultipleUserSubscriptionDTO {
    private Long edictId;
    private List<Long> userIds;
}
