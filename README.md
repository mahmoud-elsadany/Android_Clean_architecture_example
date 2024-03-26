# Android Clean Architecture

This repository demonstrates the implementation of the Android Clean Architecture with the following layers:

1. **Data Layer**: Responsible for data retrieval and storage. It includes data sources (local and remote), repositories, and data models.

2. **Domain Layer**: Contains the business logic and use cases. It defines the core functionality of the application.

3. **Remote Layer**: Handles communication with external services (APIs, web services, etc.). It includes API clients, mappers, and network models.

4. **Local Layer**: Manages local data storage (e.g., SQLite database, SharedPreferences). It includes local data sources, DAOs, and database models.

5. **Presentation Layer**: Handles UI-related logic. It includes ViewModels, Fragments/Activities, and UI components.

6. **App Layer**: The entry point of the application. It initializes the dependency injection, sets up the navigation, and launches the app.

## Getting Started

1. Clone this repository.
2. Open the project in Android Studio.
3. Build and run the app.

Feel free to explore the code and adapt it to your specific use case. Happy coding! 

