import javax.swing.*;
import java.awt.*;

public class TransferFrame extends JFrame {
    private final DashboardFrame parent;
    private final User user;
    private final JTextField toUserField;
    private final JTextField amountField;

    public TransferFrame(DashboardFrame parent, User user) {
        this.parent = parent;
        this.user = user;
        setTitle("Transfer - User " + user.getUserId());
        setSize(420, 220);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());

        JLabel toLabel = new JLabel("Transfer to User ID:");
        toUserField = new JTextField(10);
        JLabel amountLabel = new JLabel("Amount to transfer:");
        amountField = new JTextField(10);
        JButton transferBtn = new JButton("Transfer");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.gridx = 0; gbc.gridy = 0;
        add(toLabel, gbc);
        gbc.gridx = 1;
        add(toUserField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(amountLabel, gbc);
        gbc.gridx = 1;
        add(amountField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        add(transferBtn, gbc);

        transferBtn.addActionListener(e -> doTransfer());
    }

    private void doTransfer() {
        String toText = toUserField.getText().trim();
        String amountText = amountField.getText().trim();
        try {
            int toId = Integer.parseInt(toText);
            double amount = Double.parseDouble(amountText);
            ATMSystem atm = ATMSystem.getInstance();
            String res = atm.transfer(user, toId, amount);
            JOptionPane.showMessageDialog(this, res);
            parent.refreshBalance();
            if ("Transfer successful.".equals(res)) {
                toUserField.setText("");
                amountField.setText("");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter numeric User ID and amount.");
        }
    }
}
