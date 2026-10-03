# VISTA — `roadmap.md` (Version 2.1)

# VISTA — `roadmap.md` (Version 2.1)

**Project:** VISTA (Virtual Intelligent Storage & Tracking)  
**Current Phase:** Module 1 — Authentication & User Management  
**Status:** Module 0 Completed • Module 1 Completed • Module 2 Planned

# 1. Project Progress

| Module   | Name                             | Status     |
|----------|----------------------------------|------------|
| Module 0 | Foundation & Infrastructure      | ✅ Complete |
| Module 1 | Authentication & User Management | ✅ Complete |
| Module 2 | Memory Capture System            | ⏳ Planned  |
| Module 3 | AI Search & Retrieval            | ⏳ Planned  |
| Module 4 | Timeline Engine                  | ⏳ Planned  |

**Overall Progress:** Module 0 and Module 1 completed. Module 2 is the next development phase.

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

| Property  | Value |
|-----------|-------|
| Radius    | 16dp  |
| Grid      | 8dp   |
| Elevation | 4dp   |

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

| Submodule                       | Status     |
|---------------------------------|------------|
| M1.1 Supabase Integration       | ✅ Complete |
| M1.2 Registration Foundation    | ✅ Complete |
| M1.3 Registration Stabilization | ✅ Complete |
| M1.4 Gmail SMTP                 | ✅ Complete |
| M1.5 Deep Link Verification     | ✅ Complete |
| M1.6 Login System               | ✅ Complete |
| M1.7 Session Management         | ✅ Complete |

**Module 1 Status: 100% Complete**

### Module 1 Final Flow

```text
App Launch
      ↓
Supabase Session Check
      ↓
Existing Session?
 ├── Yes → Home
 └── No  → Authentication Entry
                ↓
        Login / Create Account
                ↓
          Supabase Auth
                ↓
        Email Verification
                ↓
          Verified Login
                ↓
              Home
```

### Session Management Flow

```text
Authenticated User
        ↓
Application Restart
        ↓
Session Restored
        ↓
Home
```

### Logout Flow

```text
Home
 ↓
Profile
 ↓
Logout
 ↓
Supabase Session Cleared
 ↓
Authentication Entry
```

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

| Field          | Value                 |
|----------------|-----------------------|
| Provider       | Gmail SMTP            |
| Host           | smtp.gmail.com        |
| Port           | 587                   |
| Authentication | Gmail App Password    |
| Sender         | Dedicated VISTA Gmail |
| Status         | Working               |

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

## M1.6 Login System ✅

### Objective

Allow verified users to securely sign in using Supabase Authentication.

### Completed

- Email & Password login
- Email validation
- Password validation
- Password visibility toggle
- Loading state
- User-friendly login error handling
- Invalid credentials handling
- Unverified email handling
- Network error handling
- Navigation to Home after successful login
- Navigation from Verification Success to Login
- Create Account navigation from Login

### Login Error Handling

The application distinguishes between:

- Invalid credentials
- Unverified email
- Invalid email
- Empty fields
- Network errors
- Too many requests
- Unknown authentication errors

### Files

```text
app/src/main/java/com/vista/memoryos/feature/auth/LoginScreen.kt
app/src/main/java/com/vista/memoryos/feature/auth/LoginViewModel.kt
app/src/main/java/com/vista/memoryos/domain/model/AuthError.kt
app/src/main/java/com/vista/memoryos/data/repository/AuthRepositoryImpl.kt
```

### Login Flow

```text
Login
  ↓
Email + Password Validation
  ↓
LoginViewModel
  ↓
AuthRepository
  ↓
Supabase Auth
  ↓
Authenticated Session
  ↓
Home
```

### Verification Flow

```text
Unverified Account
      ↓
Login Attempt
      ↓
Email Verification Required
      ↓
User Verifies Email
      ↓
Login
      ↓
Home
```

### Git

```text
feat(M1.6): implement login system
```

## M1.7 Session Management ✅

### Objective

Maintain the authenticated Supabase session across application restarts and provide secure logout.

### Implementation

Session management is handled using the Supabase Authentication session state.

The application observes:

```text
Supabase Auth
      ↓
sessionStatus
      ↓
AuthViewModel
      ↓
VistaNavHost
      ↓
Authenticated Routing
```

No separate `SessionManager.kt` or `AuthState.kt` was required for the current implementation.

### Completed

- Supabase session persistence
- Session restoration on application launch
- Authenticated user detection
- Automatic navigation to Home when a valid session exists
- Unauthenticated routing
- Secure logout through Supabase Auth
- Session clearing after logout
- Navigation back to authentication entry after logout
- Protection against incorrectly showing Verification Success during normal authenticated launches
- Physical-device verification of session persistence and logout

### Files

