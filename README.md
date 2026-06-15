# UserApp

A modern Android application built with MVVM architecture that fetches a list of users and displays their details.

## Demo Video

https://github.com/user-attachments/assets/775340bb-7f94-4873-94be-e72b4f893fc8

## Tech Stack

- **Architecture**: MVVM
- **UI**: Jetpack Compose
- **DI**: Dagger Hilt
- **Async**: Kotlin Flows
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Coil


## Project Structure

```
UserApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/assignment/userapp/
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── User.kt            # User data model
│   │   │   │   │   │   └── UserUiState.kt     # UI state representations
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   ├── UserRepository.kt      # Repository interface
│   │   │   │   │   │   └── UserRepositoryImpl.kt  # Repository implementation
│   │   │   │   │   └── ErrorMessage.kt        # Error message mapping/handling
│   │   │   │   ├── di/
│   │   │   │   │   └── module/
│   │   │   │   │       └── NetworkModule.kt   # Hilt module for network dependencies
│   │   │   │   ├── network/
│   │   │   │   │   ├── UserService.kt         # Retrofit API definitions
│   │   │   │   │   └── ValidateApiCall.kt     # API response validation helper
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/
│   │   │   │   │   │   ├── UserListScreen.kt     # User list screen (Composable)
│   │   │   │   │   │   ├── UserDetailsScreen.kt  # User details screen (Composable)
│   │   │   │   │   │   └── Views.kt              # Shared/reusable Composables
│   │   │   │   │   ├── theme/
│   │   │   │   │   │   ├── Color.kt
│   │   │   │   │   │   ├── Theme.kt
│   │   │   │   │   │   └── Type.kt
│   │   │   │   │   ├── AppNavHost.kt          # Navigation graph
│   │   │   │   │   └── Routes.kt              # Navigation route definitions
│   │   │   │   ├── viewmodel/
│   │   │   │   │   └── UserViewModel.kt       # ViewModel for user list/details
│   │   │   │   ├── Constants.kt               # App-wide constants
│   │   │   │   ├── MainActivity.kt            # Entry point Activity
│   │   │   │   └── UserApp.kt                 # Application class (Hilt entry point)
│   │   │   ├── res/                           # Resources (strings, themes, icons, etc.)
│   │   │   └── AndroidManifest.xml
│   │   ├── test/                              # Unit tests
│   │   │   └── java/com/assignment/userapp/
│   │   │       ├── ExampleUnitTest.kt
│   │   │       └── UserTest.kt
│   │   └── androidTest/                       # Instrumented tests
│   │       └── java/com/assignment/userapp/
│   │           └── ExampleInstrumentedTest.kt
│   ├── build.gradle.kts                       # App-level Gradle config
│   └── proguard-rules.pro
├── gradle/
│   └── libs.versions.toml                     # Version catalog
├── build.gradle.kts                           # Project-level Gradle config
├── settings.gradle.kts
└── gradle.properties
```

## Architecture Overview

- **UI Layer**: Compose screens (`UserListScreen`, `UserDetailsScreen`) observe state from `UserViewModel` via Flows.
- **ViewModel Layer**: `UserViewModel` exposes UI state and handles user actions.
- **Data Layer**: `UserRepository` abstracts data access; `UserRepositoryImpl` fetches data via `UserService` (Retrofit).
- **DI**: Dagger Hilt wires repositories, network clients, and view models throughout the app via `NetworkModule` and `UserApp`.
- The base API URL (`https://fake-json-api.mock.beeceptor.com/`) is configured via `BuildConfig.BASE_URL` for both debug and release builds.

## Testing

- Unit tests: `app/src/test`

