pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VoiceNavigatorApp"

include(
    ":app",
    ":core-model",
    ":core-common",
    ":core-ui",
    ":core-voice",
    ":core-retrieval",
    ":core-llm",
    ":core-storage",
    ":feature-home",
    ":feature-dmv",
    ":feature-esg"
)
