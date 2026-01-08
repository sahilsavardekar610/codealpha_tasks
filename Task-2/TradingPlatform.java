import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradingPlatform {
    private User currentUser;
    private StockMarket stockMarket;
    private List<Transaction> transactionHistory;
    private Scanner scanner;
    private FileHandler fileHandler;

    public TradingPlatform(User user) {
        this.currentUser = user;
        this.stockMarket = new StockMarket();
        this.transactionHistory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.fileHandler = new FileHandler();
    }

    public void run() {
        System.out.println("Welcome to the Stock Trading Platform, " + currentUser.getUsername() + "!");
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1":
                case "M":
                    displayMarketData();
                    break;
                case "2":
                case "B":
                    buyStock();
                    break;
                case "3":
                case "S":
                    sellStock();
                    break;
                case "4":
                case "P":
                    displayPortfolio();
                    break;
                case "5":
                case "T":
                    displayTransactionHistory();
                    break;
                case "6":
                case "Q":
                    System.out.println("Thank you for using the platform. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // Simulate market price changes after each action
            stockMarket.updatePrices();
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.printf("Balance: $%.2f | Portfolio Value: $%.2f%n", 
                currentUser.getBalance(), currentUser.getPortfolio().calculateTotalValue(stockMarket));
        System.out.println("1. [M]arket Data");
        System.out.println("2. [B]uy Stock");
        System.out.println("3. [S]ell Stock");
        System.out.println("4. [P]ortfolio Performance");
        System.out.println("5. [T]ransaction History");
        System.out.println("6. [Q]uit");
        System.out.print("Enter your choice: ");
    }

    private void displayMarketData() {
        System.out.println("\n--- Live Market Data ---");
        for (Stock stock : stockMarket.getAllStocks().values()) {
            System.out.println(stock);
        }
    }

    private void buyStock() {
        System.out.print("Enter stock symbol to buy: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.getStock(symbol);

        if (stock == null) {
            System.out.println("Invalid stock symbol.");
            return;
        }

        System.out.printf("Current price for %s is $%.2f%n", stock.getName(), stock.getCurrentPrice());
        System.out.print("Enter quantity to buy: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }

        double totalCost = quantity * stock.getCurrentPrice();
        if (currentUser.getBalance() < totalCost) {
            System.out.println("Insufficient balance to complete the purchase.");
            return;
        }

        if (currentUser.withdraw(totalCost)) {
            currentUser.getPortfolio().addStock(stock, quantity, stock.getCurrentPrice());
            transactionHistory.add(new Transaction(Transaction.TransactionType.BUY, symbol, quantity, stock.getCurrentPrice()));
            System.out.printf("Successfully bought %d shares of %s for $%.2f%n", quantity, stock.getName(), totalCost);
        } else {
            System.out.println("Error: Could not process the transaction.");
        }
    }

    private void sellStock() {
        System.out.print("Enter stock symbol to sell: ");
        String symbol = scanner.nextLine().toUpperCase();
        Stock stock = stockMarket.getStock(symbol);

        if (stock == null) {
            System.out.println("Invalid stock symbol.");
            return;
        }

        int ownedQuantity = currentUser.getPortfolio().getStocks().getOrDefault(symbol, 0);
        if (ownedQuantity == 0) {
            System.out.printf("You do not own any shares of %s.%n", stock.getName());
            return;
        }
        
        System.out.printf("You own %d shares of %s. Current price: $%.2f%n", ownedQuantity, stock.getName(), stock.getCurrentPrice());
        System.out.print("Enter quantity to sell: ");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }
        
        if (quantity > ownedQuantity) {
            System.out.println("You cannot sell more shares than you own.");
            return;
        }

        double totalRevenue = quantity * stock.getCurrentPrice();
        if (currentUser.getPortfolio().removeStock(symbol, quantity)) {
            currentUser.deposit(totalRevenue);
            transactionHistory.add(new Transaction(Transaction.TransactionType.SELL, symbol, quantity, stock.getCurrentPrice()));
            System.out.printf("Successfully sold %d shares of %s for $%.2f%n", quantity, stock.getName(), totalRevenue);
        } else {
            System.out.println("Error: Could not process the transaction.");
        }
    }

    private void displayPortfolio() {
        currentUser.getPortfolio().displayPerformance(stockMarket);
    }
    
    private void displayTransactionHistory() {
        System.out.println("\n--- Transaction History ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction transaction : transactionHistory) {
                System.out.println(transaction);
            }
        }
    }
}