# Sprint 1 Review

## Backlog items delivered

- User Registration API (`POST /api/auth/register`)
- User Login API (`POST /api/auth/login`) with JWT issuance
- Task creation API (`POST /api/tasks`)
- List tasks API (`GET /api/tasks`) scoped to logged-in user
- Update task status API (`PUT /api/tasks/{id}`)
- Basic static dashboard (`/dashboard.html`) that calls the APIs
- Unit tests for `TaskService` and controller integration tests
- CI pipeline (GitHub Actions) running `mvn package` and `mvn test`

## What was demonstrated

- Secure authentication and token-based flows
- Creating, listing, and updating tasks belonging to a user
- End-to-end request flows using MockMvc tests

## Screenshots (placeholders)

- CI pipeline run: docs/screenshots/ci-pipeline.md
- Registration endpoint tested: docs/screenshots/register-tested.md
- Login endpoint tested: docs/screenshots/login-tested.md
- Task endpoint tested: docs/screenshots/task-tested.md
- Dashboard UI: docs/screenshots/dashboard-ui.md

## Notes for Product Owner

- The service runs with an in-memory H2 database by default (`application.properties`).
- For production, configure a persistent DB (Postgres/MySQL) and set a strong JWT secret.
- Next sprint should focus on pagination, search/tags, and basic user UI for login.
