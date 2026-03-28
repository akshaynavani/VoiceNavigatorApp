# Claude sub-agent orchestration map

## Agent 1: feature-dmv
Allowed modules:
- feature-dmv
- core-model (only if necessary)
- core-ui (only if necessary)

## Agent 2: feature-esg
Allowed modules:
- feature-esg
- core-model (only if necessary)
- core-ui (only if necessary)

## Agent 3: shared voice
Allowed modules:
- core-voice
- core-common
- core-ui

## Agent 4: retrieval
Allowed modules:
- core-retrieval
- core-model
- core-storage

## Agent 5: llm prompting
Allowed modules:
- core-llm
- core-model

## Agent 6: integration
Allowed modules:
- app
- DI/navigation contracts only
- minimal compatibility fixes elsewhere

## Rule
Do not let feature agents directly rewrite each other’s modules.
