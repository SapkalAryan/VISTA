# VISTA --- ROADMAP.md (Master Project Roadmap)

**Version:** 1.1\
**Status:** Active (Single Source of Truth)\
**Project:** VISTA -- Vision Intelligent Storage & Thought Assistant

> This file is the official planning document for VISTA. Every new chat
> should use the latest version of this roadmap before implementation.

------------------------------------------------------------------------

# 1. Project Vision

VISTA is a **cloud-first AI Memory Operating System** for Android that
securely stores only **user-consented memories** (images, videos, PDFs,
audio, screenshots, documents) and transforms them into searchable
knowledge using OCR, AI summarization, semantic search and calendar
integration.

### Core Principle

> **Capture → Understand → Store → Remember → Retrieve**

------------------------------------------------------------------------

# 2. Frozen Architecture Decisions

  Area                   Decision
  ---------------------- ---------------------------
  Platform               Native Android
  Language               Kotlin
  UI                     Jetpack Compose
  Architecture           MVVM + Clean Architecture
  Dependency Injection   Hilt
  Backend                Supabase
  Database               PostgreSQL
  Vector Database        pgvector
  File Storage           Supabase Storage
  OCR                    Google ML Kit
  AI                     OpenAI
  Calendar               Google Calendar Sync
  Search                 Semantic Embedding Search
  Methodology            Incremental Integration
  Version Control        Git Flow

These decisions are **locked** unless ROADMAP.md is updated.

------------------------------------------------------------------------

# 3. Development Methodology

Every module follows this lifecycle:

1.  Plan
2.  Review
3.  Implement
4.  Test
5.  Git Commit
6.  Update ROADMAP

### Definition of Complete

A module is complete only if:

-   Functional
-   Tested
-   Stable
-   Git committed
-   ROADMAP updated

------------------------------------------------------------------------

# 4. Git Strategy

## Branches

``` text
main
dev
feature/module-name
```

## Commit Convention

``` text
feat:
fix:
refactor:
docs:
test:
```

### Version Tags

  Tag               Meaning
  ----------------- ----------------------
  v0.1-foundation   Foundation completed
  v0.2-auth         Authentication
  v0.3-capture      Capture engine
  v1.0-release      Final APK

------------------------------------------------------------------------

# 5. Module Status

  Module                   Status
  ------------------------ ----------------
  M0 Foundation            🟡 In Progress
  M1 Authentication        ⬜ Pending
  M2 Capture Engine        ⬜ Pending
  M3 Cloud Upload          ⬜ Pending
  M4 AI Pipeline           ⬜ Pending
  M5 Memory Database       ⬜ Pending
  M6 Semantic Search       ⬜ Pending
  M7 Timeline + Calendar   ⬜ Pending
  M8 Collections           ⬜ Pending
  M9 Polish & Release      ⬜ Pending

------------------------------------------------------------------------

# MODULE M0 --- FOUNDATION

**Git Tag:** `v0.1-foundation`

**Status:** 🟡 In Progress

## Objective

Create the engineering foundation of VISTA.

### Deliverables

-   Android project
-   Clean architecture
-   Navigation
-   Hilt
-   Design system
-   Git
-   Supabase connection

------------------------------------------------------------------------

# M0.1 Project Initialization

**Status:** ✅ Completed

## Completed Tasks

-   Kotlin project created
-   Jetpack Compose configured
-   Package: `com.vista.memoryos`
-   compileSdk = 36
-   targetSdk = 36
-   minSdk = 29
-   Build successful

Git Commit (after M0.1):

`feat(M0.1): initialize Android project with Compose foundation`

------------------------------------------------------------------------

# M0.2 Architecture & Dependency Foundation

**Status:** 🟡 Planned

## Objective

Convert the default Android project into a scalable production
architecture.

### Submodules

#### M0.2.1 Package Architecture

Create:

-   core/
-   data/
-   domain/
-   feature/
-   di/

#### M0.2.2 Dependency Injection

Technology:

-   Hilt
-   Singleton Components

#### M0.2.3 Application Layer

Create:

-   VistaApplication.kt

#### M0.2.4 Navigation Foundation

Primary Navigation:

Splash → Onboarding → Main Container

Bottom Tabs:

-   Home
-   Search
-   Timeline
-   Profile

#### M0.2.5 Design System

Reusable Components:

-   VistaButton
-   VistaCard
-   VistaTopBar
-   VistaTextField
-   VistaLoading

#### M0.2.6 MVVM Base

Architecture:

UI → ViewModel → Repository → Data Source

State Management:

-   StateFlow
-   Coroutines

LiveData will not be used.

### Planned Dependencies

-   Hilt
-   Navigation Compose
-   Lifecycle ViewModel
-   Coroutines
-   Material3
-   StateFlow
-   ktlint

### Testing Checklist

-   Hilt initializes
-   Navigation works
-   Theme applied
-   Bottom navigation functional
-   Build successful

------------------------------------------------------------------------

# 6. Future Modules

## M1 Authentication

-   Supabase Auth
-   Login
-   Register
-   Session Persistence

## M2 Capture Engine

-   MediaStore
-   ContentObserver
-   Consent Notifications

## M3 Cloud Upload

-   Compression
-   Supabase Storage
-   Retry Logic

## M4 AI Pipeline

-   OCR
-   AI Summary
-   Smart Tags
-   Embeddings

## M5 Memory Database

-   Metadata
-   Timeline
-   Memory Objects

## M6 Semantic Search

-   Natural Language Retrieval
-   Vector Search

## M7 Timeline + Calendar

-   Google Calendar Sync
-   Event-linked Memories

## M8 Collections

-   User Collections
-   Related Memories

## M9 Polish & Release

-   Encryption
-   Biometric Lock
-   APK
-   Documentation

------------------------------------------------------------------------

# Current Target

**Next Implementation:** M0.2.1 Package Architecture

Only this submodule should be implemented next.
