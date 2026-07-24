package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.AccessKeyEmbedded;
import com.parko.persistence.core.model.entity.AccessKeyEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;

import java.util.UUID;

public final class AccessKeyConverter {

    private AccessKeyConverter() {
    }

    public static AccessKeyEntity toEntity(AccessKeyEmbedded embedded) {
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setId(embedded.vehicleId());

        AccessKeyEntity entity = new AccessKeyEntity();
        entity.setId(UUID.randomUUID());
        entity.setVehicle(vehicle);
        entity.setCode(embedded.code());
        entity.setActive(embedded.active());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static AccessKeyEmbedded toEmbedded(AccessKeyEntity entity) {
        return new AccessKeyEmbedded(
                entity.getVehicle().getId(),
                entity.getCode(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
