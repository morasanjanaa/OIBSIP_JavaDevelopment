import java.util.ArrayList;
import java.util.List;

public class User {
    private final int userId;
    private final String pin;
    private double balance;
    private final List<String> history;

    public User(int userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.history = new ArrayList<>();
        addHistory(String.format("Account created with balance %,.2f", initialBalance));
    }

    public int getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addHistory(String.format("Deposited ₹%,.2f — New Balance: ₹%,.2f", amount, balance));
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            addHistory(String.format("Withdrew ₹%,.2f — New Balance: ₹%,.2f", amount, balance));
            return true;
        }
        return false;
    }

    public void addHistory(String entry) {
        history.add(entry);
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}
