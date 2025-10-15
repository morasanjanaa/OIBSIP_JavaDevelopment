package reservation;

import javax.swing.*;
import java.awt.*;

public class UserFrame extends JFrame {
    private final ReservationSystem sys = ReservationSystem.getInstance();

    public UserFrame() {
        setTitle("Reservation - User Dashboard");
        setSize(900, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new GridLayout(1, 3, 10, 10));
        JButton reserveBtn = new JButton("New Reservation");
        JButton cancelBtn = new JButton("Cancel Reservation");
        JButton logoutBtn = new JButton("Logout");
        top.add(reserveBtn);
        top.add(cancelBtn);
        top.add(logoutBtn);

        add(top, BorderLayout.NORTH);

        reserveBtn.addActionListener(e -> new ReservationFrame(this).setVisible(true));
        cancelBtn.addActionListener(e -> new CancellationFrame(this).setVisible(true));
        logoutBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
