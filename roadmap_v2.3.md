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

**Overall Progress:** Module 0 and Module 1 completed. Module 2 is in progress with M2.1, M2.2, M2.3, and M2.4 completed. M2.5 is next.

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

The VISTA File ID is the permanent logical identity of a managed item and connects its local, cloud metadata, and cloud-storage representations.

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
- Android source files remain at their original location/reference; VISTA uses a `sourceUri` / source reference rather than treating a device filesystem path as permanent identity.
- The VISTA `fileId` is the permanent logical identity of the item.
- The same logical identity connects Room, PostgreSQL, and Supabase Storage.
- Supabase Storage is the long-term managed-file storage location.
- PostgreSQL is the cloud structured-metadata layer.
- Room is the local persistence/cache/state layer.
- The ability to store a file is independent of whether VISTA can currently understand its contents.
- Automatic file detection and user-consent notifications belong to Module 3.
- AI memory generation belongs to Module 5; Module 2 establishes only the relationship foundation.
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
feat(M2.1): establish Room database architecture
```

---

## M2.2 VISTA File Identity & Inventory Model ✅

### Objective

Create the durable VISTA file identity and inventory model required for storage, upload tracking, processing, and future intelligence.

### Completed

- Permanent VISTA `fileId`
- File ID generation
- `VistaFile` domain model
- `VistaFileFactory`
- Source URI/reference support
- Filename, MIME type, file size
- Hash field foundation
- Cloud storage path foundation
- Created/updated timestamps
- `FileCategory` logical classification
- Upload states: `PENDING`, `UPLOADING`, `UPLOADED`, `FAILED`
- Processing states: `PENDING`, `PROCESSING`, `COMPLETED`, `FAILED`
- `FileInventoryRepository` and implementation
- Hilt repository binding
- Inventory observation/filtering
- Get/save/delete use cases
- File identity/category/state use cases and validation
- Room schema version 2 and migration `1 → 2`
- Unit, repository, and instrumented tests
- Successful build verification

### File Categories

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

### Identity Architecture

```text
                 VISTA File ID
                      │
          ┌───────────┼───────────┐
          ↓           ↓           ↓
       Room       PostgreSQL   Supabase Storage
      Metadata     Metadata      Object
```

### Future Memory Relationship

```text
File
 │
 ├──── FileMemory ──── Memory
 │
 └──── FileMemory ──── Memory
