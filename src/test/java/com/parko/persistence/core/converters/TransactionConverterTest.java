package com.parko.persistence.core.converters;

import com.parko.domain.lib.model.TransactionStatus;
import com.parko.domain.lib.model.TransactionType;
import com.parko.persistence.core.model.embedded.TransactionEmbedded;
import com.parko.persistence.core.model.entity.BalanceAccountEntity;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.TransactionEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionConverterTest {

    @Test
    void toEntityMapsTopUpWithoutParkingSession() {
        UUID id = UUID.randomUUID();
        UUID balanceAccountId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        TransactionEmbedded embedded = new TransactionEmbedded(
                id, balanceAccountId, null, TransactionType.TOPUP, BigDecimal.TEN,
                TransactionStatus.COMPLETED, "MERCADO_PAGO", "ext-ref", createdAt, updatedAt);

        TransactionEntity entity = TransactionConverter.toEntity(embedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getBalanceAccount().getId()).isEqualTo(balanceAccountId);
        assertThat(entity.getParkingSession()).isNull();
        assertThat(entity.getType()).isEqualTo(TransactionType.TOPUP);
        assertThat(entity.getAmount()).isEqualByComparingTo(BigDecimal.TEN);
        assertThat(entity.getStatus()).isEqualTo(TransactionStatus.COMPLETED);
        assertThat(entity.getPaymentProvider()).isEqualTo("MERCADO_PAGO");
        assertThat(entity.getExternalRef()).isEqualTo("ext-ref");
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEntityMapsChargeWithBothReferences() {
        UUID balanceAccountId = UUID.randomUUID();
        UUID parkingSessionId = UUID.randomUUID();
        TransactionEmbedded embedded = new TransactionEmbedded(
                UUID.randomUUID(), balanceAccountId, parkingSessionId, TransactionType.CHARGE, BigDecimal.ONE,
                TransactionStatus.PENDING, "INTERNAL_BALANCE", null, LocalDateTime.now(), LocalDateTime.now());

        TransactionEntity entity = TransactionConverter.toEntity(embedded);

        assertThat(entity.getBalanceAccount().getId()).isEqualTo(balanceAccountId);
        assertThat(entity.getParkingSession().getId()).isEqualTo(parkingSessionId);
    }

    @Test
    void toEmbeddedMapsEntityWithNullReferences() {
        LocalDateTime createdAt = LocalDateTime.now().minusHours(3);
        LocalDateTime updatedAt = LocalDateTime.now();
        TransactionEntity entity = new TransactionEntity();
        entity.setId(UUID.randomUUID());
        entity.setBalanceAccount(null);
        entity.setParkingSession(null);
        entity.setType(TransactionType.REFUND);
        entity.setAmount(BigDecimal.valueOf(20));
        entity.setStatus(TransactionStatus.FAILED);
        entity.setPaymentProvider("CASH");
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        TransactionEmbedded embedded = TransactionConverter.toEmbedded(entity);

        assertThat(embedded.balanceAccountId()).isNull();
        assertThat(embedded.parkingSessionId()).isNull();
        assertThat(embedded.type()).isEqualTo(TransactionType.REFUND);
        assertThat(embedded.amount()).isEqualByComparingTo(BigDecimal.valueOf(20));
        assertThat(embedded.status()).isEqualTo(TransactionStatus.FAILED);
        assertThat(embedded.paymentProvider()).isEqualTo("CASH");
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEmbeddedMapsEntityWithReferences() {
        UUID balanceAccountId = UUID.randomUUID();
        UUID parkingSessionId = UUID.randomUUID();
        BalanceAccountEntity balanceAccount = new BalanceAccountEntity();
        balanceAccount.setId(balanceAccountId);
        ParkingSessionEntity parkingSession = new ParkingSessionEntity();
        parkingSession.setId(parkingSessionId);
        TransactionEntity entity = new TransactionEntity();
        entity.setId(UUID.randomUUID());
        entity.setBalanceAccount(balanceAccount);
        entity.setParkingSession(parkingSession);
        entity.setType(TransactionType.CHARGE);
        entity.setAmount(BigDecimal.ONE);
        entity.setStatus(TransactionStatus.COMPLETED);
        entity.setPaymentProvider("INTERNAL_BALANCE");

        TransactionEmbedded embedded = TransactionConverter.toEmbedded(entity);

        assertThat(embedded.balanceAccountId()).isEqualTo(balanceAccountId);
        assertThat(embedded.parkingSessionId()).isEqualTo(parkingSessionId);
    }
}
