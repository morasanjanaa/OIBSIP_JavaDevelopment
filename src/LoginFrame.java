package reservation;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField userField;
    private final JPasswordField passField;
    private final JLabel status;

    public LoginFrame() {
        setTitle("Online Reservation - Login");
        setSize(420,230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(8,8,8,8);

        JLabel title = new JLabel("Online Reservation System");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        add(new JLabel("Role (admin/user):"), gbc);
        userField = new JTextField(14);
        gbc.gridx=1; add(userField, gbc);

        gbc.gridx=0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        passField = new JPasswordField(14);
        gbc.gridx=1; add(passField, gbc);

        JButton loginBtn = new JButton("Login");
        JButton exitBtn = new JButton("Exit");
        gbc.gridx=0; gbc.gridy++; add(loginBtn, gbc);
        gbc.gridx=1; add(exitBtn, gbc);

        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2;
        status = new JLabel(" ");
        add(status, gbc);

        loginBtn.addActionListener(e -> doLogin());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void doLogin() {
        String role = userField.getText().trim();
        String pwd = new String(passField.getPassword()).trim();

        // Default credentials: admin/admin123  and user/user123
        if ("admin".equalsIgnoreCase(role) && "admin123".equals(pwd)) {
            new AdminFrame().setVisible(true);
            dispose();
        } else if ("user".equalsIgnoreCase(role) && "user123".equals(pwd)) {
            // open user dashboard
            new UserFrame().setVisible(true);
            dispose();
        } else {
            status.setText("Invalid credentials. Admin: admin/admin123 | User: user/user123");
        }
    }
}
