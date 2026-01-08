import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class ChatbotGUI extends JFrame {

    private JTextArea chatArea;
    private JTextField inputField;
    private Chatbot chatbot;

    public ChatbotGUI() {
        super("Chatbot");

        chatbot = new Chatbot();

        // Set up the UI components
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        // Set frame properties
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void sendMessage() {
        String userMessage = inputField.getText();
        if (!userMessage.isEmpty()) {
            // Display user message
            chatArea.append("You: " + userMessage + "\n");
            
            // Get chatbot response
            String botResponse = chatbot.getResponse(userMessage);
            
            // Display bot response
            chatArea.append("Bot: " + botResponse + "\n");
            
            // Clear the input field
            inputField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatbotGUI();
            }
        });
    }
}