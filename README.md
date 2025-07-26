# 💸 Cashi Payment App (KMP)

This is a cross-platform FinTech app built with Kotlin Multiplatform (KMP), designed to allow users to send payments and view transaction history.

---

## 🚀 Features

- 🔁 **Send Payment**  
  Users can send payments by entering:
  - Email
  - Amount
  - Currency (USD, EUR)

- 📜 **Transaction History**  
  Real-time updates of sent payments from **Firebase Firestore**

- ☁️ **Backend API**  
  Integrated with a deployed REST API hosted on Render:  
  `POST https://transaction-api-hcyc.onrender.com/transaction`

- 🌐 **Cross-Platform (KMP)**  
  Shared business logic between Android and iOS with `expect/actual` declarations for platform-specific code

- 🧪 **(Optional) BDD Tests**  
  Support for Cucumber-based behavior-driven development in the shared business module

---

## 🧱 Tech Stack

| Layer              | Tech                                  |
|-------------------|----------------------------------------|
| UI (Android)       | Jetpack Compose Multiplatform         |
| Networking         | Ktor                                  |
| DI                 | Koin                                  |
| Shared Logic       | Kotlin Multiplatform                   |
| Backend API        | Node.js + Express (hosted on Render)  |
| Realtime DB        | Firebase Firestore                    |
| Testing (optional) | Cucumber, JUnit, JMeter, Appium       |

---

## 🛠️ Architecture

:androidApp/
├── Main UI (Compose)
├── Screens: SendPaymentScreen, TransactionsHistoryScreen

:business/
├── Use Cases
│ └── SendPaymentUseCase
│ └── GetTransactionsUseCase

:repository/
├── Network Layer (Ktor)
├── Firestore Integration
├── TransactionRepository.kt

:network/
├── Ktor client setup & wrapper