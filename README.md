# Close Tabs Without Breakpoints

An IntelliJ IDEA plugin designed to streamline your workspace after debugging sessions. It provides a specialized action to close all open editor tabs except those that contain active line breakpoints.

## Features

- **Context-Aware Action**: The "Close Tabs Without Breakpoints" option only appears in the tab context menu when at least one open file has a breakpoint.
- **Visual Grouping**: Integrated directly into the standard "Close" action group for a native feel.
- **Mnemonic Support**: Quickly trigger the action by opening the context menu and pressing `B`.
- **Custom Icon**: Easily identifiable via a custom "Breakpoint-Trash" icon.

## Development

This project is built using the **IntelliJ Platform Gradle Plugin (2.x)** and **Kotlin**.

### Essential Gradle Tasks

| Task | Description |
| :--- | :--- |
| `./gradlew runIde` | Launches a sandbox instance of IntelliJ IDEA with the plugin installed. |
| `./gradlew test` | Runs the integration tests (includes UI/Headless IDE tests). |
| `./gradlew buildPlugin` | Assembles the plugin distribution ZIP file in `build/distributions`. |
| `./gradlew verifyPlugin` | Validates the plugin compatibility and structure against JetBrains' rules. |
| `./gradlew patchPluginXml` | Updates versioning and metadata in the `plugin.xml` automatically. |

### Project Configuration

- **SDK**: Java 21 (managed via Foojay Toolchains)
- **Target Platform**: IntelliJ IDEA 2024.3+
- **Language**: Kotlin 2.1.0

## Installation

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Run the `./gradlew runIde` task to test the plugin locally.
4. To install in your primary IDE:
    - Run `./gradlew buildPlugin`.
    - In IntelliJ, go to `Settings` > `Plugins` > `⚙️` > `Install Plugin from Disk...`.
    - Select the ZIP file located in `build/distributions/`.
