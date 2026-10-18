# Digital Future — Android App

A premium, English-language mobile app hub for the four websites under the Digital Future umbrella. The app uses a dark navy and gold visual identity, classic typography, responsive cards, and a mobile bottom navigation.

## Official websites

- Digital Future (parent company): https://www.digital-future.ai/
- SAKAN Europe: https://www.sakanapp.net/
- NEXORA: https://www.nexoraonline.de/
- Helpme: https://www.helpmey.net/

## Upload the four official logos

Upload the four official logo files into `public/logos/` using these exact filenames:

| Platform | Required filename |
|---|---|
| Digital Future (parent) | `digital-future.png` |
| SAKAN Europe | `sakan-europe.png` |
| NEXORA | `nexora.png` |
| Helpme (official gold circular logo) | `helpme.png` |

Use square PNG files, preferably 1024 × 1024 px or larger. Transparent backgrounds are welcome. Keep the original official artwork unchanged. The app already points to these four paths.

## App technology

- React + TypeScript + Vite
- Capacitor 7 for Android packaging
- Mobile-first responsive layout
- English UI
- Website launch links for each platform

## Local development

Requires Node.js 20+ and Android Studio for local Android packaging.

```bash
npm install
npm run dev
```

## Android build

```bash
npm install
npm run build
npx cap add android
npx cap sync android
npx cap open android
```

In Android Studio, build an Android App Bundle (AAB) for Google Play. A production Play release requires the app signing key and completed Play Console listing, privacy/data safety disclosures, and store assets.

## Automatic preview APK

Every push to `main` runs the GitHub Actions workflow at `.github/workflows/android-preview.yml`. When successful, download the debug APK from the workflow run's **Artifacts** section. A debug APK is for installation/testing and is **not** the signed Google Play release.

## Important scope note

This first version is a polished hub that opens the four existing websites. It does not yet implement shared single sign-on, native account synchronization, or new backend services. Those require separately configured and tested integrations.
