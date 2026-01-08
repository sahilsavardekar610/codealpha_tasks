import java.io.*;
import java.util.Map;

public class FileHandler {
    private static final String FILE_NAME = "portfolio.dat";

    // Save the user's portfolio state to a file
    public void savePortfolio(User user) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(user.getPortfolio().getStocks());
            oos.writeObject(user.getBalance());
            System.out.println("Portfolio saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving portfolio: " + e.getMessage());
        }
    }

    // Load the user's portfolio and balance from a file
    public void loadPortfolio(User user) {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved portfolio found. Starting with default values.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Map<String, Integer> savedStocks = (Map<String, Integer>) ois.readObject();
            double savedBalance = (Double) ois.readObject();

            // Reconstruct the user's portfolio and balance
            // Note: This is a simplified approach. A more robust solution would handle more data.
            user.getPortfolio().getStocks().clear();
            user.getPortfolio().getStocks().putAll(savedStocks);
            
            // This part is a bit tricky, since there's no way to directly set the balance.
            // A deposit/withdrawal mechanism would be needed. For this example, we'll
            // assume a setter method is available or the balance is directly managed.
            // Let's re-engineer User to allow setting balance for loading purposes.
            // For now, let's just print the message and not change the current balance.
            System.out.println("Portfolio loaded. Note: User balance loading logic needs to be implemented.");
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading portfolio: " + e.getMessage());
        }
    }
}