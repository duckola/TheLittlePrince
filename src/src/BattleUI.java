
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class BattleUI extends JFrame {

    private final List<Playable> allPlayableCharacters;
    private Fox fox;
    private Lamplighter lamplighter;

    private boolean isLamplighterAcquired = false;
    private boolean isFoxAcquired = false;

    DecimalFormat df = new DecimalFormat("0.00");
    // private final JFrame Main.frame;
    private String bgIconPath;
    private JLabel backgroundLabel;

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final ExploreUI exploreUI;

    private GameStageImplementation game = new GameStageImplementation();
    private JLabel playerNameLabel, playerHealthLabel, playerManaLabel, enemyHealthLabel, enemyManaLabel, enemyNameLabel;
    private JTextArea logTextArea;

    private Playable player;
    private Base enemy;

    private Inventory inventory;
    private Shop shop;
    private Item item;
    private boolean isGameOver = false;
    private boolean isBoss = false;
    private String choosenSkill = "";

    private BossType bossType;

    //mobs
    private Mob baobab = new Mob("Baobab", 5, 10, 5, 10, "");
    private Mob snake = new Mob("Snake", 3, 5, 5, 15, "");
    private Mob weepingRose = new Mob("Weeping Rose", 2, 3, 0, 10, "");
    private Mob rogueFox = new Mob("Rogue Fox", 4, 8, 10, 20, "");

    public BattleUI(Playable player, BossType bossType, Base enemy) {
        this.player = player;
        this.fox = Fox.getInstance();
        this.lamplighter = Lamplighter.getInstance();
        // this.Main.frame = Main.frame;
        // this.item = item;
        this.bossType = bossType;
        this.inventory = Inventory.getInstance(); // Access the singleton instance
        this.exploreUI = new ExploreUI(player);
        this.shop = new Shop(player);

        switch (bossType) {
            case VAINMAN:
                this.enemy = new VainMan();
                isBoss = true;
                break;
            case KING:
                this.enemy = new King();
                isBoss = true;
                break;
            case DRUNKARD:
                this.enemy = new Drunkard();
                isBoss = true;
                break;
            case MOBS:
            default:
                this.enemy = getRandomMob(baobab, snake, weepingRose, rogueFox);
                break;
        }

        // Initialize all playable characters list
        allPlayableCharacters = new ArrayList<>();
        allPlayableCharacters.add(player);
    }

    public void startBattle() {
        if (player.getCurrentChapter() == 1) {
            System.out.println("You are in the first chapter");
            isLamplighterAcquired = true;
            allPlayableCharacters.add(lamplighter);
        } else if (player.getCurrentChapter() == 2 && fox.isFoxAcquired()) {
            System.out.println("You are in the second chapter");
            isLamplighterAcquired = true;
            allPlayableCharacters.add(lamplighter);
            isFoxAcquired = true;
            allPlayableCharacters.add(fox);
        }

        Main.frame.getContentPane().removeAll();

        backgroundLabel = game.initializeBackgroundNarration("images/fighting_box.png");
        backgroundLabel.setPreferredSize(new Dimension(1280, 800));
        backgroundLabel.setBounds(0, 0, 1280, 800);
        Main.frame.getContentPane().add(backgroundLabel);

        Font labelFont = new Font("Comic Sans MS", Font.ITALIC, 15); // Playful, storybook-like font
        Font textAreaFont = new Font("Courier New", Font.PLAIN, 14);
        Font buttonFont = new Font("Papyrus", Font.BOLD, 15); // Papyrus for a unique, handwritten look

        playerNameLabel = new JLabel(player.getName());

        playerHealthLabel = new JLabel("Player Health: " + player.getCurrHp() + "/" + player.getMaxHp());
        playerManaLabel = new JLabel("Player Mana: " + player.getCurrMana() + "/" + player.getMaxMana());

        playerNameLabel.setFont(labelFont);
        playerHealthLabel.setFont(labelFont);
        playerManaLabel.setFont(labelFont);
        playerNameLabel.setBounds(430, 520, 200, 20);
        playerHealthLabel.setBounds(430, 545, 200, 20);
        playerManaLabel.setBounds(430, 580, 200, 20);
        Main.frame.add(playerNameLabel);
        Main.frame.add(playerHealthLabel);
        Main.frame.add(playerManaLabel);

        enemyNameLabel = new JLabel(enemy.getName());
        enemyHealthLabel = new JLabel("Enemy Health: " + enemy.getCurrHp() + "/" + enemy.getMaxHp());
        enemyManaLabel = new JLabel("Enemy Mana: " + enemy.getCurrMana() + "/" + enemy.getMaxMana());
        enemyNameLabel.setFont(labelFont);
        enemyHealthLabel.setFont(labelFont);
        enemyManaLabel.setFont(labelFont);
        enemyNameLabel.setBounds(670, 520, 200, 20);
        enemyHealthLabel.setBounds(670, 545, 200, 20);
        enemyManaLabel.setBounds(670, 580, 200, 20);
        Main.frame.add(enemyNameLabel);
        Main.frame.add(enemyHealthLabel);
        Main.frame.add(enemyManaLabel);

        // Log Panel
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        logPanel.setOpaque(false);

        // Log Text Area
        logTextArea = new JTextArea(10, 100);
        logTextArea.setFont(textAreaFont);
        logTextArea.setEditable(false);
        logTextArea.setLineWrap(true);
        logTextArea.setWrapStyleWord(true);
        logTextArea.setForeground(new Color(255, 199, 100)); // Light yellow with some transparency
        logTextArea.setBackground(new Color(58, 32, 8));
        logPanel.add(logTextArea);
        logPanel.setBounds(450, 700, 600, 30);
        backgroundLabel.add(logPanel);

        player.start();
        enemy.start();
        player.updateMana();
        enemy.attack(player);

        executor.scheduleAtFixedRate(() -> {
            updateStatsDisplay();
            isGameOver();

        }, 0, 1, TimeUnit.SECONDS);

        // InputMap and ActionMap for keyboard input
        InputMap inputMap = Main.frame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = Main.frame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_1, 0), "skill1");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "skill2");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "ultimate");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_0, 0), "1hitkey");

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_J, 0), "mainchar");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), "lamplighter");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "fox");

        actionMap.put("skill1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    System.out.println("playerturn skill 1");
                    choosenSkill = "Skill1";

                    if (player.isSkillOnCooldown()) {
                        logTextArea.setText("Skill is on cooldown");
                    } else if (player.getCurrMana() < player.skill1ManaCost) {
                        logTextArea.setText("Insufficient Mana!");
                    } else {
                        player.chooseSkill1(enemy);
                        logTextArea.setText("You dealt " + player.damageDealt() + " damage to the " + enemy.getName() + "!\n");
                    }
                    updateStatsDisplay();
                }
            }
        });

        actionMap.put("skill2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    System.out.println("playerturn skill 2");
                    choosenSkill = "Skill2";
                    if (player.canUseSkill2) {
                        logTextArea.setText("Skill 2 is not available yet!");
                    } else if (player.isSkillOnCooldown()) {
                        logTextArea.setText("Skill is on cooldown!");
                    } else if (player.getCurrMana() < player.skill2ManaCost) {
                        logTextArea.setText("Insufficient Mana!");
                    } else {
                        player.chooseSkill2(enemy);
                        logTextArea.setText(player.getName() + " is pumping up! Strength increased " + df.format(player.getStrength()));
                    }

                    updateStatsDisplay();
                    // isGameOver();                
                }
            }

        });

        actionMap.put("ultimate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    System.out.println("playerturn skill ult");
                    choosenSkill = "Ultimate";
                    if (!player.canUseUltimate) {
                        logTextArea.setText("Ulltimate is not available yet");
                    } else if (player.isSkillOnCooldown()) {
                        logTextArea.setText("Skill is on cooldown");
                    } else if (player.getCurrMana() < player.ultimateManaCost) {
                        logTextArea.setText("Insufficient Mana!");
                    } else {
                        player.chooseUltimate(enemy);
                        logTextArea.setText("You dealt " + player.damageDealt() + " damage to the " + enemy.getName() + "!\n");
                    }
                    updateStatsDisplay();
                    // isGameOver();
                }
            }
        });

        actionMap.put("1hitkey", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isGameOver) {
                    System.out.println("playerturn skill ult");
                    choosenSkill = "1hit";
                    enemy.setCurrHp(0);
                    logTextArea.setText("You dealt " + player.damageDealt() + " damage to the " + enemy.getName() + "!\n");
                    updateStatsDisplay();
                    // isGameOver();
                }
            }

        });

        actionMap.put("mainchar", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player instanceof MainChar) {
                    logTextArea.setText("You are already the main character");
                } else {
                    if (MainChar.getInstance(player.getName()).getCurrHp() > 0) {
                        System.out.println("palyer changed to mainchar");
                        switchCharacter(MainChar.getInstance(player.getName()));
                    } else {
                        logTextArea.setText("Main character is dead");
                    }
                }

            }
        });

        actionMap.put("lamplighter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player instanceof Lamplighter) {
                    logTextArea.setText("You are already the lamplighter");
                } else {
                    if (isLamplighterAcquired) {
                        if (lamplighter.getCurrHp() > 0) {
                            switchCharacter(lamplighter);
                            System.out.println("palyer changed to lamplighter");
                        } else {
                            logTextArea.setText("Lamplighter is dead!");
                        }
                    } else {
                        logTextArea.setText("Lamplighter not a companion!");
                    }
                }
            }
        });

        actionMap.put("fox", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (player instanceof Fox) {
                    logTextArea.setText("You are already the fox");
                } else {
                    if (isFoxAcquired) {
                        if (fox.getCurrHp() > 0) {
                            switchCharacter(fox);
                            System.out.println("palyer changed to fox");
                        } else {
                            logTextArea.setText("Fox is dead!");
                        }
                    } else {
                        logTextArea.setText("Fox not a companion!");
                    }
                }
            }
        });

        CircularButton circularButton = new CircularButton("");

        JButton manaPotionButton = circularButton.createRoundedButton("Mana");
        manaPotionButton.setPreferredSize(new Dimension(81, 78));

        // Mana Potion Button
        manaPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inventory.hasManaPotion()) {
                    List<Item> potionOptions = new ArrayList<>();
                    for (Item item : inventory.items.values()) {
                        if (item instanceof ManaPotion) {  // Check if it's a ManaPotion
                            potionOptions.add(item);
                            System.out.println("Added Mana Potion to options: " + item.getItemByName());

                        }
                    }
                    System.out.println("Number of Mana Potions found: " + potionOptions.size());

                    if (!potionOptions.isEmpty()) {  // Check if there are any Mana Potions
                        Object[] options = potionOptions.stream()
                                .map(item -> item.getItemByName() + " (" + item.getItemCount() + ")")
                                .toArray(String[]::new);

                        int selectedIndex = JOptionPane.showOptionDialog(null,
                                "Choose a potion to use:",
                                "Potion Selection",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);

                        if (selectedIndex >= 0) {
                            Item selectedPotionName = potionOptions.get(selectedIndex);
                            inventory.useItem(selectedPotionName, player);
                            updateStatsDisplay();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "You don't have any Mana Potions.", null, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
        JButton healthPotionButton = circularButton.createRoundedButton("Health");
        healthPotionButton.setPreferredSize(new Dimension(140, 60));

        // Health Potion Button
        healthPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inventory.hasHealthPotion()
                        || inventory.hasHealthPotion()
                        || inventory.hasHealthPotion()) { // Check if item is a ManaPotion
                    List<Item> potionOptions = new ArrayList<>();
                    for (Item item : inventory.items.values()) {    //public item in inventory
                        String itemName = item.getItemByName();
                        if (inventory.items.containsKey(itemName)) {    //inventory class -> items(hash map)
                            potionOptions.add(item);
                        }
                    }
                    Object[] options = potionOptions.stream()
                            .map(item -> item.getItemByName() + " (" + item.getItemCount() + ")")
                            .toArray(String[]::new);

                    int selectedIndex = JOptionPane.showOptionDialog(null,
                            "Choose a potion to use:",
                            "Potion Selection",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    if (selectedIndex >= 0) {
                        Item selectedPotionName = potionOptions.get(selectedIndex); // Get the selected potion name
                        inventory.useItem(selectedPotionName, player);
                        updateStatsDisplay();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have any potions.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton strengthPotionButton = circularButton.createRoundedButton("Strength");
        strengthPotionButton.setPreferredSize(new Dimension(140, 60));

        // Strength Potion Button
        strengthPotionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inventory.hasStrengthPotion("Strength Potion", StrengthItem.Rarity.COMMON)
                        || inventory.hasStrengthPotion("Strength Potion", StrengthItem.Rarity.UNCOMMON)
                        || inventory.hasStrengthPotion("Strength Potion", StrengthItem.Rarity.RARE)) { // Check if item is a ManaPotion
                    List<Item> potionOptions = new ArrayList<>();
                    for (Item item : inventory.items.values()) {    //public item in inventory
                        String itemName = item.getItemByName();
                        if (inventory.items.containsKey(itemName)) {    //inventory class -> items(hash map)
                            potionOptions.add(item);
                        }
                    }
                    Object[] options = potionOptions.stream()
                            .map(item -> item.getItemByName() + " (" + item.getItemCount() + ")")
                            .toArray(String[]::new);

                    int selectedIndex = JOptionPane.showOptionDialog(null,
                            "Choose a potion to use:",
                            "Potion Selection",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);

                    if (selectedIndex >= 0) {
                        Item selectedPotionName = potionOptions.get(selectedIndex); // Get the selected potion name
                        inventory.useItem(selectedPotionName, player);
                        updateStatsDisplay();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have any potions.", null, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JButton inventoryButton = circularButton.createRoundedButton("Inventory");
        inventoryButton.setPreferredSize(new Dimension(140, 60));

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventory.showInventory();
            }
        });

        JButton shopButton = circularButton.createRoundedButton("Shop");
        shopButton.setPreferredSize(new Dimension(200, 90));

        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.displayShop(player);

            }
        });

        manaPotionButton.setBounds(1050, 490, 81, 78);
        backgroundLabel.add(manaPotionButton);

        healthPotionButton.setBounds(1150, 490, 81, 78);
        backgroundLabel.add(healthPotionButton);

        strengthPotionButton.setBounds(1050, 580, 81, 78);
        backgroundLabel.add(strengthPotionButton);

        inventoryButton.setBounds(1150, 580, 81, 78);
        backgroundLabel.add(inventoryButton);

        shopButton.setBounds(1070, 693, 160, 52);
        backgroundLabel.add(shopButton);

        updateStatsDisplay(); // updates stats after an attack

        logTextArea.setText("\t\t\tA " + enemy.getName() + " appears!");

        Main.frame.getContentPane().add(backgroundLabel);
        Main.frame.revalidate();
        Main.frame.repaint();
        Main.frame.setFocusable(true);
        Main.frame.setVisible(true);

    }

    private Mob getRandomMob(Mob... mobs) {
        if (mobs.length == 0) {
            return null;
        }
        Random random = new Random();
        return mobs[random.nextInt(mobs.length)];
    }

    public void updateStatsDisplay() {
        playerHealthLabel.setText("Health: " + player.getCurrHp() + "/" + player.getMaxHp());
        playerManaLabel.setText("Mana: " + player.getCurrMana() + "/" + player.getMaxMana());
        enemyHealthLabel.setText("Health: " + enemy.getCurrHp() + "/" + enemy.getMaxHp());

        //logtext enemy attack log
        logTextArea.setText(enemy.getName() + "attacks for " + enemy.getDamageDealt());
    }

    private boolean isGameOver() {
        if (player.getCurrHp() <= 0) {
            // Check if other playable characters are alive
            boolean hasAliveCharacter = false;
            for (Playable character : allPlayableCharacters) {
                if (character.getCurrHp() > 0) {
                    hasAliveCharacter = true;
                    break;
                }
            }

            if (hasAliveCharacter) {
                String prevPlayer = player.getName();
                // Switch to the next alive character
                switchCharacter(getNextAliveCharacter());

                // Update UI to reflect the new active character
                updateStatsDisplay();
                logTextArea.setText("Your " + prevPlayer + " has fallen. Switching to " + player.getName() + "!\n");
            } else {
                // Game Over
                JOptionPane.showMessageDialog(null, "You have been defeated!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

                if (isBoss) {
                    enemy.loseScenario();
                } else {
                    Timer timer = new Timer(1000, e -> {
                        exploreUI.startExplore();
                    });
                    timer.setRepeats(false);
                    timer.start();
                }

                enemy.cancelAttack();
                System.out.println("You have been defeated");
                player.resetState();
                enemy.resetState();
                isGameOver = true;
                return true;
            }
        } else if (enemy.getCurrHp() <= 0) {
            if (isBoss) {
                JOptionPane.showMessageDialog(null, "You defeated the " + enemy.getName() + "!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                player.incrementChapter();
                enemy.winScenario();
            } else {

                JOptionPane.showMessageDialog(null, "You defeated the " + enemy.getName() + "!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
                Timer timer = new Timer(1000, e -> {
                    exploreUI.startExplore();
                });
                timer.setRepeats(false);
                timer.start();
            }

            enemy.cancelAttack();
            System.out.println("You defeated the mob");
            player.levelUp(enemy.getExpYield());
            player.resetState();
            enemy.resetState();
            isGameOver = true;
            return true;
        }

        return false;
    }

    public void switchCharacter(Playable newPlayer) {
        try {
            enemy.cancelAttack(); // Attempt to cancel the attack
        } catch (IllegalStateException e) {
        }

        this.player = newPlayer;
        playerNameLabel.setText(player.getName());
        enemy.attack(player);
        game.initializeBackgroundNarration(game.getBackgroundImagePath(player, enemy));
    }

    // Helper method to find the next alive character
    private Playable getNextAliveCharacter() {
        for (Playable character : allPlayableCharacters) {
            if (character.getCurrHp() > 0) {
                return character;
            }
        }
        return null; // No alive characters found
    }
}
