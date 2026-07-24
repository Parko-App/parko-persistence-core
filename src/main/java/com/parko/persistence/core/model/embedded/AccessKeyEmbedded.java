package com.parko.persistence.core.model.embedded;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccessKeyEmbedded(
        UUID vehicleId,
        String code,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
