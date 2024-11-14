
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.util.Random;
// import javax.swing.*;

// public class StartGame {

//     final private JFrame frame;
//     private JTextArea dialogueArea;
//     private JLabel imageLabel;
//     private JLabel playerStatusLabel;

//     private JButton exploreButton;
//     private JButton checkStatusButton;
//     private JButton checkInventoryButton;
//     private JButton openChestButton;
//     private JButton bossButton;  // Button to enter boss room
//     private JButton skill1Button; // Rose Petal Strike
//     private JButton skill2Button; // Rose's Embrace
//     private JButton skill3Button; // Fox's Trick
//     private JButton healthPotionButton; // Use Health Potion
//     private JButton manaPotionButton; // Use Mana Potion
//     private JButton shopButton;

//     private MainChar player;
//     private Random random;
//     private Inventory inventory;
//     private ThoughtLibrary thoughtLibrary;
//     private boolean inCombat = false;
//     private Enemy currentEnemy;

//     public StartGame(JFrame frame) {
//         this.frame = frame;
//         initializeComponents();
//     }

//     private void initializeComponents() {
//         dialogueArea = new JTextArea(10, 30);  // Example size (10 rows, 30 columns)
//         dialogueArea.setEditable(false);       // Set to read-only

//         JScrollPane scrollPane = new JScrollPane(dialogueArea);

//         frame.getContentPane().add(scrollPane, BorderLayout.CENTER);  // Adjust layout if needed
//         frame.revalidate();  
//         frame.repaint();    
//     }

//     public void startGame() {
//         frame.getContentPane().removeAll();
//         frame.repaint();  

//         String playerName = JOptionPane.showInputDialog("What should I call you, little one?");
//         player = new MainChar(playerName);

//         playerStatusLabel = new JLabel();
//         frame.add(playerStatusLabel, BorderLayout.NORTH);
//         updatePlayerStatus();  // Update the status label with the player's details

//         // Initialize the thought library
//         thoughtLibrary = new ThoughtLibrary();

//         // Set up the image label for displaying GIFs or images
//         imageLabel = new JLabel(new ImageIcon("images/explore_box.png"));  // Replace with an actual path to an image or gif
//         frame.add(imageLabel, BorderLayout.CENTER);  // Position in the center

//         // Create the button panel for interaction
//         JPanel buttonPanel = new JPanel();
//         frame.add(buttonPanel, BorderLayout.SOUTH);

//         // Style for buttons
//         Font buttonFont = new Font("Arial", Font.BOLD, 16);
//         Color buttonBackground = new Color(60, 63, 65);  // Dark background
//         Color buttonForeground = new Color(187, 187, 187);  // Light text color

//         // Add main buttons for exploring, checking status, and inventory
//         exploreButton = new JButton("Explore");
//         checkStatusButton = new JButton("Check Status");
//         checkInventoryButton = new JButton("Check Inventory");

//         styleButton(exploreButton, new Font("Serif", Font.PLAIN, 18), new Color(204, 229, 255), new Color(50, 50, 50));
//         styleButton(checkStatusButton, new Font("Serif", Font.PLAIN, 18), new Color(229, 204, 255), new Color(50, 50, 50));
//         styleButton(checkInventoryButton, new Font("Serif", Font.PLAIN, 18), new Color(255, 229, 204), new Color(50, 50, 50));

//         // Add buttons to the panel
//         buttonPanel.add(exploreButton);
//         buttonPanel.add(checkStatusButton);
//         buttonPanel.add(checkInventoryButton);

//         // Initialize action buttons (combat and items), setting them initially to hidden
//         openChestButton = new JButton("Open Chest");
//         bossButton = new JButton("Enter Boss Room");  // Button to access boss fight
//         skill1Button = new JButton(player.nameSkill1());
//         skill2Button = new JButton(player.nameSkill2());
//         skill3Button = new JButton(player.nameUltimate());
//         healthPotionButton = new JButton("Use Health Potion");
//         manaPotionButton = new JButton("Use Mana Potion");
//         shopButton = new JButton("Shop");

//         // Style the combat and item buttons
//         styleButton(openChestButton, buttonFont, buttonBackground, buttonForeground);
//         styleButton(bossButton, buttonFont, buttonBackground, buttonForeground);
//         styleButton(skill1Button, buttonFont, buttonBackground, buttonForeground);
//         styleButton(skill2Button, buttonFont, buttonBackground, buttonForeground);
//         styleButton(skill3Button, buttonFont, buttonBackground, buttonForeground);
//         styleButton(healthPotionButton, buttonFont, buttonBackground, buttonForeground);
//         styleButton(manaPotionButton, buttonFont, buttonBackground, buttonForeground);
//         styleButton(shopButton, buttonFont, buttonBackground, buttonForeground);

