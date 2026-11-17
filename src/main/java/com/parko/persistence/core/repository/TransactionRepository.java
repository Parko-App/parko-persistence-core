package com.parko.persistence.core.repository;

import com.parko.domain.lib.model.TransactionStatus;
import com.parko.domain.lib.model.TransactionType;
import com.parko.persistence.core.model.entity.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    boolean existsByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);

    Optional<TransactionEntity> findByParkingSession_IdAndTypeAndStatus(UUID parkingSessionId, TransactionType type, TransactionStatus status);

    Page<TransactionEntity> findByBalanceAccount_User_FirebaseUidOrderByCreatedAtDesc(String firebaseUid, Pageable pageable);

    Page<TransactionEntity> findByBalanceAccount_User_FirebaseUidAndCreatedAtBetweenOrderByCreatedAtDesc(
            String firebaseUid, LocalDateTime from, LocalDateTime to, Pageable pageable);

    @Query("""
            SELECT t FROM TransactionEntity t
            WHERE t.balanceAccount.user.firebaseUid = :firebaseUid
              AND NOT (t.type = :topUpType AND t.status <> :completedStatus)
            ORDER BY t.createdAt DESC
            """)
    Page<TransactionEntity> findVisibleHistoryByFirebaseUid(
            @Param("firebaseUid") String firebaseUid,
            @Param("topUpType") TransactionType topUpType,
            @Param("completedStatus") TransactionStatus completedStatus,
            Pageable pageable);

    @Query("""
            SELECT t FROM TransactionEntity t
            WHERE t.balanceAccount.user.firebaseUid = :firebaseUid
              AND t.createdAt >= :from AND t.createdAt < :to
              AND NOT (t.type = :topUpType AND t.status <> :completedStatus)
            ORDER BY t.createdAt DESC
            """)
    Page<TransactionEntity> findVisibleHistoryByFirebaseUidAndPeriod(
            @Param("firebaseUid") String firebaseUid,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to,
            @Param("topUpType") TransactionType topUpType,
            @Param("completedStatus") TransactionStatus completedStatus,
            Pageable pageable);
}
