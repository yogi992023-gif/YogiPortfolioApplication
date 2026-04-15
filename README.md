# 🚗 Driver Tracking App (Live Location Sharing)

## 📌 Overview

This is a **Driver Application** that shares the driver's live location in real-time to Firebase.
It enables users to track the driver’s movement continuously on the map (Uber/Ola-like system).

---

## ✨ Features

* 📍 Real-time location sharing
* 🗺️ Google Maps integration (Jetpack Compose)
* 🔄 Continuous location updates to Firebase
* 🚗 Driver marker movement tracking
* 📡 MVVM architecture with clean separation
* ⚡ Kotlin Coroutines & Flow
* 🔐 Hilt Dependency Injection

---

## 🛠️ Tech Stack

* **Language**: Kotlin
* **UI**: Jetpack Compose
* **Architecture**: MVVM
* **Maps**: Google Maps SDK
* **Backend**: Firebase Realtime Database
* **Location**: FusedLocationProviderClient
* **DI**: Hilt
* **Async**: Coroutines + Flow

---

## 📱 How It Works

1. Driver opens the app
2. App requests location permission
3. Driver’s current location is fetched
4. Location is pushed to Firebase continuously
5. User app receives updates and displays on map

---

## 🗂️ Project Structure

```id="d5n2hn"
presentation/
    ├── screen/
    ├── viewmodel/
domain/
data/
    ├── repository/
    ├── firebase/
```

---

## 🔥 Key Implementation

* Used `FusedLocationProviderClient` for accurate GPS updates
* Sent location updates to Firebase Realtime Database
* Used Flow to emit real-time location changes
* Optimized updates to avoid excessive API calls

---

## 📸 Screenshots

(Add driver app screenshots here)

---

## 🚀 Future Improvements

* 🔋 Background location tracking (Foreground Service)
* 📶 Offline caching & retry mechanism
* 🔔 Status update (Online / Offline driver)
* 🧭 Route optimization

---

## 👨‍💻 Author

**Yogeshwaran Ravichandran**

* GitHub: https://github.com/yogi992023-gif

---

## ⭐ Support

If you like this project, give it a ⭐ on GitHub!
