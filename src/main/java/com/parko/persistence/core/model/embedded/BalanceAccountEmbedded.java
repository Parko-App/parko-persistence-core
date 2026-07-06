package com.parko.persistence.core.model.embedded;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record BalanceAccountEmbedded(
        UUID userId,
        BigDecimal balance,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
