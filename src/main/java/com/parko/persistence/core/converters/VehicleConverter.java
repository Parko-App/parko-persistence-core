package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.VehicleEmbedded;
import com.parko.persistence.core.model.entity.UserEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;

import java.util.UUID;

public final class VehicleConverter {

    private VehicleConverter() {
    }

    public static VehicleEntity toEntity(VehicleEmbedded embedded) {
        UserEntity user = new UserEntity();
        user.setId(embedded.userId());

        VehicleEntity entity = new VehicleEntity();
        entity.setId(UUID.randomUUID());
        entity.setUser(user);
        entity.setPlate(embedded.plate());
        entity.setBrand(embedded.brand());
        entity.setModel(embedded.model());
        entity.setActive(embedded.active());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static VehicleEmbedded toEmbedded(VehicleEntity entity) {
        return new VehicleEmbedded(
                entity.getUser().getId(),
                entity.getPlate(),
                entity.getBrand(),
                entity.getModel(),
                entity.isActive(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
