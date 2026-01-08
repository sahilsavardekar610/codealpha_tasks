import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chatbot {

    private Map<String, String> ruleBasedAnswers;
    
    public Chatbot() {
        // Initialize with some rule-based answers
        ruleBasedAnswers = new HashMap<>();
        ruleBasedAnswers.put("hello", "Hello there! How can I help you?");
        ruleBasedAnswers.put("how are you", "I'm a bot, but I'm doing great! Thanks for asking.");
        ruleBasedAnswers.put("what is your name", "I don't have a name, but you can call me Bot.");
        ruleBasedAnswers.put("bye", "Goodbye! Have a great day.");
    }
    
    public String getResponse(String userInput) {
        String lowerCaseInput = userInput.toLowerCase();
        
        // Simple rule-based approach
        for (Map.Entry<String, String> entry : ruleBasedAnswers.entrySet()) {
            if (lowerCaseInput.contains(entry.getKey())) {
                return entry.getValue();
            }
        }

        // More complex NLP or ML logic would go here
        // This is a placeholder for more advanced processing
        return "I'm sorry, I don't understand. Can you please rephrase?";
    }
}