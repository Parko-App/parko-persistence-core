package com.parko.persistence.core.converters;

import com.parko.domain.lib.model.TicketStatus;
import com.parko.persistence.core.model.embedded.TicketEmbedded;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.TicketEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TicketConverterTest {

    @Test
    void toEntityMapsAllFieldsAndGeneratesId() {
        UUID parkingSessionId = UUID.randomUUID();
        LocalDateTime issuedAt = LocalDateTime.now().minusHours(1);
        LocalDateTime createdAt = LocalDateTime.now().minusHours(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        TicketEmbedded embedded = new TicketEmbedded(
                parkingSessionId, "T-0001", "qr-data", TicketStatus.PENDING_PAYMENT,
                issuedAt, null, createdAt, updatedAt);

        TicketEntity entity = TicketConverter.toEntity(embedded);

        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getParkingSession().getId()).isEqualTo(parkingSessionId);
        assertThat(entity.getTicketNumber()).isEqualTo("T-0001");
        assertThat(entity.getQrData()).isEqualTo("qr-data");
        assertThat(entity.getStatus()).isEqualTo(TicketStatus.PENDING_PAYMENT);
        assertThat(entity.getIssuedAt()).isEqualTo(issuedAt);
        assertThat(entity.getPaidAt()).isNull();
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEmbeddedMapsAllFields() {
        UUID parkingSessionId = UUID.randomUUID();
        LocalDateTime issuedAt = LocalDateTime.now().minusHours(2);
        LocalDateTime paidAt = LocalDateTime.now();
        ParkingSessionEntity parkingSession = new ParkingSessionEntity();
        parkingSession.setId(parkingSessionId);
        TicketEntity entity = new TicketEntity();
        entity.setId(UUID.randomUUID());
        entity.setParkingSession(parkingSession);
        entity.setTicketNumber("T-0002");
        entity.setQrData("qr-data-2");
        entity.setStatus(TicketStatus.PAID);
        entity.setIssuedAt(issuedAt);
        entity.setPaidAt(paidAt);
        entity.setCreatedAt(issuedAt);
        entity.setUpdatedAt(paidAt);

        TicketEmbedded embedded = TicketConverter.toEmbedded(entity);

        assertThat(embedded.parkingSessionId()).isEqualTo(parkingSessionId);
        assertThat(embedded.ticketNumber()).isEqualTo("T-0002");
        assertThat(embedded.qrData()).isEqualTo("qr-data-2");
        assertThat(embedded.status()).isEqualTo(TicketStatus.PAID);
        assertThat(embedded.issuedAt()).isEqualTo(issuedAt);
        assertThat(embedded.paidAt()).isEqualTo(paidAt);
        assertThat(embedded.createdAt()).isEqualTo(issuedAt);
        assertThat(embedded.updatedAt()).isEqualTo(paidAt);
    }
}
