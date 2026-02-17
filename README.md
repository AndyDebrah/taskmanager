# Taskmanager (Java Spring Boot)

Minimal Task Management prototype implemented with Spring Boot, JWT auth, H2 DB, and simple static dashboard.

Run locally:

```bash
mvn spring-boot:run
```

Endpoints:
- `POST /api/auth/register` {username,password}
- `POST /api/auth/login` {username,password} -> returns `{token}`
- `GET /api/tasks` (Auth Bearer)
- `POST /api/tasks` (Auth Bearer) {title,description}
- `PUT /api/tasks/{id}?status=COMPLETED` (Auth Bearer)

Dashboard: open `/dashboard.html` and set token in `localStorage.setItem('tm_token', '<token>')` in browser console.

CI: GitHub Actions file at `.github/workflows/ci.yml` runs `mvn package` and tests.
# Sprint 1 docs

- Review: [docs/Sprint1Review.md](docs/Sprint1Review.md)
- Retrospective: [docs/Sprint1Retro.md](docs/Sprint1Retro.md)

# taskmanager

# Taskmanager

Taskmanager is a minimal Spring Boot prototype implementing a JWT-authenticated task management API with an in-memory H2 database and a simple static dashboard.

Key features
- REST API for user registration, authentication and task CRUD.
- JWT-based authentication (HS256 via jjwt).
- H2 in-memory database for local runs and tests.
- Actuator and OpenAPI (swagger) for observability and API docs.

Prerequisites
- JDK 17 (recommended for CI compatibility)
- Maven 3.8+

Quick start (development)

1. Run the app from the project root:

```bash
mvn spring-boot:run
```

2. Or build the jar and run it:

```bash
mvn -DskipTests package
java -jar target/taskmanager-0.0.1-SNAPSHOT.jar
```

API Endpoints (examples)
- POST /api/auth/register  { "username":"...", "password":"..." }
- POST /api/auth/login     { "username":"...", "password":"..." } -> {"token":"..."}
- GET  /api/tasks          (requires `Authorization: Bearer <token>`)
- POST /api/tasks          (requires `Authorization: Bearer <token>`)
- PUT  /api/tasks/{id}?status=COMPLETED (requires `Authorization: Bearer <token>`)

Dashboard
- Open `src/main/resources/static/dashboard.html` in a browser (or http://localhost:8080/dashboard.html when app is running). Set `tm_token` in `localStorage` to a valid JWT to use the UI.

Testing
- Run unit and integration tests (skip JaCoCo if using a newer JDK locally):

```bash
mvn -Djacoco.skip=true test
```

Notes on CI and coverage
- The repository includes a GitHub Actions workflow at `.github/workflows/ci.yml` that runs the Maven build. For reliable CI builds ensure the runner uses JDK 17 (the project is compiled with `--release 17`).
- JaCoCo 0.8.10 is included but may fail when running under very new JDKs (for example, Java 25) due to bytecode instrumentation incompatibilities. Options:
	- Run CI on JDK 17 or 21 (recommended), or
	- Upgrade the coverage tooling to a JaCoCo version compatible with your JDK, or
	- Temporarily skip JaCoCo in the CI job until tooling compatibility is resolved.

Troubleshooting
- Compilation error "var cannot be resolved": ensure the Maven compiler is configured to target Java 11+ or 17. The project sets `<release>17` in `pom.xml`.
- jjwt WeakKeyException: the JWT secret must be at least 256 bits for HS256. In tests we derive a 256-bit key from a short secret to avoid failures; store secrets securely in production (env vars / secret store).

Documentation
- Sprint 1 review: [docs/Sprint1Review.md](docs/Sprint1Review.md)
- Sprint 1 retrospective: [docs/Sprint1Retro.md](docs/Sprint1Retro.md)

Badges (replace placeholders with real URLs)

- CI: ![CI](https://img.shields.io/badge/CI-pending-yellow)
- Coverage: ![coverage](https://img.shields.io/badge/coverage-unknown-lightgrey)

Contributing
- Fork the repo, create a branch, open a PR against `main`. Keep changes focused and include tests for bug fixes or new features.

License
- (Add license here)

