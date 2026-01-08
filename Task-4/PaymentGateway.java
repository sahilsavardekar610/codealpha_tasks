public class PaymentGateway {
    public boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println("Payment failed: Amount must be greater than zero.");
            return false;
        }
        
        // In a real application, this would connect to a payment processor API.
        // Here, we'll just simulate a successful transaction.
        System.out.printf("Processing a payment of $%.2f...%n", amount);
        System.out.println("Payment successful!");
        return true;
    }
}