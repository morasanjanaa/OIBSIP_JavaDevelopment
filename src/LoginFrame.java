package library;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JLabel status;

    public LoginFrame() {
        setTitle("Digital Library - Login");
        setSize(360, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);

        JLabel title = new JLabel("Digital Library System");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        gbc.gridx=0; gbc.gridy=0; gbc.gridwidth=2;
        add(title, gbc);

        gbc.gridwidth=1;
        gbc.gridy++;
        add(new JLabel("Role (admin/user):"), gbc);
        userField = new JTextField(12);
        gbc.gridx=1; add(userField, gbc);

        gbc.gridx=0; gbc.gridy++;
        add(new JLabel("Password:"), gbc);
        passField = new JPasswordField(12);
        gbc.gridx=1; add(passField, gbc);

        JButton loginBtn = new JButton("Login");
        gbc.gridx=0; gbc.gridy++; add(loginBtn, gbc);
        JButton exitBtn = new JButton("Exit");
        gbc.gridx=1; add(exitBtn, gbc);

        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2;
        status = new JLabel(" ");
        add(status, gbc);

        loginBtn.addActionListener(e -> doLogin());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private void doLogin() {
        String role = userField.getText().trim();
        String pass = new String(passField.getPassword()).trim();
        if (role.equalsIgnoreCase("admin") && pass.equals("admin")) {
            new AdminFrame().setVisible(true);
            dispose();
        } else if (role.equalsIgnoreCase("user") && pass.equals("user")) {
            String idStr = JOptionPane.showInputDialog(this, "Enter your Member ID:");
            try {
                int mid = Integer.parseInt(idStr);
                Member m = LibrarySystem.getInstance().findMember(mid);
                if (m != null) {
                    new UserFrame(m).setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Member not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid ID.");
            }
        } else {
            status.setText("Invalid credentials. Use admin/admin or user/user");
        }
    }
}
