# Digital Wallet & Transaction System 💸

Welcome to the Digital Wallet & Transaction System! This project allows users to manage multiple wallets, transfer funds across wallets with different currencies, schedule recurring payments, and more.

## Features ✨

1. **User & Wallet System 🧑‍💼💰**
    - A user can have multiple wallets (e.g., USD Wallet, EUR Wallet).
    - Wallet balances are stored using a `Map` (wallet type → balance).

2. **Currency Conversion & Transactions 💱**
    - Allows transactions between wallets with different currencies using fixed conversion rates (e.g., 1 USD = 0.9 EUR).
    - Transaction history is stored for each wallet.
    - Transactions that exceed the wallet balance are rejected.

3. **Scheduled Payments ⏰**
    - Users can set up recurring payments (e.g., "Send $50 to User X every 7 days").
    - Payments are executed automatically using Java’s `ScheduledExecutorService`.

## Team Members 👥

- **Enes** - User & Wallet Management 🛠️
- **Emre** - Transaction System 💳
- **Juan** - Currency Conversion Module 💱
- **Jaime** - Scheduled Payments Engine 🗓️

## Project Structure 📁

The project is structured as follows:

```
com.qaracter.digitalwallet
├── controller
│   ├── SchedulePaymentController.java
│   ├── TransactionController.java
│   ├── UserController.java
│   └── WalletController.java
├── model
│   ├── Currency.java
│   ├── ExchangeRates.java
│   ├── SchedulePayment.java
│   ├── Transaction.java
│   └── User.java
└── service
    ├── CurrencyConversionService.java
    ├── SchedulePaymentService.java
    ├── TransactionService.java
    ├── TransactionStorage.java
    ├── UserService.java
    └── WalletService.java
```

### Controllers 🖥️

Each controller handles the endpoints for a specific part of the system:

- `SchedulePaymentController`: Manages scheduled payments.
- `TransactionController`: Manages transactions (sending/receiving money).
- `UserController`: Manages user creation and retrieval.
- `WalletController`: Handles wallet creation.

### Models 📊

- `Currency`: Enum for the supported currencies (EUR, USD, GBP, JPY).
- `ExchangeRates`: Provides exchange rates between different currencies.
- `SchedulePayment`: Defines the structure for recurring payments.
- `Transaction`: Stores information about transactions.
- `User`: Represents a user with multiple wallets.

### Services ⚙️

- `CurrencyConversionService`: Converts amounts between different currencies using fixed exchange rates.
- `SchedulePaymentService`: Manages and executes recurring payments.
- `TransactionService`: Handles the logic for creating and processing transactions.
- `TransactionStorage`: Stores and retrieves transaction data.
- `UserService`: Manages user data.
- `WalletService`: Manages wallet creation and balance updates.

## How to Use 🚀

### 1. **Create a User**
- Call the `POST /users` endpoint with the user name.
- Example:
  ```json
  {
    "name": "John Doe"
  }
  ```

### 2. **Create a Wallet**
- Call the `POST /wallets` endpoint to create a wallet for a user with a specified currency and balance.
- Example:
  ```json
  {
    "userId": 1,
    "currency": "USD",
    "balance": 1000.0
  }
  ```

### 3. **Make a Transaction**
- Call the `POST /transactions` endpoint to transfer funds between two wallets (can handle different currencies).
- Example:
  ```json
  {
    "senderUserId": 1,
    "senderCurrency": "USD",
    "recipientUserId": 2,
    "recipientCurrency": "EUR",
    "amount": 100.0,
    "exchangeRate": 0.9
  }
  ```

### 4. **Schedule a Payment**
- Call the `POST /schedule` endpoint to set up a recurring payment.
- Example:
  ```json
  {
    "transaction": {
      "senderWalletId": "1-USD",
      "recipientWalletId": "2-EUR",
      "amount": 50.0,
      "currency": "USD"
    },
    "days": 7
  }
  ```

## API Endpoints 📡

| Endpoint                         | Method | Description                                |
| --------------------------------- | ------ | ------------------------------------------ |
| `/users`                          | POST   | Create a new user                          |
| `/wallets`                        | POST   | Create a new wallet                        |
| `/transactions`                   | POST   | Create a new transaction                   |
| `/transactions/{walletId}`        | GET    | Retrieve transaction history for a wallet  |
| `/schedule`                        | GET    | Retrieve all scheduled payments            |
| `/schedule`                        | POST   | Create a new scheduled payment             |
| `/schedule/{id}`                   | GET    | Retrieve a specific scheduled payment      |
| `/schedule/{id}`                   | DELETE | Delete a scheduled payment                 |

## Technologies Used 💻

- Java 17
- Spring Boot
- Spring Web
- Java Threads (`ScheduledExecutorService`)

## How to Run Locally 🏃‍♂️

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/digital-wallet.git
   ```

2. Navigate into the project folder:
   ```bash
   cd digital-wallet
   ```

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Access the API at `http://localhost:8080`.