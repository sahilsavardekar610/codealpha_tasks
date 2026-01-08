import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable {
    private String guestName;
    private int roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private double totalCost;

    public Reservation(String guestName, int roomNumber, LocalDate checkInDate, LocalDate checkOutDate, double totalCost) {
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
    }

    // Getters
    public String getGuestName() {
        return guestName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return String.format("Reservation for %s | Room #%d | Check-in: %s | Check-out: %s | Total Cost: $%.2f",
                guestName, roomNumber, checkInDate, checkOutDate, totalCost);
    }
}