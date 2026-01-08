import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private RoomCategory category;
    private boolean isAvailable;

    public Room(int roomNumber, RoomCategory category) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = true;
    }

    // Getters
    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomCategory getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return category.getPrice();
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setter for availability
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Reserved";
        return String.format("Room #%d - %s, Price: $%.2f/night, Status: %s",
                roomNumber, category.getName(), getPricePerNight(), status);
    }
}