## Sprint 1 Review

### Sprint Goal

Deliver a working Sprint 1 prototype of the Task Manager backend that includes user registration/login (JWT-based), task create/list/update APIs, automated tests, a CI pipeline, and a Docker image for local deployment.

### Summary of Delivery

This sprint delivered a focused backend prototype suitable for developer validation and early demos. The implementation emphasizes a minimal secure API surface, testability, and simple deployment via Docker.

Key deliverables

- User registration endpoint: `POST /api/auth/register`
- User login endpoint with JWT issuance: `POST /api/auth/login`
- JWT validation filter and stateless security
- Task APIs: create (`POST /api/tasks`), list (`GET /api/tasks`), update status (`PUT /api/tasks/{id}`)
- Unit and slice/integration tests for services and controllers
- GitHub Actions CI workflow to build and run tests
- `Dockerfile` and packaging to produce a runnable image

### How to Reproduce (developer steps)

1. Run unit/integration tests:

```bash
mvn clean test
```

2. Run the application locally:

```bash
mvn spring-boot:run
# Default base URL: http://localhost:8080
```

3. Example requests (replace values as needed):

Register a user

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"pw"}'
```

Login (returns JSON `{"token":"..."}`)

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"pw"}'
```

Create a task (replace TOKEN)

```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{"title":"Buy milk","description":"one litre"}'
```

List tasks

```bash
curl -X GET http://localhost:8080/api/tasks \
  -H "Authorization: Bearer <TOKEN>"
```

Run in Docker

```bash
mvn package -DskipTests
docker build -t taskmanager:latest .
docker run -p 8080:8080 taskmanager:latest
```

### Done vs. Deferred

Done

- Registration and login with JWT
- Task create/list/update APIs
- Unit and controller integration tests
- Docker packaging and CI workflow configured

Deferred / for future sprint

- Frontend polish and protected UI flows
- End-to-end (E2E) browser tests and coverage badge automation
- Full CD / infrastructure-as-code for production deployment

### Product Backlog (for next sprint)

- Add E2E tests (Cypress or Playwright)
- Add coverage reporting and badge (investigate JaCoCo compatibility with CI JDK)
- Implement frontend dashboard and protected UI
- Add deployment IaC and CD pipeline

### Risks & Mitigations

1. JWT secret management: avoid embedding secrets in code — use environment variables or a secrets manager in production. For local runs, set a strong secret via `SPRING_APPLICATION_JSON` or environment variables.
2. CI coverage tooling: JaCoCo instrumentation may be incompatible with very new JDKs; run CI on a supported JDK (17/21) or upgrade coverage tooling.
3. No persistence in prod: the default uses H2 in-memory DB for tests and local runs; configure a persistent external DB (Postgres/MySQL) for production.

### CI Proof (notes)

The project contains a GitHub Actions workflow (`.github/workflows/ci.yml`) that builds the project and runs tests. Recent CI runs are visible on the repository Actions page; update this document with a link and status image after a successful run.

For example:

- Actions page: https://github.com/AndyDebrah/taskmanager/actions

Replace this section with an up-to-date run URL and timestamp after re-running CI.

### Notes for the Product Owner

- The service uses an in-memory H2 database by default (`application.properties`). For production use, configure a persistent DB and externalize secrets.
- The code includes an opt-in test/CI toggle for disabling authentication (`app.security.enabled=false`) which can simplify integration testing or CI validation when appropriate.

---

_Document maintained by the development team. Update this file with any new demo evidence, run links or production deployment notes._
