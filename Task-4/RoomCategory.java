public enum RoomCategory {
    STANDARD("Standard", 100.00),
    DELUXE("Deluxe", 150.00),
    SUITE("Suite", 250.00);

    private final String name;
    private final double price;

    RoomCategory(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}