package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {

    Optional<TicketEntity> findByParkingSession_Id(UUID parkingSessionId);
}
