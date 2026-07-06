package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.AccessLogEmbedded;
import com.parko.persistence.core.model.entity.AccessLogEntity;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;

public final class AccessLogConverter {

    private AccessLogConverter() {
    }

    public static AccessLogEntity toEntity(AccessLogEmbedded embedded) {
        ParkingSessionEntity parkingSession = null;
        if (embedded.parkingSessionId() != null) {
            parkingSession = new ParkingSessionEntity();
            parkingSession.setId(embedded.parkingSessionId());
        }

        AccessLogEntity entity = new AccessLogEntity();
        entity.setId(embedded.id());
        entity.setParkingSession(parkingSession);
        entity.setEventType(embedded.eventType());
        entity.setAccessMethod(embedded.accessMethod());
        entity.setResult(embedded.result());
        entity.setRawPayload(embedded.rawPayload());
        entity.setOccurredAt(embedded.occurredAt());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static AccessLogEmbedded toEmbedded(AccessLogEntity entity) {
        var parkingSession = entity.getParkingSession();
        return new AccessLogEmbedded(
                entity.getId(),
                parkingSession != null ? parkingSession.getId() : null,
                entity.getEventType(),
                entity.getAccessMethod(),
                entity.getResult(),
                entity.getRawPayload(),
                entity.getOccurredAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
