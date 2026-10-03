package Dto;

import types.EntryType;

import java.math.BigDecimal;
import java.util.UUID;

public record JournalEntryRequest(
        UUID accountId,
        EntryType entryType,
        BigDecimal amount,
        String currency
) {}

