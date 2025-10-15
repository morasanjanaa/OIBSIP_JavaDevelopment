package library;

import java.time.LocalDate;
import java.util.*;

public class LibrarySystem {
    private static LibrarySystem instance;
    private Map<Integer, library.Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private List<IssueRecord> allRecords = new ArrayList<>();
    private final double FINE_PER_DAY = 5.0;

    private LibrarySystem() {
        // preload sample data
        addBook(new Book(1, "Java: The Complete Reference", "Herbert Schildt", "Programming"));
        addBook(new Book(2, "Introduction to Algorithms", "Cormen", "CS"));
        addBook(new Book(3, "Clean Code", "Robert C. Martin", "Programming"));
        addMember(new Member(101, "Alice", "alice@example.com"));
        addMember(new Member(102, "Bob", "bob@example.com"));
        addMember(new Member(103, "Mora Sanjana", "morasanjana2058@gmail.com"));
    }

    public static LibrarySystem getInstance() {
        if (instance == null) instance = new LibrarySystem();
        return instance;
    }

    // Book operations
    public void addBook(Book b) { books.put(b.getId(), b); }
    public void updateBook(int id, String title, String author, String category) {
        Book b = books.get(id);
        if (b != null) {
            // Replace with new Book but preserve reservationQueue and issued flag
            Deque<Integer> queue = b.getReservationQueue();
            boolean issued = b.isIssued();
            Book nb = new Book(id, title, author, category);
            if (issued) nb.setIssued(true);
            nb.getReservationQueue().addAll(queue);
            books.put(id, nb);
        }
    }
    public void deleteBook(int id) { books.remove(id); }
    public Collection<Book> getBooks() { return books.values(); }
    public Book findBook(int id) { return books.get(id); }

    // Member operations
    public void addMember(Member m) { members.put(m.getId(), m); }
    public Collection<Member> getMembers() { return members.values(); }
    public Member findMember(int id) { return members.get(id); }

    // Issue
    public String issueBook(int bookId, int memberId) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);
        if (b == null) return "Book not found.";
        if (m == null) return "Member not found.";
        if (b.isIssued()) return "Book currently issued.";
        if (b.hasReservations()) {
            Integer next = b.getReservationQueue().peekFirst();
            if (next != null && next != memberId) {
                return "Book reserved by another member.";
            } else {
                b.pollNextReservation();
            }
        }
        b.setIssued(true);
        LocalDate issueDate = LocalDate.now();
        LocalDate dueDate = issueDate.plusDays(14);
        IssueRecord r = new IssueRecord(bookId, memberId, issueDate, dueDate);
        m.addRecord(r);
        allRecords.add(r);
        return "Issued. Due date: " + dueDate.toString();
    }

    // Return
    public String returnBook(int bookId, int memberId) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);
        if (b == null || m == null) return "Book or Member not found.";
        Optional<IssueRecord> opt = m.getRecords().stream()
                .filter(r -> r.getBookId() == bookId && r.getReturnDate() == null)
                .findFirst();
        if (!opt.isPresent()) return "No active issue record for this book and member.";
        IssueRecord r = opt.get();
        r.setReturnDate(LocalDate.now());
        double fine = r.calculateFine(FINE_PER_DAY);
        b.setIssued(false);
        m.removeRecord(r);
        return fine > 0 ? String.format("Book returned. Fine: ₹%.2f", fine) : "Book returned. No fine.";
    }

    // Reserve
    public String reserveBook(int bookId, int memberId) {
        Book b = findBook(bookId);
        Member m = findMember(memberId);
        if (b == null || m == null) return "Book or Member not found.";
        if (b.getReservationQueue().stream().anyMatch(id -> id == memberId)) return "Already reserved.";
        b.reserveFor(memberId);
        return "Book reserved successfully.";
    }

    // Reports
    public String generateReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Total books: ").append(books.size()).append("\n");
        long issuedCount = books.values().stream().filter(Book::isIssued).count();
        sb.append("Issued books: ").append(issuedCount).append("\n");
        sb.append("Members: ").append(members.size()).append("\n");
        sb.append("Active issue records: ").append(allRecords.stream().filter(r -> r.getReturnDate() == null).count()).append("\n");
        sb.append("\nIssued Records:\n");
        allRecords.stream().filter(r -> r.getReturnDate() == null).forEach(r -> sb.append(r).append("\n"));
        return sb.toString();
    }

    public double getFinePerDay() { return FINE_PER_DAY; }
}
