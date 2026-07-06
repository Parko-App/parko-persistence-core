package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.SessionStatus;
import com.parko.domain.lib.model.SessionType;

import java.time.LocalDateTime;
import java.util.UUID;

public record ParkingSessionEmbedded(
        UUID id,
        UUID vehicleId,
        String plateSnapshot,
        SessionType sessionType,
        SessionStatus status,
        LocalDateTime entryAt,
        LocalDateTime exitAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
