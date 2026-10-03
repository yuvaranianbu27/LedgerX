package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "reference_id", nullable = false, unique = true)
    private String referenceId;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    @Builder.Default
    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<JournalEntry> entries = new ArrayList<>();

    // Custom constructor matching your LedgerService usage: new Transaction(referenceId, description)
    public Transaction(String referenceId, String description) {
        this.referenceId = referenceId;
        this.description = description;
        this.createdAt = Instant.now();
        this.entries = new ArrayList<>();
    }

    /**
     * Helper method to maintain bi-directional relationship integrity
     * when adding JournalEntry items.
     */
    public void addEntry(JournalEntry entry) {
        entries.add(entry);
        entry.setTransaction(this);
    }
}
