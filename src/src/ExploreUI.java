
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.swing.*;

public class ExploreUI extends JFrame {

    static String playerName;

    private final Shop shop;
    private final ThoughtLibrary thoughtLibrary;

    private final Inventory inventory;
    private final ItemManager itemManager = new ItemManager();
    private final GameStageImplementation game = new GameStageImplementation();

    private boolean hasCompletedDialogueForBoss = false;
    private boolean isFoxAcquired = false;

    private JLabel playerNameLabel, playerHealthLabel, playerManaLabel, playerLevelLabel;
    private Random random;
    private BattleUI battleUI;
    private Playable player;
    private Base enemy;

    private boolean hasBossKey = false;

    public ExploreUI(Playable player) {
        this.player = MainChar.getInstance(playerName);
        this.enemy = enemy;
        this.inventory = Inventory.getInstance(); // Access the singleton instance
        random = new Random();
        thoughtLibrary = new ThoughtLibrary(); // Create ThoughtLibrary instance
        shop = new Shop(player); // Create Shop instance
    }

    public void startExplore() {

        System.out.println("Chapter is:" + player.getCurrentChapter());

        Main.frame.getContentPane().removeAll();

        try {
            if (player == null) {
                boolean validName = false;
                while (!validName) {
                    playerName = JOptionPane.showInputDialog("What should I call you, little one?");

                    if (playerName != null && !playerName.isEmpty()) {  // Check if name is not empty
                        player = MainChar.getInstance(playerName);  // If the name is valid, create the player
                        validName = true;  // Exit the loop
                    } else if (playerName != null) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty. Please enter a valid name.");
                    } else {
                        JOptionPane.showMessageDialog(null, "You canceled the input. Using default name.");
                        player = MainChar.getInstance("Knight");  // Assign a default name if input is canceled
                        validName = true;  // Exit the loop after assigning the default name
                    }
                }
            }
        } catch (Exception e) {
            // Handle unexpected errors such as issues with the MainChar constructor or other logic
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
            e.printStackTrace();  // Optionally log the stack trace for debugging purposes
        }

        if (player.getCurrHp() == 0) {
            JOptionPane.showInputDialog("Spend 100 petals to restore health?");
            player.setCurrHp(player.getMaxHp());
        }

        random = new Random();

        JLabel backgroundLabel = initializeBackgroundNarration();
        backgroundLabel.setPreferredSize(new Dimension(1280, 800));
        backgroundLabel.setBounds(0, 0, 1280, 800);
        Main.frame.getContentPane().add(backgroundLabel);

        Font labelFont = new Font("Comic Sans MS", Font.ITALIC, 11); // Playful, storybook-like font
        // Font textAreaFont = new Font("Courier New", Font.PLAIN, 14);
        Font buttonFont = new Font("Papyrus", Font.BOLD, 30); // Papyrus for a unique, handwritten look
        // Font inputFont = new Font("Comic Sans MS", Font.ITALIC, 14);

        playerNameLabel = new JLabel(player.getName());
        playerHealthLabel = new JLabel("Player Health: " + player.getCurrHp() + "/" + player.getMaxHp());
        playerManaLabel = new JLabel("Player Mana: " + player.getCurrMana() + "/" + player.getMaxMana());
        playerLevelLabel = new JLabel("" + player.getCurrLevel());

        playerNameLabel.setFont(labelFont);
        playerHealthLabel.setFont(labelFont);
        playerManaLabel.setFont(labelFont);
        playerLevelLabel.setFont(new Font("Papyrus", Font.BOLD, 12));

        playerNameLabel.setBounds(200, 28, 200, 20);
        playerHealthLabel.setBounds(170, 60, 180, 20);
        playerManaLabel.setBounds(170, 80, 180, 20);
        playerLevelLabel.setBounds(170, 28, 180, 20);

        Main.frame.add(playerNameLabel);
        Main.frame.add(playerHealthLabel);
        Main.frame.add(playerManaLabel);
        Main.frame.add(playerLevelLabel);

        updateStatsDisplay();

