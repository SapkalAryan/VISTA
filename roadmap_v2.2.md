# VISTA — `roadmap.md` (Version 2.2)

**Project:** VISTA (Virtual Intelligent Storage & Tracking)  
**Current Phase:** Module 2 — Storage & Inventory  
**Status:** Module 0 Completed • Module 1 Completed • Module 2 In Progress

# 1. Project Progress

| Module   | Name                             | Status     |
|----------|----------------------------------|------------|
| Module 0 | Foundation & Infrastructure      | ✅ Complete |
| Module 1 | Authentication & User Management | ✅ Complete |
| Module 2 | Storage & Inventory             | 🔄 In Progress |
| Module 3 | AI Search & Retrieval            | ⏳ Planned     |
| Module 4 | Timeline Engine                  | ⏳ Planned     |

**Overall Progress:** Module 0 and Module 1 completed. Module 2 is currently in progress with M2.1 and M2.2 completed.

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


# 4. Module 2 — Storage & Inventory

## Objective

Build VISTA's durable file storage and inventory foundation before implementing automatic capture, AI understanding, memory generation, and smart retrieval.

The Module 2 architecture separates:

```text
Original / Managed File
        ↓
Supabase Storage

Structured File Information
        ↓
Supabase PostgreSQL

Local Lightweight State / Cache
        ↓
Room Database
```

The VISTA File ID is the permanent logical identity of a managed item and is used to connect its local, cloud metadata, and cloud-storage representations.

### Module 2 Core Flow

```text
File Selected
      ↓
VISTA File Identity Created
      ↓
Local Inventory Record
      ↓
Upload State
      ↓
Supabase Storage
      ↓
Cloud File Metadata
      ↓
VISTA Inventory
```

### Important Architecture Decisions

- Room stores lightweight local metadata/state and does not store actual file bytes.
- Android source files remain at their original location/reference; VISTA uses a `sourceUri` / source reference rather than treating a device filesystem path as the permanent identity.
- The VISTA `fileId` is the permanent logical identity of the item.
- The same logical file identity will connect Room, PostgreSQL, and Supabase Storage.
- Supabase Storage is the long-term managed-file storage location.
- PostgreSQL is the cloud structured-metadata layer.
- Room is the local persistence/cache/state layer.
- The ability to store a file is independent of whether VISTA can currently understand its contents.
- Automatic file detection and user-consent notifications are part of Module 3, not Module 2.
- AI memory generation is part of Module 5; Module 2 only establishes the relationship foundation required for future memories.
- File-to-memory architecture is many-to-many: one file can belong to multiple memories and one memory can contain multiple files.

---

## M2.1 Room Database Architecture ✅

### Objective

Establish Room as VISTA's local persistence layer for lightweight file inventory, state, and cache information.

### Completed

- Room Database integration
- Room 2.8.5
- `VistaDatabase`
- `FileEntity`
- `FileDao`
- Room type converters for file states
- Domain/database mapping
- Database dependency injection
- File repository foundation
- Domain use-case foundation
- Room unit tests
- Room instrumented tests
- Database migration infrastructure

### Architecture

```text
Domain
  ↓
Repository
  ↓
Room DAO
  ↓
FileEntity
  ↓
SQLite
```

### Design Rule

Room stores metadata and state only.

It does not store original file bytes.

### Git Checkpoint

```text
M2.1 Room Database Architecture
```

---

## M2.2 VISTA File Identity & Inventory Model ✅

### Objective

Create the durable VISTA file identity and inventory model required for storage, upload tracking, processing, and future intelligence.

### Completed

#### File Identity

- Permanent VISTA `fileId`
- File ID generation
- `VistaFile` domain model
- `VistaFileFactory`
- Source URI/reference support
- Filename
- MIME type
- File size
- Hash field foundation
- Cloud storage path foundation
- Import/created/updated timestamps

#### File Classification

Implemented `FileCategory` with logical categories for inventory organization.

```text
IMAGE
VIDEO
AUDIO
DOCUMENT
TEXT
SPREADSHEET
PRESENTATION
ARCHIVE
OTHER
```

The category system is extensible and does not imply that every category is already AI-understood.

#### File State Model

Upload state:

```text
PENDING
UPLOADING
UPLOADED
FAILED
```

Processing state:

```text
PENDING
PROCESSING
COMPLETED
FAILED
```

These states provide the foundation for later upload, processing, retry, and failure handling.

#### Repository Layer

Implemented:

