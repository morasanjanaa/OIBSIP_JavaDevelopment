package reservation;

import javax.swing.*;
import java.awt.*;

public class ReservationFrame extends JFrame {
    private final ReservationSystem sys = ReservationSystem.getInstance();
    private final JFrame parent;

    private final JTextField nameField = new JTextField(16);
    private final JTextField ageField = new JTextField(4);
    private final JTextField mobileField = new JTextField(12);
    private final JTextField trainNoField = new JTextField(8);
    private final JTextField trainNameField = new JTextField(20);
    private final JComboBox<String> classCombo = new JComboBox<>(new String[]{"1A","2A","3A","SL"});
    private final JTextField dateField = new JTextField(10); // yyyy-MM-dd
    private final JTextField fromField = new JTextField(10);
    private final JTextField toField = new JTextField(10);

    public ReservationFrame(JFrame parent) {
        this.parent = parent;
        setTitle("New Reservation");
        setSize(520, 420);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(8,8,8,8);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx=0; gbc.gridy = row; add(new JLabel("Name:"), gbc);
        gbc.gridx=1; add(nameField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Age:"), gbc);
        gbc.gridx=1; add(ageField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Mobile:"), gbc);
        gbc.gridx=1; add(mobileField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Train No:"), gbc);
        gbc.gridx=1; add(trainNoField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Train Name:"), gbc);
        gbc.gridx=1; trainNameField.setEditable(false); add(trainNameField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Class:"), gbc);
        gbc.gridx=1; add(classCombo, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("Date (yyyy-MM-dd):"), gbc);
        gbc.gridx=1; add(dateField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("From:"), gbc);
        gbc.gridx=1; add(fromField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; add(new JLabel("To:"), gbc);
        gbc.gridx=1; add(toField, gbc);

        row++; gbc.gridx=0; gbc.gridy=row; JButton autofill = new JButton("Auto-fill Train Name"); add(autofill, gbc);
        gbc.gridx=1; JButton submit = new JButton("Submit Reservation"); add(submit, gbc);

        autofill.addActionListener(e -> {
            String tn = trainNoField.getText().trim();
            if (tn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter train number to auto-fill.");
                return;
            }
            trainNameField.setText(sys.getTrainName(tn));
        });

        submit.addActionListener(e -> doSubmit());
    }

    private void doSubmit() {
        try {
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText().trim());
            String mobile = mobileField.getText().trim();
            String trainNo = trainNoField.getText().trim();
            String trainName = trainNameField.getText().trim();
            String travelClass = (String) classCombo.getSelectedItem();
            String date = dateField.getText().trim();
            String from = fromField.getText().trim();
            String to = toField.getText().trim();

            if (name.isEmpty() || mobile.isEmpty() || trainNo.isEmpty() || date.isEmpty() || from.isEmpty() || to.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all mandatory fields.");
                return;
            }
            String pnr = sys.createReservation(name, age, mobile, trainNo, travelClass, date, from, to);
            JOptionPane.showMessageDialog(this, "Reservation successful! PNR: " + pnr);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid age. Enter numeric value.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving reservation.");
        }
    }
}
