package reservation;

import javax.swing.*;
import java.awt.*;

public class CancellationFrame extends JFrame {
    private final ReservationSystem sys = ReservationSystem.getInstance();
    private final JFrame parent;

    private final JTextField pnrField = new JTextField(20);
    private final JTextArea display = new JTextArea(8, 40);

    public CancellationFrame(JFrame parent) {
        this.parent = parent;
        setTitle("Cancellation");
        setSize(560, 360);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(8,8,8,8);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx=0; gbc.gridy=0; add(new JLabel("Enter PNR:"), gbc);
        gbc.gridx=1; add(pnrField, gbc);
        gbc.gridx=2; JButton fetch = new JButton("Fetch"); add(fetch, gbc);

        gbc.gridx=0; gbc.gridy=1; gbc.gridwidth=3; add(new JScrollPane(display), gbc);

        gbc.gridx=0; gbc.gridy=2; gbc.gridwidth=1; JButton cancelBtn = new JButton("Confirm Cancel"); add(cancelBtn, gbc);
        gbc.gridx=1; JButton closeBtn = new JButton("Close"); add(closeBtn, gbc);

        fetch.addActionListener(e -> doFetch());
        cancelBtn.addActionListener(e -> doCancel());
        closeBtn.addActionListener(e -> dispose());
    }

    private void doFetch() {
        display.setText("");
        String pnr = pnrField.getText().trim();
        if (pnr.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter PNR."); return; }
        sys.findByPnr(pnr).ifPresentOrElse(r -> {
            display.setText("PNR: " + r.getPnr() + "\nName: " + r.getName() + "\nAge: " + r.getAge() +
                    "\nMobile: " + r.getMobile() + "\nTrain: " + r.getTrainNo() + " - " + r.getTrainName() +
                    "\nClass: " + r.getTravelClass() + "\nDate: " + r.getDateOfJourney() +
                    "\nFrom: " + r.getFrom() + "\nTo: " + r.getTo() + "\nStatus: " + r.getStatus());
        }, () -> JOptionPane.showMessageDialog(this, "PNR not found."));
    }

    private void doCancel() {
        String pnr = pnrField.getText().trim();
        if (pnr.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter PNR."); return; }
        boolean ok = sys.cancelReservation(pnr);
        if (ok) {
            JOptionPane.showMessageDialog(this, "Reservation cancelled successfully.");
            doFetch(); // refresh display
        } else {
            JOptionPane.showMessageDialog(this, "Cancellation failed (PNR not found or already cancelled).");
        }
    }
}
