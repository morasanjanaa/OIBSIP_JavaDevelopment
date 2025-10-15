🎮 Number Guessing Game (Java)

Project Overview

The **Number Guessing Game** is a simple and fun Java application where the player tries to guess a randomly generated number within a given range. The program provides hints after each guess and tracks the score based on performance.

This project is ideal for beginners to understand:

* Loops
* Conditional statements
* Random number generation
* User input/output
* Basic scoring logic

---

✨ Features

✅ Random number generation (default: 1–100)
✅ Hints: "Too High" / "Too Low"
✅ Limited attempts
✅ Multiple rounds
✅ Score calculation based on attempts
✅ Console-based (run in IntelliJ or any Java IDE)

---

## 🧩 Game Flow

1. The system generates a random number between **1 and 100**.
2. The user is prompted to enter a guess.
3. The program tells whether the guess is:

   * ✅ Correct
   * 🔼 Higher
   * 🔽 Lower
4. If gets correct shows the no of attempts used to guess.

---


## 🎯 Rules & Customization

You can customize these values in the code:

* ✅ Range of numbers → e.g., `1–50`, `1–200`
* ✅ Number of attempts
* ✅ Number of rounds
* ✅ Scoring system

---

## ✅ How to Run (Using IntelliJ IDEA)

1. Open **IntelliJ IDEA**.
2. Create a **New Java Project**.
3. Add a new class file (e.g., `NumberGuessingGame.java`).
4. Copy and paste the game code.
5. Click **Run ▶** to start the game.

---

## 📁 File Structure (If Single File Project)

```
NumberGuessingGame/
│
└── src/
    └── NumberGuessingGame.java
```

If you want to separate classes, you can structure it like:

```
NumberGuessingGame/
│
└── src/
    ├── Main.java
    ├── GameEngine.java
    └── ScoreManager.java
```

---

## 📌 Requirements

* **Java 8+**
* Any Java IDE (IntelliJ IDEA preferred) or terminal/compiler

---

## ✅ Example Output (Console)

```
Welcome to the Number Guessing Game!
I'm thinking of a number between 1 and 100.

Enter your guess: 50
Too low!

Enter your guess: 75
Too high!

Enter your guess: 62
Correct! 🎉
You took 3 attempts.

Do you want to play another round? (yes/no)
```

🔚 Conclusion

This project demonstrates core Java concepts in a fun and interactive way. It's perfect for internship submissions, practice, or academic mini-projects. You can further enhance the features based on requirements.


