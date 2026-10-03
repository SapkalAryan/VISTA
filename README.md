# VISTA --- Virtual Intelligent Storage & Tracking Assistant

> Android-based intelligent personal file management, storage, tracking,
> and retrieval system.

## Project Status

**Current phase:** Module 1 --- Authentication & User Management\
**Completed through:** M1.6 --- Login System\
**Next milestone:** M1.7 --- Session Persistence

  Module   Name                               Status
  -------- ---------------------------------- ------------------
  M0       Foundation & Infrastructure        ✅ Complete
  M1       Authentication & User Management   🟢 M1.6 Complete
  M2       Memory Capture System              ⏳ Planned
  M3       AI Search & Retrieval              ⏳ Planned
  M4       Timeline Engine                    ⏳ Planned

------------------------------------------------------------------------

## 1. What is VISTA?

VISTA stands for **Virtual Intelligent Storage & Tracking Assistant**.

The long-term product pipeline is:

``` text
Detect / Select
      ↓
User Consent
      ↓
Store
      ↓
Extract & Understand
      ↓
Metadata + Tags + Summary + Memory
      ↓
Index
      ↓
Track Important Information
      ↓
Natural-Language / Smart Search
      ↓
Original File
```

VISTA is intended to evolve from a conventional file-storage application
into an intelligent personal information system.

------------------------------------------------------------------------

## 2. Core Product Principles

-   Cloud storage for persistent user files
-   Local persistence/cache where appropriate
-   Explicit user control over importing and storing files
-   AI-assisted interpretation rather than AI-controlled deterministic
    operations
-   Metadata, extracted text, summaries, and memory treated as distinct
    concepts
-   Memories linked back to their source files
-   Hybrid retrieval using metadata, keyword, and semantic techniques
-   Tracking and notifications as core functionality
-   Android permissions and privacy treated as first-class requirements

------------------------------------------------------------------------

## 3. Technology Stack

### Android

-   Kotlin
-   Jetpack Compose
-   Android SDK
-   Navigation Compose
-   MVVM
-   Clean Architecture
-   StateFlow
-   Hilt
-   KSP

### Backend --- Supabase

Supabase is the project's backend platform.

``` text
Supabase Authentication
        ↓
Email / Password Authentication

Supabase Storage
        ↓
Cloud File Storage

Supabase PostgreSQL
        ↓
Structured Cloud Data
```

Integrated services:

-   Supabase Auth
-   Supabase PostgREST
-   Supabase Storage

### Development Email

``` text
Provider: Gmail SMTP
Host: smtp.gmail.com
Port: 587
Authentication: Gmail App Password
```

> Never commit SMTP passwords, Gmail App Passwords, Supabase secrets,
> API keys, access tokens, or other credentials.

------------------------------------------------------------------------

## 4. Architecture

VISTA follows Clean Architecture + MVVM.

``` text
Presentation / Compose UI
          ↓
ViewModel / StateFlow
          ↓
Domain / Models / Repository APIs
          ↓
Data / Repository Implementations
          ↓
Supabase / Local Data
```

### Dependency rule

``` text
UI
 ↓
ViewModel
 ↓
Domain Repository
 ↓
Repository Implementation
 ↓
Data Source
```

UI should not directly access Supabase or other data sources.

------------------------------------------------------------------------

## 5. Current Project Structure

``` text
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
│   ├── onboarding
│   │   ├── OnboardingScreen.kt
│   │   └── OnboardingViewModel.kt
│   ├── splash
│   │   └── SplashScreen.kt
│   ├── home
│   ├── search
│   ├── timeline
│   └── profile
│
├── MainActivity.kt
└── VistaApplication.kt
```

------------------------------------------------------------------------

# 6. Authentication Navigation

Current authentication flow:

``` text
VISTA
  │
  ├── Sign In
  │      ↓
  │    Login
  │      ↓
  │    Home
  │
  └── Create Account
         ↓
      Registration
         ↓
   Verification Email
         ↓
  Verification Success
         ↓
       Login
```

Registration and login can also navigate to each other.

Main application destinations:

``` text
Home
Search
Timeline
Profile
```

------------------------------------------------------------------------

# 7. Module 0 --- Foundation & Infrastructure

**Status: ✅ Complete**

### M0.1 --- Project Initialization

Completed:

-   Android Studio project
-   Kotlin + Jetpack Compose
-   Gradle configuration
-   Emulator verification
-   Git and GitHub integration

Commit:

``` text
feat(M0.1): initialize Android project with Compose foundation
```

### M0.2.1 --- Package Architecture

``` text
com.vista.memoryos
├── core
├── data
├── di
├── domain
└── feature
```

Commit:

``` text
refactor(M0.2.1): establish clean architecture
```

### M0.2.2 --- Hilt Infrastructure

Completed:

-   Hilt
-   KSP
-   Version Catalog
-   VistaApplication
-   AppModule

Commit:

``` text
feat(M0.2.2): integrate Hilt dependency injection
```

### M0.2.3 --- Navigation Foundation

Completed:

