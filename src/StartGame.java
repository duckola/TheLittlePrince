import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartGame extends GameStage implements ActionListener{   

    private JFrame frame;
    private Narration narration;
    private Timer currentTimer; // Store the reference to the current timer


    public StartGame(JFrame frame, Narration narration) {
        super(frame, narration);
        this.frame = frame;
        this.narration = narration;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showStoryline();

    }

    public void showStoryline() {
        JPanel storyPanel = new JPanel(new GridBagLayout());
        storyPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        JTextArea storyTextArea = new JTextArea();
        storyTextArea.setForeground(Color.WHITE);
        storyTextArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setOpaque(false);
        storyTextArea.setEditable(false);
        storyTextArea.setPreferredSize(new Dimension(800, 400));
        storyTextArea.setMargin(new Insets(20, 20, 20, 20));
        storyPanel.add(storyTextArea, gbc);

        // Add the Skip Button
        JButton skipButton = new JButton("Skip");
        skipButton.addActionListener(e -> showStage()); // Skip directly to the next part
        gbc.gridy = 1;
        storyPanel.add(skipButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(storyPanel);
        frame.revalidate();
        frame.repaint();

        showDialogue(storyTextArea, 0);
    }



    private void showDialogue(JTextArea storyTextArea, int dialogueIndex) {
        if (dialogueIndex >= narration.getDialogueCount()) {
            showStage();
            return;
        }

        String storyText = narration.getDialogue(dialogueIndex);

        // Stop any existing timer before starting a new one
        if (currentTimer != null && currentTimer.isRunning()) {
            currentTimer.stop();
        }

        // Typing effect
        currentTimer = new Timer(75, new ActionListener() {
            int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < storyText.length()) {
                    storyTextArea.append(String.valueOf(storyText.charAt(charIndex)));
                    charIndex++;
                    storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
                } else {
                    ((Timer) e.getSource()).stop();
                    showDialogue(storyTextArea, dialogueIndex + 1); // Show next dialogue
                }
            }
        });
        currentTimer.start();
    }

    public void showStage() {
        JPanel explorationPanel = new JPanel();
        explorationPanel.setBackground(Color.BLACK);
        explorationPanel.setLayout(new BorderLayout());

        JLabel explorationLabel = new JLabel("<html><center>BEYOND THE STARS: Trials of Survival<br> Chapter 1: The Beginning After The End</center></html>");
        explorationLabel.setForeground(Color.WHITE);
        explorationLabel.setFont(new Font("Serif", Font.BOLD, 24));
        explorationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        explorationPanel.add(explorationLabel, BorderLayout.CENTER);

        // Display the initial exploration panel
        frame.getContentPane().removeAll();
        frame.add(explorationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        // Delay before showing narration to give time for exploration panel display
        Timer timer = new Timer(2000, e -> {
            displayNarration("BEYOND THE STARS: Trials of Survival\n\n"
                    + "Narration: You wake up to the sound of roosters crowing—a sign of the sun rising as its rays hit your eyes. "
                    + "You hide your face under the blankets to continue sleeping, but a sudden buzz from your phone interrupts you from doing so.");
            // Player choices
            JButton checkPhoneButton = new JButton("Check the phone");
            JButton continueSleepingButton = new JButton("Continue sleeping");

            // Add action listeners for choices
            checkPhoneButton.addActionListener(a-> checkPhone());
            continueSleepingButton.addActionListener(a -> continueSleeping());

            displayChoices(checkPhoneButton, continueSleepingButton);
        });
        timer.setRepeats(false); // Only run once
        timer.start();
    }

// Method to display narration text on the screen
    public void displayNarration(String narrationText) {
        JPanel narrationPanel = new JPanel(new BorderLayout());
        narrationPanel.setBackground(Color.BLACK);

        JTextArea narrationArea = new JTextArea(narrationText);
        narrationArea.setForeground(Color.WHITE);
        narrationArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setEditable(false);
        narrationArea.setOpaque(false);
        narrationArea.setMargin(new Insets(10, 10, 10, 10));

        narrationPanel.add(narrationArea, BorderLayout.CENTER);

        // Clear previous content and display the narration
        frame.getContentPane().removeAll();
        frame.add(narrationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
    // Method to add player choices (buttons for different actions)
    public void displayChoices(JButton... buttons){
        JPanel buttonPanel = new JPanel(new FlowLayout());
    buttonPanel.setBackground(Color.BLACK);

    for (JButton button : buttons) {
        button.setFont(new Font("Serif", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        buttonPanel.add(button);
    }

    frame.add(buttonPanel, BorderLayout.SOUTH);
    frame.revalidate();
    frame.repaint();
    }

    // Dummy methods for actions
    private void checkPhone() {
    displayNarration("You reach for your phone and see a notification on the screen.");
        JButton checkEmail = new JButton("Check the notification");
        checkEmail.addActionListener(a -> checkEmail());
        displayChoices(checkEmail);
    }

    private void continueSleeping() {
    displayNarration("You decide to continue sleeping, enjoying a few more minutes of peace. After an hour, your phone’s alarm started to ring. You turn the alarm off and check your phone. You saw an email from a sender named Anonymous with the subject line “CC. Little Prince”. Out of curiosity, you read the email and saw that you are the chosen participant in what they called the “Cosmic Challenge");
        JButton checkEmail = new JButton("Check the notification");
        checkEmail.addActionListener(a -> checkEmail());
        displayChoices(checkEmail);
}

    private void checkEmail(){
        displayNarration("You wanted to know more about the game so you scan the QR code attached to it. It redirects to a website with a timer countdown (00: 01: 10)  and the text “GAME STARTS AT”. In a panic, you close the website and delete the email sent to you. And suddenly…");
        JPanel explorationPanel = new JPanel();
        explorationPanel.setBackground(Color.BLACK);
        explorationPanel.setLayout(new BorderLayout());

        Timer timer = new Timer(2000, e-> {
        JLabel explorationLabel = new JLabel("<html><center>BEYOND THE STARS: Trials of Survival<br> Chapter 1: The Beginning After The End</center></html>");
        explorationLabel.setForeground(Color.WHITE);
        explorationLabel.setFont(new Font("Serif", Font.BOLD, 24));
        explorationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        explorationPanel.add(explorationLabel, BorderLayout.CENTER);

        // Display the initial exploration panel
        frame.getContentPane().removeAll();
        frame.add(explorationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        });  
    }
}

