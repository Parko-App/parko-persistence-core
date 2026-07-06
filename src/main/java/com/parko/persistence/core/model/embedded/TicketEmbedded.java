package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.TicketStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TicketEmbedded(
        UUID parkingSessionId,
        String ticketNumber,
        String qrData,
        TicketStatus status,
        LocalDateTime issuedAt,
        LocalDateTime paidAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
