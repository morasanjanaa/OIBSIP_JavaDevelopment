 🏦 ATM Interface (Java)

Project Overview

The **ATM Interface** is a Java-based application that simulates the core functionalities of an Automated Teller Machine. The system allows users to log in securely and perform operations like withdrawing, depositing, transferring funds, viewing transaction history, and logging out.

This project is ideal for showcasing your understanding of:

* Object-Oriented Programming (OOP)
* Java Swing (GUI) or Console I/O
* Collections and Data Storage
* User Authentication
* Transaction Handling

---

 ✨ Features Implemented

### ✅ 1. User Login

* Secure login using **User ID** and **PIN**
* Multiple user accounts supported

### ✅ 2. Transaction History

* Displays previous transactions for each user

### ✅ 3. Withdraw

* Allows users to withdraw money
* Checks for sufficient balance

### ✅ 4. Deposit

* Adds amount to the user's balance

### ✅ 5. Transfer

* Transfer funds between existing users
* Secure validation using recipient ID

### ✅ 6. Quit / Logout

* Ends session safely with a closing message

---

## ✅ Bonus Features (Optional Based on Your Version)

* GUI using Swing (Login screen, Dashboard, Transaction windows)
* Indian Rupee (₹) currency support
* Multiple accounts with initial balances
* Data stored using in-memory structures (e.g., HashMap)

---

## ✅ Technologies Used

| Component    | Technology                               |
| ------------ | ---------------------------------------- |
| Language     | Java                                     |
| IDE          | IntelliJ IDEA                            |
| UI           | Swing (for GUI version) / Console input  |
| Collections  | HashMap, ArrayList                       |
| OOP Concepts | Classes, Objects, Encapsulation, Methods |

---

## 📁 Project Structure (Example for GUI)

```
ATMInterface/
│
└── src/
    ├── Main.java
    ├── ATMSystem.java
    ├── User.java
    ├── LoginFrame.java
    ├── DashboardFrame.java
    ├── WithdrawFrame.java
    ├── DepositFrame.java
    ├── TransferFrame.java
    └── HistoryFrame.java
```

For console-based apps:

```
ATMInterface/
│
└── src/
    ├── Main.java
    ├── ATM.java
    └── User.java
```

---

## ✅ Sample Login Credentials

You can use the following default users (example):

| User ID | PIN  | Balance |
| ------- | ---- | ------- |
| 1001    | 1234 | ₹10,000 |
| 1002    | 2345 | ₹15,000 |
| 1003    | 3456 | ₹20,000 |

(You can modify users in the code.)

---

## ▶️ How to Run (IntelliJ IDEA)

1. Open **IntelliJ IDEA**
2. Create a **New Java Project**
3. Add the `.java` files to `src/`
4. Set `Main.java` as the entry point
5. Click **Run ▶**

---

## ✅ Future Improvements

* Store data in a database (MySQL/SQLite)
* Add receipt printing
* Add account creation and PIN reset
* Add mobile/email notifications
* Add admin module

---
