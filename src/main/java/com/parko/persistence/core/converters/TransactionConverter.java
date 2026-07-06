package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.TransactionEmbedded;
import com.parko.persistence.core.model.entity.BalanceAccountEntity;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import com.parko.persistence.core.model.entity.TransactionEntity;

public final class TransactionConverter {

    private TransactionConverter() {
    }

    public static TransactionEntity toEntity(TransactionEmbedded embedded) {
        BalanceAccountEntity balanceAccount = null;
        if (embedded.balanceAccountId() != null) {
            balanceAccount = new BalanceAccountEntity();
            balanceAccount.setId(embedded.balanceAccountId());
        }

        ParkingSessionEntity parkingSession = null;
        if (embedded.parkingSessionId() != null) {
            parkingSession = new ParkingSessionEntity();
            parkingSession.setId(embedded.parkingSessionId());
        }

        TransactionEntity entity = new TransactionEntity();
        entity.setId(embedded.id());
        entity.setBalanceAccount(balanceAccount);
        entity.setParkingSession(parkingSession);
        entity.setType(embedded.type());
        entity.setAmount(embedded.amount());
        entity.setStatus(embedded.status());
        entity.setPaymentProvider(embedded.paymentProvider());
        entity.setExternalRef(embedded.externalRef());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static TransactionEmbedded toEmbedded(TransactionEntity entity) {
        var balanceAccount = entity.getBalanceAccount();
        var parkingSession = entity.getParkingSession();
        return new TransactionEmbedded(
                entity.getId(),
                balanceAccount != null ? balanceAccount.getId() : null,
                parkingSession != null ? parkingSession.getId() : null,
                entity.getType(),
                entity.getAmount(),
                entity.getStatus(),
                entity.getPaymentProvider(),
                entity.getExternalRef(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
