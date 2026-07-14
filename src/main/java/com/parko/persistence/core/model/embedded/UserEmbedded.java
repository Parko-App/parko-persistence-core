package com.parko.persistence.core.model.embedded;

import com.parko.domain.lib.model.UserRole;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserEmbedded(
        UUID id,
        String studentId,
        String fullName,
        EmailEmbedded email,
        String firebaseUid,
        UserRole role,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
