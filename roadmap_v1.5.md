# VISTA — `roadmap.md` (Version 2.1)

**Project:** VISTA (Virtual Intelligent Storage & Tracking)  
**Current Phase:** Module 1 — Authentication & User Management (M1.6 Login)  
**Status:** Module 0 Completed • Module 1 In Progress

# 1. Project Progress

| Module | Name | Status |
| --- | --- | --- |
| Module 0 | Foundation & Infrastructure | ✅ Complete |
| Module 1 | Authentication & User Management | 🟡 65% Complete |
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

# Current Module 1 Progress

| Submodule | Status |
| --- | --- |
| M1.1 Supabase Integration | ✅ Complete |
| M1.2 Registration Foundation | ✅ Complete |
| M1.3 Registration Stabilization | ✅ Complete |
| M1.4 Gmail SMTP | ✅ Complete |
| M1.5 Deep Link Verification | ✅ Complete |
| M1.6 Login System | ⏳ Next |
| M1.7 Session Persistence | ⏳ Pending |

**Overall Progress: 65%**

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

Replace the **entire M1.3 section** with this:

```markdown
## M1.3 Registration Stabilization ✅

### M1.3.1 Form UX ✅

### Completed

- Single-line fields
- Next keyboard action
- Done submits form
- Keyboard dismissal
- Focus traversal
- Email format validation
- Password minimum-length validation
- Create Account button validation
- Loading state during registration
- Password visibility toggle

### M1.3.2 Error Handling ✅

### Completed

- Domain-level authentication error model
- `AuthResult` sealed class
- Repository-level authentication error handling
- User-friendly authentication messages
- No raw authentication exceptions exposed to the UI
- Loading state protection
- Registration failure handling

### Files

```text
domain/model/AuthError.kt
domain/model/AuthResult.kt
domain/repository/AuthRepository.kt
data/repository/AuthRepositoryImpl.kt
feature/onboarding/OnboardingViewModel.kt
feature/onboarding/OnboardingScreen.kt
```

### M1.3.3 Authentication Failure Handling ✅

### Completed

- Authentication failure detection
- Supabase authentication error mapping
- User-friendly error messages
- Registration failure feedback
- Loading state reset after success or failure

### Current Registration Flow

```text
User Input
      ↓
Email Validation
      ↓
Password Validation
      ↓
Create Account
      ↓
OnboardingViewModel
      ↓
AuthRepository
      ↓
Supabase Auth
      ↓
Verification Email
```

### Registration UX

```text
Email
 ↓ Next
Password
 ↓ Done
Create Account
```

### Git

```text
Included in Module 1 authentication implementation
```

**Paste it exactly where your current `## M1.3 Registration Stabilization (Current)` section is.** Do not keep any part of the old M1.3 section.

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

### Verification

- Gmail SMTP successfully sends real verification emails.
- Verification emails were tested using the physical Android device.
- The verification link successfully reaches the user's inbox.

## M1.5 Deep Link Verification ✅

### Objective

Complete the email verification journey by opening VISTA directly from the verification link and detecting the authenticated Supabase session.

### Implementation

The Android application handles the Supabase authentication deep link directly through `MainActivity.kt`.

No separate `DeepLinkHandler.kt` is required.

### Files Created

```text
app/src/main/java/com/vista/memoryos/feature/auth/

AuthViewModel.kt
VerificationSuccessScreen.kt
```

### Files Modified

```text
app/src/main/AndroidManifest.xml

app/src/main/java/com/vista/memoryos/MainActivity.kt
app/src/main/java/com/vista/memoryos/core/navigation/Screen.kt
app/src/main/java/com/vista/memoryos/core/navigation/VistaNavHost.kt
```

### Deep Link Configuration

```text
Scheme: vista
Host: auth

Redirect URI:

vista://auth
```

### Android Flow

```text
Create Account
      ↓
Supabase Auth
      ↓
Gmail SMTP
      ↓
Verification Email
      ↓
User Taps Verification Link
      ↓
Browser
      ↓
VISTA Opens
      ↓
Supabase handleDeeplinks(intent)
      ↓
Session Restored
      ↓
SessionStatus.Authenticated
      ↓
Verification Success Screen
```

### Verification Success Screen

The application displays:

```text
Email verified!

Your VISTA account has been successfully verified.
```

A Continue button is provided.

### Current Continue Behaviour

Because the Login System (M1.6) has not yet been implemented, the Continue button currently returns the user to the existing onboarding/registration screen.

This will be changed to the Login screen after M1.6 is implemented.

### Physical Device Testing

M1.5 was tested successfully on a physical OnePlus Nord CE4.

Verified:

- Registration works.
- Verification email is received.
- Verification link opens the VISTA application.
- Supabase authentication deep link is processed.
- Authenticated session is detected.
- Verification Success screen is displayed.
- Normal application launch does not incorrectly display the verification screen.

### Git

```text
feat(M1.5): implement email verification deep link flow
```

# Next Implementation Plan

## M1.6 Login System

### Objective

Allow verified users to securely sign in after completing email verification.

### Planned Features

- Email & Password login
- Friendly error handling
- Forgot Password
- Loading state
- Form validation
- Password visibility toggle
- Navigation to Home
- Navigation from Verification Success to Login

### Planned Files

```text
feature/auth/

LoginScreen.kt
LoginViewModel.kt
```

### Expected Flow

```text
App Launch
      ↓
Login
      ↓
Email + Password
      ↓
Supabase Auth
      ↓
Authenticated
      ↓
Home
```

### Post-Verification Flow

```text
Verification Success
        ↓
Continue
        ↓
Login
```

## M1.7 Session Persistence

### Objective

Keep users authenticated after restarting the application.

### Planned Features

- Auto Login
- Secure Logout
- Session Restore
- Splash Authentication Check
- Authenticated user routing
- Unauthenticated user routing

### Planned Files

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
 └── No  → Login / Onboarding
```

# 4. Current Project Structure

```text
app/src/main/java/com/vista/memoryos/

├── core
│   └── navigation
│
├── data
│   ├── remote
│   │   └── SupabaseClient.kt
│   └── repository
│
├── di
│
├── domain
│   ├── model
│   │   ├── AuthError.kt
│   │   └── AuthResult.kt
│   └── repository
│       └── AuthRepository.kt
│
├── feature
│   ├── auth
│   │   ├── AuthViewModel.kt
│   │   └── VerificationSuccessScreen.kt
│   │
│   ├── onboarding
│   │   ├── OnboardingScreen.kt
│   │   └── OnboardingViewModel.kt
│   │
│   ├── home
│   ├── search
│   ├── timeline
│   └── profile
│
├── MainActivity.kt
└── VistaApplication.kt
```

# 7. Latest Git Milestone

### M1.5 Completed

M1.5 email verification deep-link flow has been implemented and tested on a physical Android device.

### Branch

```text
feature/module-1-auth
```

### Previous Commit

```text
8a77ed7
chore(M1.4): configure Gmail SMTP
```

### M1.5 Commit

```text
feat(M1.5): implement email verification deep link flow
```

### Current Git Flow

```text
M0
 ↓
M1.1 Supabase Integration
 ↓
M1.2 Registration Foundation
 ↓
M1.3 Registration Stabilization
 ↓
M1.4 Gmail SMTP
 ↓
M1.5 Deep Link Verification ✅
 ↓
M1.6 Login System
 ↓
M1.7 Session Persistence
```

### Next Development Milestone

```text
M1.6 — Login System
```

This roadmap matches the actual completed work from Module 0 and both Module 1 conversations, without including unimplemented or extra sections.
