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

Badges:

- CI: ![CI](https://img.shields.io/badge/CI-pending-yellow) (replace with your GitHub Actions badge URL)
- Coverage: ![coverage](https://img.shields.io/badge/coverage-unknown-lightgrey) (replace with coverage service badge after configuring coverage)
