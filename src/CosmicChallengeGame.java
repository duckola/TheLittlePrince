import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class CosmicChallengeGame {
    private JFrame frame;
    private int dialogueIndex = 0;
    private String[] dialogues;
    private String[] images;
    private JTextArea storyTextArea;
    private JLabel imageLabel;
    private JButton nextButton;
    private JButton prevButton;

    public CosmicChallengeGame() {
        frame = new JFrame("Cosmic Challenge");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        loadDialogues(); // Load dialogues and images from JSON
        blackScreen(); // Initialize the black screen
        frame.setVisible(true);
    }

    public void blackScreen() {
        JPanel blackPanel = new JPanel();
        blackPanel.setBackground(Color.BLACK);

        frame.getContentPane().removeAll();
        frame.add(blackPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public void straightDialogue() {
        blackScreen(); // Show black screen

        // Create and configure the JTextArea for dialogue
        storyTextArea = new JTextArea(10, 30);
        storyTextArea.setEditable(false);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setBackground(Color.BLACK);
        storyTextArea.setForeground(Color.WHITE);

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(storyTextArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Create the image label
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER); // Center the image

        // Add the scroll pane and image label to the black panel
        JPanel blackPanel = (JPanel) frame.getContentPane().getComponent(0);
        blackPanel.setLayout(new BorderLayout());
        blackPanel.add(scrollPane, BorderLayout.CENTER);
        blackPanel.add(imageLabel, BorderLayout.NORTH); // Add image above the text area

        // Create buttons
        nextButton = new JButton("Next");
        prevButton = new JButton("Previous");
        prevButton.setEnabled(false); // Disable initially

        // Add action listeners for the buttons
        nextButton.addActionListener(e -> showNextDialogue());
        prevButton.addActionListener(e -> showPreviousDialogue());

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Add button panel to the black panel
        blackPanel.add(buttonPanel, BorderLayout.SOUTH);
        blackPanel.revalidate();
        blackPanel.repaint();

        // Add key listeners for navigation
        blackPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "next");
        blackPanel.getActionMap().put("next", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextDialogue();
            }
        });

        blackPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("BACK_SPACE"), "previous");
        blackPanel.getActionMap().put("previous", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousDialogue();
            }
        });

        // Display the first dialogue
        showDialogue();
    }

    private void showDialogue() {
        if (dialogueIndex >= 0 && dialogueIndex < dialogues.length) {
            String dialogue = dialogues[dialogueIndex];
            storyTextArea.setText(""); // Clear previous text
            fadeInText(storyTextArea, dialogue);
            updateImage(dialogueIndex); // Update the image based on the current dialogue
            prevButton.setEnabled(dialogueIndex > 0); // Enable/disable previous button
        } else {
            storyTextArea.setText("End of dialogues.");
            dialogueIndex = 0; // Reset for demonstration or set to an appropriate state
        }
    }

    private void fadeInText(JTextArea textArea, String dialogue) {
        Timer fadeTimer = new Timer(50, new ActionListener() {
            int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < dialogue.length()) {
                    textArea.append(String.valueOf(dialogue.charAt(charIndex)));
                    charIndex++;
                } else {
                    ((Timer) e.getSource()).stop(); // Stop when done
                }
            }
        });
        fadeTimer.start(); // Start the fade-in effect
    }

    private void updateImage(int index) {
        if (index >= 0 && index < images.length) {
            ImageIcon icon = new ImageIcon(images[index]);
            imageLabel.setIcon(icon); // Update the image based on the current dialogue
        }
    }

    private void showNextDialogue() {
        dialogueIndex++;
        showDialogue();
    }

    private void showPreviousDialogue() {
        if (dialogueIndex > 0) {
            dialogueIndex--;
            showDialogue();
        }
    }

    private void loadDialogues() {
        try (Reader reader = new FileReader("path/to/dialogues.json")) {
            Type listType = new TypeToken<List<DialogueEntry>>() {}.getType();
            List<DialogueEntry> entries = new Gson().fromJson(reader, listType);
            dialogues = entries.stream().map(entry -> entry.dialogue).toArray(String[]::new);
            images = entries.stream().map(entry -> entry.image).toArray(String[]::new);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Define a class to match the structure of your JSON
    class DialogueEntry {
        String dialogue;
        String image;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CosmicChallengeGame::new);
    }
}
