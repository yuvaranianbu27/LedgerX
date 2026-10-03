package repository;

import entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    /**
     * Used by LedgerService to check if a transaction has already been processed.
     * Guarantees idempotency across retries.
     */
    Optional<Transaction> findByReferenceId(String referenceId);

    /**
     * Checks whether a transaction reference exists before processing.
     */
    boolean existsByReferenceId(String referenceId);
}