//         // Set initial visibility to false (hidden)
//         openChestButton.setVisible(false);
//         bossButton.setVisible(false);
//         skill1Button.setVisible(false);
//         skill2Button.setVisible(false);
//         skill3Button.setVisible(false);
//         healthPotionButton.setVisible(false);
//         manaPotionButton.setVisible(false);

//         buttonPanel.add(openChestButton);
//         buttonPanel.add(bossButton);  // Add boss room button
//         buttonPanel.add(skill1Button);
//         buttonPanel.add(skill2Button);
//         buttonPanel.add(skill3Button);
//         buttonPanel.add(healthPotionButton);
//         buttonPanel.add(manaPotionButton);
//         buttonPanel.add(shopButton);

//         random = new Random();

//         exploreButton.addActionListener(e -> {
//             if (inCombat) {
//                 dialogueArea.setText("You're in combat! You cannot explore right now.");
//             } else {
//                 triggerRandomEvent();  // Initiates a random event (exploration or encounter)
//             }
//         });

//         checkInventoryButton.addActionListener(e -> {
//             inventory.showInventory();  // Directly show the inventory
//         });

       
//     }

    

//     private void styleButton(JButton button, Font font, Color bgColor, Color fgColor) {
//         button.setFont(new Font("Serif", Font.PLAIN, 18));  // Elegant font, 18pt
//         button.setBackground(new Color(204, 229, 255));     // Soft sky blue background
//         button.setForeground(new Color(50, 50, 50));        // Dark gray text color
//         button.setFocusPainted(false);                      // Remove default focus border
//         button.setBorder(BorderFactory.createLineBorder(new Color(153, 204, 255), 2));  // Light border matching background
//         button.setCursor(new Cursor(Cursor.HAND_CURSOR));   // Change cursor to hand when hovering

//         // Hover effect
//         button.addMouseListener(new java.awt.event.MouseAdapter() {
//             public void mouseEntered(java.awt.event.MouseEvent evt) {
//                 button.setBackground(new Color(153, 204, 255));  // Slightly darker blue on hover
//             }

//             public void mouseExited(java.awt.event.MouseEvent evt) {
//                 button.setBackground(new Color(204, 229, 255));  // Restore original color
//             }
//         });
//     }

//     // Move startBattle outside the action listeners or other methods
//     public void startBattle(MainChar player, Base enemy) {
//         dialogueArea.setText("Battle started: " + player.getName() + " vs " + enemy.getName() + "\n");

//         // Skill 1 button logic
//         skill1Button.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (player.chooseSkill1(enemy)) {
//                     dialogueArea.append(player.getName() + " used Skill 1!\n");
//                     displayStats(player, enemy);
//                 }
//             }
//         });

//         // Skill 2 button logic
//         skill2Button.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (player.chooseSkill2(enemy)) {
//                     dialogueArea.append(player.getName() + " used Skill 2!\n");
//                     displayStats(player, enemy);
//                 }
//             }
//         });

//         // Ultimate button logic
//         skill3Button.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (player.chooseUltimate(enemy)) {
//                     dialogueArea.append(player.getName() + " used Ultimate Skill!\n");
//                     displayStats(player, enemy);
//                 }
//             }
//         });

//         // Health Potion button logic
//         healthPotionButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (inventory.hasItem("Health Potion")) {
//                     inventory.useItem("Health Potion", player);
//                     dialogueArea.append(player.getName() + " used a Health Potion and recovered health!\n");
//                     updatePlayerStatus();
//                 } else {
//                     dialogueArea.append("You don't have any Health Potions!\n");
//                 }
//             }
//         });

//         // Mana Potion button logic
//         manaPotionButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 if (inventory.hasItem("Mana Potion")) {
//                     inventory.useItem("Mana Potion", player);
//                     dialogueArea.append(player.getName() + " used a Mana Potion and recovered mana!\n");
//                     updatePlayerStatus();
//                 } else {
//                     dialogueArea.append("You don't have any Mana Potions!\n");
//                 }
//             }
//         });

//         // Simulate enemy's turn (basic example)
//         enemy.attack(player);
//         dialogueArea.append(enemy.getName() + " attacks!\n");
//         displayStats(player, enemy);

//         if (player.getCurrHp() <= 0) {
//             dialogueArea.append(player.getName() + " has been defeated!\n");
//             inCombat = false;
//             resetCombatButtons();
//         } else if (enemy.getCurrHp() <= 0) {
//             dialogueArea.append(enemy.getName() + " has been defeated!\n");
//             inCombat = false;
//             resetCombatButtons();
//         }
//     }

