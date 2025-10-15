package library;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    private JTextArea displayArea;
    private LibrarySystem lib = LibrarySystem.getInstance();

    public AdminFrame() {
        setTitle("Library - Admin Dashboard");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel top = new JPanel(new GridLayout(2,4,8,8));
        JButton viewBooks = new JButton("View Books");
        JButton addBook = new JButton("Add Book");
        JButton updateBook = new JButton("Update Book");
        JButton deleteBook = new JButton("Delete Book");
        JButton viewMembers = new JButton("View Members");
        JButton addMember = new JButton("Add Member");
        JButton reports = new JButton("Generate Report");
        JButton logout = new JButton("Logout");

        top.add(viewBooks); top.add(addBook); top.add(updateBook); top.add(deleteBook);
        top.add(viewMembers); top.add(addMember); top.add(reports); top.add(logout);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        viewBooks.addActionListener(e -> {
            displayArea.setText("Books:\n");
            lib.getBooks().forEach(b -> displayArea.append(b + "\nReservations:" + b.getReservationQueue() + "\n"));
        });

        addBook.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Book ID:"));
                String title = JOptionPane.showInputDialog(this, "Title:");
                String author = JOptionPane.showInputDialog(this, "Author:");
                String cat = JOptionPane.showInputDialog(this, "Category:");
                lib.addBook(new Book(id, title, author, cat));
                JOptionPane.showMessageDialog(this, "Book added.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        updateBook.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Book ID to update:"));
                Book b = lib.findBook(id);
                if (b == null) { JOptionPane.showMessageDialog(this, "No such book."); return; }
                String title = JOptionPane.showInputDialog(this, "Title:", b.getTitle());
                String author = JOptionPane.showInputDialog(this, "Author:", b.getAuthor());
                String cat = JOptionPane.showInputDialog(this, "Category:", b.getCategory());
                lib.updateBook(id, title, author, cat);
                JOptionPane.showMessageDialog(this, "Book updated.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        deleteBook.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Book ID to delete:"));
                lib.deleteBook(id);
                JOptionPane.showMessageDialog(this, "Book deleted (if existed).");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        viewMembers.addActionListener(e -> {
            displayArea.setText("Members:\n");
            lib.getMembers().forEach(m -> displayArea.append(m + "\n"));
        });

        addMember.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Member ID:"));
                String name = JOptionPane.showInputDialog(this, "Name:");
                String email = JOptionPane.showInputDialog(this, "Email:");
                lib.addMember(new Member(id, name, email));
                JOptionPane.showMessageDialog(this, "Member added.");
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Invalid input."); }
        });

        reports.addActionListener(e -> {
            String report = lib.generateReport();
            JTextArea ta = new JTextArea(report);
            ta.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Report", JOptionPane.INFORMATION_MESSAGE);
        });

        logout.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            dispose();
        });
    }
}
