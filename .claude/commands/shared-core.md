Use the rag-ingestion skill.

Read:
- .claude/CLAUDE.md
- docs/architecture/retrieval-flow.md
- related files in core and app

Task:
Implement or improve shared voice, retrieval, navigation, or answer-rendering infrastructure.

Requirements:
- keep shared contracts stable
- avoid feature-specific leakage into core
- keep retrieval simple and source-grounded
- preserve metadata for citations
- make integration easy for both feature modules

Return:
1. short plan
2. files changed
3. verification performed
4. downstream integration impact