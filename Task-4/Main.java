import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Hotel Reservation System!");

        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "1":
                case "s":
                    searchRooms(hotel, scanner);
                    break;
                case "2":
                case "b":
                    bookRoom(hotel, scanner);
                    break;
                case "3":
                case "c":
                    cancelReservation(hotel, scanner);
                    break;
                case "4":
                case "v":
                    hotel.displayAllRooms();
                    break;
                case "5":
                case "r":
                    hotel.displayAllReservations();
                    break;
                case "6":
                case "q":
                    System.out.println("Thank you for using the system. Goodbye!");
                    hotel.saveReservations(); // Save on exit
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. [S]earch Available Rooms");
        System.out.println("2. [B]ook a Room");
        System.out.println("3. [C]ancel a Reservation");
        System.out.println("4. [V]iew All Rooms");
        System.out.println("5. [R]eservation Details");
        System.out.println("6. [Q]uit");
        System.out.print("Enter your choice: ");
    }
    
    private static void searchRooms(Hotel hotel, Scanner scanner) {
        System.out.println("\n--- Search Rooms ---");
        System.out.print("Enter room category (STANDARD, DELUXE, SUITE): ");
        try {
            RoomCategory category = RoomCategory.valueOf(scanner.nextLine().toUpperCase());
            List<Room> availableRooms = hotel.searchAvailableRooms(category);
            if (availableRooms.isEmpty()) {
                System.out.println("No available rooms in this category.");
            } else {
                System.out.println("Available " + category.getName() + " rooms:");
                availableRooms.forEach(room -> System.out.println("  " + room));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid room category.");
        }
    }
    
    private static void bookRoom(Hotel hotel, Scanner scanner) {
        System.out.println("\n--- Book a Room ---");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter room number to book: ");
        int roomNumber = getIntInput(scanner);
        if (roomNumber == -1) return;
        
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = getDateInput(scanner);
        if (checkIn == null) return;
        
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = getDateInput(scanner);
        if (checkOut == null) return;

        Optional<Reservation> reservation = hotel.makeReservation(name, roomNumber, checkIn, checkOut);
        if (reservation.isPresent()) {
            System.out.println("Booking successful! Your reservation details:");
            System.out.println("  " + reservation.get());
        }
    }
    
    private static void cancelReservation(Hotel hotel, Scanner scanner) {
        System.out.println("\n--- Cancel Reservation ---");
        System.out.print("Enter the room number of the reservation to cancel: ");
        int roomNumber = getIntInput(scanner);
        if (roomNumber != -1) {
            hotel.cancelReservation(roomNumber);
        }
    }
    
    private static int getIntInput(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Please try again.");
            return -1;
        }
    }
    
    private static LocalDate getDateInput(Scanner scanner) {
        try {
            return LocalDate.parse(scanner.nextLine());
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }
}