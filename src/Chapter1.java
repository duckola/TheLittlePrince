
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Chapter1 implements GameStage {

    private JFrame frame;
    private Narration narration;
    private Timer currentTimer;

    private JPanel dialogBox;
    private JLabel speakerLabel;
    private JTextArea dialogueTextArea;

    public Chapter1(JFrame frame, Narration narration) {
        this.frame = frame;
        this.narration = narration;
    }

    @Override
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
                    showDialogue(storyTextArea, dialogueIndex + 1);
                }
            }
        });
        currentTimer.start();
    }

    @Override
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
                    + narration.getDialogue(1)
                    + narration.getDialogue(2));
            // Player choices
            JButton checkPhoneButton = new JButton("Check the phone");
            JButton continueSleepingButton = new JButton("Continue sleeping");

            // Add action listeners for choices
            checkPhoneButton.addActionListener(a -> checkPhone());
            continueSleepingButton.addActionListener(a -> continueSleeping());

            displayChoices(checkPhoneButton, continueSleepingButton);
        });
        timer.setRepeats(false); // Only run once
        timer.start();

    }

    @Override
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

    @Override
    public void displayChoices(JButton... buttons) {
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

    @Override

    public void blackScreen() { // change implementation
        JPanel blackPanel = new JPanel();  
        blackPanel.setBackground(Color.BLACK);  

        frame.getContentPane().removeAll();
        frame.add(blackPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Dummy methods for actions
    private void checkPhone() {
        displayNarration(narration.getDialogue(3));
        JButton checkNotif = new JButton("Check the notification");
        checkNotif.addActionListener(a -> checkEmail());
        displayChoices(checkNotif);
    }

    private void continueSleeping() {
        displayNarration(narration.getDialogue(4));
        JButton checkNotif = new JButton("Check the notification");
        checkNotif.addActionListener(a -> checkEmail());
        displayChoices(checkNotif);
    }

    private void checkEmail() {
        displayNarration(narration.getDialogue(5));
        JPanel explorationPanel = new JPanel();
        explorationPanel.setBackground(Color.BLACK);
        explorationPanel.setLayout(new BorderLayout());
        Timer timer = new Timer(2000, e -> {   
             blackScreen();
             straightDialogue();
        });
    
    timer.setRepeats (false); // Only run once
    timer.start ();

}
    @Override
    public void straightDialogue() {
        // Display the first part of the dialogue
        displaySpeakerName("Main Character: ");
        String dialogue = narration.getDialogue(6);
        typeWriterEffect(dialogue, 50);

        // Create a timer for the first dialogue
        Timer timer1 = new Timer(2500, e -> {
            // Clear the previous narration and display the speaker's name
            clearDialogBox(); // Clear previous dialog box
            displaySpeakerName("Speaker: "); // Method to display speaker name

            String dialogue2 = narration.getDialogue(7);
            typeWriterEffect(dialogue2, 50);

            // Create a timer for the tutorial text
            Timer timer2 = new Timer(2500, ev -> {
                clearDialogBox(); // Clear previous dialog box before showing tutorial text
                String tutorialText = narration.getDialogue(8) + "\n\n" + narration.getDialogue(9);
                typeWriterEffect(tutorialText, 50);
            });

            timer2.setRepeats(false);
            timer2.start();
        });

        timer1.setRepeats(false);
        timer1.start();
    }


    @Override
    public void setupMainFrame() {
        frame = new JFrame("Dialogue Example");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    @Override
    public void createDialogBox() {
        // Create a panel for the dialog box
        dialogBox = new JPanel();
        dialogBox.setBackground(new Color(100, 100, 100, 200)); // Grey with some transparency
        dialogBox.setLayout(new BorderLayout());
        dialogBox.setPreferredSize(new Dimension(800, 150)); // Set preferred size for the dialog box

        // Initialize the speaker label
        speakerLabel = new JLabel("Speaker: ");
        speakerLabel.setForeground(Color.WHITE); // Set text color to white
        dialogBox.add(speakerLabel, BorderLayout.NORTH);

        // Initialize the dialogue text area
        dialogueTextArea = new JTextArea();
        dialogueTextArea.setEditable(false); // Make the text area non-editable
        dialogueTextArea.setLineWrap(true); // Enable line wrapping
        dialogueTextArea.setWrapStyleWord(true); // Wrap words
        dialogueTextArea.setBackground(new Color(150, 150, 150)); // Lighter grey for the text area
        dialogueTextArea.setForeground(Color.BLACK); // Text color for readability
        dialogBox.add(new JScrollPane(dialogueTextArea), BorderLayout.CENTER); // Add text area to scroll pane

        // Add dialog box to the bottom of the frame
        frame.add(dialogBox, BorderLayout.SOUTH);
    }


    @Override
    public void displaySpeakerName(String speakerName) {
        speakerLabel.setText(speakerName);
    }

    // Method to clear and set dialogue text
    @Override
    public void displayDialogue(String dialogue) {
        dialogueTextArea.setText(dialogue);
    }

    @Override
    public void typeWriterEffect(String text, int delay) {
        // Clear any previous text
        displayNarration("");

        // Timer to update the text character by character
        Timer typingTimer = new Timer(delay, new ActionListener() {
            int charIndex = 0;
            StringBuilder currentText = new StringBuilder();

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the next character to the text
                if (charIndex < text.length()) {
                    currentText.append(text.charAt(charIndex));
                    displayNarration(currentText.toString()); // Update the display
                    charIndex++;
                } else {
                    // Stop the timer when done
                    ((Timer) e.getSource()).stop();
                }
            }
        });

        typingTimer.start();
    }

    private void clearDialogBox() {
        // Clear the dialogue text area
        if (dialogueTextArea != null) {
            dialogueTextArea.setText("");
        }
    }
}
