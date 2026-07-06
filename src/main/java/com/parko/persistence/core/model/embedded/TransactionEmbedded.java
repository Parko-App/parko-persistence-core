package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.TransactionStatus;
import com.parko.domain.lib.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionEmbedded(
        UUID id,
        UUID balanceAccountId,
        UUID parkingSessionId,
        TransactionType type,
        BigDecimal amount,
        TransactionStatus status,
        String paymentProvider,
        String externalRef,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
