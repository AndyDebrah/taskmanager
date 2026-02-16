## Sprint 2 Retrospective

**What improved**

- UI now includes a login page and dashboard integrates with backend for CRUD operations.
- API includes DELETE endpoint with owner verification and tests.
- CI now collects JaCoCo coverage and caches Maven dependencies.
- Actuator provides health endpoint; logging added to track key events.

**What didn't go well / risks**

- Coverage could be improved further (E2E tests missing).
- JWT secret is still configured in properties for dev; should be in secrets manager.

**Action items**

1. Add E2E tests for UI flows (login → create → delete). Owner: dev. Due: next sprint.
2. Move JWT secret to environment/secret store. Owner: dev/ops. Due: before prod.
3. Add coverage badge and integrate with codecov or similar. Owner: dev. Due: next sprint.
