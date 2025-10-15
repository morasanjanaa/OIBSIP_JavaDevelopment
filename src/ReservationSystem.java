package reservation;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class ReservationSystem {
    private static ReservationSystem instance;
    private final Map<String, Reservation> reservations = new LinkedHashMap<>();
    private final Path dataFile = Paths.get("reservations.csv");

    // sample train map: trainNo -> trainName
    private final Map<String, String> trains = new HashMap<>();

    private ReservationSystem() {
        // sample trains
        trains.put("12345", "Shatabdi Express");
        trains.put("12001", "Rajdhani Express");
        trains.put("22688", "Garib Rath");
        trains.put("15675", "Intercity Express");
        trains.put("11081", "Mail Express");

        loadFromFile();
    }

    public static ReservationSystem getInstance() {
        if (instance == null) instance = new ReservationSystem();
        return instance;
    }

    public Collection<Reservation> getAllReservations() {
        return reservations.values();
    }

    public Optional<Reservation> findByPnr(String pnr) {
        return Optional.ofNullable(reservations.get(pnr));
    }

    public String getTrainName(String trainNo) {
        return trains.getOrDefault(trainNo, "Unknown Train");
    }

    public String createReservation(String name, int age, String mobile,
                                    String trainNo, String travelClass,
                                    String dateOfJourney, String from, String to) {
        String trainName = getTrainName(trainNo);
        String pnr = generatePnr();
        Reservation r = new Reservation(pnr, name, age, mobile, trainNo, trainName, travelClass, dateOfJourney, from, to, "BOOKED");
        reservations.put(pnr, r);
        persistToFile();
        return pnr;
    }

    public boolean cancelReservation(String pnr) {
        Reservation r = reservations.get(pnr);
        if (r == null) return false;
        if ("CANCELLED".equalsIgnoreCase(r.getStatus())) return false;
        r.setStatus("CANCELLED");
        persistToFile();
        return true;
    }

    private String generatePnr() {
        long ts = System.currentTimeMillis();
        int rnd = new Random().nextInt(900) + 100;
        return "TS" + ts + rnd;
    }

    private void loadFromFile() {
        if (Files.notExists(dataFile)) return;
        try (BufferedReader br = Files.newBufferedReader(dataFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                Reservation r = Reservation.fromCsvRow(line);
                if (r != null) reservations.put(r.getPnr(), r);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void persistToFile() {
        try (BufferedWriter bw = Files.newBufferedWriter(dataFile, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Reservation r : reservations.values()) {
                bw.write(r.toCsvRow());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
