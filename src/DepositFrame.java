import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class DepositFrame extends JFrame {
    private final DashboardFrame parent;
    private final User user;
    private final JTextField amountField;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public DepositFrame(DashboardFrame parent, User user) {
        this.parent = parent;
        this.user = user;
        setTitle("Deposit - User " + user.getUserId());
        setSize(360, 180);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        JLabel instruction = new JLabel("Enter amount to deposit:");
        amountField = new JTextField(12);
        JButton depositBtn = new JButton("Deposit");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.gridx = 0; gbc.gridy = 0;
        add(instruction, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        add(depositBtn, gbc);

        depositBtn.addActionListener(e -> doDeposit());
    }

    private void doDeposit() {
        String text = amountField.getText().trim();
        try {
            double amount = Double.parseDouble(text);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter an amount greater than 0.");
                return;
            }
            user.deposit(amount);
            JOptionPane.showMessageDialog(this, "Deposit successful: " + nf.format(amount));
            parent.refreshBalance();
            amountField.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter a valid numeric amount.");
        }
    }
}
