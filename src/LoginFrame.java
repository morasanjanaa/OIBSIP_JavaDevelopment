import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private final JTextField userIdField;
    private final JPasswordField pinField;
    private final JLabel statusLabel;

    public LoginFrame() {
        setTitle("ATM - Login");
        setSize(380, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Welcome to Java ATM");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        JLabel userIdLabel = new JLabel("User ID:");
        JLabel pinLabel = new JLabel("PIN:");

        userIdField = new JTextField(12);
        pinField = new JPasswordField(12);

        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");
        statusLabel = new JLabel(" ");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(userIdLabel, gbc);
        gbc.gridx = 1;
        add(userIdField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(pinLabel, gbc);
        gbc.gridx = 1;
        add(pinField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(loginBtn, gbc);
        gbc.gridx = 1;
        add(exitBtn, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        add(statusLabel, gbc);

        loginBtn.addActionListener((ActionEvent e) -> doLogin());
        exitBtn.addActionListener((ActionEvent e) -> System.exit(0));
    }

    private void doLogin() {
        String userIdText = userIdField.getText().trim();
        String pin = new String(pinField.getPassword()).trim();
        if (userIdText.isEmpty() || pin.isEmpty()) {
            statusLabel.setText("Please enter both User ID and PIN.");
            return;
        }
        try {
            int userId = Integer.parseInt(userIdText);
            ATMSystem atm = ATMSystem.getInstance();
            User user = atm.authenticate(userId, pin);
            if (user != null) {
                // open dashboard
                DashboardFrame dashboard = new DashboardFrame(user);
                dashboard.setVisible(true);
                this.dispose();
            } else {
                statusLabel.setText("Invalid credentials. Try again.");
            }
        } catch (NumberFormatException ex) {
            statusLabel.setText("User ID must be numeric.");
        }
    }
}
