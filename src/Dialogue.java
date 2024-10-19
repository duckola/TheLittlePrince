
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Assuming you have a Dialogue class to encapsulate speaker and text
public class Dialogue implements GameStage {
    private String speaker;
    private String text;

    public Dialogue(String speaker, String text) {
        this.speaker = speaker;
        this.text = text;
    }

    public String getSpeaker() {
        return speaker;
    }

    public String getText() {
        return text;
    }
}

@Override
public void straightDialogue() {
    System.out.print("Successfully!");
    dialogueIndex = 5; // Start from the specified index

    // Check if the dialogue exists
    if (dialogueIndex < narration.getDialogueCount()) {
        // Display the first dialogue
        displayDialogue(dialogueIndex);
    } else {
        System.out.println("No dialogue found at index 5.");
    }
}

private void displayDialogue(int index) {
    // Create a new JPanel for the dialogue box
    JPanel dialoguePanel = new JPanel();
    dialoguePanel.setLayout(new BorderLayout());
    dialoguePanel.setBackground(new Color(30, 30, 30)); // Darker background

    // Get the current dialogue
    Dialogue dialogue = narration.getDialogue(index);
    String speaker = dialogue.getSpeaker(); // Dynamically get the speaker's name
    String dialogueText = dialogue.getText(); // Get the dialogue text

    // Create a label for the speaker's name
    JLabel speakerLabel = new JLabel(speaker);
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
    dialogueTextArea.setText(dialogueText); // Set initial text
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
        dialogueTextArea.setText(""); // Clear the current dialogue
        dialogueIndex++; // Move to the next dialogue
        if (dialogueIndex < narration.getDialogueCount()) {
            displayDialogue(dialogueIndex); // Display the next dialogue
        } else {
            System.out.println("End of dialogues."); // Optionally handle the end
        }
    });

    dialoguePanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom

    // Clear previous content and display the dialogue panel
    frame.getContentPane().removeAll();
    frame.add(dialoguePanel, BorderLayout.SOUTH); // Position the dialogue panel at the bottom
    frame.revalidate();
    frame.repaint();
}
