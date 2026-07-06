package com.parko.persistence.core.converters;

import com.parko.domain.lib.model.AccessEventType;
import com.parko.domain.lib.model.AccessMethod;
import com.parko.domain.lib.model.AccessResult;
import com.parko.persistence.core.model.embedded.AccessLogEmbedded;
import com.parko.persistence.core.model.entity.AccessLogEntity;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccessLogConverterTest {

    @Test
    void toEntityMapsEventWithParkingSession() {
        UUID id = UUID.randomUUID();
        UUID parkingSessionId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.now().minusMinutes(5);
        LocalDateTime createdAt = LocalDateTime.now().minusMinutes(5);
        LocalDateTime updatedAt = LocalDateTime.now();
        AccessLogEmbedded embedded = new AccessLogEmbedded(
                id, parkingSessionId, AccessEventType.EXIT, AccessMethod.TICKET, AccessResult.DENIED,
                "raw-payload", occurredAt, createdAt, updatedAt);

        AccessLogEntity entity = AccessLogConverter.toEntity(embedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getParkingSession().getId()).isEqualTo(parkingSessionId);
        assertThat(entity.getEventType()).isEqualTo(AccessEventType.EXIT);
        assertThat(entity.getAccessMethod()).isEqualTo(AccessMethod.TICKET);
        assertThat(entity.getResult()).isEqualTo(AccessResult.DENIED);
        assertThat(entity.getRawPayload()).isEqualTo("raw-payload");
        assertThat(entity.getOccurredAt()).isEqualTo(occurredAt);
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEntityMapsEventWithoutParkingSession() {
        AccessLogEmbedded embedded = new AccessLogEmbedded(
                UUID.randomUUID(), null, AccessEventType.ENTRY, AccessMethod.PLATE, AccessResult.DENIED,
                "raw-payload", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        AccessLogEntity entity = AccessLogConverter.toEntity(embedded);

        assertThat(entity.getParkingSession()).isNull();
    }

    @Test
    void toEmbeddedMapsEventWithParkingSession() {
        UUID parkingSessionId = UUID.randomUUID();
        LocalDateTime occurredAt = LocalDateTime.now().minusMinutes(10);
        ParkingSessionEntity parkingSession = new ParkingSessionEntity();
        parkingSession.setId(parkingSessionId);
        AccessLogEntity entity = new AccessLogEntity();
        entity.setId(UUID.randomUUID());
        entity.setParkingSession(parkingSession);
        entity.setEventType(AccessEventType.ENTRY);
        entity.setAccessMethod(AccessMethod.PROXIMITY_KEY);
        entity.setResult(AccessResult.AUTHORIZED);
        entity.setRawPayload("payload-2");
        entity.setOccurredAt(occurredAt);

        AccessLogEmbedded embedded = AccessLogConverter.toEmbedded(entity);

        assertThat(embedded.parkingSessionId()).isEqualTo(parkingSessionId);
        assertThat(embedded.eventType()).isEqualTo(AccessEventType.ENTRY);
        assertThat(embedded.accessMethod()).isEqualTo(AccessMethod.PROXIMITY_KEY);
        assertThat(embedded.result()).isEqualTo(AccessResult.AUTHORIZED);
        assertThat(embedded.rawPayload()).isEqualTo("payload-2");
        assertThat(embedded.occurredAt()).isEqualTo(occurredAt);
    }

    @Test
    void toEmbeddedMapsEventWithoutParkingSession() {
        AccessLogEntity entity = new AccessLogEntity();
        entity.setId(UUID.randomUUID());
        entity.setParkingSession(null);
        entity.setEventType(AccessEventType.ENTRY);
        entity.setAccessMethod(AccessMethod.PLATE);
        entity.setResult(AccessResult.DENIED);
        entity.setRawPayload("payload-3");
        entity.setOccurredAt(LocalDateTime.now());

        AccessLogEmbedded embedded = AccessLogConverter.toEmbedded(entity);

        assertThat(embedded.parkingSessionId()).isNull();
    }
}
