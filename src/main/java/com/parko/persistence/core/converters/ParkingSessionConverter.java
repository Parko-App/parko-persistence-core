package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.ParkingSessionEmbedded;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.VehicleEntity;

public final class ParkingSessionConverter {

    private ParkingSessionConverter() {
    }

    public static ParkingSessionEntity toEntity(ParkingSessionEmbedded embedded) {
        VehicleEntity vehicle = null;
        if (embedded.vehicleId() != null) {
            vehicle = new VehicleEntity();
            vehicle.setId(embedded.vehicleId());
        }

        ParkingSessionEntity entity = new ParkingSessionEntity();
        entity.setId(embedded.id());
        entity.setVehicle(vehicle);
        entity.setPlateSnapshot(embedded.plateSnapshot());
        entity.setSessionType(embedded.sessionType());
        entity.setStatus(embedded.status());
        entity.setEntryAt(embedded.entryAt());
        entity.setExitAt(embedded.exitAt());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static ParkingSessionEmbedded toEmbedded(ParkingSessionEntity entity) {
        var vehicle = entity.getVehicle();
        return new ParkingSessionEmbedded(
                entity.getId(),
                vehicle != null ? vehicle.getId() : null,
                entity.getPlateSnapshot(),
                entity.getSessionType(),
                entity.getStatus(),
                entity.getEntryAt(),
                entity.getExitAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
