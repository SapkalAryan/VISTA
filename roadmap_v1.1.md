# VISTA — `roadmap.md` (Version 1.1)

**Project:** VISTA (Virtual Intelligent Storage & Tracking Assistant)  
**Current Phase:** Module 1 — Authentication & User Management  
**Status:** Module 0 Completed • Module 1 In Progress

# 1. Project Vision

VISTA is an AI-powered digital storage and tracking system that helps users securely store memories, documents, media, and events with intelligent search, timeline visualization, and contextual retrieval using modern Android architecture.

## Tech Stack

| Layer | Technology |
| --- | --- |
| Platform | Native Android |
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM + Clean Architecture |
| Dependency Injection | Hilt |
| Database | Supabase PostgreSQL |
| Authentication | Supabase Auth |
| Email Provider | Gmail SMTP (Development) |
| Storage | Supabase Storage |
| Navigation | Navigation Compose |
| Backend | Supabase |

# 2. Overall Roadmap

| Module | Name | Status |
| --- | --- | --- |
| 0 | Foundation & Infrastructure | ✅ Complete |
| 1 | Authentication & User Management | 🟡 In Progress |
| 2 | Memory Capture System | ⏳ Planned |
| 3 | AI Search & Retrieval | ⏳ Planned |
| 4 | Timeline Engine | ⏳ Planned |
| 5 | Media & Document Storage | ⏳ Planned |
| 6 | Smart Tags & NLP | ⏳ Planned |
| 7 | Sync & Offline Layer | ⏳ Planned |
| 8 | Security & Production Release | ⏳ Planned |

# 3. Module 0 (Completed)

## M0.1 Project Initialization

### Objective

Create a stable Android project.

### Completed

- Android Studio project
- Kotlin + Compose
- compileSdk 36
- targetSdk 36
- minSdk 29
- Gradle working
- Emulator verified

**Git**

`feat(M0.1): initialize Android project with Compose foundation`

---

## M0.2.1 Package Architecture

### Final Structure

```text
com.vista.memoryos
│
├── core
│   ├── common
│   ├── navigation
│   ├── theme
│   ├── ui
│   └── util
│
├── data
│   ├── local
│   ├── model
│   ├── remote
│   └── repository
│
├── di
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
└── feature
    ├── splash
    ├── onboarding
    ├── home
    ├── search
    ├── timeline
    └── profile
```

### Rule

- UI never accesses data directly.
- Empty packages contain `.gitkeep`.

**Git**

`refactor(M0.2.1): establish clean package architecture`

---

## M0.2.2 Hilt Infrastructure

### Implemented

- Hilt plugin
- KSP
- Version catalog
- VistaApplication
- AppModule
- AppInfo
- Dependency injection verified

### Files

```text
VistaApplication.kt
AppModule.kt
AppInfo.kt
```

### Flow

```text
Application
      ↓
Hilt Container
      ↓
Activity
      ↓
Injected Objects
```

**Git**

`feat(M0.2.2): integrate Hilt dependency injection architecture`

---

## M0.2.3 Navigation Foundation

### Navigation Graph

```text
Splash
   ↓
Onboarding
   ↓
Main Container
 ├── Home
 ├── Search
 ├── Timeline
 └── Profile
```

### Implemented

- Navigation Compose
- Screen routes
- Bottom navigation
- Splash
- Onboarding
- 4 feature screens
- Back stack preservation

### Files

```text
Screen.kt
BottomDestination.kt
VistaNavHost.kt
SplashScreen.kt
OnboardingScreen.kt
HomeScreen.kt
SearchScreen.kt
TimelineScreen.kt
ProfileScreen.kt
```

**Git**

`feat(M0.2.3): implement navigation foundation`

---

## M0.2.4 Design System

### Components

```text
VistaButton
VistaCard
VistaTopBar
Spacing
```

### Design Tokens

| Property | Value |
|---|---|
| Primary | Deep Purple |
| Radius | 16dp |
| Grid | 8dp |
| Elevation | 4dp |

### Rule

Future screens must use reusable components instead of raw Material widgets.

**Git**

`feat(M0.2.4): establish reusable design system`

---

## M0.2.5 MVVM Base

### Implemented

- UiState
- BaseViewModel
- HomeViewModel
- StateFlow
- Lifecycle aware collection

### Architecture

```text
Compose Screen
        ↓
ViewModel
        ↓
UiState
        ↓
StateFlow
```

**Git**

`feat(M0.2.5): establish MVVM architecture with StateFlow`

---

# CURRENT PROJECT STRUCTURE

```text
app
└── src/main/java/com/vista/memoryos
    ├── core
    ├── data
    ├── di
    ├── domain
    ├── feature
    ├── MainActivity.kt
    └── VistaApplication.kt
```

---

## M0.2 Architecture

* MVVM folder structure
* Clean Architecture packages
* Hilt integrated
* Navigation Compose setup

## M0.3 Backend

* Supabase project created
* PostgreSQL connected
* Authentication enabled
* Storage initialized
* RLS enabled

## M0.4 UI Foundation

* Bottom navigation
* Placeholder screens
* Theme setup
* Home screen

**Git Tag**

```text
v0.1-foundation
```

