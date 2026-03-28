# Project Instructions

You are helping build a modular Android hackathon app in Kotlin with Jetpack Compose.

## Product
Single Android app with:
1. DMV Voice Navigator
2. ESG Report Navigator

## Modularity goals
This repo is intentionally split for parallel Claude sub-agent work.

Shared modules:
- core-model
- core-common
- core-ui
- core-voice
- core-retrieval
- core-llm
- core-storage

Feature modules:
- feature-home
- feature-dmv
- feature-esg

Integration:
- app

## Ownership model
- feature-dmv: DMV-specific logic and UI
- feature-esg: ESG-specific logic and UI
- core-* modules: shared contracts and infra
- app: navigation and dependency wiring

## Rules
- Do not collapse modules unless explicitly asked
- Avoid editing unrelated modules
- Keep feature logic out of app
- Keep retrieval source-grounded
- Prefer additive changes over disruptive refactors

## Retrieval constraints
- DMV answers must only use DMV-approved sources
- ESG answers must only use approved reports
- Preserve citation metadata

## Before changes
1. Read related files
2. State short plan
3. List impacted modules

## After changes
1. List files changed
2. Summarize implementation
3. State verification
4. Mention integration risks
