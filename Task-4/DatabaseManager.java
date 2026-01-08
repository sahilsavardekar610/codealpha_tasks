import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String RESERVATIONS_FILE = "reservations.dat";

    public List<Reservation> loadReservations() {
        List<Reservation> reservations = new ArrayList<>();
        File file = new File(RESERVATIONS_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                reservations = (List<Reservation>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading reservations: " + e.getMessage());
            }
        }
        return reservations;
    }

    public void saveReservations(List<Reservation> reservations) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RESERVATIONS_FILE))) {
            oos.writeObject(reservations);
        } catch (IOException e) {
            System.err.println("Error saving reservations: " + e.getMessage());
        }
    }
}