-   Navigation Compose
-   Splash screen
-   Onboarding
-   Bottom navigation
-   Home/Search/Timeline/Profile placeholders

Commit:

``` text
feat(M0.2.3): implement navigation foundation
```

### M0.2.4 --- Design System

Current design tokens:

  Property    Value
  ----------- -------
  Radius      16dp
  Grid        8dp
  Elevation   4dp

Commit:

``` text
feat(M0.2.4): establish reusable design system
```

### M0.2.5 --- MVVM Base

Completed:

-   Base ViewModel
-   UI state foundation
-   StateFlow
-   Lifecycle-aware collection

Commit:

``` text
feat(M0.2.5): establish MVVM architecture
```

------------------------------------------------------------------------

# 8. Module 1 --- Authentication & User Management

**Status: 🟢 Complete through M1.6**

  Submodule                         Status
  --------------------------------- --------
  M1.1 Supabase Integration         ✅
  M1.2 Registration Foundation      ✅
  M1.3 Registration Stabilization   ✅
  M1.4 Gmail SMTP                   ✅
  M1.5 Deep Link Verification       ✅
  M1.6 Login System                 ✅
  M1.7 Session Persistence          ⏳

## M1.1 --- Supabase Backend Integration

**Status: ✅ Complete**

Completed:

-   Supabase Kotlin SDK
-   Auth Client
-   PostgREST Client
-   Storage Client
-   Hilt integration
-   Singleton Supabase client

Flow:

``` text
Android
  ↓
Repository
  ↓
Supabase Client
  ↓
Supabase Cloud
```

Commit:

``` text
feat(M1.1): integrate Supabase backend
```

## M1.2 --- Registration Foundation

**Status: ✅ Complete**

Completed:

-   AuthRepository
-   AuthRepositoryImpl
-   Registration ViewModel
-   Registration UI
-   Password field
-   Loading state
-   StateFlow integration

Flow:

``` text
User Input
    ↓
OnboardingViewModel
    ↓
AuthRepository
    ↓
Supabase Auth
```

Commit:

``` text
feat(M1.2): implement registration foundation
```

## M1.3 --- Registration Stabilization

**Status: ✅ Complete**

Completed:

-   Single-line fields
-   Keyboard Next/Done actions
-   Focus traversal
-   Keyboard dismissal
-   Email validation
-   Password minimum-length validation
-   Create Account button validation
-   Loading-state protection
-   Password visibility
-   Domain-level authentication errors
-   AuthResult sealed class
-   Repository-level error mapping
-   User-friendly error messages
-   Registration failure handling
-   Network failure handling

Registration flow:

``` text
Email Validation
      ↓
Password Validation
      ↓
Create Account
      ↓
ViewModel
      ↓
Repository
      ↓
Supabase Auth
      ↓
Verification Email
```

## M1.4 --- Gmail SMTP

**Status: ✅ Complete**

``` text
Registration
    ↓
Supabase Auth
    ↓
Gmail SMTP
    ↓
Verification Email
    ↓
User Inbox
```

Real verification emails were tested successfully.

## M1.5 --- Deep Link Verification

**Status: ✅ Complete**

Deep link:

``` text
vista://auth
```

Configured in:

-   AndroidManifest
-   Supabase Auth
-   Supabase Redirect URLs
-   MainActivity

Flow:

``` text
Registration
     ↓
Verification Email
     ↓
User taps link
     ↓
Browser
     ↓
VISTA
     ↓
Supabase deep-link handling
     ↓
Authenticated Session
     ↓
Verification Success
     ↓
Login
```

Physical-device testing confirmed:

-   Registration works
-   Verification email arrives
-   Verification link opens VISTA
-   Supabase callback is processed
-   Authenticated session is detected
-   Verification Success screen appears

## M1.6 --- Login System

**Status: ✅ Complete**

Implemented:

-   Login route
-   LoginScreen
-   LoginViewModel
-   Supabase email/password login
-   Email validation
-   Password validation
-   Loading state
-   Password visibility toggle
-   Invalid credential handling
-   Unverified account handling
-   Network failure handling
-   Start → Sign In / Create Account
-   Registration ↔ Login navigation
-   Successful login → Home

Flow:

``` text
Login Screen
      ↓
Email + Password
      ↓
Validation
      ↓
LoginViewModel
      ↓
AuthRepository
      ↓
Supabase Auth
      ↓
Authenticated
      ↓
Home
```

Current domain authentication errors:

``` text
InvalidEmail
WeakPassword
EmptyFields
UserAlreadyExists
InvalidCredentials
EmailNotVerified
NetworkError
TooManyRequests
Unknown
```

Verified login has been tested on a physical Android device and reaches
the Home screen.

------------------------------------------------------------------------

# 9. M1.7 --- Session Persistence

**Status: ⏳ Next**

Objective:

> Keep authenticated users signed in after application restart and route
> them according to their Supabase session.

Target flow:

``` text
App Launch
    ↓
Check Supabase Session
    ↓
Session exists?
    │
    ├── YES → Home
    │
    └── NO → Authentication Entry
```

