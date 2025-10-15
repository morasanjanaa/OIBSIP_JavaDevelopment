package library;

import java.util.ArrayDeque;
import java.util.Deque;

public class Book {
    private int id;
    private String title;
    private String author;
    private String category;
    private boolean issued;
    private Deque<Integer> reservationQueue;

    public Book(int id, String title, String author, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.issued = false;
        this.reservationQueue = new ArrayDeque<>();
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return issued; }
    public void setIssued(boolean issued) { this.issued = issued; }

    public void reserveFor(int memberId) { reservationQueue.addLast(memberId); }
    public Integer pollNextReservation() { return reservationQueue.pollFirst(); }
    public boolean hasReservations() { return !reservationQueue.isEmpty(); }
    public Deque<Integer> getReservationQueue() { return reservationQueue; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + category + (issued ? " (Issued)" : "");
    }
}
