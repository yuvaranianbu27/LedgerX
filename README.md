# LedgerX

**A Java 21 and Spring Boot foundation for a double-entry ledger and wallet API.**

LedgerX models accounts, transactions, and journal entries for a financial ledger. Its current codebase contains the domain entities, repository interfaces, enums, and request records that can support ledger posting.

> **Project status:** The repository is still a foundation. It does not currently include REST controllers or a transaction-posting service, so the request records do not yet represent a callable API. Debit/credit balancing and concurrency guarantees are not enforced by the current application code.

## Domain model

- **Account** — UUID, account type, status, currency, and negative-balance setting.
- **Transaction** — unique reference ID, description, timestamp, and associated journal entries.
- **Journal entry** — account ID, debit/credit entry type, amount, and currency.
- **Request records** — `PostTransactionRequest` and `JournalEntryRequest` describe a possible posting payload.

The account types are `ASSET`, `LIABILITY`, `EQUITY`, `REVENUE`, and `EXPENSE`. Entry types are `DEBIT` and `CREDIT`.

## Technology

| Technology | Use |
| --- | --- |
| Java 21 | Application runtime |
| Spring Boot 3 | Application framework |
| Spring Web | Web starter dependency |
| Spring Data JPA / Hibernate | Persistence model and repositories |
| PostgreSQL | Configured relational database |
| Jakarta Validation | Request-validation dependency |
| Maven Wrapper | Build and test commands |

## Run locally

Prerequisites: Java 21 and PostgreSQL. The local application configuration targets a database named `ledgerx_db`; make sure the database and credentials are configured for your environment before starting the app.

From the repository root:

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

On macOS or Linux, use `./mvnw` instead of `.\mvnw.cmd`. Keep database credentials out of source control and use environment-specific configuration for deployments.

## Next steps

- Implement account and transaction services and REST endpoints.
- Validate that journal entries balance by currency before posting.
- Make a posting atomic and idempotent, and exercise concurrent updates with tests.
- Add database migrations and documented API examples.

## Repository layout

- `src/main/java/entity/` — account, transaction, and journal-entry entities.
- `src/main/java/repository/` — Spring Data repository interfaces.
- `src/main/java/Dto/` — request records.
- `src/main/resources/` — application configuration.
