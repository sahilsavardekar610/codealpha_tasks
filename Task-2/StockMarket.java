import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class StockMarket {
    private Map<String, Stock> availableStocks;

    public StockMarket() {
        this.availableStocks = new HashMap<>();
        initializeMarket();
    }

    private void initializeMarket() {
        // Add some sample stocks
        availableStocks.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.00));
        availableStocks.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 2500.00));
        availableStocks.put("TSLA", new Stock("TSLA", "Tesla, Inc.", 800.00));
        availableStocks.put("AMZN", new Stock("AMZN", "Amazon.com, Inc.", 3300.00));
    }

    public void updatePrices() {
        Random random = new Random();
        for (Stock stock : availableStocks.values()) {
            double change = (random.nextDouble() * 10) - 5; // Price change between -5 and +5
            double newPrice = stock.getCurrentPrice() + change;
            if (newPrice < 1) newPrice = 1.0; // Prevent price from going too low
            stock.updatePrice(newPrice);
        }
    }

    public Stock getStock(String symbol) {
        return availableStocks.get(symbol.toUpperCase());
    }

    public Map<String, Stock> getAllStocks() {
        return availableStocks;
    }
}