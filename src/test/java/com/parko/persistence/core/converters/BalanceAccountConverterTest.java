package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.BalanceAccountEmbedded;
import com.parko.persistence.core.model.entity.BalanceAccountEntity;
import com.parko.persistence.core.model.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class BalanceAccountConverterTest {

    @Test
    void toEntityMapsAllFieldsAndGeneratesId() {
        UUID userId = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        BalanceAccountEmbedded embedded = new BalanceAccountEmbedded(
                userId, BigDecimal.valueOf(150.50), createdAt, updatedAt);

        BalanceAccountEntity entity = BalanceAccountConverter.toEntity(embedded);

        assertThat(entity.getId()).isNotNull();
        assertThat(entity.getUser().getId()).isEqualTo(userId);
        assertThat(entity.getAmount()).isEqualByComparingTo(BigDecimal.valueOf(150.50));
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
        BalanceAccountEntity entity = new BalanceAccountEntity();
        entity.setId(UUID.randomUUID());
        entity.setUser(user);
        entity.setAmount(BigDecimal.valueOf(75));
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        BalanceAccountEmbedded embedded = BalanceAccountConverter.toEmbedded(entity);

        assertThat(embedded.userId()).isEqualTo(userId);
        assertThat(embedded.balance()).isEqualByComparingTo(BigDecimal.valueOf(75));
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }
}
