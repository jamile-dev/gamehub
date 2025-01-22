# Game Hub App

## Overview
Android application designed to display and search Games. The app is built using modern Android development practices and leverages Jetpack Compose for the UI.

## Architecture
The project follows the MVVM (Model-View-ViewModel) architecture pattern, which helps in separating concerns and making the codebase more maintainable and testable.

### Layers
- **Domain Layer**: Contains the business logic of the application.
- **Data Layer**: Manages data sources, including network and local database.
- **Presentation Layer**: Handles the UI and user interactions using Jetpack Compose.

## Technologies Used
- **Kotlin**: The primary programming language.
- **Jetpack Compose**: For building the UI.
- **Hilt**: For dependency injection.
- **Retrofit**: For network operations.
- **Coil**: For image loading.
- **Material3**: For UI components and theming.

## Key Components

### Domain Layer
- **Models**: Defines the data structures used across the app.
- **Repositories**: Interfaces for data operations.

### Data Layer
- **API**: Contains Retrofit interfaces for network calls.
- **Database**: Contains Room database and DAO interfaces.
- **Repositories**: Implements the domain layer repositories.

### Presentation Layer
- **Components**: Reusable UI components built with Jetpack Compose.
- **Screens**: Composables representing different screens of the app.
- **ViewModels**: Manages UI-related data and business logic.

## Setup and Installation
1. **Clone the repository**:
    ```sh
    git clone https://github.com/arctouch-jamilebastos/game-rating-app.git
    ```
2. **Open the project in Android Studio**.
3. **Build the project** to download dependencies.
4. **Run the app** on an emulator or physical device.

## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.
