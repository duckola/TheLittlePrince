
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class GameStageImplementation implements GameStage {

    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = Color.BLACK;
    private static final Color SOFT_YELLOW = new Color(255, 215, 0);
    private static final int DELAY = 75; // Typing effect delay (milliseconds)
    private static final Font MONOSPACED_FONT = new Font("Monospaced", Font.PLAIN, 18);
    private static final Font SERIF_BOLD_FONT = new Font("Serif", Font.BOLD, 20);

    private JLayeredPane layeredPane = new JLayeredPane();
    private final Narration narration;
    private Playable player;

    private int dialogueIndex = 5;
    private Image backgroundImage = null;

    public GameStageImplementation() {
        this.narration = new Narration();

    }

    public void showChapter(String chapterName) {
        // Clear the content pane
        Main.frame.getContentPane().removeAll();

        // Create a JPanel for the exploration area
        JPanel explorationPanel = new JPanel();
        explorationPanel.setLayout(new BorderLayout()); // Optional layout manager

        // Load the background image using initializeBackgroundNarration
        JLabel backgroundLabel = initializeBackgroundNarration(chapterName);

        // Check if background was loaded successfully (optional)
        if (backgroundLabel != null) {
            explorationPanel.add(backgroundLabel, BorderLayout.CENTER); // Add to panel
        }

        // Create a label for the planet name with desired formatting
        JLabel explorationLabel = new JLabel("<html><center>" + "" + "</center></html>");
        explorationLabel.setForeground(WHITE);
        explorationLabel.setFont(SERIF_BOLD_FONT);
        explorationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the planet name label on top of the background (optional positioning)
        explorationPanel.add(explorationLabel, BorderLayout.CENTER); // Adjust position as needed

        // Add the explorationPanel to the frame
        Main.frame.add(explorationPanel, BorderLayout.CENTER);

        // Revalidate and repaint the frame
        Main.frame.revalidate();
        Main.frame.repaint();
    }

    public void showDialogue(JTextArea storyTextArea, int dialogueIndex) {
        String storyText = narration.getDialogue(dialogueIndex);

        Timer timer = new Timer(50, new ActionListener() {
            int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < storyText.length()) {
                    storyTextArea.append(String.valueOf(storyText.charAt(charIndex)));
                    charIndex++;
                    storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
                }
            }
        });
        timer.start();
    }

    public void showDialogue(JTextArea storyTextArea, int dialogueIndex, Runnable onComplete) {
        String storyText = narration.getDialogue(dialogueIndex);

        Timer timer = new Timer(50, new ActionListener() {
            int charIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex < storyText.length()) {
                    storyTextArea.append(String.valueOf(storyText.charAt(charIndex)));
                    charIndex++;
                    storyTextArea.setCaretPosition(storyTextArea.getDocument().getLength());
                }
            }
        });
        timer.start();
    }

    public void displayChoices(JButton... buttons) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        buttonPanel.setOpaque(false);
        buttonPanel.setSize(1280,800 );
        buttonPanel.setBounds(0, 600, 1280, 800); // Adjust x, y, width, and height as needed

        buttonPanel.setBorder(new EmptyBorder(10, 10, 30, 10)); // Top, Left, Bottom, Right margins

        for (JButton button : buttons) {
            button.setFont(SERIF_BOLD_FONT);
            button.setForeground(WHITE);
            button.setBackground(SOFT_YELLOW);
            button.setBorder(createButtonBorder());
            button.setFocusPainted(false);
            button.setOpaque(true);

            // Add a hover effect
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent evt) {
                    button.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
                }

                @Override
                public void mouseExited(MouseEvent evt) {
                    button.setBackground(SOFT_YELLOW); // Reset to original color
                }
            });
            // Add the button to the panel
            buttonPanel.add(button);
        }

        // Print size and location for debugging (optional)
        System.out.println("Button Panel Size: " + buttonPanel.getSize());
        System.out.println("Button Panel Location: " + buttonPanel.getLocation());
        // Get the layeredPane from typeWriterEffect (assuming it's accessible)
        JLayeredPane layeredPane = getLayeredPane();
        // Add the buttonPanel to the south region

        // Add the buttonPanel to the layeredPane with a higher layer (e.g., 2)
        layeredPane.add(buttonPanel, Integer.valueOf(2));

        // Revalidate and repaint the layeredPane
        layeredPane.revalidate();
        layeredPane.repaint();
    }

    public void displayPlanetName(String planetNamePath) {
        // Clear the content pane
        Main.frame.getContentPane().removeAll();

        // Create a JPanel for the exploration area
        JPanel explorationPanel = new JPanel();
        explorationPanel.setLayout(new BorderLayout()); // Optional layout manager

        // Load the background image using initializeBackgroundNarration
        JLabel backgroundLabel = initializeBackgroundNarration(planetNamePath);

        // Check if background was loaded successfully (optional)
        if (backgroundLabel != null) {
            explorationPanel.add(backgroundLabel, BorderLayout.CENTER); // Add to panel
        }

        // Create a label for the planet name with desired formatting
        JLabel explorationLabel = new JLabel("<html><center>" + "" + "</center></html>");
        explorationLabel.setForeground(WHITE);
        explorationLabel.setFont(SERIF_BOLD_FONT);
        explorationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the planet name label on top of the background (optional positioning)
        explorationPanel.add(explorationLabel, BorderLayout.CENTER); // Adjust position as needed

        // Add the explorationPanel to the frame
        Main.frame.add(explorationPanel, BorderLayout.CENTER);

        // Revalidate and repaint the frame
        Main.frame.revalidate();
        Main.frame.repaint();
    }

    public JButton createButton(String text) {
        JButton button = new JButton(text);
        
        button.setFont(SERIF_BOLD_FONT);
        button.setForeground(WHITE);
        button.setBackground(SOFT_YELLOW);
        button.setBorder(createButtonBorder());
        button.setFocusPainted(false);
        button.setOpaque(true);
        return button;
    }

    public Border createButtonBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 245, 0), 2), // Yellow border
                new EmptyBorder(10, 20, 10, 20) // Padding inside the button
        );
    }

    public JLabel initializeBackgroundNarration(String imagePath) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        byte[] imageData;
        try (InputStream inputStream = classLoader.getResourceAsStream(imagePath)) {
            if (inputStream == null) {
                System.err.println("GIF not found at images/gameplay bg.png");
                return null;
            }

            // Read image data into a byte array
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            imageData = buffer.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Create the GIF image from the byte array without scaling
        Image gifImage = Toolkit.getDefaultToolkit().createImage(imageData);
        ImageIcon gifIcon = new ImageIcon(gifImage);

        // Create a JLabel to display the GIF and set it as the background
        JLabel backgroundLabel = new JLabel(gifIcon);
        backgroundLabel.setBounds(0, 0, 1280, 800);

        return backgroundLabel;
    }

    public void dialogue(JTextArea storyTextArea, String dialogueText, int index) {
        Timer dialogueTimer = new Timer(DELAY, new ActionListener() {
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

    @Override
    public void showNextDialogue(JTextArea storyTextArea) {
        if (dialogueIndex < narration.getDialogueCount() - 1) {
            dialogueIndex++; // Move to the next dialogue
            String text = narration.getDialogue(dialogueIndex);
            dialogue(storyTextArea, text, dialogueIndex);
        } else {
            storyTextArea.append("\nEnd of dialogue."); // Optional: notify end
        }
    }

    // @Override
    // public void displayDialogue(int startIndex, int endIndex, String[] speakerNames, Runnable onDialogueComplete) {
    //     Main.frame.getContentPane().removeAll(); // Remove previous content     
    //     // Check if dialogue index is within range
    //     if (dialogueIndex < startIndex || dialogueIndex >= endIndex) {
    //         System.out.println(dialogueIndex + " " + startIndex + " " + endIndex);
    //         throw new IndexOutOfBoundsException("Dialogue index out of range.");
    //     }

    //     JPanel dialoguePanel = new JPanel();
    //     dialoguePanel.setLayout(new BorderLayout());
    //     dialoguePanel.setBackground(new Color(30, 30, 30)); // Darker background

    //     String currentSpeakerName = speakerNames[dialogueIndex % speakerNames.length];
    //     JLabel speakerLabel = new JLabel(currentSpeakerName);
    //     speakerLabel.setForeground(Color.YELLOW);
    //     speakerLabel.setFont(SERIF_BOLD_FONT);
    //     speakerLabel.setHorizontalAlignment(SwingConstants.LEFT);
    //     dialoguePanel.add(speakerLabel, BorderLayout.NORTH);

    //     JTextArea dialogueTextArea = new JTextArea();
    //     dialogueTextArea.setForeground(WHITE);
    //     dialogueTextArea.setFont(MONOSPACED_FONT);
    //     dialogueTextArea.setLineWrap(true);
    //     dialogueTextArea.setWrapStyleWord(true);
    //     dialogueTextArea.setEditable(false);
    //     dialogueTextArea.setOpaque(false);

    //     dialogueTextArea.setMargin(new Insets(20, 20, 20, 20));

    //     dialoguePanel.add(dialogueTextArea, BorderLayout.CENTER);

    //     JButton nextButton = createButton("Next");
    //     nextButton.addActionListener(e -> {
    //         dialogueIndex++;
    //         if (dialogueIndex < endIndex) {
    //             dialogueTextArea.setText("");
    //             displayDialogue(startIndex, endIndex, speakerNames, onDialogueComplete); // Recursive call for next dialogue
    //         } else {
    //             System.out.println("""
    //                         End of dialogues.
    //                         last dialogueIndex is: """ + dialogueIndex); // Handle end of dialogue
    //             onDialogueComplete.run(); // Call the callback after end
    //         }
    //     });

    //     dialoguePanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom

    //     Main.frame.getContentPane().add(dialoguePanel, BorderLayout.SOUTH); // Position the dialogue panel at the bottom
    //     Main.frame.revalidate();
    //     Main.frame.repaint();

    //     String dialogue = narration.getDialogue(dialogueIndex); // Assuming Narration class provides dialogue text

    //     typeWriterEffect(dialogueTextArea, dialogue); // Implement your typewriter effect here (not shown)
    // }

    @Override
    public void displayDialogue(int startIndex, int endIndex, String[] speakerNames, Runnable onDialogueComplete) {
        Main.frame.getContentPane().removeAll(); // Remove previous content

        if (dialogueIndex < startIndex || dialogueIndex >= endIndex) {
            System.out.println(dialogueIndex + " " + startIndex + " " + endIndex);
            throw new IndexOutOfBoundsException("Dialogue index out of range.");
        }

        // Panel to hold both animation and dialogue
        JPanel animationAndDialoguePanel = new JPanel(new BorderLayout());

        // Initialize animation components
        JLabel animationLabel = null;
        AnimationManager animationManager = new AnimationManager();
        String animationPath = animationManager.getAnimationPath(dialogueIndex);
        if (animationPath != null) {
            animationLabel = initializeBackgroundNarration(animationPath);
            if (animationLabel != null) {
                animationAndDialoguePanel.add(animationLabel, BorderLayout.CENTER);
            } else {
                System.err.println("Error loading animation: " + animationPath);
            }
        }

        // Dialogue section within the animationAndDialoguePanel
        JPanel dialoguePanel = new JPanel(new BorderLayout());
        dialoguePanel.setBackground(new Color(30, 30, 30)); // Darker background

        String currentSpeakerName = speakerNames[dialogueIndex % speakerNames.length];
        JLabel speakerLabel = new JLabel(currentSpeakerName);
        speakerLabel.setForeground(Color.YELLOW);
        speakerLabel.setFont(SERIF_BOLD_FONT);
        speakerLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dialoguePanel.add(speakerLabel, BorderLayout.NORTH);

        JTextArea dialogueTextArea = new JTextArea();
        dialogueTextArea.setForeground(Color.WHITE);
        dialogueTextArea.setFont(MONOSPACED_FONT);
        dialogueTextArea.setLineWrap(true);
        dialogueTextArea.setWrapStyleWord(true);
        dialogueTextArea.setEditable(false);
        dialogueTextArea.setOpaque(false);
        dialogueTextArea.setMargin(new Insets(20, 20, 20, 20));

        dialoguePanel.add(dialogueTextArea, BorderLayout.CENTER);

        JButton nextButton = createButton("Next");
        nextButton.addActionListener(e -> {
            dialogueIndex++;
            if (dialogueIndex < endIndex) {
                dialogueTextArea.setText("");
                displayDialogue(startIndex, endIndex, speakerNames, onDialogueComplete); // Recursive call for next dialogue
            } else {
                System.out.println("End of dialogues. Last dialogueIndex is: " + dialogueIndex);
                onDialogueComplete.run(); // Call the callback after end
            }
        });

        dialoguePanel.add(nextButton, BorderLayout.SOUTH); // Add Next button at the bottom

        // Add the dialoguePanel within animationAndDialoguePanel
        animationAndDialoguePanel.add(dialoguePanel, BorderLayout.SOUTH);

        // Add animationAndDialoguePanel to the frame
        Main.frame.getContentPane().add(animationAndDialoguePanel, BorderLayout.SOUTH); // Adjust position as needed
        Main.frame.revalidate();
        Main.frame.repaint();

        // Typewriter effect
        String dialogue = narration.getDialogue(dialogueIndex); // Assuming Narration class provides dialogue text
        typeWriterEffect(dialogueTextArea, dialogue); // Implement your typewriter effect here (not shown)
    }


    @Override
    public void typeWriterEffect(String text, int delay) {
        typeWriterEffect(text, delay, null);
    }

    //used in displayDialogue()
    public void typeWriterEffect(JTextArea textArea, String text) {
        new Thread(() -> {
            for (char c : text.toCharArray()) {
                textArea.append(String.valueOf(c));
                try {
                    Thread.sleep(DELAY); // Adjust speed (in milliseconds) of the typing effect
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void typeWriterEffect(String text, int delay, Runnable onComplete) {

        layeredPane.removeAll();

        // Load the background image (assuming a separate method exists)
        JLabel backgroundLabel = initializeBackgroundNarration("images/narration_bg3.png");
        if (backgroundLabel != null) {
            layeredPane.add(backgroundLabel, Integer.valueOf(0));
        }


        // Create a narration panel with GridBagLayout
        JPanel narrationPanel = new JPanel(new GridBagLayout());
        narrationPanel.setOpaque(false); // Transparent to show GIF background
        narrationPanel.setBounds(0, 0, 1280, 800);

        // Set constraints for background image (adjust size as needed)
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.fill = GridBagConstraints.NONE;

        // Create and style the JTextArea for narration text
        JTextArea narrationArea = new JTextArea();
        narrationArea.setOpaque(false);
        narrationArea.setForeground(Color.BLACK);
        narrationArea.setFont(MONOSPACED_FONT);
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setEditable(false);
        narrationArea.setMargin(new Insets(125, 10, 0, 10));
        narrationArea.setPreferredSize(new Dimension(800, 400));
        narrationPanel.add(narrationArea, gbc);

        // Timer to update the text character by character
        Timer typingTimer = new Timer(delay, new ActionListener() {
            int charIndex = 0;
            StringBuilder currentText = new StringBuilder();

            @Override
            public void actionPerformed(ActionEvent e) {
                // Add the next character to the text
                if (charIndex < text.length()) {
                    currentText.append(text.charAt(charIndex));
                    narrationArea.setText(currentText.toString()); // Update text content
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

        // Use JLayeredPane for layering
        layeredPane.add(narrationPanel, Integer.valueOf(1)); // narrationPanel at layer 1

        // Clear previous content and display the layeredPane
        Main.frame.getContentPane().removeAll();
        Main.frame.add(layeredPane);
        Main.frame.revalidate();
        Main.frame.repaint();

    }

    public void displayText(String text) {

        // Create a narration panel with background color and GridBagLayout
        JPanel narrationPanel = new JPanel(new GridBagLayout());
        narrationPanel.setBackground(BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center the component
        gbc.fill = GridBagConstraints.NONE;

        // Create and style the JTextArea for narration text
        JTextArea narrationArea = new JTextArea();
        narrationArea.setForeground(WHITE);
        narrationArea.setFont(MONOSPACED_FONT);
        narrationArea.setLineWrap(true);
        narrationArea.setWrapStyleWord(true);
        narrationArea.setEditable(false);
        narrationArea.setOpaque(false);
        narrationArea.setMargin(new Insets(125, 10, 0, 10));
        narrationArea.setPreferredSize(new Dimension(800, 400));

        // Add narration area to the panel using GridBagConstraints
        narrationPanel.add(narrationArea, gbc);

        // Clear previous content and display the narration panel
        Main.frame.getContentPane().removeAll();
        Main.frame.add(narrationPanel, BorderLayout.CENTER);
        Main.frame.revalidate();
        Main.frame.repaint();
    }

    public String getBackgroundImagePath(Playable player, Base enemy) {
        //for fox
        if (player.getName().equals("Fox")) {
            if(enemy.getName().equals("Baobab")){
                return "images/fox_vs_baobab.png";
            } else if (enemy.getName().equals("Snake")){
                return "images/fox_vs_snake.png";
            } else if (enemy.getName().equals("Weeping Rose")){
                return "images/fox_vs_weeping_rose.png";
            } else if (enemy.getName().equals("Rogue Fox")){
                return "images/fox_vs_rogue_fox.png";
            } else if (enemy.getName().equals("The Vain Main")){
                return "images/fox_vs_vain_main.png";
            } else if (enemy.getName().equals("The Drunkard")){
                return "images/fox_vs_drunkard.png";
            } else if (enemy.getName().equals("The King")){
                return "images/fox_vs_king.png";
            }
        }
        //for lamplighter
        else if (player.getName().equals("Lamplighter")) {
            if (enemy.getName().equals("Baobab")) {
                return "images/lamplighter_vs_baobab.png";
            } else if (enemy.getName().equals("Snake")) {
                return "images/lamplighter_vs_snake.png";
            } else if (enemy.getName().equals("Weeping Rose")) {
                return "images/lamplighter_vs_weeping_rose.png";
            } else if (enemy.getName().equals("Rogue Fox")) {
                return "images/lamplighter_vs_rogue_fox.png";
            } else if (enemy.getName().equals("The Vain Main")) {
                return "images/lamplighter_vs_vain_main.png";
            } else if (enemy.getName().equals("The Drunkard")) {
                return "images/lamplighter_vs_drunkard.png";
            } else if (enemy.getName().equals("The King")) {
                return "images/lamplighter_vs_king.png";
            }
        }
        //for mainchar
        else {
            if (enemy.getName().equals("Baobab")) {
                return "images/mainChar_vs_baobab.png";
            } else if (enemy.getName().equals("Snake")) {
                return "images/mainChar_vs_snake.png";
            } else if (enemy.getName().equals("Weeping Rose")) {
                return "images/mainChar_vs_weeping_rose.png";
            } else if (enemy.getName().equals("Rogue Fox")) {
                return "images/mainChar_vs_rogue_fox.png";
            } else if (enemy.getName().equals("The Vain Main")) {
                return "images/mainChar_vs_vain_main.png";
            } else if (enemy.getName().equals("The Drunkard")) {
                return "images/mainChar_vs_drunkard.png";
            } else if (enemy.getName().equals("The King")) {
                return "images/mainChar_vs_king.png";
            }
        }
        return "default_background.png";
    }


    public void pause() {
        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public int getDialogueIndex() {
        return dialogueIndex;
    }

    public void setDialogueIndex(int dialogueIndex) {
        this.dialogueIndex = dialogueIndex;
    }

    public JLayeredPane getLayeredPane(){
        return layeredPane;
    }
}
