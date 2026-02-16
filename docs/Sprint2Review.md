## Sprint 2 Review

**Sprint Goal**

Deliver Sprint 2 improvements: UI login & dashboard enhancements, delete task API, monitoring (Actuator), OpenAPI docs, improved logging and JWT handling, and CI coverage reporting.

**Work Delivered**

- UI: `login.html` (AJAX login, stores JWT in localStorage)
- UI: `dashboard.html` improved (redirect to login, delete task button)
- API: `DELETE /api/tasks/{id}` with owner checks and tests
- Monitoring: Spring Boot Actuator enabled (`/actuator/health`)
- Docs: OpenAPI UI via Springdoc (Swagger)
- CI: JaCoCo coverage enabled, Maven cache, coverage artifact upload
- Logging: controllers, services, and exception handler log important events

**Evidence / Screenshots (placeholders)**

- Login page: docs/screenshots/login-sprint2.png
- Dashboard with delete button: docs/screenshots/dashboard-sprint2.png
- CI run with coverage: docs/screenshots/ci-coverage-sprint2.png
- Actuator health: docs/screenshots/actuator-health.png

**How to verify locally**

1. Run tests and generate coverage:

```bash
mvn clean test
```

2. Run app locally and open UI:

```bash
mvn spring-boot:run
# Visit http://localhost:8080/login.html then login and go to /dashboard.html
```

3. Check health endpoint:

```bash
curl http://localhost:8080/actuator/health
```

**CI Proof (placeholder)**

- Actions page: https://github.com/AndyDebrah/taskmanager/actions
- Run URL: [PASTE_RUN_URL_HERE]

**Latest CI run**

- Run URL: https://github.com/AndyDebrah/taskmanager/actions/runs/22070252613
- Run ID: 22070252613
- Date & time (UTC): 2026-02-16T16:21:02Z
- Status: ❌ failure

Logs: https://github.com/AndyDebrah/taskmanager/actions/runs/22070252613/logs
Artifacts: https://github.com/AndyDebrah/taskmanager/actions/runs/22070252613/artifacts
