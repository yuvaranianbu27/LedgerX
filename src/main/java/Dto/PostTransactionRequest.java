package Dto;

import java.util.List;

public record PostTransactionRequest(
        String referenceId,
        String description,
        List<JournalEntryRequest> entries
) {}
