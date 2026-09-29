# digital-future.ai — Android App Specification

## Official platform URLs

- SAKAN: https://www.sakanapp.net
- NEXORA: https://www.nexoraonline.de
- HELP-ME: https://www.helpmey.net
- digital-future.ai: https://www.digital-future.ai

## Product

One Android application named **digital-future.ai** containing three platform entry points: SAKAN, NEXORA and HELP-ME.

## UX rules

- Preserve the approved visual design supplied by the owner.
- Use each platform's real production logo; do not replace the logos with generated alternatives.
- Keep the three platform identities visually distinct while maintaining one digital-future.ai shell.
- Each platform card provides an official web entry point and a QR entry point.
- QR codes must resolve to the corresponding official platform URL.
- Use Android App Links / deep links when the application routes are implemented.
- Android / Google Play is the first release target. iOS is deferred.

## Engineering sequence

1. Audit and stabilize the four production web properties.
2. Build the Android shell without duplicating unstable web functionality.
3. Add SAKAN, NEXORA and HELP-ME navigation and deep-link routing.
4. Add QR generation for the three official URLs.
5. Add language handling and shared app settings.
6. Prepare Android release configuration for Google Play.

## Production URLs are authoritative

The URLs above are the current official destinations supplied for app integration. If a platform URL changes, update this specification and the app routing configuration together.
