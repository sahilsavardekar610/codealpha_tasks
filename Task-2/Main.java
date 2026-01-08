public class Main {
    public static void main(String[] args) {
        // Create a new user with an initial balance
        User user = new User("JohnDoe", 10000.00);

        // Initialize and run the trading platform
        TradingPlatform platform = new TradingPlatform(user);
        
        // This is where you would call the run method
        platform.run();
    }
}