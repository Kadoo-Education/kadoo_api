package com.kadoo_academy.kadoo.dto;

import java.util.List;

public record MultipleUserSubscriptionDTO(Long edictId, List<Long> userIds) {
}
