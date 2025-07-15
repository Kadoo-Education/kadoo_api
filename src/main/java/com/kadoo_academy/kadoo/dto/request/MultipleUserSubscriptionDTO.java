package com.kadoo_academy.kadoo.dto.request;

import java.util.List;

public record MultipleUserSubscriptionDTO(Long edictId, List<Long> userIds) {
}
