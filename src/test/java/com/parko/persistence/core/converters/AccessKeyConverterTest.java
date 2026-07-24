package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.AccessKeyEmbedded;
import com.parko.persistence.core.model.entity.AccessKeyEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class AccessKeyConverterTest {

    @Test
    void toEntityMapsAllFieldsAndGeneratesId() {
        UUID vehicleId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        AccessKeyEmbedded embedded = new AccessKeyEmbedded(
                vehicleId, "AK-0001", true, createdAt, updatedAt);

        AccessKeyEntity entity = AccessKeyConverter.toEntity(embedded);

        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getVehicle().getId()).isEqualTo(vehicleId);
        assertThat(entity.getCode()).isEqualTo("AK-0001");
        assertThat(entity.isActive()).isTrue();
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEmbeddedMapsAllFields() {
        UUID vehicleId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(vehicleId);
        AccessKeyEntity entity = new AccessKeyEntity();
        entity.setId(UUID.randomUUID());
        entity.setVehicle(vehicle);
        entity.setCode("AK-0002");
        entity.setActive(false);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        AccessKeyEmbedded embedded = AccessKeyConverter.toEmbedded(entity);

        assertThat(embedded.vehicleId()).isEqualTo(vehicleId);
        assertThat(embedded.code()).isEqualTo("AK-0002");
        assertThat(embedded.active()).isFalse();
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }
}
