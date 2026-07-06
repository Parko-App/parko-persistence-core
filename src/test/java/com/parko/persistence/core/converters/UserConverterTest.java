package com.parko.persistence.core.converters;

import com.parko.domain.lib.model.InstitutionalDomain;
import com.parko.domain.lib.model.UserRole;
import com.parko.persistence.core.model.embedded.EmailEmbedded;
import com.parko.persistence.core.model.embedded.UserEmbedded;
import com.parko.persistence.core.model.entity.UserEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class UserConverterTest {

    @Test
    void toEntityMapsAllFields() {
        UUID id = UUID.randomUUID();
        EmailEmbedded email = new EmailEmbedded("12345@frc.utn.edu.ar", "12345", InstitutionalDomain.FRC);
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        UserEmbedded embedded = new UserEmbedded(
                id, "12345", "Juan Perez", email, "hash", UserRole.STUDENT, createdAt, updatedAt);

        UserEntity entity = UserConverter.toEntity(embedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getStudentId()).isEqualTo("12345");
        assertThat(entity.getFullName()).isEqualTo("Juan Perez");
        assertThat(entity.getEmail()).isEqualTo("12345@frc.utn.edu.ar");
        assertThat(entity.getInstitutionalDomain()).isEqualTo(InstitutionalDomain.FRC);
        assertThat(entity.getPasswordHash()).isEqualTo("hash");
        assertThat(entity.getRole()).isEqualTo(UserRole.STUDENT);
        assertThat(entity.getCreatedAt()).isEqualTo(createdAt);
        assertThat(entity.getUpdatedAt()).isEqualTo(updatedAt);
    }

    @Test
    void toEmbeddedMapsAllFieldsAndRebuildsEmail() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();
        UserEntity entity = new UserEntity();
        entity.setId(id);
        entity.setStudentId("54321");
        entity.setFullName("Maria Gomez");
        entity.setEmail("54321@sistemas.frc.utn.edu.ar");
        entity.setInstitutionalDomain(InstitutionalDomain.SISTEMAS);
        entity.setPasswordHash("hash2");
        entity.setRole(UserRole.STAFF);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        UserEmbedded embedded = UserConverter.toEmbedded(entity);

        assertThat(embedded.id()).isEqualTo(id);
        assertThat(embedded.studentId()).isEqualTo("54321");
        assertThat(embedded.fullName()).isEqualTo("Maria Gomez");
        assertThat(embedded.email().value()).isEqualTo("54321@sistemas.frc.utn.edu.ar");
        assertThat(embedded.email().studentId()).isEqualTo("54321");
        assertThat(embedded.email().institutionalDomain()).isEqualTo(InstitutionalDomain.SISTEMAS);
        assertThat(embedded.passwordHash()).isEqualTo("hash2");
        assertThat(embedded.role()).isEqualTo(UserRole.STAFF);
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }
}
