package com.parko.persistence.core.converters;

import com.parko.domain.lib.model.SessionStatus;
import com.parko.domain.lib.model.SessionType;
import com.parko.persistence.core.model.embedded.ParkingSessionEmbedded;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingSessionConverterTest {

    @Test
    void toEntityMapsRegisteredSessionWithVehicle() {
        UUID id = UUID.randomUUID();
        UUID vehicleId = UUID.randomUUID();
        LocalDateTime entryAt = LocalDateTime.now().minusHours(1);
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        ParkingSessionEmbedded embedded = new ParkingSessionEmbedded(
                id, vehicleId, "AB123CD", SessionType.REGISTERED, SessionStatus.ACTIVE,
                entryAt, null, createdAt, updatedAt);

        ParkingSessionEntity entity = ParkingSessionConverter.toEntity(embedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getVehicle().getId()).isEqualTo(vehicleId);
        assertThat(entity.getPlateSnapshot()).isEqualTo("AB123CD");
        assertThat(entity.getSessionType()).isEqualTo(SessionType.REGISTERED);
        assertThat(entity.getStatus()).isEqualTo(SessionStatus.ACTIVE);
        assertThat(entity.getEntryAt()).isEqualTo(entryAt);
        assertThat(entity.getExitAt()).isNull();
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEntityMapsVisitorSessionWithoutVehicle() {
        UUID id = UUID.randomUUID();
        LocalDateTime entryAt = LocalDateTime.now();
        ParkingSessionEmbedded embedded = new ParkingSessionEmbedded(
                id, null, null, SessionType.VISITOR, SessionStatus.ACTIVE,
                entryAt, null, entryAt, entryAt);

        ParkingSessionEntity entity = ParkingSessionConverter.toEntity(embedded);

        assertThat(entity.getVehicle()).isNull();
        assertThat(entity.getPlateSnapshot()).isNull();
        assertThat(entity.getSessionType()).isEqualTo(SessionType.VISITOR);
    }

    @Test
    void toEmbeddedMapsSessionWithVehicle() {
        UUID id = UUID.randomUUID();
        UUID vehicleId = UUID.randomUUID();
        LocalDateTime entryAt = LocalDateTime.now().minusHours(2);
        LocalDateTime exitAt = LocalDateTime.now();
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(vehicleId);
        ParkingSessionEntity entity = new ParkingSessionEntity();
        entity.setId(id);
        entity.setVehicle(vehicle);
        entity.setPlateSnapshot("XY987ZW");
        entity.setSessionType(SessionType.REGISTERED);
        entity.setStatus(SessionStatus.COMPLETED);
        entity.setEntryAt(entryAt);
        entity.setExitAt(exitAt);
        entity.setCreatedAt(entryAt);
        entity.setUpdatedAt(exitAt);

        ParkingSessionEmbedded embedded = ParkingSessionConverter.toEmbedded(entity);

        assertThat(embedded.id()).isEqualTo(id);
        assertThat(embedded.vehicleId()).isEqualTo(vehicleId);
        assertThat(embedded.plateSnapshot()).isEqualTo("XY987ZW");
        assertThat(embedded.sessionType()).isEqualTo(SessionType.REGISTERED);
        assertThat(embedded.status()).isEqualTo(SessionStatus.COMPLETED);
        assertThat(embedded.entryAt()).isEqualTo(entryAt);
        assertThat(embedded.exitAt()).isEqualTo(exitAt);
    }

    @Test
    void toEmbeddedMapsSessionWithoutVehicle() {
        ParkingSessionEntity entity = new ParkingSessionEntity();
        entity.setId(UUID.randomUUID());
        entity.setVehicle(null);
        entity.setSessionType(SessionType.VISITOR);
        entity.setStatus(SessionStatus.ACTIVE);
        entity.setEntryAt(LocalDateTime.now());

        ParkingSessionEmbedded embedded = ParkingSessionConverter.toEmbedded(entity);

        assertThat(embedded.vehicleId()).isNull();
        assertThat(embedded.plateSnapshot()).isNull();
    }
}
