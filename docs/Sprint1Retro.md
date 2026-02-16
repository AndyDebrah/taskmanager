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
