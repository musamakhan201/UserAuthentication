# UserAuthentication

Spring Boot service for user registration, email confirmation, login/logout, profile and password updates, plus SEO entries. Uses Spring Data JPA and MySQL by default.

## Requirements

- Java 8+ (project parent targets Java 8; newer JDKs work with the included Lombok version)
- Maven (wrapper included: `./mvnw`)
- **MySQL** for the default profile, or use the **H2** profile for local runs without MySQL

## Configuration

| Setting | Default / notes |
|--------|------------------|
| **HTTP port** | **8189** (`server.port` in `application.properties`) |
| **Database** | `seo_optimization` on `localhost` |
| **DB user** | `root` |
| **DB password** | `root` if `DB_PASSWORD` is unset; override with env `DB_PASSWORD` |
| **Mail** | Set `MAIL_USERNAME` and `MAIL_PASSWORD` for Gmail (or your SMTP). If unset, mail calls may fail until configured. |

Create the database:

```sql
CREATE DATABASE seo_optimization;
```

MySQL 8 JDBC URL includes `allowPublicKeyRetrieval=true` for common `caching_sha2_password` setups.

## Run (MySQL)

```bash
export DB_PASSWORD=your_mysql_password   # optional if root password is "root"
export MAIL_USERNAME=your@email.com
export MAIL_PASSWORD=your_app_password
./mvnw spring-boot:run
```

## Run (H2, no MySQL / no SMTP)

Uses in-memory H2, a no-op mail sender, and seeded email templates. Useful for smoke tests.

```bash
SPRING_PROFILES_ACTIVE=h2 ./mvnw spring-boot:run
```

With the `h2` profile, after **register**, the server logs a line:

`h2 profile: confirmation token for API tests: <uuid>`

Use that value with `GET /seo/confirm-account?token=<uuid>` to complete confirmation before calling `/seo/auth`.

## API overview

Base path: **`/seo`** (all routes below are under this prefix).

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/register` | Register user; sends confirmation (real SMTP in default profile) |
| `GET` or `POST` | `/confirm-account?token=` | Confirm email token |
| `POST` | `/auth` | Authenticate (JSON `email`, `password`) |
| `POST` | `/login/user` | Records a row in `login_users` (audit-style; not the same as `/auth`) |
| `POST` | `/logout?user_id=` | Clears logged-in flag |
| `GET` | `/profile?user_id=` | User profile as JSON |
| `PUT` | `/user/update` | Update profile fields |
| `PUT` | `/password/change` | Change password (JSON `user_id`, `old_password`, `new_password`) |
| `POST` | `/add/seo` | Save SEO entry |

Example register body:

```json
{
  "user_id": 0,
  "first_name": "John",
  "last_name": "Doe",
  "address": "123 Main St",
  "email": "john@example.com",
  "password": "your-secure-password"
}
```

## Security notes

- New passwords are stored with **BCrypt**. Legacy plaintext hashes in the DB are still accepted until users reset passwords.
- Do **not** commit real `MAIL_PASSWORD` or DB credentials; use environment variables.
- Profile responses may include sensitive fields; consider a dedicated response DTO for production.

## Build & test

```bash
./mvnw clean test
./mvnw clean package -DskipTests
```

## Branches

Active development for refactors and local-run improvements is on branch **`refactored`**.
