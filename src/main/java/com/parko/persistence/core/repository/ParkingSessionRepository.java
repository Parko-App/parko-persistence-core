package com.parko.persistence.core.repository;

import com.parko.domain.lib.model.SessionStatus;
import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParkingSessionRepository extends JpaRepository<ParkingSessionEntity, UUID> {

    Optional<ParkingSessionEntity> findByPlateSnapshotAndStatus(String plateSnapshot, SessionStatus status);
}