```

Actual memory creation and semantic understanding remain future work.

### Git Checkpoint

```text
feat(M2.2): implement VISTA file identity and inventory model
```

---

## M2.3 Manual File Capture ✅

### Objective

Allow the user to explicitly select files through Android's Storage Access Framework, create permanent VISTA identities, persist them locally, and provide robust manual-capture UX.

### Completed

#### M2.3.1 System File Picker

- Android `OpenMultipleDocuments()` picker
- Multi-file selection
- Supported arbitrary file selection through `*/*`
- URI acquisition
- Persistable URI permission where supported

#### M2.3.2 File Metadata & VISTA Identity

- Display name extraction
- MIME type extraction
- File size extraction
- File category resolution
- Permanent VISTA `fileId`
- Authenticated user association
- `sourceUri` persistence

#### M2.3.3 Local Inventory Persistence

- Selected files saved to Room
- Inventory observed through `ObserveFilesViewModel`
- Inventory survives application restart
- Existing items remain visible

#### M2.3.4 Manual Capture UX

- Add File action
- Multi-file sequential processing
- Progress state
- Success state
- Partial-success state
- Error state
- VISTA ID displayed in inventory cards

#### M2.3.5 Failure Handling

- Picker cancellation handled safely
- Invalid/unreadable URI handling
- Per-file failure isolation
- Partial multi-file success handling
- Failed items do not silently disappear

#### M2.3.6 Inventory Verification

Verified:

- JPG/PNG
- PDF
- PPT/PPTX
- M4A
- MP4
- Multiple-file selection
- Local persistence after restart
- Unique VISTA ID per captured item

### Manual Capture Flow

```text
User
 ↓
System File Picker
 ↓
Selected URI(s)
 ↓
Read Metadata
 ↓
Create VISTA File ID
 ↓
Create VistaFile
 ↓
Save to Room
 ↓
Inventory Ready
```

### Boundary

M2.3 does not implement:

- Automatic device monitoring
- Automatic-capture consent notifications
- Cloud upload as a separate automatic-detection feature

Cloud upload is implemented in M2.4.

### Git Checkpoint

```text
43f5d58 feat(M2.3): implement manual file capture and inventory
```

---

## M2.4 Supabase Storage Integration ✅

### Objective

Upload VISTA-managed files to private Supabase Storage while preserving the VISTA file identity across local and cloud representations.

### Completed Submodules

#### M2.4.1 Storage Repository Architecture ✅

Implemented:

- `FileStorageRepository`
- `FileStorageRepositoryImpl`
- Supabase Storage integration
- Upload/delete use cases
- Hilt repository binding
- `Context` injection required for source URI access

#### M2.4.2 Supabase Bucket & Actual Upload ✅

Configured:

```text
Bucket: files
Public: OFF
```

Storage path:

```text
users/{userId}/files/{VISTA_ID}/original
```

Configured authenticated-user policies for:

- INSERT
- SELECT
- UPDATE
- DELETE

Verified actual Android upload to Supabase Storage.

#### M2.4.3 Upload State & Failure Recovery ✅

Implemented lifecycle:

```text
PENDING
   ↓
UPLOADING
   ↓
UPLOADED
```

Failure:

```text
PENDING
   ↓
UPLOADING
   ↓
FAILED
```

Implemented:

- Local Room state synchronization
- `cloudPath` persistence
- Failed-upload persistence
- Retry upload
- Same VISTA ID preserved during retry
- Successful retry changes state to `UPLOADED`

#### M2.4.4 Multi-file Cloud Upload ✅

Verified:

- Multiple files can be selected
- Each file receives a unique VISTA ID
- Files upload independently
- Each file gets its own cloud object
- Multiple uploaded files remain correctly represented in Room and Storage

#### M2.4.5 Upload Error UX & Recovery ✅

Implemented:

- Raw Supabase/network errors are no longer exposed as the primary user-facing message
- Network failures produce user-friendly messages
- Failed files remain in inventory
- Retry action remains available
- Retry after network restoration was successfully verified

Example:

```text
Upload failed

No internet connection. Please check your connection
and tap Retry Upload.
```

#### M2.4.6 Final Storage Verification ✅

Verified:

- Single-file upload
- Multi-file upload
- App inventory state after upload
- Supabase Storage object existence
- Correct `users/{userId}/files/{VISTA_ID}/original` path
- VISTA ID consistency between Android inventory and Storage
- Application restart persistence
- Failed upload recovery
- Retry preserving the original VISTA ID

### M2.4 Final Architecture

```text
Android Source File
        ↓
CreateVistaFileUseCase
        ↓
Permanent VISTA ID
        ↓
Room Inventory Record
        ↓
Upload State = UPLOADING
        ↓
UploadVistaFileUseCase
        ↓
Supabase Storage
        ↓
users/{userId}/files/{VISTA_ID}/original
        ↓
Room
cloudPath + UPLOADED
```

### Failure / Recovery

```text
Upload
  ↓
Network Failure
  ↓
FAILED
  ↓
User Restores Network
  ↓
Retry Upload
  ↓
Same VISTA ID
  ↓
UPLOADED
```

### Important State Note

Files captured before M2.4 may still show:

```text
Upload: PENDING
```

because they predate the cloud-upload workflow. This is expected until a later migration/backfill/retry policy is intentionally implemented.

`Processing: PENDING` remains expected because content processing belongs to later modules.

### Git Checkpoint

```text
feat(M2.4): integrate Supabase storage and upload recovery
```

---

# <-- BOOKMARK [Current progress Mark] -->

## M2.5 Inventory UI ⏳ Next

### Objective

Build the proper inventory experience around the now-functional local + cloud file foundation.

### Planned Submodules

#### M2.5.1 Inventory Screen

- Dedicated inventory screen
- Move beyond temporary Home-screen inventory presentation
- File list
- Empty state
- Loading state
- Error state

#### M2.5.2 File Cards

- Filename
- VISTA ID
- File category
- File size
- Upload state
- Processing state
- File-type appropriate visual representation

#### M2.5.3 Filtering & Organization

- Category filtering
- Upload-state filtering
- Processing-state filtering
- Future extensibility for tags/memories

#### M2.5.4 File Details

- Full metadata
- VISTA ID
- Source reference
- Cloud path/reference
- Upload status
- Processing status
- Timestamps

#### M2.5.5 Inventory UX Stabilization

- Loading states
- Empty states
- Error states
- Long filename handling
- Large inventory performance
- Navigation into file details

### Target Flow

```text
Home / Inventory
       ↓
File List
       ↓
File Card
       ↓
File Details
```

---

## M2.6 Delete & State Management ⏳ Planned

### Objective

Implement safe lifecycle management for VISTA files across cloud storage and local inventory.

### Planned Capabilities

- File deletion confirmation
- Supabase Storage deletion
- Cloud metadata deletion
- Room deletion
- Failure handling
- Retry handling
- State consistency
- Safe deletion ordering

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
Delete Local Record
      ↓
File Removed from Inventory
```

---

## M2.7 Relationship Foundation ⏳ Planned

### Objective

Prepare the inventory for future VISTA memories without implementing AI memory generation.

### Planned Architecture

```text
File
  ↕
FileMemory
  ↕
Memory
```

### Planned Capabilities

- Many-to-many file/memory relationship
- Source-file traceability
- Relationship metadata foundation
- Future compatibility with AI-generated memories

Actual memory creation, summarization, semantic understanding, and embeddings remain outside Module 2.

---

## Module 2 Completion Target

At the end of Module 2, VISTA should be able to:

1. Select files through Android's system picker.
2. Assign every managed file a permanent VISTA ID.
3. Maintain lightweight local file inventory in Room.
4. Upload managed files to private Supabase Storage.
5. Maintain the cloud storage reference.
6. Track upload and processing states.
7. Display the file inventory.
8. Delete files safely across cloud/local representations.
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
│   │   └── SupabaseClientProvider.kt
│   ├── repository
│   │   ├── AuthRepositoryImpl.kt
│   │   ├── FileInventoryRepositoryImpl.kt
│   │   └── FileStorageRepositoryImpl.kt
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
│   ├── AppModule.kt
│   ├── DatabaseModule.kt
│   ├── NetworkModule.kt
│   └── RepositoryModule.kt
│
├── domain
│   ├── model
│   │   ├── AuthError.kt
│   │   ├── AuthResult.kt
│   │   ├── FileCategory.kt
│   │   ├── FileProcessingStatus.kt
│   │   ├── FileUploadStatus.kt
│   │   └── VistaFile.kt
│   │
│   ├── repository
│   │   ├── AuthRepository.kt
│   │   ├── FileInventoryRepository.kt
│   │   └── FileStorageRepository.kt
│   │
│   └── usecase
│       ├── CreateVistaFileUseCase.kt
│       ├── DeleteFileUseCase.kt
│       ├── DeleteVistaCloudFileUseCase.kt
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
│       ├── UploadVistaFileUseCase.kt
│       └── VistaFileFactory.kt
│
├── feature
│   ├── auth
│   ├── onboarding
│   ├── home
│   │   ├── HomeScreen.kt
│   │   ├── HomeViewModel.kt
│   │   ├── ManualFileCaptureViewModel.kt
│   │   └── ObserveFilesViewModel.kt
│   ├── search
│   ├── timeline
│   ├── profile
│   └── splash
│
├── MainActivity.kt
└── VistaApplication.kt
```

# B. Latest Git Milestone

### Current Development Branch

```text
feature/module-2-storage
```

### Module 2 Git Flow

```text
M2.1 Room Database Architecture
        ↓
M2.2 VISTA File Identity & Inventory Model
        ↓
M2.3 Manual File Capture
        ↓
M2.4 Supabase Storage Integration
        ↓
M2.5 Inventory UI  ← NEXT
        ↓
M2.6 Delete & State Management
        ↓
M2.7 Relationship Foundation
        ↓
Module 2 Complete
```

### Completed Module 2 Checkpoints

```text
M2.1 — Room Database Architecture                 ✅
M2.2 — VISTA File Identity & Inventory Model      ✅
M2.3 — Manual File Capture                        ✅
M2.4 — Supabase Storage Integration               ✅
```

### Latest Git Milestone

```text
feat(M2.4): integrate Supabase storage and upload recovery
```

### Next Development Milestone

```text
M2.5 — Inventory UI
```

### Git Rule

A Git commit is created at each **major module milestone**:

```text
M2.1 → commit
M2.2 → commit
M2.3 → commit
M2.4 → commit
M2.5 → commit
...
```

Submodules inside a major milestone are completed and tested before the corresponding major-milestone commit.

### Roadmap Update Rule

Every roadmap update must:

1. Preserve the same overall format.
2. Move the `BOOKMARK` to immediately before the next major module/submodule being started, while reflecting the module just completed.
3. Keep **A. Current Project Structure** and **B. Latest Git Milestone** as the final two sections.
4. Update completed work, current status, architecture, Git checkpoint, and next milestone.
5. Never place A or B before the end of the roadmap.
6. Keep completed milestones marked `✅`, the active milestone marked `⏳`/`🔄`, and future milestones marked `⏳`.
