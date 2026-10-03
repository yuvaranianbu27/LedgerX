package entity;
import jakarta.persistence.*;
import lombok.*;
import types.EntryType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "journal_entries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "entry_type", nullable = false)
    private EntryType entryType; // DEBIT or CREDIT

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "currency", nullable = false, length = 3)
    private String currency;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    // Constructor matching LedgerService usage:
    // new JournalEntry(transaction, account.getId(), entryReq.entryType(), entryReq.amount(), entryReq.currency())
    public JournalEntry(Transaction transaction, UUID accountId, EntryType entryType, BigDecimal amount, String currency) {
        this.transaction = transaction;
        this.accountId = accountId;
        this.entryType = entryType;
        this.amount = amount;
        this.currency = currency;
        this.createdAt = Instant.now();
    }
}
