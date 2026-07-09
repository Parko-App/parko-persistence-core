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
                id, "12345", "Juan Perez", email, "firebase-uid-1", UserRole.STUDENT, true, createdAt, updatedAt);

        UserEntity entity = UserConverter.toEntity(embedded);

        assertThat(entity.getId()).isEqualTo(id);
        assertThat(entity.getStudentId()).isEqualTo("12345");
        assertThat(entity.getFullName()).isEqualTo("Juan Perez");
        assertThat(entity.getEmail()).isEqualTo("12345@frc.utn.edu.ar");
        assertThat(entity.getInstitutionalDomain()).isEqualTo(InstitutionalDomain.FRC);
        assertThat(entity.getFirebaseUid()).isEqualTo("firebase-uid-1");
        assertThat(entity.getRole()).isEqualTo(UserRole.STUDENT);
        assertThat(entity.isActive()).isTrue();
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
        entity.setFirebaseUid("firebase-uid-2");
        entity.setRole(UserRole.STAFF);
        entity.setActive(true);
        entity.setCreatedAt(createdAt);
        entity.setUpdatedAt(updatedAt);

        UserEmbedded embedded = UserConverter.toEmbedded(entity);

        assertThat(embedded.id()).isEqualTo(id);
        assertThat(embedded.studentId()).isEqualTo("54321");
        assertThat(embedded.fullName()).isEqualTo("Maria Gomez");
        assertThat(embedded.email().value()).isEqualTo("54321@sistemas.frc.utn.edu.ar");
        assertThat(embedded.email().studentId()).isEqualTo("54321");
        assertThat(embedded.email().institutionalDomain()).isEqualTo(InstitutionalDomain.SISTEMAS);
        assertThat(embedded.firebaseUid()).isEqualTo("firebase-uid-2");
        assertThat(embedded.role()).isEqualTo(UserRole.STAFF);
        assertThat(embedded.active()).isTrue();
        assertThat(embedded.createdAt()).isEqualTo(createdAt);
        assertThat(embedded.updatedAt()).isEqualTo(updatedAt);
    }
}
