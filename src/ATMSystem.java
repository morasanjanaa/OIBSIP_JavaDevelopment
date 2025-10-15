import java.util.HashMap;
import java.util.Map;

public class ATMSystem {
    private final Map<Integer, User> users = new HashMap<>();
    private static ATMSystem instance;

    private ATMSystem() {
        // Predefined users
        users.put(1001, new User(1001, "1234", 10000));
        users.put(1002, new User(1002, "2345", 15000));
        users.put(1003, new User(1003, "3456", 20000));
    }

    public static ATMSystem getInstance() {
        if (instance == null) {
            instance = new ATMSystem();
        }
        return instance;
    }

    public User authenticate(int userId, String pin) {
        User u = users.get(userId);
        if (u != null && u.getPin().equals(pin)) return u;
        return null;
    }

    public boolean userExists(int userId) {
        return users.containsKey(userId);
    }

    public User getUser(int userId) {
        return users.get(userId);
    }

    public String transfer(User from, int toUserId, double amount) {
        if (amount <= 0) return "Enter an amount greater than 0.";
        if (!users.containsKey(toUserId)) return "Target user does not exist.";
        User to = users.get(toUserId);
        if (from.getUserId() == toUserId) return "Cannot transfer to the same account.";
        if (from.getBalance() < amount) return "Insufficient balance.";
        boolean ok = from.withdraw(amount);
        if (ok) {
            to.deposit(amount);
            String logFrom = String.format("Transferred ₹%,.2f to %d — New Balance: ₹%,.2f", amount, toUserId, from.getBalance());
            String logTo = String.format("Received ₹%,.2f from %d — New Balance: ₹%,.2f", amount, from.getUserId(), to.getBalance());
            from.addHistory(logFrom);
            to.addHistory(logTo);
            return "Transfer successful.";
        } else {
            return "Transfer failed.";
        }
    }
}
