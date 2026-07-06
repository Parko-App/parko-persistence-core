package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.ParkingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParkingSessionRepository extends JpaRepository<ParkingSessionEntity, UUID> {
}
