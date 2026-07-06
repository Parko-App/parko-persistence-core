package com.parko.persistence.core.converters;

import com.parko.persistence.core.model.embedded.EmailEmbedded;
import com.parko.persistence.core.model.embedded.UserEmbedded;
import com.parko.persistence.core.model.entity.UserEntity;

public final class UserConverter {

    private UserConverter() {
    }

    public static UserEntity toEntity(UserEmbedded embedded) {
        UserEntity entity = new UserEntity();
        entity.setId(embedded.id());
        entity.setStudentId(embedded.studentId());
        entity.setFullName(embedded.fullName());
        entity.setEmail(embedded.email().value());
        entity.setInstitutionalDomain(embedded.email().institutionalDomain());
        entity.setPasswordHash(embedded.passwordHash());
        entity.setRole(embedded.role());
        entity.setCreatedAt(embedded.createdAt());
        entity.setUpdatedAt(embedded.updatedAt());
        return entity;
    }

    public static UserEmbedded toEmbedded(UserEntity entity) {
        EmailEmbedded email = new EmailEmbedded(entity.getEmail(), entity.getStudentId(), entity.getInstitutionalDomain());
        return new UserEmbedded(
                entity.getId(),
                entity.getStudentId(),
                entity.getFullName(),
                email,
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