//     private void displayStats(MainChar player, Base enemy) {
//         dialogueArea.append(player.getName() + " HP: " + player.getCurrHp() + ", Mana: " + player.getCurrMana() + "\n");
//         dialogueArea.append(enemy.getName() + " HP: " + enemy.getCurrHp() + "\n");
//     }

//     private void resetCombatButtons() {
//         skill1Button.setVisible(false);
//         skill2Button.setVisible(false);
//         skill3Button.setVisible(false);
//         healthPotionButton.setVisible(false);
//         manaPotionButton.setVisible(false);

//         exploreButton.setEnabled(true);  // Enable exploration after combat
//         checkStatusButton.setEnabled(true);
//         checkInventoryButton.setEnabled(true);
//         shopButton.setEnabled(true);
//     }

//     private void findTreasureChest() {
//         dialogueArea.setText("You found a treasure chest!");
//         openChestButton.setVisible(true);
//     }

//     private void triggerInnerDialogue() {
//         inCombat = false;

//         String thought = thoughtLibrary.getRandomThought();
//         dialogueArea.setText(thought);
//         updateButtonVisibility();
//     }

//     private void startCombatEvent() {
//         inCombat = true;

//         String[] enemyStrings = {"Baobab", "Snake", "Lamplighter"};

//         Random random = new Random();
//         String selectedEnemyName = enemyStrings[random.nextInt(enemyStrings.length)];

//         currentEnemy = new Enemy(selectedEnemyName, 100, 100, "A wild " + selectedEnemyName + " appears", 50);

//         dialogueArea.setText("A wild " + currentEnemy.getName() + " appears!");

//         updateButtonVisibility();
//         updatePlayerStatus();

//         if (player.gameOver(currentEnemy)) {
//             endCombat();
//         }
//     }

//     private void endCombat() {
//         inCombat = false;
//         currentEnemy = null;

//         dialogueArea.setText("Combat ended. You are free to explore again.");
//         updateButtonVisibility();
//         updatePlayerStatus();
//     }


//     private void updatePlayerStatus() {
//         if (playerStatusLabel == null) {
//             playerStatusLabel = new JLabel();
//             frame.add(playerStatusLabel, BorderLayout.NORTH);
//         }

//         String status = String.format("<html>HP: %d/%d<br>MP: %d/%d<br>LVL: %d<br>Petals: %d</html>",
//                 player.getCurrHp(), player.getMaxHp(),
//                 player.getCurrMana(), player.getMaxMana());
//                 // player.getCurrLevel(), player.getPetals());

//         playerStatusLabel.setText(status);
//         playerStatusLabel.setHorizontalAlignment(SwingConstants.LEFT);
//         playerStatusLabel.setVerticalAlignment(SwingConstants.TOP);
//     }


//     private void updateButtonVisibility() {
//         if (inCombat) {
//             exploreButton.setVisible(false);
//             checkStatusButton.setVisible(false);
//             checkInventoryButton.setVisible(false);

//             skill1Button.setVisible(true);
//             skill2Button.setVisible(true);
//             skill3Button.setVisible(true);
//             healthPotionButton.setVisible(true);
//             manaPotionButton.setVisible(true);
//         } else {
//             exploreButton.setVisible(true);
//             checkStatusButton.setVisible(true);
//             checkInventoryButton.setVisible(true);

//             skill1Button.setVisible(false);
//             skill2Button.setVisible(false);
//             skill3Button.setVisible(false);
//             healthPotionButton.setVisible(false);
//             manaPotionButton.setVisible(false);
//         }

//         shopButton.setVisible(!inCombat);

//         frame.revalidate();
//         frame.repaint();
//     }

//     private void triggerRandomEvent() {
//         int event = random.nextInt(100); // Generates a number between 0 and 99

//         if (event < 40) { // 40% chance of combat encounter
//             startCombatEvent();
//             // Start the battle immediately after the combat event is triggered
//             startBattle(player, currentEnemy);
//         } else if (event < 70) { // 30% chance of finding a chest
//             findTreasureChest();
//         } else { // 30% chance of a random thought (exit after display)
//             String randomThought = thoughtLibrary.getRandomThought();
//             displayThoughtWithTypingEffect(randomThought); // New method to display with typing effect
//             System.exit(0); // Exit the program after displaying the thought
//         }
//     }

//     private void displayThoughtWithTypingEffect(String thought) {
//         for (int i = 0; i < thought.length(); i++) {
//             try {
//                 // Simulate typing speed (adjust delay as needed)
//                 Thread.sleep(50);
//             } catch (InterruptedException e) {
//                 e.printStackTrace();
//             }

//             // Append each character to the dialogue area
//             dialogueArea.append(String.valueOf(thought.charAt(i)));
//         }

//         // Add a newline character after the thought
//         dialogueArea.append("\n");
//     }

// }
