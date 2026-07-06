package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.BalanceAccountEmbedded;
import com.parko.persistence.core.model.entity.BalanceAccountEntity;
import com.parko.persistence.core.model.entity.UserEntity;

import java.util.UUID;

public final class BalanceAccountConverter {

    private BalanceAccountConverter() {
    }

    public static BalanceAccountEntity toEntity(BalanceAccountEmbedded embedded) {
        UserEntity user = new UserEntity();
        user.setId(embedded.userId());

        BalanceAccountEntity entity = new BalanceAccountEntity();
        entity.setId(UUID.randomUUID());
        entity.setUser(user);
        entity.setAmount(embedded.balance());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static BalanceAccountEmbedded toEmbedded(BalanceAccountEntity entity) {
        return new BalanceAccountEmbedded(
                entity.getUser().getId(),
                entity.getAmount(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
