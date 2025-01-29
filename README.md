<p align="center">
    <img src="https://github.com/user-attachments/assets/47ff9102-54d1-4c24-ae87-b167d80377bc" alt="drawing" width="120"/>
    <h1 align="center">GameHub App</h1>
</p>

## Overview
Android application designed to display and search Games. The app is built using modern Android development practices and leverages Jetpack Compose for the UI.

<p align="center">
    <img src="https://github.com/user-attachments/assets/983b7948-f19a-4764-9f8e-30546b7dcf84"/>
</p>

<p align="center">
<a href="https://app.bitrise.io/app/ea4403df-6539-49fd-8949-7545b198562f"><img alt="Build Status" src="https://app.bitrise.io/app/ea4403df-6539-49fd-8949-7545b198562f/status.svg?token=5OHAbH5n0_jsufpFp60SiQ&branch=master"/></a>
<a href="https://opensource.org/license/apache-2-0"><img alt="License" src="https://img.shields.io/badge/license-Apache%202.0-blue?style=flat-square"/></a>
<a href="https://android-arsenal.com/api?level=28"><img alt="API" src="https://img.shields.io/badge/API-28%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/jamile-dev"><img alt="Profile" src="https://img.shields.io/badge/jamile-dev?style=flat&logo=Github&label=Github&color=blue&link=github.com%2Fjamile-dev"/></a>
</p>

<p align="center">
🎮 An Android app to discover and explore games, with ratings, genres, platforms, and more!
</p>

## Preview
[demo_app.webm](https://github.com/user-attachments/assets/66617a5a-e010-4db5-9002-8d614d4a3971)

## Download
Get your apk [here](https://github.com/jamile-dev/gamehub/releases/tag/v1)!

## Setting up for Development and Testing

This project uses the RAWG Video Games Database API, which requires an API key.

1. **Obtain an API Key:**

Create a RAWG account and obtain an API key from [https://rawg.io/apidocs](https://rawg.io/apidocs).

2. **Set the API Key as an environment variable:**

The recommended approach is to set your API key as an environment variable.  On Linux/macOS, add the following line to your `.zshrc` (or `.bashrc`, `.bash_profile`, etc., depending on your shell) file:

```bash
export API_KEY="YOUR_ACTUAL_API_KEY"
```
After adding this line, restart your terminal or source the file:
```source ~/.zshrc  # Or source ~/.bashrc, etc.```

On Windows, you can set environment variables through the System Properties (search for "environment variables" in the Start Menu).

> [!TIP]
> This project is already configured to read the API_KEY environment variable during the build process. You don't need to add it anywhere else.


## **Syncing your project:**
After adding or modifying dependencies or making changes to the Gradle files, make sure to sync your project with Gradle to ensure that all dependencies are resolved and the project is configured correctly. You can do this by clicking the "**Sync Project with Gradle Files**" button (usually an elephant icon) in the toolbar of Android Studio.

## **Building the project:**
To build the project, you can use the following command in your terminal:
```
./gradlew assembleDebug  // On Linux/macOS
gradlew assembleDebug    // On Windows
```
This command will compile the project and generate a debug APK that you can install on a device or emulator.

## **Running the app:**

From Android Studio: Click the "Run" button (usually a green play icon) in the toolbar.
From the terminal: After building the project, use the following command:
```
./gradlew installDebug  // On Linux/macOS
gradlew installDebug    // On Windows
```

This will install the debug APK on a connected device or emulator and launch the app.

## Tech Stack & Open-source libraries

* Minimum SDK level 28
* **Kotlin:** Language, Coroutines + Flow for asynchronous operations.
* **Jetpack:**
   * Compose: Modern UI toolkit.
   * Lifecycle: Lifecycle-aware components.
   * ViewModel: UI-related data management.
   * Room: Local database persistence.
   * Hilt: Dependency injection.
   * Paging 3: Efficient pagination.
* **Retrofit2 & OkHttp3:** REST API communication.
* **Coil:** Image loading.
* **Material Design 3:** UI components and theming.
* **ksp:** Kotlin Symbol Processing API for code generation and analysis.

## Architecture & Data Flow

**GameHub** follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture) and utilizes a modularized project structure for improved maintainability and testability. 
The app is designed with an offline-first approach, leveraging Room for caching and data persistence.

<img width="790" alt="Screenshot 2025-01-27 at 08 49 53" src="https://github.com/user-attachments/assets/98bab9eb-e36d-42cd-bd8b-d3fef587322b" />


## Modularization

**GameHub** have a modular architecture to enhance development and maintainability. 
Key benefits include:
* **Improved Code Organization:**  Modules provide clear separation of concerns.
* **Enhanced Reusability:**  Reusable code components are isolated and easily shared.
* **Faster Build Times:** Parallel building of modules reduces overall build time.
* **Strict Visibility Control:**  Modules enforce access restrictions, preventing unintended dependencies.

<img width="1015" alt="Screenshot 2025-01-27 at 08 32 32" src="https://github.com/user-attachments/assets/aa7bcf40-a024-4c96-8afc-726b932095fb" />

For further details on modularization, refer to the official [Guide to app architecture](https://developer.android.com/topic/modularization).

## Open API

This project leverages the [RAWG Video Games Database API](https://rawg.io/apidocs) for fetching game data.  RAWG provides a comprehensive and well-documented API for accessing a vast library of video game information, including details, ratings, genres, platforms, and more.  The API uses a key-based authentication system, and requests are made using standard HTTP methods (GET, POST, etc.).

**Key features of the RAWG API used in this project:**

* **Game details retrieval:**  The `/games/{id}` endpoint is used to fetch detailed information about individual games.
* **Game search:** The `/games` endpoint with search parameters is used to search for games by name.
* **Popular games:** The `/games` endpoint with appropriate sorting parameters is used to retrieve a list of popular games.
* **Recent releases:** The `/games` endpoint with date parameters is used to fetch recently released games.

The RAWG API is a crucial component of this project, enabling access to a rich dataset of video game information and powering the core functionalities of the app.

## Future Enhancements and Possible Improvements

Given more development time, the following enhancements and improvements would be implemented to elevate the **GameHub** app to the next level:

* **Robust Error Handling:**  A more comprehensive error handling system would be implemented, providing specific and user-friendly error messages for various scenarios, ensuring a smoother user experience.  This would involve defining a wider range of error types and handling them gracefully within the UI.

* **Advanced Search Filters:** The search functionality would be significantly enhanced by adding advanced filters. Imagine effortlessly finding games based on genre, platform, release date, and other criteria, empowering users to discover hidden gems and tailor their search precisely to their preferences.

* **Real-time Notifications:**  Push notifications would keep users informed about the latest happenings in the gaming world.  Imagine receiving timely alerts about new game releases, exciting updates, upcoming events, and personalized recommendations, ensuring you never miss a beat.

* **Seamless Offline Experience:**  Enhanced offline support would allow users to access game details and images even without an internet connection.  Imagine browsing your favorite games, reading descriptions, and viewing screenshots anytime, anywhere, regardless of connectivity.

**These enhancements represent a vision for the future of GameHub and would significantly enrich the user experience.  Contributions and suggestions are welcome!**

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/jamile-dev/gamehub/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/jamile-dev)__ on GitHub for my next creations! 🤩
    

# License
```xml
Designed and developed by 2025 Jamile Bastos (jamile-dev)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
