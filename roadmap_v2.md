# VISTA — `roadmap.md` (Version 2.0)

**Project:** VISTA (Virtual Intelligent Storage & Tracking)  
**Current Phase:** Module 1 — Authentication & User Management  
**Status:** Module 0 Completed • Module 1 In Progress

# 1. Project Progress

| Module | Name | Status |
| --- | --- | --- |
| Module 0 | Foundation & Infrastructure | ✅ Complete |
| Module 1 | Authentication & User Management | 🟡 45% Complete |
| Module 2 | Memory Capture System | ⏳ Planned |
| Module 3 | AI Search & Retrieval | ⏳ Planned |
| Module 4 | Timeline Engine | ⏳ Planned |

# 2. Module 0 (Completed)

## M0.1 Project Initialization

### Objective

Create a stable Android project with modern Compose architecture.

### Completed

- Android Studio project created
- Kotlin + Jetpack Compose
- Gradle configured
- Emulator verified
- Git & GitHub connected

### Git

```text
feat(M0.1): initialize Android project with Compose foundation
```

## M0.2.1 Package Architecture

### Final Structure

```text
com.vista.memoryos

├── core
├── data
├── di
├── domain
└── feature
```

### Rule

- Clean Architecture
- Feature-first organization
- UI never accesses data directly

### Git

```text
refactor(M0.2.1): establish clean architecture
```

## M0.2.2 Hilt Infrastructure

### Implemented

- Hilt
- KSP
- Version Catalog
- VistaApplication
- AppModule

### Architecture

```text
Application
      ↓
Hilt Container
      ↓
Activity
      ↓
Injected Dependencies
```

### Git

```text
feat(M0.2.2): integrate Hilt dependency injection
```

## M0.2.3 Navigation Foundation

### Navigation

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

### Completed

- Navigation Compose
- Bottom Navigation
- Splash Screen
- Onboarding
- Feature placeholders

### Git

```text
feat(M0.2.3): implement navigation foundation
```

## M0.2.4 Design System

### Components

- VistaButton
- VistaCard
- VistaTopBar
- Spacing System

### Design Tokens

| Property | Value |
| --- | --- |
| Radius | 16dp |
| Grid | 8dp |
| Elevation | 4dp |

### Git

```text
feat(M0.2.4): establish reusable design system
```

## M0.2.5 MVVM Base

### Implemented

- BaseViewModel
- UiState
- StateFlow
- Lifecycle collection

### Architecture

```text
Compose
   ↓
ViewModel
   ↓
UiState
   ↓
StateFlow
```

### Git

```text
feat(M0.2.5): establish MVVM architecture
```

# 3. Module 1 — Authentication & User Management

## Objective

Build a complete end-to-end authentication system including registration, email verification, deep linking, login and persistent sessions.

## M1.1 Supabase Backend Integration ✅

### Objective

Connect Android with Supabase services using Dependency Injection.

### Completed

- Supabase Kotlin SDK
- Auth Client
- PostgREST Client
- Storage Client
- Hilt Module
- Singleton Supabase Client

### Files

```text
app/build.gradle.kts
gradle/libs.versions.toml
core/supabase/SupabaseClient.kt
di/SupabaseModule.kt
```

### Architecture

```text
Android App
      ↓
Repository
      ↓
Supabase Client
      ↓
Supabase Cloud
```

### Git

```text
feat(M1.1): integrate Supabase backend
```

## M1.2 Registration Foundation ✅

### Objective

Implement the first functional registration system.

### Completed

- AuthRepository
- Repository Implementation
- Registration ViewModel
- Registration UI
- Password field
- Loading state
- StateFlow integration

### Files

```text
domain/repository/AuthRepository.kt
data/repository/AuthRepositoryImpl.kt
feature/onboarding/OnboardingViewModel.kt
feature/onboarding/OnboardingScreen.kt
```

### Flow

```text
User Input
      ↓
ViewModel
      ↓
Repository
      ↓
Supabase Auth
```

### Git

```text
feat(M1.2): implement registration foundation
```

