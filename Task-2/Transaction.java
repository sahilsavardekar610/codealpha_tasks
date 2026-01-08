import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType { BUY, SELL }

    private TransactionType type;
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(TransactionType type, String stockSymbol, int quantity, double price) {
        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    // Getters for all fields
    public TransactionType getType() { return type; }
    public String getStockSymbol() { return stockSymbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public LocalDateTime getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("%s %d shares of %s at $%.2f on %s",
                type, quantity, stockSymbol, price, timestamp.toString());
    }
}