        JButton exploreButton = createButton("Explore", buttonFont);
        exploreButton.setPreferredSize(new Dimension(200, 100)); // Set a preferred size for the button
        exploreButton.setBounds(240, 620, 200, 100); // Adjust position and size
        exploreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                triggerRandomEvent();
            }
        });
        backgroundLabel.add(exploreButton);

        JButton inventoryButton = createButton("Inventory", buttonFont);
        inventoryButton.setPreferredSize(new Dimension(200, 100)); // Set a preferred size for the button
        inventoryButton.setBounds(530, 620, 200, 100);
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.showInventory();
            }
        });
        backgroundLabel.add(inventoryButton);

        // Create Shop button
        JButton shopButton = createButton("Shop", buttonFont);
        shopButton.setPreferredSize(new Dimension(200, 100)); // Set a preferred size for the button
        shopButton.setBounds(820, 620, 200, 100);
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.displayShop(player); // Assuming 'player' is a member variable in ExploreUI
            }
        });

        backgroundLabel.add(shopButton);

        Main.frame.getContentPane().add(backgroundLabel);
        Main.frame.revalidate();
        Main.frame.repaint();
        Main.frame.setVisible(true);

    }

    private void updateStatsDisplay() {
        playerHealthLabel.setText("Player Health: " + player.getCurrHp() + "/" + player.getMaxHp());
        playerManaLabel.setText("Player Mana: " + player.getCurrMana() + "/" + player.getMaxMana());
    }

    private JLabel initializeBackgroundNarration() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        byte[] imageData;
        try (InputStream inputStream = classLoader.getResourceAsStream("images/explore_box.png")) {
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

    public void triggerRandomEvent() {
        int event = random.nextInt(100);
        if (event < 40) {
            // enemy = new VainMan();
            // enemy.encounter();
            // Battle encounter
            battleUI = new BattleUI(player, BossType.MOBS, enemy);
            battleUI.startBattle();
            System.out.println("Battle is triggered");
        } else if (event < 70) {
            // Find a chest
            System.out.println("Found a chest!");
            int chestLoot = random.nextInt(5);
            switch (chestLoot) {
                case 0 -> {
                    // Find a Mana Potion with random rarity
                    System.out.println("found a mana");

                    Item manaPotion = itemManager.createManaPotion(itemManager.getRandomSize());
                    if (inventory.addItem(manaPotion)) {
                        JOptionPane.showMessageDialog(null, "You found a " + manaPotion.getItemByName() + "!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your inventory is full!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 1 -> {
                    // Find a Health Potion with random rarity
                    System.out.println("found a healthp");

                    Item healthPotion = itemManager.createHealthPotion(itemManager.getRandomSize());
                    if (inventory.addItem(healthPotion)) {
                        JOptionPane.showMessageDialog(null, "You found a " + healthPotion.getItemByName() + "!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your inventory is full!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 2 -> {
                    // Find a Strength Potion with random rarity
                    System.out.println("found a strengthp");

                    Item strengthPotion = itemManager.createStrengthPotion(itemManager.getRandomRarity());
                    if (inventory.addItem(strengthPotion)) {
                        JOptionPane.showMessageDialog(null, "You found a " + strengthPotion.getItemByName() + "!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your inventory is full!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 3 -> {
                    // Find a Rose
                    System.out.println("found a rose");
                    Item rose = itemManager.getItem("Rose");
                    if (inventory.addItem(rose)) {
                        JOptionPane.showMessageDialog(null, "You found a Rose!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Your inventory is full!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                case 4 -> {
                    // Find petals
                    System.out.println("found a petal");
                    player.addCurrency(random.nextInt(10) + 5);
                    JOptionPane.showMessageDialog(null, "You found " + (random.nextInt(10) + 5) + " petals!", "Chest Loot", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            Timer timer = new Timer(1000, e -> {
                ((Timer) e.getSource()).stop();
            });
            timer.start();
        } else if (event < 80) {
            // Found a key to the boss room (adjust logic to determine boss type)
            System.out.println("Found a key!");
            hasBossKey = true;
            BossType bossType = determineBossType(); // Replace with your logic
            JOptionPane.showMessageDialog(null, "You found a key to the " + bossType.toString() + " boss room!", "Exploration Event", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Random thought
            String randomThought = thoughtLibrary.getRandomThought();
            displayThought(randomThought);
        }

        if (!Fox.getInstance().isFoxAcquired()) {
            if (player.getCurrLevel() >= 30 && player.getCurrentChapter() == 2) {
                Fox.getInstance().foxAcquired();
                game.pause();
                startExplore();
            }
        }
        
        if (hasBossKey) {
            if (hasCompletedDialogueForBoss) {
                System.out.println(hasCompletedDialogueForBoss + " = true");
                startBossBattle();
            } else {
                System.out.println(hasCompletedDialogueForBoss + " = false");
                displayBossDialogue(determineBossType());
                hasCompletedDialogueForBoss = true;

            }
        }

    }

    //used in passing who to attack
    private BossType determineBossType() {
        return switch (player.getCurrentChapter()) {
            case 1 ->
                BossType.VAINMAN;
            case 2 ->
                BossType.DRUNKARD;
            case 3 ->
                BossType.KING;
            default ->
                BossType.MOBS;
        };
    }

    public void displayThought(String thought) {
        JOptionPane.showMessageDialog(null, thoughtLibrary.getRandomThought(), "", JOptionPane.INFORMATION_MESSAGE);
        Timer timer = new Timer(1000, e -> {
            ((Timer) e.getSource()).stop();
        });
        timer.start();
    }

    //implement dialogue before battling
    private void displayBossDialogue(BossType bossType) {
        switch (bossType) {
            case VAINMAN -> {
                enemy = new VainMan();
                enemy.encounter();
                hasCompletedDialogueForBoss = true;

            }
            case KING -> {
                enemy = new King();
                enemy.encounter();
                hasCompletedDialogueForBoss = true;

            }
            case DRUNKARD -> {
                enemy = new Drunkard();
                enemy.encounter();
                hasCompletedDialogueForBoss = true;
            }
            default -> {
            }
        }
    }

    public static String getStaticName() {
        return playerName;
    }

    public void startBossBattle() {
        battleUI = new BattleUI(player, determineBossType(), enemy);
        battleUI.startBattle();
    }


    private JButton createButton(String text, Font font) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

}
