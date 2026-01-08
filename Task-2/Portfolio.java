import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private Map<String, Integer> stocks; // Key: Stock Symbol, Value: Quantity
    private Map<String, Double> stockPurchasePrices; // Store original purchase price for performance tracking

    public Portfolio() {
        this.stocks = new HashMap<>();
        this.stockPurchasePrices = new HashMap<>();
    }

    public void addStock(Stock stock, int quantity, double purchasePrice) {
        String symbol = stock.getSymbol();
        stocks.put(symbol, stocks.getOrDefault(symbol, 0) + quantity);
        stockPurchasePrices.put(symbol, purchasePrice); // Simplistic, assumes all shares bought at same price
    }

    public boolean removeStock(String symbol, int quantity) {
        if (stocks.containsKey(symbol) && stocks.get(symbol) >= quantity) {
            stocks.put(symbol, stocks.get(symbol) - quantity);
            if (stocks.get(symbol) == 0) {
                stocks.remove(symbol);
                stockPurchasePrices.remove(symbol);
            }
            return true;
        }
        return false;
    }

    public Map<String, Integer> getStocks() {
        return stocks;
    }

    public double calculateTotalValue(StockMarket market) {
        double totalValue = 0.0;
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            Stock stock = market.getStock(symbol);
            if (stock != null) {
                totalValue += stock.getCurrentPrice() * quantity;
            }
        }
        return totalValue;
    }

    public void displayPerformance(StockMarket market) {
        System.out.println("\n--- Portfolio Performance ---");
        for (Map.Entry<String, Integer> entry : stocks.entrySet()) {
            String symbol = entry.getKey();
            int quantity = entry.getValue();
            Stock stock = market.getStock(symbol);
            double purchasePrice = stockPurchasePrices.getOrDefault(symbol, 0.0);
            
            if (stock != null) {
                double currentValue = stock.getCurrentPrice() * quantity;
                double profitLoss = (stock.getCurrentPrice() - purchasePrice) * quantity;
                System.out.printf("%s: %d shares, Current Value: $%.2f, P/L: $%.2f%n",
                        stock.getName(), quantity, currentValue, profitLoss);
            }
        }
        System.out.printf("Total Portfolio Value: $%.2f%n", calculateTotalValue(market));
        System.out.println("-----------------------------");
    }
}