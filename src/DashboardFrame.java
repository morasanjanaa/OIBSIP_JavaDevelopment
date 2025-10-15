import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Locale;

public class DashboardFrame extends JFrame {
    private final User user;
    private final JLabel balanceLabel;
    private final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("ATM - Dashboard (User: " + user.getUserId() + ")");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JLabel welcome = new JLabel("Welcome, User " + user.getUserId());
        welcome.setFont(new Font("SansSerif", Font.BOLD, 16));
        balanceLabel = new JLabel("Balance: " + nf.format(user.getBalance()));

        JButton withdrawBtn = new JButton("Withdraw");
        JButton depositBtn = new JButton("Deposit");
        JButton transferBtn = new JButton("Transfer");
        JButton historyBtn = new JButton("Transaction History");
        JButton logoutBtn = new JButton("Logout");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(welcome, gbc);

        gbc.gridy++;
        add(balanceLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        add(withdrawBtn, gbc);
        gbc.gridx = 1;
        add(depositBtn, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        add(transferBtn, gbc);
        gbc.gridx = 1;
        add(historyBtn, gbc);

        gbc.gridy++;
        gbc.gridx = 0; gbc.gridwidth = 2;
        add(logoutBtn, gbc);

        withdrawBtn.addActionListener(e -> openWithdraw());
        depositBtn.addActionListener(e -> openDeposit());
        transferBtn.addActionListener(e -> openTransfer());
        historyBtn.addActionListener(e -> openHistory());
        logoutBtn.addActionListener(e -> logout());
    }

    private void openWithdraw() {
        WithdrawFrame wf = new WithdrawFrame(this, user);
        wf.setVisible(true);
    }

    private void openDeposit() {
        DepositFrame df = new DepositFrame(this, user);
        df.setVisible(true);
    }

    private void openTransfer() {
        TransferFrame tf = new TransferFrame(this, user);
        tf.setVisible(true);
    }

    private void openHistory() {
        HistoryFrame hf = new HistoryFrame(user);
        hf.setVisible(true);
    }

    public void refreshBalance() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));
        balanceLabel.setText("Balance: " + nf.format(user.getBalance()));
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            LoginFrame login = new LoginFrame();
            login.setVisible(true);
            this.dispose();
        }
    }
}