```text
app/src/main/java/com/vista/memoryos/feature/auth/AuthViewModel.kt
app/src/main/java/com/vista/memoryos/core/navigation/VistaNavHost.kt
app/src/main/java/com/vista/memoryos/feature/profile/ProfileScreen.kt
app/src/main/java/com/vista/memoryos/data/remote/SupabaseClient.kt
```

### Session Restore Flow

```text
Application Launch
        ↓
Supabase Auth
        ↓
Session Status
        ↓
Authenticated?
   ┌────┴────┐
  Yes        No
   ↓          ↓
 Home     Authentication
```

### Logout Flow

```text
Profile
   ↓
Logout
   ↓
Supabase Auth Sign Out
   ↓
Session Cleared
   ↓
Authentication Entry
```

### Verification

The following scenarios were successfully tested:

- Correct credentials → Home
- Incorrect password → login error
- Unverified email → verification error
- Unregistered email → login error
- Application restart with active session → Home
- Logout → authentication entry
- Application restart after logout → authentication entry

### Git

```text
feat(M1.7): complete session management and logout
```


# A. Next Implementation Plan

## Module 2 — Memory Capture System

### Objective

Build VISTA's core memory-capture pipeline so that files selected or detected on the Android device can be presented to the user for consent and then uploaded to Supabase Storage.

### Module 2 Core Flow

```text
File Detected / Selected
        ↓
Capture Queue
        ↓
File Information
        ↓
User Consent
   ┌────┴────┐
  Upload    Reject
    ↓
Supabase Storage
    ↓
File Record
    ↓
Supabase PostgreSQL
    ↓
Memory Capture Complete
```

### Planned Capabilities

- System file picker integration
- File selection
- File metadata collection
- Capture queue
- User consent notification
- Upload approval / rejection
- Supabase Storage upload
- Supabase PostgreSQL file record
- Upload status tracking
- Basic file inventory
- Retry handling for failed uploads
- Local queue/state where required
- Original file preservation

### Important Architecture Decision

VISTA will use:

```text
Supabase Storage
        +
Supabase PostgreSQL
        +
Android Local Storage / Room
```

Cloud storage is the primary long-term storage location for captured files.

The application must ask the user for consent before uploading a newly detected file.

### Planned Module 2 Structure

```text
feature/
├── capture/
├── files/
└── upload/

data/
├── remote/
│   ├── Supabase Storage
│   └── Supabase PostgreSQL
└── room/

services/
├── CaptureQueueService
├── MediaMonitorService
├── NotificationService
└── CompressionService
```

### Module 2 Expected Flow

```text
Android Device
      ↓
File Detection / Selection
      ↓
VISTA Capture System
      ↓
User Notification
      ↓
"Upload this file?"
   ┌────┴────┐
  Yes        No
   ↓          ↓
Upload      Ignore
   ↓
Supabase Storage
   ↓
PostgreSQL Metadata
   ↓
File Inventory
```

### Module 2 Completion Target

At the end of Module 2, VISTA should be able to:

1. Receive a file through supported Android capture/selection mechanisms.
2. Display the detected file information.
3. Ask the user whether the file should be uploaded.
4. Upload approved files to Supabase Storage.
5. Store the associated file record in Supabase PostgreSQL.
6. Track upload state and failures.
7. Display the captured file in the VISTA inventory.

### Next Milestone

```text
M2.1 — File Selection & Capture Foundation
```


# B. Current Project Structure

```text
app/src/main/java/com/vista/memoryos/

├── core
│   └── navigation
│       ├── Screen.kt
│       └── VistaNavHost.kt
│
├── data
│   ├── remote
│   │   └── SupabaseClient.kt
│   └── repository
│       └── AuthRepositoryImpl.kt
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
│   │   ├── LoginScreen.kt
│   │   ├── LoginViewModel.kt
│   │   └── VerificationSuccessScreen.kt
│   │
│   ├── onboarding
│   │   ├── OnboardingScreen.kt
│   │   └── OnboardingViewModel.kt
│   │
│   ├── home
│   ├── search
│   ├── timeline
│   ├── profile
│   │   └── ProfileScreen.kt
│   └── splash
│       └── SplashScreen.kt
│
├── MainActivity.kt
└── VistaApplication.kt
```


# C. Latest Git Milestone

### Module 1 Completed

Module 1 — Authentication & User Management is now complete.

### Branch

```text
feature/module-1-auth
```

### Module 1 Git Flow

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
M1.5 Deep Link Verification
 ↓
M1.6 Login System
 ↓
M1.7 Session Management + Logout
 ↓
Module 1 Complete ✅
```

### Final Module 1 Commit

```text
feat(M1.7): complete session management and logout
```

### Next Development Milestone

```text
M2.1 — File Selection & Capture Foundation
```

This roadmap matches the actual completed work from Module 0 and both Module 1 conversations, without including unimplemented or extra sections.
