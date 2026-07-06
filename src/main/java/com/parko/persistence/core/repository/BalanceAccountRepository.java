package com.parko.persistence.core.repository;

import com.parko.persistence.core.model.entity.BalanceAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BalanceAccountRepository extends JpaRepository<BalanceAccountEntity, UUID> {
}
