
import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class StartGame {

    final private JFrame frame;
    private JTextArea dialogueArea;
    private JLabel imageLabel;
    private JLabel playerStatusLabel;

    private JButton exploreButton;
    private JButton checkStatusButton;
    private JButton checkInventoryButton;
    
    // Action buttons for combat and chest opening
    private JButton attackButton;
    private JButton openChestButton;
    private JButton bossButton;  // Button to enter boss room
    private JButton skill1Button; // Rose Petal Strike
    private JButton skill2Button; // Rose's Embrace
    private JButton skill3Button; // Fox's Trick
    private JButton healthPotionButton; // Use Health Potion
    private JButton manaPotionButton; // Use Mana Potion

    private MainChar player;
    private Random random;
    private Inventory inventory;
    private ThoughtLibrary thoughtLibrary;
    private boolean inCombat = false;  // Track if the player is in combat
    private Enemy currentEnemy;  // Track the current enemy in combat

    public StartGame(JFrame frame) {
        this.frame = frame;
        initializeComponents();

    }

    private void initializeComponents() {
        // Initialize the dialogueArea with appropriate size and properties
        dialogueArea = new JTextArea(10, 30);  // Example size (10 rows, 30 columns)
        dialogueArea.setEditable(false);       // Set to read-only

        // Optionally wrap the text area in a scroll pane if needed
        JScrollPane scrollPane = new JScrollPane(dialogueArea);

        // Add the scrollPane (with the dialogueArea) to the frame's content pane
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);  // Adjust layout if needed
        frame.revalidate();  // Revalidate the frame to update its components
        frame.repaint();     // Repaint to ensure it displays properly
    }
    
    public void startGame() {
        // Clear the frame's content and repaint
        frame.getContentPane().removeAll();
        frame.repaint();  // Reflect the changes immediately

        // Initialize player with user input for name
        String playerName = JOptionPane.showInputDialog("What should I call you, little one?");
        player = new MainChar(playerName);

        // Initialize the player status label and add it to the frame
        playerStatusLabel = new JLabel();
        frame.add(playerStatusLabel, BorderLayout.NORTH);
        updatePlayerStatus();  // Update the status label with the player's details

        // Initialize the thought library
        thoughtLibrary = new ThoughtLibrary();

        // Set up the image label for displaying GIFs or images
        imageLabel = new JLabel(new ImageIcon("path_to_your_image_or_gif.gif"));  // Replace with an actual path to an image or gif
        frame.add(imageLabel, BorderLayout.CENTER);  // Position in the center

        // Create the button panel for interaction
        JPanel buttonPanel = new JPanel();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Style for buttons
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonBackground = new Color(60, 63, 65);  // Dark background
        Color buttonForeground = new Color(187, 187, 187);  // Light text color

        // Add main buttons for exploring, checking status, and inventory
        exploreButton = new JButton("Explore");
        checkStatusButton = new JButton("Check Status");
        checkInventoryButton = new JButton("Check Inventory");

        styleButton(exploreButton, new Font("Serif", Font.PLAIN, 18), new Color(204, 229, 255), new Color(50, 50, 50));
        styleButton(checkStatusButton, new Font("Serif", Font.PLAIN, 18), new Color(229, 204, 255), new Color(50, 50, 50));
        styleButton(checkInventoryButton, new Font("Serif", Font.PLAIN, 18), new Color(255, 229, 204), new Color(50, 50, 50));

        // Add buttons to the panel
        buttonPanel.add(exploreButton);
        buttonPanel.add(checkStatusButton);
        buttonPanel.add(checkInventoryButton);

        // Initialize action buttons (combat and items), setting them initially to hidden
        attackButton = new JButton("Attack");
        openChestButton = new JButton("Open Chest");
        bossButton = new JButton("Enter Boss Room");  // Button to access boss fight
        skill1Button = new JButton(player.nameSkill1());
        skill2Button = new JButton(player.nameSkill2());
        skill3Button = new JButton(player.nameUltimate());
        healthPotionButton = new JButton("Use Health Potion");
        manaPotionButton = new JButton("Use Mana Potion");

        // Style the combat and item buttons
        styleButton(openChestButton, buttonFont, buttonBackground, buttonForeground);
        styleButton(bossButton, buttonFont, buttonBackground, buttonForeground);
        styleButton(skill1Button, buttonFont, buttonBackground, buttonForeground);
        styleButton(skill2Button, buttonFont, buttonBackground, buttonForeground);
        styleButton(skill3Button, buttonFont, buttonBackground, buttonForeground);
        styleButton(healthPotionButton, buttonFont, buttonBackground, buttonForeground);
        styleButton(manaPotionButton, buttonFont, buttonBackground, buttonForeground);

        // Set initial visibility to false (hidden)
        openChestButton.setVisible(false);
        bossButton.setVisible(false);
        skill1Button.setVisible(false);
        skill2Button.setVisible(false);
        skill3Button.setVisible(false);
        healthPotionButton.setVisible(false);
        manaPotionButton.setVisible(false);

        // Add action buttons to the panel
        buttonPanel.add(openChestButton);
        buttonPanel.add(bossButton);  // Add boss room button
        buttonPanel.add(skill1Button);
        buttonPanel.add(skill2Button);
        buttonPanel.add(skill3Button);
        buttonPanel.add(healthPotionButton);
        buttonPanel.add(manaPotionButton);

        // Set up random event generator
        random = new Random();

        // Add action listeners for exploration and skills
        exploreButton.addActionListener(e -> {
            if (inCombat) {
                dialogueArea.setText("You're in combat! You cannot explore right now.");
            } else {
                triggerRandomEvent();  // Initiates a random event (exploration or encounter)
            }
        });

        // Add action listener for skill 1 (Rose Petal Strike)
        skill1Button.addActionListener(e -> {
            if (currentEnemy != null) {  // Check if there is a current enemy
                if (player.chooseSkill1(currentEnemy)) {
                    dialogueArea.setText(player.getName() + " used " + player.nameSkill1());
                } else {
                    dialogueArea.setText("Failed to use " + player.nameSkill1());
                }
                updatePlayerStatus();
            }
        });

        // Add action listener for skill 2 (Rose's Embrace)
        skill2Button.addActionListener(e -> {
            if (player.chooseSkill2()) {
                dialogueArea.setText(player.getName() + " used " + player.nameSkill2());
            } else {
                dialogueArea.setText("Failed to use " +player.nameSkill2());
            }
            updatePlayerStatus();
        });

        // Add action listener for skill 3 (Fox's Trick)
        skill3Button.addActionListener(e -> {
            if (currentEnemy != null) {
                if (player.chooseUltimate(currentEnemy)) {
                    dialogueArea.setText(player.getName() + " used " + player.nameUltimate());
                } else {
                    dialogueArea.setText("Failed to use "  + player.nameUltimate());
                }
                updatePlayerStatus();
            }
        });

        // Add action listener for exploration
        exploreButton.addActionListener(e -> {
            if (inCombat) {
                dialogueArea.setText("You're in combat! You cannot explore right now.");
            } else {
                triggerRandomEvent();  // Initiates a random event (exploration or encounter)
            }
        });

        // Add action listener for checking inventory
// Add action listener for checking inventory
        checkInventoryButton.addActionListener(e -> {
            inventory.showInventory();  // Directly show the inventory
        });


// Add action listener for shop
        JButton shopButton = new JButton("Shop");
        styleButton(shopButton, new Font("Serif", Font.PLAIN, 18), new Color(255, 229, 204), new Color(50, 50, 50));
        buttonPanel.add(shopButton);

        shopButton.addActionListener(e -> {
            dialogueArea.setText("You found a shop! What would you like to buy?");
            // Open shop dialog or list of items available for purchase
            // Example:
            String[] shopItems = {"Health Potion - 10 petals", "Mana Potion - 15 petals", "Upgrade - 50 petals"};
            String selectedItem = (String) JOptionPane.showInputDialog(
                    frame,
                    "Choose an item:",
                    "Shop",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    shopItems,
                    shopItems[0]);

            if (selectedItem != null) {
                switch (selectedItem) {
                    case "Health Potion - 10 petals":
                        if (player.getPetals() >= 10) {
                            inventory.addHealthPotion();
                            player.removePetals(10);
                            dialogueArea.setText("You bought a Health Potion!");
                        } else {
                            dialogueArea.setText("You don't have enough petals for this.");
                        }
                        break;
                    case "Mana Potion - 15 petals":
                        if (player.getPetals() >= 15) {
                            inventory.addManaPotion();
                            player.removePetals(15);
                            dialogueArea.setText("You bought a Mana Potion!");
                        } else {
                            dialogueArea.setText("You don't have enough petals for this.");
                        }
                        break;
                    case "Upgrade - 50 petals":
                        if (player.getPetals() >= 50) {
                            player.upgradeStats(player.maxMana);
                            player.removePetals(50);
                            dialogueArea.setText("You upgraded your stats!");
                        } else {
                            dialogueArea.setText("You don't have enough petals for this.");
                        }
                        break;
                    default:
                        dialogueArea.setText("Shop closed.");
                        break;
                }
                updatePlayerStatus();  // Update status after buying items
            }
        });

        // Ensure the frame is refreshed and the UI components are displayed properly
        frame.revalidate();
        frame.repaint();
    }

