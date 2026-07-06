package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.TicketEmbedded;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.TicketEntity;

import java.util.UUID;

public final class TicketConverter {

    private TicketConverter() {
    }

    public static TicketEntity toEntity(TicketEmbedded embedded) {
        ParkingSessionEntity parkingSession = new ParkingSessionEntity();
        parkingSession.setId(embedded.parkingSessionId());

        TicketEntity entity = new TicketEntity();
        entity.setId(UUID.randomUUID());
        entity.setParkingSession(parkingSession);
        entity.setTicketNumber(embedded.ticketNumber());
        entity.setQrData(embedded.qrData());
        entity.setStatus(embedded.status());
        entity.setIssuedAt(embedded.issuedAt());
        entity.setPaidAt(embedded.paidAt());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static TicketEmbedded toEmbedded(TicketEntity entity) {
        return new TicketEmbedded(
                entity.getParkingSession().getId(),
                entity.getTicketNumber(),
                entity.getQrData(),
                entity.getStatus(),
                entity.getIssuedAt(),
                entity.getPaidAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
