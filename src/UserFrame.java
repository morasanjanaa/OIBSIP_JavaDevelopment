package library;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserFrame extends JFrame {
    private Member member;
    private LibrarySystem lib = LibrarySystem.getInstance();
    private JTextArea display;

    public UserFrame(Member m) {
        this.member = m;
        setTitle("Library - User (" + m.getName() + " | ID:" + m.getId() + ")");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new GridLayout(2,4,8,8));
        JButton viewBooks = new JButton("View Books");
        JButton search = new JButton("Search");
        JButton issue = new JButton("Issue Book");
        JButton ret = new JButton("Return Book");
        JButton reserve = new JButton("Reserve Book");
        JButton myRecords = new JButton("My Issues");
        JButton query = new JButton("Send Query");
        JButton logout = new JButton("Logout");

        top.add(viewBooks); top.add(search); top.add(issue); top.add(ret);
        top.add(reserve); top.add(myRecords); top.add(query); top.add(logout);

        display = new JTextArea();
        display.setEditable(false);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(display), BorderLayout.CENTER);

        viewBooks.addActionListener(e -> {
            display.setText("Books:\n");
            lib.getBooks().forEach(b -> display.append(b + "\nReservations:" + b.getReservationQueue() + "\n"));
        });

        search.addActionListener(e -> {
            String kw = JOptionPane.showInputDialog(this, "Enter title or category:");
            if (kw == null) return;
            String ll = lib.getBooks().stream()
                    .filter(b -> b.getTitle().toLowerCase().contains(kw.toLowerCase())
                            || b.getCategory().toLowerCase().contains(kw.toLowerCase()))
                    .map(Book::toString).collect(Collectors.joining("\n"));
            display.setText("Search Results:\n" + (ll.isEmpty() ? "No matches." : ll));
        });

        issue.addActionListener(e -> {
            try {
                int bid = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Book ID to issue:"));
                String res = lib.issueBook(bid, member.getId());
                JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        ret.addActionListener(e -> {
            try {
                int bid = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Book ID to return:"));
                String res = lib.returnBook(bid, member.getId());
                JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        reserve.addActionListener(e -> {
            try {
                int bid = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Book ID to reserve:"));
                String res = lib.reserveBook(bid, member.getId());
                JOptionPane.showMessageDialog(this, res);
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        myRecords.addActionListener(e -> {
            List<IssueRecord> recs = member.getRecords();
            if (recs.isEmpty()) display.setText("No active issues.");
            else {
                StringBuilder sb = new StringBuilder("My Active Issues:\n");
                recs.forEach(r -> sb.append(r).append("\n"));
                display.setText(sb.toString());
            }
        });

        query.addActionListener(e -> {
            String q = JOptionPane.showInputDialog(this, "Enter your query to admin:");
            if (q != null && !q.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Query sent to admin. (Simulated)\nYour query:\n" + q);
            }
        });

        logout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
