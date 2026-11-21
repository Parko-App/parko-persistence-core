package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.AccessLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccessLogRepository extends JpaRepository<AccessLogEntity, UUID> {

    @EntityGraph(attributePaths = {"parkingSession"})
    Page<AccessLogEntity> findAllByOrderByOccurredAtDesc(Pageable pageable);
}
