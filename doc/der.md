# DER - parko-persistence-core

Diagrama entidad-relación generado a partir de las clases `*Entity` en `com.parko.persistence.core.model.entity`.

```mermaid
erDiagram
    USERS ||--o{ VEHICLES : posee
    USERS ||--|| BALANCE_ACCOUNTS : tiene
    VEHICLES ||--o| ACCESS_KEYS : tiene
    VEHICLES ||--o{ PARKING_SESSIONS : usado_en
    PARKING_SESSIONS ||--o| TICKETS : genera
    PARKING_SESSIONS ||--o{ ACCESS_LOG : registra
    PARKING_SESSIONS ||--o{ TRANSACTIONS : cobra
    BALANCE_ACCOUNTS ||--o{ TRANSACTIONS : financia

    USERS {
        UUID id PK
        string student_id UK
        string full_name
        string email UK
        string firebase_uid UK
        enum role
        enum institutional_domain
        boolean email_verified
        boolean active
        datetime created_at
        datetime updated_at
    }

    VEHICLES {
        UUID id PK
        UUID user_id FK
        string plate UK
        string brand
        string model
        boolean active
        datetime created_at
        datetime updated_at
    }

    ACCESS_KEYS {
        UUID id PK
        UUID vehicle_id FK, UK
        string code UK
        boolean active
        datetime created_at
        datetime updated_at
    }

    BALANCE_ACCOUNTS {
        UUID id PK
        UUID user_id FK, UK
        decimal amount
        datetime created_at
        datetime updated_at
    }

    PARKING_SESSIONS {
        UUID id PK
        UUID vehicle_id FK "nullable"
        string plate_snapshot
        enum session_type
        enum status
        datetime entry_at
        datetime exit_at
        datetime created_at
        datetime updated_at
    }

    TICKETS {
        UUID id PK
        UUID parking_session_id FK, UK
        string ticket_number UK
        string qr_data
        enum status
        datetime issued_at
        datetime paid_at
        datetime created_at
        datetime updated_at
    }

    ACCESS_LOG {
        UUID id PK
        UUID parking_session_id FK "nullable"
        enum event_type
        enum access_method
        enum result
        text raw_payload
        datetime occurred_at
        datetime created_at
        datetime updated_at
    }

    TRANSACTIONS {
        UUID id PK
        UUID balance_account_id FK "nullable"
        UUID parking_session_id FK "nullable"
        enum type
        decimal amount
        enum status
        string payment_provider
        string external_ref
        datetime created_at
        datetime updated_at
    }
```

## Notas

- `parking_session_id` en `TRANSACTIONS` y `ACCESS_LOG` es nullable: no toda transacción o log de acceso está atado a una sesión activa.
- `ACCESS_KEYS.vehicle_id` y `BALANCE_ACCOUNTS.user_id` son unique (relación 1:1 real aunque mapeadas como `@ManyToOne`/`@OneToOne`).
