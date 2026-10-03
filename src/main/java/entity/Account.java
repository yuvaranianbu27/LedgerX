package entity;

import jakarta.persistence.*;
import lombok.*;
import types.AccountStatus;
import types.AccountType;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType type; // ASSET, LIABILITY, EQUITY, REVENUE, EXPENSE

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE; // ACTIVE, INACTIVE, FROZEN

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Column(name = "allow_negative", nullable = false)
    private boolean allowNegative = false;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();
}
