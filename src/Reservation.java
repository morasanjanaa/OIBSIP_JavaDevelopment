package reservation;

public class Reservation {
    private String pnr;
    private String name;
    private int age;
    private String mobile;
    private String trainNo;
    private String trainName;
    private String travelClass;
    private String dateOfJourney;
    private String from;
    private String to;
    private String status; // BOOKED / CANCELLED

    public Reservation(String pnr, String name, int age, String mobile,
                       String trainNo, String trainName, String travelClass,
                       String dateOfJourney, String from, String to, String status) {
        this.pnr = pnr;
        this.name = name;
        this.age = age;
        this.mobile = mobile;
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.travelClass = travelClass;
        this.dateOfJourney = dateOfJourney;
        this.from = from;
        this.to = to;
        this.status = status;
    }

    // getters and setters
    public String getPnr() { return pnr; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getMobile() { return mobile; }
    public String getTrainNo() { return trainNo; }
    public String getTrainName() { return trainName; }
    public String getTravelClass() { return travelClass; }
    public String getDateOfJourney() { return dateOfJourney; }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    public String toCsvRow() {
        // escape commas if needed (simple)
        return String.join(",",
                pnr, name, String.valueOf(age), mobile,
                trainNo, trainName, travelClass, dateOfJourney, from, to, status);
    }

    public static Reservation fromCsvRow(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 11) return null;
        try {
            return new Reservation(
                    parts[0], parts[1], Integer.parseInt(parts[2]), parts[3],
                    parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10]
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return pnr + " | " + name + " | " + trainNo + "-" + trainName + " | " + dateOfJourney + " | " + travelClass + " | " + status;
    }
}
