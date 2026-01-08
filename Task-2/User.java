public class User {
    private String username;
    private double balance;
    private Portfolio portfolio;

    public User(String username, double initialBalance) {
        this.username = username;
        this.balance = initialBalance;
        this.portfolio = new Portfolio();
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }
}