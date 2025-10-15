package library;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int id;
    private String name;
    private String email;
    private List<IssueRecord> records;

    public Member(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.records = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public List<IssueRecord> getRecords() { return records; }
    public void addRecord(IssueRecord r) { records.add(r); }
    public void removeRecord(IssueRecord r) { records.remove(r); }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email;
    }
}