# 4. Module 1 — Authentication & User Management

## Objective

Build a complete production-style authentication system with email verification, deep linking, session persistence, and scalable architecture.

# M1.1 Backend Integration ✅

### Completed

* Supabase Kotlin SDK
* Auth client
* PostgREST client
* Storage client
* Dependency Injection

### Files

```text
app/build.gradle.kts
gradle/libs.versions.toml
SupabaseModule.kt
SupabaseClient.kt
```

# M1.2 Registration Foundation ✅

### Completed

* Repository layer
* ViewModel
* Registration UI
* Password input
* Loading state

### Files

```text
AuthRepository.kt
AuthRepositoryImpl.kt
OnboardingViewModel.kt
OnboardingScreen.kt
```

# M1.3 Registration Stabilization (Current)

## M1.3.1 Form UX ✅

### Completed

* Single-line fields
* Next keyboard action
* Done submits form
* Keyboard dismissal
* Focus traversal

## M1.3.2 Error Handling

**Status:** Pending

### Goals

* Friendly messages
* Domain error model
* No raw exceptions
* Loading lock
* Duplicate prevention

### New Files

```text
domain/model/AuthError.kt
domain/model/AuthResult.kt
```

## M1.3.3 Duplicate Account Detection

**Status:** Pending

### Features

* Existing account detection
* Already verified handling
* Verification pending handling
* Sign-in suggestion

# M1.4 Email Infrastructure ✅ (Decision Finalized)

## Architecture Decision M1-AUTH-01

**Decision:** Gmail SMTP will be used during development and academic evaluation.

### Reason

* Free
* No domain purchase
* Fully compatible with Supabase
* Real email verification
* Provider can be replaced without changing Android code

### Current SMTP Provider

| Field | Value |
| --- | --- |
| Provider | Gmail SMTP |
| Host | smtp.gmail.com |
| Port | 587 |
| Authentication | Gmail App Password |
| Sender | Dedicated VISTA Gmail |
| Status | ✅ Working |

### Future Migration

```text
Development
Gmail SMTP
        │
        ▼
Production
Resend + Custom Domain
```

No Android code changes required.

# M1.5 Deep Link Verification

**Status:** Planned

## Goal

Replace blank verification page with automatic app opening.

### User Flow

```text
Verification Email
        │
        ▼
vista://auth
        │
        ▼
Exchange Session
        │
        ▼
Verification Success
        │
        ▼
Continue to Sign In
```

### Files

```text
MainActivity.kt
AndroidManifest.xml
DeepLinkHandler.kt
VerificationSuccessScreen.kt
VistaNavHost.kt
```

# M1.6 Login System

**Status:** Planned

### Features

* Email login
* Password login
* Forgot password
* Loading state
* Error handling

### New Files

```text
LoginScreen.kt
LoginViewModel.kt
```

# M1.7 Session Persistence

**Status:** Planned

### Features

* Auto login
* Auto logout
* Session restore
* Splash auth check

### New Files

```text
SessionManager.kt
AuthState.kt
```

# 5. Current Project Structure

```text
app/src/main/java/com/vista/memoryos/

├── core/
│   ├── di/
│   ├── navigation/
│   └── supabase/
│
├── data/
│   └── repository/
│
├── domain/
│   ├── model/
│   └── repository/
│
├── feature/
│   ├── onboarding/
│   ├── home/
│   ├── search/
│   ├── timeline/
│   └── profile/
│
├── ui/
│   └── theme/
│
└── MainActivity.kt
```

# 6. Git Commit History

| Commit | Status |
| --- | --- |
| Initial Android project | ✅ |
| MVVM + Hilt architecture | ✅ |
| Supabase integration | ✅ |
| Registration foundation | ✅ |

## Next Commit

```text
feat(auth): complete end-to-end registration with Gmail SMTP and deep-link verification
```

This commit will include:

* Gmail SMTP infrastructure
* Friendly error handling
* Duplicate account detection
* Deep link authentication
* Verification success screen

# 7. Architectural Decisions

## AD-01: Clean Architecture

* MVVM
* Repository Pattern
* Dependency Injection
* Feature-first organization

## AD-02: Backend

Supabase is the single backend responsible for:

* Authentication
* PostgreSQL
* Storage
* Session management

## AD-03: Email Infrastructure

### Development

* Gmail SMTP
* Dedicated VISTA Gmail account
* Gmail App Password

### Production

* Resend
* Custom domain
* Branded sender (`noreply@vista.ai`)

This change affects only SMTP configuration—not the Android application.

# 8. Pending Tasks (Priority Order)

### High Priority

* M1.3.2 Friendly error abstraction
* M1.3.3 Duplicate account detection
* M1.5 Deep-link verification
* M1.5 Verification success screen
* M1.6 Login screen
* M1.7 Session persistence

### Medium Priority

* Forgot password
* Resend verification button
* Verification cooldown timer

# 9. Milestone Definition

## Module 1 Completion Criteria

A user should be able to:

* Create an account
* Receive verification email
* Open verification link on mobile
* Return automatically to VISTA
* See verification success page
* Sign in
* Stay logged in after app restart
* Sign out securely

**Current Progress:** ~45% of Module 1 complete
