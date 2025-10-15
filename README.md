🚆 Online Reservation System (Java Swing)

## ✅ Project Overview

The **Online Reservation System** is a Java Swing-based desktop application that allows users to reserve and cancel tickets through a simple and interactive interface. All data is stored in a **CSV/text file**, and login access is provided for both **Admin** and **Users**.

This project is ideal for internship submissions, academic projects, and Java GUI practice.

---

## ✅ Key Features

### 🔐 1. Login System

Supports two roles:

* **Admin Login**
* **User Login**

Credentials can be predefined in the code or loaded from a file.

---

### 🎫 2. Reservation Module

Users can:
✅ Enter personal and travel details
✅ Choose train number and class
✅ Auto-fill train name based on train number
✅ Select journey date, source, and destination
✅ Save reservation details to a file

---

### ❌ 3. Cancellation Module

Users can:
✅ Enter PNR number
✅ Fetch booking details from file
✅ Confirm cancellation by clicking OK
✅ Remove or update the record in the file

---

### 💾 4. File-Based Storage

All reservation and cancellation records are saved in a **CSV/text file** so that the data is retained.

Example files:

* `reservations.csv`
* `cancellations.csv`

---

## ✅ Technologies Used

| Component     | Technology      |
| ------------- | --------------- |
| Language      | Java            |
| GUI Framework | Swing           |
| IDE           | IntelliJ IDEA   |
| Data Storage  | CSV / Text File |

---

## ✅ Project Structure (Example)

```
OnlineReservationSystem/
│
└── src/
    ├── Main.java
    ├── LoginFrame.java
    ├── DashboardFrame.java
    ├── ReservationForm.java
    ├── CancellationForm.java
    ├── FileManager.java
    ├── User.java
    └── Admin.java
```

---

## ✅ How to Run (IntelliJ IDEA)

1. Open **IntelliJ IDEA**
2. Create a **New Java Project**
3. Place all `.java` files in the `src/` folder
4. Set `Main.java` as the entry class
5. Run the project using ▶ (Run button)

---

## ✅ Sample Login Credentials

You can use these unless changed:

### 👤 Admin:

```
Username: admin
Password: admin123
```

### 👤 User:

```
Username: user
Password: user123
```

---

## ✅ Future Enhancements

You can improve the system with:

* Database (MySQL/SQLite) integration
* Print ticket and download as PDF
* Search reservation by name/PNR
* Email/SMS confirmation
* Train list dropdown
* Admin dashboard analytics

---
