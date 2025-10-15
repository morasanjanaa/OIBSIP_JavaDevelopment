package library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class IssueRecord {
    private int bookId;
    private int memberId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public IssueRecord(int bookId, int memberId, LocalDate issueDate, LocalDate dueDate) {
        this.bookId = bookId;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
    }

    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate rd) { this.returnDate = rd; }

    public long getLateDays() {
        LocalDate ref = (returnDate == null) ? LocalDate.now() : returnDate;
        long days = ChronoUnit.DAYS.between(dueDate, ref);
        return Math.max(0, days);
    }

    public double calculateFine(double perDay) {
        return getLateDays() * perDay;
    }

    @Override
    public String toString() {
        return "BookID:" + bookId + " MemberID:" + memberId +
                " Issued:" + issueDate + " Due:" + dueDate +
                (returnDate == null ? " Return: - " : " Return:" + returnDate) +
                " LateDays:" + getLateDays();
    }
}