// Method to apply consistent styles to buttons
private void styleButton(JButton button, Font font, Color bgColor, Color fgColor) {
        button.setFont(new Font("Serif", Font.PLAIN, 18));  // Elegant font, 18pt
        button.setBackground(new Color(204, 229, 255));     // Soft sky blue background
        button.setForeground(new Color(50, 50, 50));        // Dark gray text color
        button.setFocusPainted(false);                      // Remove default focus border
        button.setBorder(BorderFactory.createLineBorder(new Color(153, 204, 255), 2));  // Light border matching background
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));   // Change cursor to hand when hovering

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(153, 204, 255));  // Slightly darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(204, 229, 255));  // Restore original color
            }
        });
    }


    // Randomized event system
    private void triggerRandomEvent() {
        int event = random.nextInt(100);  // Generates a number between 0 and 99

        if (event < 40) {  // 40% chance of combat encounter
            startCombatEvent();
        } else if (event < 70) {  // 30% chance of finding a chest
            findTreasureChest();
        } else {  // 30% chance of no event, trigger inner dialogue
            triggerInnerDialogue();
        }
    }

    private void startCombatEvent() {
        inCombat = true;

        String[] enemyStrings = {"Baobab", "Snake", "Lamplighter"};

        Random random = new Random();
        String selectedEnemyName = enemyStrings[random.nextInt(enemyStrings.length)];

        currentEnemy = new Enemy(selectedEnemyName, 100, 100, "A wild " + selectedEnemyName + " appears", 50);

        dialogueArea.setText("A wild " + currentEnemy.getName() + " appears!");

        // Show skill buttons
        skill1Button.setVisible(true);
        skill2Button.setVisible(true);
        skill3Button.setVisible(true);

        // Show potion buttons
        healthPotionButton.setVisible(true);
        manaPotionButton.setVisible(true);

        // Update player status
        updatePlayerStatus();


        if(player.gameOver(player, currentEnemy)){
            endCombat();
        }
    }

    private void findTreasureChest() {
        // Treasure chest logic here
        dialogueArea.setText("You found a treasure chest!");
        openChestButton.setVisible(true);
    }

    private void triggerInnerDialogue() {
        // Inner dialogue logic here
        String thought = thoughtLibrary.getRandomThought();
        dialogueArea.setText(thought);
    }
    
    private void endCombat() {
        inCombat = false;
        currentEnemy = null;

        skill1Button.setVisible(false);
        skill2Button.setVisible(false);
        skill3Button.setVisible(false);
        healthPotionButton.setVisible(false);
        manaPotionButton.setVisible(false);

        updatePlayerStatus();
    }

    
    private void updatePlayerStatus() {
        if (playerStatusLabel == null) {
            playerStatusLabel = new JLabel();
            frame.add(playerStatusLabel, BorderLayout.NORTH);
        }

        String status = String.format("<html>HP: %d/%d<br>MP: %d/%d<br>LVL: %d</html>",
                player.getCurrHp(), player.getMaxHp(),
                player.getCurrMana(), player.getMaxMana(),
                player.getCurrLevel());

        playerStatusLabel.setText(status);
        playerStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        playerStatusLabel.setVerticalAlignment(SwingConstants.TOP);
    }
}
