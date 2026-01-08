import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Hotel {
    private List<Room> rooms;
    private List<Reservation> reservations;
    private DatabaseManager dbManager;
    private PaymentGateway paymentGateway;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.dbManager = new DatabaseManager();
        this.paymentGateway = new PaymentGateway();
        initializeRooms();
        loadReservations();
    }

    private void initializeRooms() {
        // Create a few rooms of each category
        for (int i = 101; i <= 105; i++) {
            rooms.add(new Room(i, RoomCategory.STANDARD));
        }
        for (int i = 201; i <= 203; i++) {
            rooms.add(new Room(i, RoomCategory.DELUXE));
        }
        for (int i = 301; i <= 302; i++) {
            rooms.add(new Room(i, RoomCategory.SUITE));
        }
    }

    private void loadReservations() {
        this.reservations = dbManager.loadReservations();
        // Update room availability based on loaded reservations
        for (Reservation res : reservations) {
            findRoomByNumber(res.getRoomNumber()).ifPresent(room -> room.setAvailable(false));
        }
    }

    public void saveReservations() {
        dbManager.saveReservations(reservations);
    }

    public List<Room> searchAvailableRooms(RoomCategory category) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable() && room.getCategory() == category) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Optional<Reservation> makeReservation(String guestName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        Optional<Room> roomOpt = findRoomByNumber(roomNumber);
        // FIX: Replaced roomOpt.isEmpty() with !roomOpt.isPresent() for Java 8 compatibility
        if (!roomOpt.isPresent() || !roomOpt.get().isAvailable()) { 
            System.out.println("Reservation failed: Room is not available or does not exist.");
            return Optional.empty();
        }

        Room room = roomOpt.get();
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (nights <= 0) {
            System.out.println("Reservation failed: Check-out date must be after check-in date.");
            return Optional.empty();
        }
        
        double totalCost = room.getPricePerNight() * nights;
        
        System.out.printf("Total cost for %d nights: $%.2f%n", nights, totalCost);

        if (paymentGateway.processPayment(totalCost)) {
            room.setAvailable(false);
            Reservation newReservation = new Reservation(guestName, roomNumber, checkIn, checkOut, totalCost);
            reservations.add(newReservation);
            saveReservations();
            return Optional.of(newReservation);
        } else {
            System.out.println("Reservation failed due to payment issues.");
            return Optional.empty();
        }
    }

    public boolean cancelReservation(int roomNumber) {
        Optional<Reservation> resOpt = findReservationByRoomNumber(roomNumber);
        // FIX: Replaced resOpt.isEmpty() with !resOpt.isPresent() if you were to use it here.
        // The original code already used resOpt.isPresent(), so no change is needed here.
        if (resOpt.isPresent()) {
            reservations.remove(resOpt.get());
            findRoomByNumber(roomNumber).ifPresent(room -> room.setAvailable(true));
            saveReservations();
            System.out.printf("Reservation for room #%d has been canceled.%n", roomNumber);
            return true;
        } else {
            System.out.println("Cancellation failed: No reservation found for room #" + roomNumber);
            return false;
        }
    }

    public Optional<Reservation> findReservationByRoomNumber(int roomNumber) {
        return reservations.stream()
                .filter(res -> res.getRoomNumber() == roomNumber)
                .findFirst();
    }
    
    private Optional<Room> findRoomByNumber(int roomNumber) {
        return rooms.stream()
                .filter(room -> room.getRoomNumber() == roomNumber)
                .findFirst();
    }

    public void displayAllRooms() {
        System.out.println("\n--- All Hotel Rooms ---");
        rooms.forEach(System.out::println);
    }
    
    public void displayAllReservations() {
        System.out.println("\n--- All Reservations ---");
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            reservations.forEach(System.out::println);
        }
    }
}