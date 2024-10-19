
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Chapter1 implements GameStage {

    private JFrame frame;
    private Narration narration;
    private Timer currentTimer;
    private int dialogueIndex = 5; // Start at dialogue 6 for `straightDialogue`
    private String speaker;
    private String text;


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

        JButton skipButton = new JButton("Skip");
        skipButton.setFont(new Font("Serif", Font.BOLD, 20));
        skipButton.setForeground(Color.WHITE);
        skipButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        skipButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        skipButton.setFocusPainted(false); // No focus border
        skipButton.setOpaque(true); // Ensure background color is painted
        skipButton.addActionListener(e -> showStage()); // Skip directly to the next part

        // Add hover effect to Skip button
        skipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                skipButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                skipButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        gbc.gridy = 1; // Move Y-POSITION for the Skip button
        storyPanel.add(skipButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(storyPanel);
        frame.revalidate();
        frame.repaint();

        showDialogue(storyTextArea, 0); //
    }

    @Override
    public void showStage() {
        // Create the main exploration panel
        JPanel explorationPanel = new JPanel();
        explorationPanel.setBackground(Color.BLACK);
        explorationPanel.setLayout(new BorderLayout());

        // Create the exploration label
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

        // Timer for delayed narration display
        Timer timer = new Timer(2000, e -> {
            // Typewriter effect for the dialogue, and display choices afterward
            typeWriterEffect(narration.getDialogue(1), 50, () -> {
                // Display choices after the typewriter effect is complete
                JButton checkPhoneButton = new JButton("Check the phone");
                JButton continueSleepingButton = new JButton("Continue sleeping");

                // Add action listeners for the buttons
                checkPhoneButton.addActionListener(a -> checkPhone());
                continueSleepingButton.addActionListener(a -> continueSleeping());

                // Call the displayChoices method to show options
                displayChoices(checkPhoneButton, continueSleepingButton);
            });
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
        narrationArea.setEditable(false);
        narrationArea.setMargin(new Insets(10, 10, 10, 10));

        narrationPanel.add(narrationArea, BorderLayout.CENTER);

        // Clear previous content and display the narration
        frame.getContentPane().removeAll();
        frame.add(narrationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void displayNarration(String narrationText, Runnable onNext) {
        JPanel narrationPanel = new JPanel(new GridBagLayout());
        narrationPanel.setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        gbc.fill = GridBagConstraints.NONE;

        JTextArea narrationArea = new JTextArea(narrationText);
        narrationArea.setForeground(Color.WHITE);
        narrationArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setEditable(false);
        narrationArea.setOpaque(false);
        narrationArea.setEditable(false);
        narrationArea.setMargin(new Insets(125, 10, 0, 10));
        narrationArea.setPreferredSize(new Dimension(800, 400)); // Optional: set size to control the area

        narrationPanel.add(narrationArea, gbc);

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
            // Set button properties
            button.setFont(new Font("Serif", Font.PLAIN, 18));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(255, 215, 0)); // Soft pastel yellow (golden color)
            button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
            button.setFocusPainted(false); // No focus border
            button.setOpaque(true); // Ensure background color is painted

            // Add a hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 215, 0)); // Reset to original color
                }
            });

            // Add button to the panel
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void blackScreen() {
        JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);

        frame.getContentPane().removeAll();
        frame.add(blackPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void straightDialogue() {
        System.out.print("Successfully!");
        dialogueIndex = 5; // Start from the specified index
        boolean isDone = false;
        String[] speaker = {"Main Character", "Speaker"};
        int i = 0;
        int stop = 7;
        // Check if the dialogue exists
        if (dialogueIndex < stop) {
            isDone = true;
            System.out.println("Dialogue at index 5: " + text); // Debug statement
            displayDialogue(dialogueIndex, stop, speaker[i++]);
            if(dialogueIndex < stop){
                isDone = false;
            }
        } else {
            System.out.println("No dialogue found at index 5.");
        }

        if(isDone){
            displayPlanet();

        }
    }



    public void displayPlanet() {
        JPanel explorationPanel = new JPanel();
        explorationPanel.setBackground(Color.GRAY);

        explorationPanel.setLayout(new BorderLayout());

        JLabel explorationLabel = new JLabel("<html><center>ASTEROID B-612</center></html>");
        explorationLabel.setForeground(Color.WHITE);
        explorationLabel.setFont(new Font("Serif", Font.BOLD, 24));
        explorationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        explorationPanel.add(explorationLabel, BorderLayout.CENTER);

        // Display the initial exploration panel
        frame.getContentPane().removeAll();
        frame.add(explorationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void typeWriterEffect(String text, int delay) {
        typeWriterEffect(text, delay, null);  // Call the version with the Runnable, but pass null as the callback
    }

    //used in displayDialogue()
    private void typeWriterEffect(JTextArea textArea, String text) {
        new Thread(() -> {
            for (char c : text.toCharArray()) {
                textArea.append(String.valueOf(c)); // Add one character at a time
                try {
                    Thread.sleep(50); // Adjust speed (in milliseconds) of the typing effect
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void typeWriterEffect(String text, int delay, Runnable onComplete) {
        // Clear any previous text
        displayNarration("", null);  // Set showSkipButton to false

        // Timer to update the text character by character
        Timer typingTimer = new Timer(delay, new ActionListener() {
            int charIndex = 0;
            StringBuilder currentText = new StringBuilder();

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the next character to the text
                if (charIndex < text.length()) {
                    currentText.append(text.charAt(charIndex));
                    displayNarration(currentText.toString(), onComplete); // Update the display with Skip/Next button

                    charIndex++;
                } else {
                    // Stop the timer when done
                    ((Timer) e.getSource()).stop();

                    // Call the onComplete callback if provided
                    if (onComplete != null) {
                        onComplete.run();  // This will show buttons after typing finishes
                    }
                }
            }
        });

        typingTimer.start();
    }

    // Dummy methods for actions
    private void checkPhone() {
        if (narration != null && narration.getDialogueCount() > 2) {
            typeWriterEffect(narration.getDialogue(2), 70, () -> {
                JButton checkNotif = new JButton("Check the notification");
                checkNotif.addActionListener(a -> checkEmail());
                displayChoices(checkNotif);
            });
        }
    }

    private void continueSleeping() {
        if (narration != null && narration.getDialogueCount() > 3) {
            typeWriterEffect(narration.getDialogue(3), 70, () -> {
                JButton checkNotif = new JButton("Check the notification");
                checkNotif.addActionListener(a -> checkEmail());
                displayChoices(checkNotif);
            });
        }
    }

    private void checkEmail() {
        if (narration != null && narration.getDialogueCount() > 4) {
            typeWriterEffect(narration.getDialogue(4), 90, () -> {
                // Callback executed after typewriter effect
                JPanel explorationPanel = new JPanel();
                explorationPanel.setBackground(Color.BLACK);
                explorationPanel.setLayout(new BorderLayout());

                // Add the exploration panel to the frame (if necessary)
                frame.getContentPane().removeAll(); // Clear previous content
                frame.getContentPane().add(explorationPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();

                // Start a timer to proceed to straight dialogue
                Timer timer = new Timer(2500, e -> {
                    System.out.println("Success");
                    straightDialogue(); // Proceed to straight dialogue
                });

                timer.setRepeats(false);
                timer.start();
            });
        }
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

// Overloaded showDialogue method with speaker's name
// Modified showDialogue method to accept JTextArea and display the text
private void dialogue(JTextArea storyTextArea, String dialogueText, int index) {
        Timer dialogueTimer = new Timer(75, new ActionListener() {
            int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < dialogueText.length()) {
                    storyTextArea.append(String.valueOf(dialogueText.charAt(charIndex)));
                    charIndex++;
                    storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
                } else {
                    ((Timer) e.getSource()).stop();
                    // Automatically show the next dialogue after a delay or upon button press
                    Timer nextDialogueTimer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            showNextDialogue(storyTextArea);
                        }
                    });
                    nextDialogueTimer.setRepeats(false);
                    nextDialogueTimer.start(); // Start the delay before showing the next dialogue
                }
            }
        });
        dialogueTimer.start(); // Start the typing effect
    }
    private void showNextDialogue(JTextArea storyTextArea) {
        if (dialogueIndex < narration.getDialogueCount() - 1) {
            dialogueIndex++; // Move to the next dialogue
            String text = narration.getDialogue(dialogueIndex);
            dialogue(storyTextArea, text, dialogueIndex);
        } else {
            // Optionally handle the end of dialogues
            storyTextArea.append("\nEnd of dialogue."); // Optional: notify end
        }
    }


    private void displayDialogue(int startIndex, int endIndex, String speakerName) {
        // Check if the current dialogue index is within the specified range
        if (dialogueIndex < startIndex || dialogueIndex >= endIndex) {
            System.out.println("Dialogue index out of range.");
            return; // Exit if the index is out of range
        }

        // Create a new JPanel for the dialogue box
        JPanel dialoguePanel = new JPanel();
        dialoguePanel.setLayout(new BorderLayout());
        dialoguePanel.setBackground(new Color(30, 30, 30)); // Darker background

        // Create a label for the speaker's name
        JLabel speakerLabel = new JLabel(speakerName);
        speakerLabel.setForeground(Color.YELLOW); // Bright color for visibility
        speakerLabel.setFont(new Font("Serif", Font.BOLD, 22));
        speakerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dialoguePanel.add(speakerLabel, BorderLayout.NORTH);

        // Create a text area for the dialogue
        JTextArea dialogueTextArea = new JTextArea();
        dialogueTextArea.setForeground(Color.WHITE);
        dialogueTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        dialogueTextArea.setLineWrap(true);
        dialogueTextArea.setWrapStyleWord(true);
        dialogueTextArea.setEditable(false);
        dialogueTextArea.setOpaque(false);
        dialogueTextArea.setMargin(new Insets(20, 20, 20, 20));

        dialoguePanel.add(dialogueTextArea, BorderLayout.CENTER);

        // Create the Next button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Serif", Font.BOLD, 18));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.setFocusPainted(false);
        nextButton.setOpaque(true);

        // Action listener for the Next button
        nextButton.addActionListener(e -> {
            dialogueIndex++; // Move to the next dialogue
            if (dialogueIndex < endIndex) {
                dialogueTextArea.setText(""); // Clear the current text
                displayDialogue(startIndex, endIndex, speakerName); // Display the next dialogue
            } else {
                System.out.println("End of dialogues."); // Optionally handle the end
                // You can also add code here to transition to the next part of the story or exploration
            }
        });

        dialoguePanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom

        // Clear previous content and display the dialogue panel
        frame.getContentPane().removeAll();
        frame.add(dialoguePanel, BorderLayout.SOUTH); // Position the dialogue panel at the bottom
        frame.revalidate();
        frame.repaint();

        // Start the typewriter effect on the dialogue text
        String dialogue = narration.getDialogue(dialogueIndex);
        typeWriterEffect(dialogueTextArea, dialogue);
    }


}