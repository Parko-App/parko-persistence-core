package com.parko.persistence.core.repository;

import com.parko.domain.lib.model.TransactionStatus;
import com.parko.domain.lib.model.TransactionType;
import com.parko.persistence.core.model.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    boolean existsByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);

    Optional<TransactionEntity> findByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);

    Page<TransactionEntity> findByBalanceAccount_User_FirebaseUidOrderByCreatedAtDesc(String firebaseUid, Pageable pageable);

    Page<TransactionEntity> findByBalanceAccount_User_FirebaseUidAndCreatedAtBetweenOrderByCreatedAtDesc(
            String firebaseUid, LocalDateTime from, LocalDateTime to, Pageable pageable);
}
