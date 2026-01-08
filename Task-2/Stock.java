public class Stock {
    private String symbol;
    private String name;
    private double currentPrice;

    public Stock(String symbol, String name, double currentPrice) {
        this.symbol = symbol;
        this.name = name;
        this.currentPrice = currentPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void updatePrice(double newPrice) {
        this.currentPrice = newPrice;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", name, symbol, currentPrice);
    }
}