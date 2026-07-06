package com.parko.persistence.core.model.embedded;

import java.time.LocalDateTime;
import java.util.UUID;

public record VehicleEmbedded(
        UUID userId,
        String plate,
        String brand,
        String model,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
