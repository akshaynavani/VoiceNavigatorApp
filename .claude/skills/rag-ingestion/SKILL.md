---
name: rag-ingestion
description: Work on local document retrieval, chunking, source metadata, and citation-safe answer grounding for the Voice Navigator app.
---

# RAG Ingestion Skill

When invoked:

1. Read related files in:
    - `core/retrieval/`
    - `docs/sources/`
    - `docs/architecture/retrieval-flow.md`
    - `.claude/CLAUDE.md`

2. Keep implementation simple:
    - local corpus only
    - preserve page/document metadata
    - avoid unnecessary abstraction
    - do not add internet retrieval

3. Ensure domain isolation:
    - DMV mode uses handbook corpus only
    - ESG mode uses report corpus only

4. Output format:
    - short plan
    - files changed
    - verification performed
    - grounding/citation risks