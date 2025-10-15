import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class WithdrawFrame extends JFrame {
    private final DashboardFrame parent;
    private final User user;
    private final JTextField amountField;
    private final JLabel statusLabel;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public WithdrawFrame(DashboardFrame parent, User user) {
        this.parent = parent;
        this.user = user;
        setTitle("Withdraw - User " + user.getUserId());
        setSize(360, 200);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        JLabel instruction = new JLabel("Enter amount to withdraw:");
        amountField = new JTextField(12);
        JButton withdrawBtn = new JButton("Withdraw");
        statusLabel = new JLabel("Balance: " + nf.format(user.getBalance()));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.gridx = 0; gbc.gridy = 0;
        add(instruction, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(withdrawBtn, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(statusLabel, gbc);

        withdrawBtn.addActionListener(e -> doWithdraw());
    }

    private void doWithdraw() {
        String text = amountField.getText().trim();
        try {
            double amount = Double.parseDouble(text);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter an amount greater than 0.");
                return;
            }
            if (user.getBalance() < amount) {
                JOptionPane.showMessageDialog(this, "Insufficient balance.");
                return;
            }
            boolean ok = user.withdraw(amount);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Withdrawal successful: " + nf.format(amount));
                parent.refreshBalance();
                statusLabel.setText("Balance: " + nf.format(user.getBalance()));
                amountField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Withdrawal failed.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric amount.");
        }
    }
}