Planned functionality:

-   Session restoration
-   Automatic authentication routing
-   Splash authentication check
-   Authenticated routing
-   Unauthenticated routing
-   Secure logout
-   Restart-app testing

The existing `AuthViewModel` and Supabase session state will be reused.

------------------------------------------------------------------------

# 10. Future Modules

## Module 2 --- Memory Capture System

Planned direction:

``` text
File Detection / Selection
        ↓
User Consent
        ↓
Capture Queue
        ↓
Cloud Storage
        ↓
Metadata
        ↓
Processing
```

Planned areas:

-   Room database architecture
-   File/inventory models
-   Manual file picker
-   Supabase Storage integration
-   File list/details/delete
-   Processing state
-   MediaStore observation
-   Pending capture queue
-   Notification consent
-   Foreground/background execution
-   Capture filtering
-   End-to-end automatic capture

## Module 3 --- AI Search & Retrieval

``` text
User Query
    ↓
Query Understanding
    ↓
Metadata Search
    +
Keyword Search
    +
Semantic Search
    ↓
Ranked Results
    ↓
Original File
```

The exact AI provider, model, vector store, and ranking strategy remain
to be finalized.

## Module 4 --- Timeline Engine

``` text
Stored Files
     ↓
Extracted Metadata
     ↓
Dates / Events
     ↓
Timeline
     ↓
Tracked Information
```

------------------------------------------------------------------------

# 11. Security & Secrets

Never commit:

``` text
.env
Supabase secret/service-role keys
Gmail passwords
Gmail App Passwords
Private API keys
Access tokens
Refresh tokens
Database credentials
```

Use local configuration for secrets.

Privileged Supabase service-role credentials must never be embedded in
the Android application.

------------------------------------------------------------------------

# 12. Development Environment

Recommended/current environment:

``` text
Android Studio
Kotlin
Jetpack Compose
Gradle
JDK 17
Android Emulator and/or physical Android device
Git
GitHub
```

------------------------------------------------------------------------

# 13. Build

From the project root:

``` powershell
./gradlew assembleDebug
```

Expected successful result:

``` text
BUILD SUCCESSFUL
```

------------------------------------------------------------------------

# 14. Git

Current authentication branch:

``` text
feature/module-1-auth
```

Typical commands:

``` powershell
git status
git add .
git commit -m "feat(Mx.x): description"
git push
```

Latest milestone:

``` text
M1.6 — Login System
```

Recommended commit:

``` text
feat(M1.6): implement login system
```

Next:

``` text
M1.7 — Session Persistence
```

------------------------------------------------------------------------

# 15. Definition of Done

A milestone is complete when:

-   Project builds successfully
-   Feature runs on emulator or physical device
-   Happy path works
-   Expected failure paths work
-   UX is understandable
-   Android permissions are appropriate
-   Source code follows the architecture
-   Changes are committed
-   Changes are pushed
-   Roadmap/documentation is updated

------------------------------------------------------------------------

# 16. Long-Term Architecture

``` text
                    ┌─────────────────────┐
                    │      VISTA App      │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │ Presentation / UI   │
                    │ Jetpack Compose     │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │ ViewModel / State   │
                    │ StateFlow / MVVM    │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │       Domain        │
                    │ Models / Use Cases  │
                    └──────────┬──────────┘
                               │
                    ┌──────────▼──────────┐
                    │        Data         │
                    │    Repositories     │
                    └──────┬───────┬──────┘
                           │       │
              ┌────────────▼─┐   ┌─▼────────────────┐
              │ Local Storage │   │     Supabase     │
              │ Room / SQLite │   │ Auth/Postgres/   │
              │               │   │ Storage          │
              └───────────────┘   └──────────────────┘
```

Future specialized services:

``` text
Capture
Processing
OCR
AI / Intelligence
Search
Tracking
Notifications
```

------------------------------------------------------------------------

# 17. Roadmap

``` text
M0 Foundation
   ↓
M1 Authentication
   ├── M1.1 Supabase Integration       ✅
   ├── M1.2 Registration               ✅
   ├── M1.3 Registration Stabilization ✅
   ├── M1.4 Gmail SMTP                 ✅
   ├── M1.5 Deep Link Verification    ✅
   ├── M1.6 Login                     ✅
   └── M1.7 Session Persistence       ⏳
   ↓
M2 Memory Capture
   ↓
M3 AI Search & Retrieval
   ↓
M4 Timeline Engine
```

------------------------------------------------------------------------

# 18. Project Vision

VISTA is being developed toward a personal intelligent information layer
where files are not treated as isolated objects.

The intended end state is:

``` text
Files
  ↓
Understanding
  ↓
Memory
  ↓
Tracking
  ↓
Search
  ↓
Useful Information
```

The goal is to make important information in a user's digital life
easier to capture, understand, retrieve, and act upon while keeping the
user in control of what is stored and processed.

------------------------------------------------------------------------

## Current Status

**M1.6 --- Login System: ✅ Complete**

**M1.7 --- Session Persistence: ⏳ Next**
