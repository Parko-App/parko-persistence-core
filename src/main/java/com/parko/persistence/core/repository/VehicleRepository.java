package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VehicleRepository extends JpaRepository<VehicleEntity, UUID> {

    List<VehicleEntity> findByUserId(UUID userId);
}
