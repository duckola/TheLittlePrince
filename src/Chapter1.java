
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Chapter1 implements GameStage {

    private JFrame frame;
    private Narration narration;
    private Timer currentTimer;
    private int dialogueIndex = 0;
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
        skipButton.setFocusPainted(false);
        skipButton.setOpaque(true);
        skipButton.addActionListener(e -> showStage());

        skipButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                skipButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                skipButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        gbc.gridy = 1; // Move Y-POSITION
        storyPanel.add(skipButton, gbc);

        frame.getContentPane().removeAll();
        frame.add(storyPanel);
        frame.revalidate();
        frame.repaint();

        showDialogue(storyTextArea, 0);
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

        frame.getContentPane().removeAll();
        frame.add(explorationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        Timer timer = new Timer(2000, e -> {
            typeWriterEffect(narration.getDialogue(1), 50, () -> {
                JButton checkPhoneButton = new JButton("Check the phone");
                JButton continueSleepingButton = new JButton("Continue sleeping");

                checkPhoneButton.addActionListener(a -> checkPhone());
                continueSleepingButton.addActionListener(a -> continueSleeping());

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
        narrationArea.setPreferredSize(new Dimension(800, 400));

        narrationPanel.add(narrationArea, gbc);

        // Clear previous content and display the narration
        frame.getContentPane().removeAll();
        frame.add(narrationPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void displayChoices(JButton... buttons) {
        // Create a JPanel with FlowLayout for centering the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setBackground(Color.BLACK);

        // Add an empty border to the panel to create margin at the bottom
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10)); // Top, Left, Bottom, Right margins

        // Loop through each button and set the properties
        for (JButton button : buttons) {
            // Set button properties
            button.setFont(new Font("Serif", Font.PLAIN, 18));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(255, 215, 0)); // Soft pastel yellow (golden color)
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(255, 245, 0), 2), // Yellow border
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)));           // Padding inside the button
            button.setFocusPainted(false);
            button.setOpaque(true);

            // Add a hover effect
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(255, 215, 0)); // Reset to original color
                }
            });

            // Add the button to the panel
            buttonPanel.add(button);
        }

        // Add the button panel to the frame
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
        dialogueIndex = 5;
        int stop = 7;

        String[] speakers = {"Speaker", "Main Character"};
        if (dialogueIndex < stop) {
            System.out.println("Starting dialogue...");
            displayDialogue(dialogueIndex, stop, speakers);
        } else {
            System.out.println("No dialogue found at this index.");
        }
    }

    @Override
    public void typeWriterEffect(String text, int delay) {
        typeWriterEffect(text, delay, null);
    }

    //used in displayDialogue()
    private void typeWriterEffect(JTextArea textArea, String text) {
        new Thread(() -> {
            for (char c : text.toCharArray()) {
                textArea.append(String.valueOf(c));
                try {
                    Thread.sleep(30); // Adjust speed (in milliseconds) of the typing effect
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
                    displayNarration(currentText.toString(), onComplete);
                    charIndex++;
                } else {
                    ((Timer) e.getSource()).stop();

                    if (onComplete != null) {
                        onComplete.run();
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
            typeWriterEffect(narration.getDialogue(3), 60, () -> {
                JButton checkNotif = new JButton("Check the notification");
                checkNotif.addActionListener(a -> checkEmail());
                displayChoices(checkNotif);
            });
        }
    }

    private void checkEmail() {
        if (narration != null && narration.getDialogueCount() > 4) {
            typeWriterEffect(narration.getDialogue(4), 50, () -> {
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
                Timer timer = new Timer(3000, e -> {
                    System.out.println("Success");
                    straightDialogue(); // Proceed to straight dialogue
                });

                timer.setRepeats(false);
                timer.start();
            });
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

        System.out.print("Successfully!");
        dialogueIndex = 8;
        int stop = 10;

        String[] speakers = {"Little Prince", "Rose"};
        Timer timer = new Timer(3000, e -> {
            if (dialogueIndex < stop) {
                System.out.println("Starting dialogue...");
                displayDialogue(dialogueIndex, stop, speakers);
            } else {
                System.out.println("No dialogue found at this index.");
            }
        });

        // Only fire the Timer once
        timer.setRepeats(false);
        timer.start();
    }

    private void talkToRose() {
        typeWriterEffect(narration.getDialogue(11), 30, () -> {
            JButton talkToRose = new JButton("Talk To Rose");
            String[] speakers = {"Main Character", "Rose"};
            dialogueIndex = 12;
            int stop = 14;
            if (dialogueIndex < stop) {
                System.out.println("Starting dialogue...");
                talkToRose.addActionListener(a -> displayDialogue(dialogueIndex, stop, speakers));
            }
            displayChoices(talkToRose);
            
        });
    }

    private void talkToPrince() {
        String[] speakers = {"Main Character", "Little Prince"};
        dialogueIndex = 14;
        int stop = 20;
        if (dialogueIndex < stop) {
            System.out.println("Starting dialogue...");
            displayDialogue(dialogueIndex, stop, speakers);
        }
    }

    private void nextPlanet() {
        typeWriterEffect("The next day...", 60, () -> {
            Timer timer = new Timer(3000, e1 -> {
                typeWriterEffect(narration.getDialogue(20), 50, () -> {
                    String[] speakers = {"Speaker"};
                    dialogueIndex = 21;
                    displayDialogue(dialogueIndex, 22, speakers);
                });
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private void displayNextPlanet() {
        Timer timer2 = new Timer(3000, e2 -> {
            JLabel phase2JLabel = new JLabel("<html><center><b>The Vain Man Planet: Asteroid B-326 <\b>\n Planet 1: Gallery of the Flaring Statuette  </center></html>");
            phase2JLabel.setForeground(Color.WHITE);
            phase2JLabel.setFont(new Font("Serif", Font.BOLD, 24));
            phase2JLabel.setHorizontalAlignment(SwingConstants.CENTER);
            phase2JLabel.add(phase2JLabel, BorderLayout.CENTER);

            frame.getContentPane().removeAll();
            frame.add(phase2JLabel, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();

            String[] speakers = {" Speaker", " Main Character"};
            dialogueIndex = 22;
            int stop = 24;
            Timer timer3 = new Timer(3000, e3 -> {
                if (dialogueIndex < stop) {
                    System.out.println("Starting dialogue...");
                    displayDialogue(dialogueIndex, stop, speakers);
                } else {
                    System.out.println("No dialogue found at this index.");
                }
            });

            // Only fire the Timer once
            timer3.setRepeats(false);
            timer3.start();
        });

        // Only fire the Timer once
        timer2.setRepeats(false);
        timer2.start();
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

    private void displayDialogue(int startIndex, int endIndex, String[] speakerNames) {
        // Check if the current dialogue index is within the specified range
        if (dialogueIndex < startIndex || dialogueIndex >= endIndex) {
            System.out.println("Dialogue index out of range.");
            return; // Exit if the index is out of range
        }

        JPanel dialoguePanel = new JPanel();
        dialoguePanel.setLayout(new BorderLayout());
        dialoguePanel.setBackground(new Color(30, 30, 30)); // Darker background

        String currentSpeakerName = speakerNames[dialogueIndex % speakerNames.length]; // Get current speaker name
        JLabel speakerLabel = new JLabel(currentSpeakerName);
        speakerLabel.setForeground(Color.YELLOW); // Bright color for visibility
        speakerLabel.setFont(new Font("Serif", Font.BOLD, 22));
        speakerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dialoguePanel.add(speakerLabel, BorderLayout.NORTH);

        JTextArea dialogueTextArea = new JTextArea();
        dialogueTextArea.setForeground(Color.WHITE);
        dialogueTextArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
        dialogueTextArea.setLineWrap(true);
        dialogueTextArea.setWrapStyleWord(true);
        dialogueTextArea.setEditable(false);
        dialogueTextArea.setOpaque(false);
        dialogueTextArea.setMargin(new Insets(20, 20, 20, 20));

        dialoguePanel.add(dialogueTextArea, BorderLayout.CENTER);

        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Serif", Font.BOLD, 18));
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        nextButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        nextButton.setFocusPainted(false);
        nextButton.setOpaque(true);

        nextButton.addActionListener(e -> {
            dialogueIndex++;
            if (dialogueIndex < endIndex) {
                dialogueTextArea.setText("");
                displayDialogue(startIndex, endIndex, speakerNames); // Display the next dialogue with the speakerNames array
            } else {
                System.out.println("End of dialogues." + "\nDialogueIndex is: " + dialogueIndex); // Optionally handle the end
                nextEventTrigger();

            }
        });

        dialoguePanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom

        frame.getContentPane().removeAll();
        frame.add(dialoguePanel, BorderLayout.SOUTH); // Position the dialogue panel at the bottom
        frame.revalidate();
        frame.repaint();

        String dialogue = narration.getDialogue(dialogueIndex);

        typeWriterEffect(dialogueTextArea, dialogue);
    }

private void startChapter1() {
    // Call startGame() on the same frame (no need to create a new frame)
    StartGame chapter1 = new StartGame(frame);
    chapter1.startGame();  // Start the game using the existing frame
}



    private void nextEventTrigger() {
        switch (dialogueIndex) {
            case 7:
                displayPlanet();
                break;
            case 10:
                talkToRose();
                break;
            case 14:
                talkToPrince();
                break;
            case 20:
                nextPlanet();
                break;
            case 22:
                displayNextPlanet();
                break;
            case 24:
                startChapter1();
                break;
        }
    }

}
