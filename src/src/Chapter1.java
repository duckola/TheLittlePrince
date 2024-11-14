
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Chapter1 {

    private static Chapter1 instance;
    private static final Color WHITE = Color.WHITE;
    private static final Color BLACK = Color.BLACK;
    private static final Color SOFT_YELLOW = new Color(255, 215, 0);
    private static final int DELAY = 75; // Typing effect delay (milliseconds)
    private static final Font MONOSPACED_FONT = new Font("Monospaced", Font.PLAIN, 18);
    private static final Font SERIF_BOLD_FONT = new Font("Serif", Font.BOLD, 20);
    private static final JLayeredPane layeredPane = new JLayeredPane();

    private GameStageImplementation game;
    private final Narration narration;
    private ExploreUI explore;
    private Playable player;

    private int start;
    private int stop;
    private Image backgroundImage = null;

    public Chapter1() {
        this.explore = new ExploreUI(player);
        this.narration = new Narration();
        this.game = new GameStageImplementation();
    }

    public void showStoryline() {

        Main.frame.getContentPane().removeAll();

        // JLayeredPane layeredPane = new JLayeredPane();

        JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");
        if (backgroundLabel != null) {
            layeredPane.add(backgroundLabel, Integer.valueOf(0));
        }

        // Create the main story panel with GridBagLayout
        JPanel storyPanel = new JPanel(new GridBagLayout());
        storyPanel.setOpaque(false); // Transparent to show GIF background
        storyPanel.setBounds(0, 0, 1280, 800);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;

        // Storyline text area setup
        JTextArea storyTextArea = new JTextArea();
        storyTextArea.setForeground(WHITE);
        storyTextArea.setFont(MONOSPACED_FONT);
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setOpaque(false);
        storyTextArea.setEditable(false);
        storyTextArea.setPreferredSize(new Dimension(800, 400));
        storyTextArea.setMargin(new Insets(100, 20, 20, 20));
        storyPanel.add(storyTextArea, gbc);

        // Skip button setup
        JButton skipButton = game.createButton("Skip");
        skipButton.addActionListener(e -> showStage());

        skipButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                skipButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                skipButton.setBackground(SOFT_YELLOW);
            }
        });

        gbc.gridy = 1; // Move Y-POSITION
        storyPanel.add(skipButton, gbc);

        layeredPane.add(storyPanel, Integer.valueOf(1));

        Main.frame.getContentPane().removeAll();
        Main.frame.add(layeredPane);
        Main.frame.revalidate();
        Main.frame.repaint();

        game.showDialogue(storyTextArea, 0);
    }

    public void showStage() {
        game.showChapter("images/chapter1.png");

        Timer timer = new Timer(2000, e -> {
            game.typeWriterEffect(narration.getDialogue(1), 20, () -> {
                JButton checkPhoneButton = game.createButton("Check the phone");
                JButton continueSleepingButton = game.createButton("Continue sleeping");

                checkPhoneButton.addActionListener(a -> checkPhone());
                continueSleepingButton.addActionListener(a -> continueSleeping());
                
                game.displayChoices(checkPhoneButton, continueSleepingButton);
            });
        });
        timer.setRepeats(false); // Only run once
        timer.start();
    }

    private void checkPhone() {
        if (narration != null && narration.getDialogueCount() > 2) {
            game.typeWriterEffect(narration.getDialogue(2), 30, () -> {
                JButton checkNotif = game.createButton("Check the notification");
                checkNotif.addActionListener(a -> checkEmail());
                game.displayChoices(checkNotif);
            });
        }
    }

    private void continueSleeping() {
        if (narration != null && narration.getDialogueCount() > 3) {
            game.typeWriterEffect(narration.getDialogue(3), 30, () -> {
                JButton checkNotif = game.createButton("Check the notification");
                checkNotif.addActionListener(a -> checkEmail());
                game.displayChoices(checkNotif);
            });
        }
    }

    private void checkEmail() {
        if (narration != null && narration.getDialogueCount() > 4) {
            game.typeWriterEffect(narration.getDialogue(4), 30, () -> {

                Timer timer = new Timer(3000, e -> {
                    System.out.println("Success");
                    startDialogue(); // Proceed to straight dialogue
                });

                timer.setRepeats(false);
                timer.start();
            });
        }
    }

    public void startDialogue() {
        Main.frame.getContentPane().removeAll();

        System.out.println("startDialobr!");
        start = 5;
        stop = 7;


        // game.showAnimation();
        String[] speakers = {"Speaker", "Main Character"};
        if (game.getDialogueIndex() < stop) {
            System.out.println("Starting dialogue..." + game.getDialogueIndex() + "" + start);
            game.displayDialogue(start, stop, speakers, () -> {
                displayPlanet();
            });
        } else {
            System.out.println("No dialogue found at this index.");
        }
    }

    public void displayPlanet() {
        game.displayPlanetName("images/asteroid_b612.png");
        System.out.println("Starting dialogue..." + game.getDialogueIndex() + "" + start);

        System.out.println("displaneyat!");
        start = 7;
        stop = 9;

        String[] speakers = {"Rose", "Little Prince"};
        Timer timer = new Timer(3000, e -> {
            if (game.getDialogueIndex() < stop) {
                System.out.println("Starting dialogue...");
                game.displayDialogue(start, stop, speakers, () -> {
                    talkToRose();
                });
            } else {
                System.out.println("No dialogue found at this index.");
            }
        });

        // Only fire the Timer once
        timer.setRepeats(false);
        timer.start();
    }

    private void talkToRose() {
        game.typeWriterEffect(narration.getDialogue(10), 30, () -> {
            JButton talkToRose = game.createButton("Talk To Rose");
            game.setDialogueIndex(11);
            System.out.println(game.getDialogueIndex());
            talkToRose.addActionListener(e -> {
                if (game.getDialogueIndex() < 13) {
                    game.displayDialogue(11, 13, new String[]{"Rose", "Main Character"}, () -> {
                        talkToPrince();
                    });
                } else {
                    System.out.println("Dialogue index out of bounds.");
                }
            });

            game.displayChoices(talkToRose);
        });
    }

    private void talkToPrince() {

        System.out.println("talktoprince");
        String[] speakers = {"Little Prince", "Main Character"};
        start = 13;
        stop = 19;

        System.out.println(game.getDialogueIndex());

        if (game.getDialogueIndex() < stop) {
            System.out.println("Starting dialogue...");
            game.displayDialogue(start, stop, speakers, () -> {
                nextPlanet();
            });
        }
    }

    private void nextPlanet() {
        System.out.println("nextplanet");
        game.typeWriterEffect("\t\t\tThe next day...", 30, () -> {
            Timer timer = new Timer(2000, e1 -> {
                game.typeWriterEffect(narration.getDialogue(19), 50, () -> {
                    game.setDialogueIndex(20);

                    String[] speakers = {"Speaker",};
                    game.displayDialogue(20, 21, speakers, () -> {
                        displayNextPlanet();
                    });
                });
            });
            timer.setRepeats(false);
            timer.start();
        });
    }

    private void displayNextPlanet() {
        System.out.println("displaynextplanet");
        game.displayPlanetName("images/planet1.png");

        String[] speakers = {" Main Character", " Speaker"};
        Timer timer3 = new Timer(2500, e3 -> {
            System.out.println("Starting dialogue...");
            game.displayDialogue(21, 23, speakers, () -> {
                startExploring();
            });
        });

        // Only fire the Timer once
        timer3.setRepeats(false);
        timer3.start();
    }

    private void startExploring() {
        explore.startExplore();  // Start the game using the existing Main.frame
    }
}