## M1.3 Registration Stabilization (Current)

### M1.3.1 Form UX ✅

### Completed

* Single-line fields
* Next keyboard action
* Done submits form
* Keyboard dismissal
* Focus traversal

### M1.3.2 Error Handling

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

### M1.3.3 Duplicate Account Detection

**Status:** Pending

### Features

* Existing account detection
* Already verified handling
* Verification pending handling
* Sign-in suggestion

### UX Flow

```text
Name
 ↓ Next
Email
 ↓ Next
Password
 ↓ Done
Create Account
```

### Git

```text
feat(M1.3): improve registration UX
```

## M1.4 Gmail SMTP Infrastructure ✅

### Objective

Replace Supabase default email sender with Gmail SMTP for reliable development testing.

### Architecture Decision

**Development SMTP Provider:** Gmail

### Reason

- Free
- No custom domain required
- Compatible with Supabase
- Real email verification
- Easy to migrate later

### Current Configuration

| Field | Value |
| --- | --- |
| Provider | Gmail SMTP |
| Host | smtp.gmail.com |
| Port | 587 |
| Authentication | Gmail App Password |
| Sender | Dedicated VISTA Gmail |
| Status | Working |

### Email Flow

```text
User Registers
      ↓
Supabase Auth
      ↓
Gmail SMTP
      ↓
Verification Email
      ↓
User Inbox
```

### Git

```text
chore(M1.4): configure Gmail SMTP
```

# 4. Current Module 1 Progress

| Submodule | Status |
| --- | --- |
| M1.1 Supabase Integration | ✅ Complete |
| M1.2 Registration Foundation | ✅ Complete |
| M1.3 Registration UX | ✅ Complete |
| M1.4 Gmail SMTP | ✅ Complete |
| M1.5 Deep Link Verification | ⏳ Next |
| M1.6 Login System | ⏳ Pending |
| M1.7 Session Persistence | ⏳ Pending |

**Overall Progress: 45%**

# 5. Next Implementation Plan

## M1.5 Deep Link Verification (NEXT)

### Objective

Complete the email verification journey by opening VISTA directly from the verification link.

### Files to Create

```text
app/src/main/java/com/vista/memoryos/feature/auth/

DeepLinkHandler.kt
VerificationSuccessScreen.kt
```

### Files to Modify

```text
app/src/main/AndroidManifest.xml

app/src/main/java/com/vista/memoryos/

MainActivity.kt
core/navigation/VistaNavHost.kt
```

### Expected Flow

```text
Create Account
      ↓
Verification Email
      ↓
Tap Email Link
      ↓
VISTA Opens
      ↓
Session Exchanged
      ↓
Verification Success
      ↓
Continue to Login
```

## M1.6 Login System

### Objective

Allow verified users to securely sign in.

### Planned Features

- Email & Password login
- Friendly error handling
- Forgot Password
- Loading state
- Navigation to Home

### Files

```text
feature/auth/

LoginScreen.kt
LoginViewModel.kt
```

## M1.7 Session Persistence

### Objective

Keep users authenticated after restarting the application.

### Planned Features

- Auto Login
- Secure Logout
- Session Restore
- Splash Authentication Check

### Files

```text
core/session/

SessionManager.kt
AuthState.kt
```

### Flow

```text
App Launch
     ↓
Session Exists?
 ├── Yes → Home
 └── No  → Onboarding
```

# 6. Current Project Structure

```text
app/src/main/java/com/vista/memoryos/

├── core
│   ├── navigation
│   └── supabase
│
├── data
│   └── repository
│
├── di
│
├── domain
│   ├── model
│   └── repository
│
├── feature
│   ├── onboarding
│   ├── home
│   ├── search
│   ├── timeline
│   └── profile
│
├── MainActivity.kt
└── VistaApplication.kt
```

# 7. Pending Git Milestone

The next commit will be created after M1.5 is completed.

```text
feat(auth): implement deep-link email verification flow
```

This roadmap matches the actual completed work from Module 0 and both Module 1 conversations, without including unimplemented or extra sections.
