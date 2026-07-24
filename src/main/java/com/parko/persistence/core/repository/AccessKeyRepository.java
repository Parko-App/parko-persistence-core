package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.AccessKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccessKeyRepository extends JpaRepository<AccessKeyEntity, UUID> {

    Optional<AccessKeyEntity> findByCode(String code);
}
