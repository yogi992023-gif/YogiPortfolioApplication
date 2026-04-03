# 📍 Live Tracking App (Jetpack Compose + Firebase)

## 🚀 Overview

This project is a real-time location tracking application built using **Jetpack Compose** and **Firebase Realtime Database**. It demonstrates live location updates between users (like Uber/Rapido concept) with modern Android architecture.

---

## ✨ Key Features

* 🔴 Real-time location tracking using Firebase
* 🗺️ Google Maps integration with live marker updates
* 📡 Continuous location updates (Driver/User)
* 🔄 Auto-refresh UI using Jetpack Compose state
* 🧭 MVVM architecture with clean code structure
* ⚡ Coroutines & Flow for async handling
* 🔐 Secure Firebase database structure

---

## 🛠️ Tech Stack

* **Kotlin**
* **Jetpack Compose**
* **Firebase Realtime Database**
* **Google Maps SDK**
* **MVVM Architecture**
* **Hilt (Dependency Injection)**
* **Coroutines & Flow**
* **Retrofit (Optional API integration)**

---

## 📱 App Flow

1. User selects a driver
2. Driver location stored in Firebase
3. App listens to Firebase changes
4. Map updates marker in real-time
5. UI reflects live movement

---

## 📂 Project Structure

```
├── data
│   ├── repository
│   ├── remote
├── domain
├── presentation
│   ├── screen
│   ├── viewmodel
├── di
```

---

## 🔥 Firebase Database Structure

```json
{
  "drivers": {
    "driver1": {
      "lat": 12.9716,
      "lng": 77.5946
    }
  }
}
```

---

## 🗺️ Google Maps Setup

* Add API Key in `AndroidManifest.xml`
* Enable Maps SDK in Google Cloud Console

---

## ⚙️ Setup Instructions

1. Clone the repository
2. Add `google-services.json` in `app/`
3. Add Google Maps API key
4. Sync project
5. Run the app

---

## 📸 Screenshots

* Live map with moving marker
* Real-time location updates

---

## 🎯 Use Cases

* Ride tracking apps (Uber, Rapido)
* Delivery tracking
* Fleet management

---

## 📌 Future Improvements

* Route drawing (Polyline)
* ETA calculation
* Multiple driver tracking
* Background location updates

---

## 🤝 Contribution

Feel free to fork and improve this project.

---

## 📧 Contact

**Yogi MR**
Android Developer (4+ Years Experience)
GitHub: https://github.com/yogi992023-gif

---
