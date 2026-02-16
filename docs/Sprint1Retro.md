## Sprint 1 Retrospective

**What went well**

- Core API endpoints implemented (auth + tasks) and tested.
- JWT-based security integrated and verified by tests.
- Tests were used to find and fix issues early (good feedback loop).
- Dockerfile and CI workflow scaffolded.

**What didn't go well**

- A few failing tests initially due to test-data collisions (duplicate usernames).
- CI not yet run with the final docs changes (placeholder present).

**Action items**

1. Use unique fixtures or reset DB state between tests to avoid collisions — Owner: dev — Due: next sprint start
2. Add E2E tests to cover full auth+task flows — Owner: dev — Due: Sprint 2
3. Move JWT secret to secure config and add environment-based overrides — Owner: dev/ops — Due: Sprint 2
# Sprint 1 Retrospective

## 2 things that went well

- Clear baseline scaffolding: auth and task APIs implemented and wired.
- CI pipeline added early ensuring tests run automatically.

## 2 things that didn’t go well

- Initial JWT secret handling is hardcoded; needs property-based configuration.
- Minimal UI — dashboard is basic and requires token injection manually.

## 2 improvements for Sprint 2

- Add login UI and persist token in browser; improve dashboard UX.
- Externalize configuration (JWT secret, DB URL) and add deployment IaC.
