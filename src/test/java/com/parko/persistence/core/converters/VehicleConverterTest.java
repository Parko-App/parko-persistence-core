package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.VehicleEmbedded;
import com.parko.persistence.core.model.entity.UserEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleConverterTest {

    @Test
    void toEntityMapsAllFieldsAndGeneratesId() {
        UUID userId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        VehicleEmbedded embedded = new VehicleEmbedded(
                userId, "AB123CD", "Toyota", "Corolla", true, createdAt, updatedAt);

        VehicleEntity entity = VehicleConverter.toEntity(embedded);

        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getUser().getId()).isEqualTo(userId);
        assertThat(entity.getPlate()).isEqualTo("AB123CD");
        assertThat(entity.getBrand()).isEqualTo("Toyota");
        assertThat(entity.getModel()).isEqualTo("Corolla");
        assertThat(entity.isActive()).isTrue();
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEmbeddedMapsAllFields() {
        UUID userId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        UserEntity user = new UserEntity();
        user.setId(userId);
        VehicleEntity entity = new VehicleEntity();
        entity.setId(UUID.randomUUID());
        entity.setUser(user);
        entity.setPlate("XY987ZW");
        entity.setBrand("Ford");
        entity.setModel("Fiesta");
        entity.setActive(false);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        VehicleEmbedded embedded = VehicleConverter.toEmbedded(entity);

        assertThat(embedded.userId()).isEqualTo(userId);
        assertThat(embedded.plate()).isEqualTo("XY987ZW");
        assertThat(embedded.brand()).isEqualTo("Ford");
        assertThat(embedded.model()).isEqualTo("Fiesta");
        assertThat(embedded.active()).isFalse();
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }
}
