package reservation;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    private final JTextArea display;
    private final ReservationSystem sys = ReservationSystem.getInstance();

    public AdminFrame() {
        setTitle("Reservation - Admin Dashboard");
        setSize(800, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton refresh = new JButton("Refresh List");
        JButton logout = new JButton("Logout");
        top.add(refresh);
        top.add(logout);

        display = new JTextArea();
        display.setEditable(false);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(display), BorderLayout.CENTER);

        refresh.addActionListener(e -> refreshList());
        logout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });

        refreshList();
    }

    private void refreshList() {
        StringBuilder sb = new StringBuilder();
        sb.append("PNR | Name | Train | Date | Class | Status\n\n");
        for (Reservation r : sys.getAllReservations()) {
            sb.append(r.toString()).append("\n");
        }
        display.setText(sb.toString());
    }
}
