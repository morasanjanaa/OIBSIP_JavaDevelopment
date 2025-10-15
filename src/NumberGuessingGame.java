import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private int randomNumber;
    private int attempts;

    private JTextField guessField;
    private JLabel messageLabel, attemptLabel;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        // Random Number Generator
        resetGame();

        // Message Label
        messageLabel = new JLabel("Guess a number between 1 and 100:", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(messageLabel);

        // Text Field
        guessField = new JTextField();
        guessField.setHorizontalAlignment(JTextField.CENTER);
        add(guessField);

        // Attempt Label
        attemptLabel = new JLabel("Attempts: 0", SwingConstants.CENTER);
        add(attemptLabel);

        // Guess Button
        JButton guessButton = new JButton("Submit Guess");
        add(guessButton);

        // Reset Button
        JButton resetButton = new JButton("New Game");
        add(resetButton);

        // Guess Logic
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = guessField.getText();

                try {
                    int guess = Integer.parseInt(userInput);
                    attempts++;
                    attemptLabel.setText("Attempts: " + attempts);

                    if (guess < 1 || guess > 100) {
                        messageLabel.setText("Enter a number between 1 and 100!");
                    } else if (guess < randomNumber) {
                        messageLabel.setText("Too Low! Try again.");
                    } else if (guess > randomNumber) {
                        messageLabel.setText("Too High! Try again.");
                    } else {
                        messageLabel.setText("Correct! You guessed it in " + attempts + " attempts!");
                    }

                } catch (NumberFormatException ex) {
                    messageLabel.setText("Please enter a valid number!");
                }

                // ✅ Clear the text field after each guess
                guessField.setText("");
            }
        });

        // Reset Logic
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private void resetGame() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
        if (messageLabel != null) {
            messageLabel.setText("Guess a number between 1 and 100:");
        }
        if (attemptLabel != null) {
            attemptLabel.setText("Attempts: 0");
        }
        if (guessField != null) {
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessingGame().setVisible(true);
        });
    }
}