- `FileInventoryRepository`
- `FileInventoryRepositoryImpl`
- Hilt repository binding
- Observe all files
- Observe files by category
- Observe files by upload status
- Observe files by processing status
- Get file by VISTA ID
- Save file
- Delete file

#### Use Cases

Implemented:

```text
ObserveFilesUseCase
ObserveFilesByCategoryUseCase
ObserveFilesByUploadStatusUseCase
ObserveFilesByProcessingStatusUseCase
GetFileUseCase
SaveFileUseCase
DeleteFileUseCase

CreateVistaFileUseCase
UpdateFileUploadStatusUseCase
UpdateFileProcessingStatusUseCase

FileCategoryResolver
FileIdGenerator
VistaFileFactory
FileStateValidator
```

#### Database Evolution

- Room schema upgraded from version 1 to version 2.
- Migration `1 → 2` implemented.
- Category/state fields integrated into Room persistence.
- DAO queries added for category and state filtering.

#### Testing

- Room database tests updated for the new schema.
- Instrumented database tests updated.
- Repository tests added.
- Domain/use-case tests added.
- Build verification completed successfully.

### M2.2 Architecture

```text
File Source / Future Picker
        ↓
CreateVistaFileUseCase
        ↓
VistaFileFactory
        ↓
VISTA File ID
        ↓
VistaFile
        ↓
FileInventoryRepository
        ↓
FileDao
        ↓
Room
```

### File Identity Relationship

```text
                 VISTA File ID
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
       Room       PostgreSQL   Supabase Storage
      Metadata     Metadata      Object
```

### Future Memory Relationship

The inventory model is prepared for a future many-to-many file/memory relationship:

```text
File
 │
 ├──── FileMemory ──── Memory
 │
 └──── FileMemory ──── Memory
```

A file may belong to multiple memories, while a memory may contain multiple files.

Actual memory generation and semantic understanding remain future work.

### Git Checkpoint

```text
feat(M2.2): implement VISTA file identity and inventory model
```

### Current M2.2 Working Tree Note

The implementation has been completed and verified. The final M2.2 changes should be committed at the major-module checkpoint before beginning M2.3.

---

# <-- BOOKMARK [Current progress Mark] -->

## M2.3 Manual File Capture ⏳ Next

### Objective

Allow the user to explicitly select a file using Android's Storage Access Framework and create a VISTA inventory item without yet performing automatic detection or cloud upload.

### Planned Flow

```text
User
 ↓
System File Picker
 ↓
Selected URI
 ↓
Read File Metadata
 ↓
Create VISTA File ID
 ↓
Create VistaFile
 ↓
Save to Room
 ↓
Inventory Ready
```

### Planned Capabilities

- Android Storage Access Framework integration
- System file picker
- Supported file-type selection
- Persistable URI permission where available
- Display name extraction
- MIME type extraction
- File-size extraction
- File-category resolution
- VISTA ID generation
- Local inventory creation
- Initial upload/processing states
- Manual-capture failure handling

### Important Boundary

M2.3 will **not**:

- Automatically monitor device files.
- Ask for automatic-capture consent notifications.
- Upload to Supabase Storage.

Those capabilities belong to later milestones.

---

## M2.4 Supabase Storage Integration ⏳ Planned

### Objective

Upload approved/selected VISTA files to Supabase Storage while preserving the VISTA file identity.

### Planned Storage Path

```text
users/
└── {userId}/
    └── files/
        └── {fileId}/
            └── original
```

### Planned Capabilities

- Supabase Storage upload
- Authenticated user ownership
- File ID based storage path
- Upload progress/state
- Upload failure handling
- Retry support
- Cloud storage reference persistence

---

## M2.5 Inventory UI ⏳ Planned

### Objective

Expose stored VISTA files through a usable inventory interface.

### Planned Capabilities

- File list
- Category filtering
- Upload-state visibility
- Processing-state visibility
- File details
- Source/reference information
- Empty-state UX
- Loading/error states

---

## M2.6 Delete & State Management ⏳ Planned

### Objective

Implement safe lifecycle management for VISTA files.

### Planned Capabilities

- File deletion confirmation
- Supabase Storage deletion
- PostgreSQL metadata deletion
- Local Room deletion
- Failure handling
- Upload retry
- Processing-state transitions
- Consistent lifecycle state

### Delete Flow

```text
User Deletes File
      ↓
Confirm
      ↓
Delete Cloud File
      ↓
Delete Cloud Metadata
      ↓
Delete Local Cache
      ↓
File Removed from Inventory
```

