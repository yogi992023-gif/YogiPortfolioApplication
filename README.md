🚗 User Tracking App (Driver Tracking System)
📌 Overview

This is a User Application that allows users to track a driver in real-time (similar to Uber/Ola concept).
The app displays the live location of the driver on Google Maps and updates continuously.

✨ Features
📍 Real-time driver location tracking
🗺️ Google Maps integration (Jetpack Compose)
🔄 Live location updates using Firebase Realtime Database
🚗 Dynamic marker movement (Driver tracking)
📡 MVVM Architecture with clean code structure
⚡ Kotlin Coroutines & Flow for async operations
🔐 Hilt Dependency Injection
🛠️ Tech Stack
Language: Kotlin
UI: Jetpack Compose
Architecture: MVVM
Maps: Google Maps SDK
Backend: Firebase Realtime Database
DI: Hilt
Async: Coroutines + Flow
📱 How It Works
User opens the app
App requests location permission
Driver location is fetched from Firebase
Map updates in real-time with driver marker
Camera follows driver movement smoothly
🗂️ Project Structure
presentation/
    ├── screen/
    ├── viewmodel/
domain/
data/
    ├── repository/
    ├── firebase/
🔥 Key Implementation
Used FusedLocationProviderClient for location handling
Synced driver coordinates using Firebase
Observed live updates using Flow
Updated map UI using Compose state
📸 Screenshots

(Add your app screenshots here)

🚀 Future Improvements
🛣️ Route drawing (Polyline between user & driver)
⏱️ ETA calculation
🔔 Push notifications when driver is nearby
📍 Background tracking
👨‍💻 Author

Yogeshwaran Ravichandran

GitHub: https://github.com/yogi992023-gif
⭐ Support

If you like this project, give it a ⭐ on GitHub!
