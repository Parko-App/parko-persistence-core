package com.parko.persistence.core.repository;

import com.parko.domain.lib.model.TransactionStatus;
import com.parko.domain.lib.model.TransactionType;
import com.parko.persistence.core.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    boolean existsByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);

    Optional<TransactionEntity> findByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);
}