---

## M2.7 Relationship Foundation ⏳ Planned

### Objective

Prepare the file inventory for future VISTA memories without implementing AI memory generation yet.

### Planned Architecture

```text
File
  ↕
FileMemory
  ↕
Memory
```

### Design

- Many-to-many file/memory relationship
- Source-file traceability
- Memory association metadata foundation
- Compatible with future AI-generated memories

Actual memory creation, summarization, semantic understanding, and embeddings remain outside Module 2.

---

## Module 2 Completion Target

At the end of Module 2, VISTA should be able to:

1. Select supported files through Android's system picker.
2. Assign every managed file a permanent VISTA ID.
3. Maintain lightweight local file inventory in Room.
4. Upload managed files to Supabase Storage.
5. Maintain structured cloud file metadata.
6. Track upload and processing states.
7. Display the file inventory.
8. Delete files safely across local/cloud representations.
9. Preserve source-file traceability.
10. Provide the relationship foundation required for future memories.

### After Module 2

```text
Module 2 — Storage & Inventory
        ↓
Module 3 — Automatic Capture
        ↓
Module 4 — Content & Metadata
        ↓
Module 5 — Intelligence
        ↓
Module 6 — Smart Retrieval
        ↓
Module 7 — Tracking & Notifications
        ↓
Module 8 — Advanced VISTA
```

# A. Current Project Structure

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
│   ├── repository
│   │   ├── AuthRepositoryImpl.kt
│   │   └── FileInventoryRepositoryImpl.kt
│   └── room
│       ├── converter
│       │   └── FileStatusConverters.kt
│       ├── dao
│       │   └── FileDao.kt
│       ├── entity
│       │   └── FileEntity.kt
│       ├── mapper
│       │   └── FileMapper.kt
│       └── VistaDatabase.kt
│
├── di
│   ├── DatabaseModule.kt
│   ├── RepositoryModule.kt
│   └── SupabaseModule.kt
│
├── domain
│   ├── model
│   │   ├── AuthError.kt
│   │   ├── AuthResult.kt
│   │   ├── FileCategory.kt
│   │   └── VistaFile.kt
│   │
│   ├── repository
│   │   ├── AuthRepository.kt
│   │   └── FileInventoryRepository.kt
│   │
│   └── usecase
│       ├── CreateVistaFileUseCase.kt
│       ├── DeleteFileUseCase.kt
│       ├── FileCategoryResolver.kt
│       ├── FileIdGenerator.kt
│       ├── FileStateValidator.kt
│       ├── GetFileUseCase.kt
│       ├── ObserveFilesByCategoryUseCase.kt
│       ├── ObserveFilesByProcessingStatusUseCase.kt
│       ├── ObserveFilesByUploadStatusUseCase.kt
│       ├── ObserveFilesUseCase.kt
│       ├── SaveFileUseCase.kt
│       ├── UpdateFileProcessingStatusUseCase.kt
│       ├── UpdateFileUploadStatusUseCase.kt
│       └── VistaFileFactory.kt
│
├── feature
│   ├── auth
│   ├── onboarding
│   ├── home
│   ├── search
│   ├── timeline
│   ├── profile
│   └── splash
│
├── MainActivity.kt
└── VistaApplication.kt
```

# B. Latest Git Milestone

### Module 1 Completed

Module 1 — Authentication & User Management is complete.

### Current Development Branch

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

### Module 2 Git Flow

```text
M2.1 Room Database Architecture
 ↓
M2.2 VISTA File Identity & Inventory Model 
 ↓
M2.3 Manual File Capture
 ↓
M2.4 Supabase Storage
 ↓
M2.5 Inventory UI
 ↓
M2.6 Delete & State Management
 ↓
M2.7 Relationship Foundation
 ↓
Module 2 Complete
```

### Completed Module 2 Checkpoints

```text
M2.1 — Room Database Architecture ✅
M2.2 — VISTA File Identity & Inventory Model ✅
```

### Current Next Development Milestone

```text
M2.3 — Manual File Capture
```

### M2.2 Commit

```text
feat(M2.2): implement VISTA file identity and inventory model
```

The commit is the required major-module checkpoint before starting M2.3.

### Roadmap Update Rule

From this point forward, every completed major milestone will update this roadmap in the same format:

```text
1. Project Progress
2. Completed milestone details
3. Current architecture/files
4. Git milestone/checkpoint
5. Next implementation milestone
```