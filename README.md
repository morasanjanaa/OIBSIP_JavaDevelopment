📚 Digital Library Management System (Java Swing)

## ✅ Project Overview

The **Digital Library Management System** is a GUI-based Java application designed to automate core library operations. It provides separate modules for **Admin** and **Users**, allowing easy management of books, members, and issue/return processes.

The system is built using **Java Swing** and stores data **temporarily in memory** (no database), making it suitable for academic projects and internship submissions.

---

## ✅ Key Features

### 🔹 1. Login System

Two types of users:

* **Admin**

  * Username: `admin`
  * Password: `admin123` *(or use your updated credentials)*
* **User**

  * Username: `user`
  * Password: `user123` *(or use your updated credentials)*

### 🔹 2. Admin Module

Admin has full control and can:
✅ Add new books
✅ Delete books
✅ Update book details
✅ Add library members
✅ View all members
✅ View all books

### 🔹 3. User Module

Normal users have limited access:
✅ View available books
✅ Search books by title/author/category
✅ Issue books
✅ Return books
✅ Raise a query (simulated email option)

---

## ✅ Technologies Used

| Component     | Technology                      |
| ------------- | ------------------------------- |
| Language      | Java                            |
| GUI Framework | Swing                           |
| IDE           | IntelliJ IDEA                   |
| Data Storage  | In-memory (ArrayList / HashMap) |

---

## ✅ Project Structure (Example)

```
DigitalLibrary/
│
└── src/
    ├── Main.java
    ├── LoginFrame.java
    ├── AdminDashboard.java
    ├── UserDashboard.java
    ├── Book.java
    ├── Member.java
    ├── LibrarySystem.java
    ├── IssueBookFrame.java
    ├── ReturnBookFrame.java
    ├── SearchBookFrame.java
    ├── AddBookFrame.java
    ├── AddMemberFrame.java
    └── QueryFrame.java
```

---

## ✅ How to Run (IntelliJ IDEA)

1. Open **IntelliJ IDEA**
2. Create a **New Java Project**
3. Copy all `.java` files into the `src` folder
4. Set `Main.java` as the entry (run) class
5. Click **Run ▶** to start the application

---

## ✅ Sample Credentials

You can use the following credentials unless you changed them:

### 🔐 Admin Login:

```
Username: admin
Password: admin123
```

### 🔐 User Login:

```
Username: user
Password: user123
```

### 🎟 Sample Member IDs (User Module):

* `101` – Alice
* `102` – Bob
* `103` – Mora Sanjana

---

## ✅ Future Enhancements (Optional)

You can add the following features later:

* Database support (MySQL / SQLite / JDBC)
* Fine calculation for late returns
* Book reservation/advance booking
* PDF reports for issued books
* Email notifications
* Dark mode UI

---
