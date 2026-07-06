package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.AccessEventType;
import com.parko.domain.lib.model.AccessMethod;
import com.parko.domain.lib.model.AccessResult;

import java.time.LocalDateTime;
import java.util.UUID;

public record AccessLogEmbedded(
        UUID id,
        UUID parkingSessionId,
        AccessEventType eventType,
        AccessMethod accessMethod,
        AccessResult result,
        String rawPayload,
        LocalDateTime occurredAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
