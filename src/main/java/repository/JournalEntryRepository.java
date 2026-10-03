package repository;

import entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, UUID> {

    // Event-Sourced Balance Calculation:
    // Balance = Sum(Debits) - Sum(Credits) [Adjusted according to account type in service]
    @Query("""
        SELECT COALESCE(SUM(CASE WHEN e.entryType = 'DEBIT' THEN e.amount ELSE -e.amount END), 0)
        FROM JournalEntry e WHERE e.accountId = :accountId
    """)
    BigDecimal calculateRawBalance(@Param("accountId") UUID accountId);
}
