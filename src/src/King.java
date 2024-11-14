
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class King extends Base {

    private MainChar player;
    private Base enemy;

    private BattleUI battleUI;
    private ExploreUI exploreUI;
    private int dialogueIndex = 24;
    private int stop;

    private Chapter1 ch1;
    private GameStageImplementation game = new GameStageImplementation();
    private Narration narration = new Narration();

    protected int summonMax = 2;
    protected int summoned = 0;
    private int knightCounter = 1;
    private boolean isFirstUse = true;
    private boolean isGameOver = false;
    protected long countdown = 300000; // 5 minutes = 300,000 milliseconds

    protected boolean isShieldActive = false;
    protected long shieldDuration = 6000;

    private TimerTask attackTask;
    private boolean isAttacking = false;

    public King() { //2.5 strength
        super("The King", 1000, 1000, 3.0, "Royal Decree", "Royal Protection", "Absolute Authority", 500000, "Sovereignia");
        attackTimer = new Timer();
        starter = new Timer();
        this.player = MainChar.getInstance(name);
        this.exploreUI = new ExploreUI(player);
        skill1CooldownDuration = 5000;
        skill2CooldownDuration = 25000;
        ultimateCooldownDuration = 60000;
    }

    public String getName() {
        return name;
    }

    public void cancelAttack() {
        if (attackTask != null) {
            attackTask.cancel();
            isAttacking = false;
        }
    }

    @Override
    public void start() {
        // Set a 10-second delay for Ultimate
        starter.schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateAvailable = true; // Ultimate is now available
                System.out.println("The King's ultimate is now available!");
            }
        }, ultimateCooldownDuration);
    }

    @Override
    public void attack(Base target) {
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (target.getCurrHp() <= 0 || King.this.getCurrHp() <= 0) {
                    attackTimer.cancel(); // Stop attacking if target or Vain Man dies
                    return;
                }
                boolean skillUsed = false; // Track if a skill has been successfully used

                while (!skillUsed) {
                    Random random = new Random();
                    int num = random.nextInt(3);  // Randomly select a skill

                    if (num == 0) {  // Skill 1
                        if (!isSkill1OnCooldown && summoned <= summonMax) {
                            skill1(target);
                            System.out.println("The King used " + nameSkill1());
                            System.out.println("The King has summoned a knight");
                            skillUsed = true; // Skill successfully used
                        }
                    } else if (num == 1) {  // Ultimate
                        if (!isUltimateOnCooldown && isUltimateAvailable) {
                            int damageDealt = ultimate(target);
                            System.out.println("The King used " + nameUltimate());
                            if (isFirstUse) {
                                System.out.println("The King has cursed " + target.getName() + ". Win the game within 5 minutes or you automatically lose");
                                isFirstUse = false;
                            } else {
                                System.out.println("The King deals " + damageDealt + " damage and has paralyzed " + target.getName() + " for 3 seconds");
                                System.out.println(target.getName() + "'s strength has been reduced by 0.5 for 6 seconds.");
                                target.setCurrHp(target.currHp - damageDealt);
                            }
                            skillUsed = true; // Skill successfully used
                        }
                    } else {  // Skill 2
                        if (!isSkill2OnCooldown) {
                            skill2();
                            System.out.println("The King used " + nameSkill2());
                            System.out.println("The King has shielded himself for 7 seconds");
                            skillUsed = true; // Skill successfully used
                        }
                    }
                }
                // Display stats after every attack
                displayStats(target, King.this);

                if (target.getCurrHp() > 0) {
                    displayChoices(target);
                } else {
                    System.out.println("You have 0 health! \nEnter any key to continue...");
                }
            }
        }, 5000, 5000); // 5000 initial delay, 5000 ms (5 seconds) interval
    }

    @Override
    public void setCurrHp(int hp) {
        if (isShieldActive) {
            int damage = currHp + hp;
            int upDamage = (int) Math.ceil(damage / 2.0);
            System.out.println("The King's shield absorbed " + (currHp - upDamage) + " damage! ");
            if (hp < 0) {
                currHp = 0;
            } else {
                currHp = (int) Math.floor(damage / 2.0);
            }
            return; // If the shield is active, no damage is applied
        }
        if (hp < 0) {
            currHp = 0;
        } else {
            currHp = Math.min(hp, maxHp);
        }
    }

    public void decreaseSummoned() {
        if (summoned > 0) {
            summoned--;
        }// Mark the knight number as available
    }

    public void skill1(Base target) {
        if (summoned < summonMax) {
            summoned += 1;
        } else {
            summoned = 1;  // Reset to 1 if max is reached
        }
        KingKnights knights = new KingKnights(strength);
        int knightNumber = knightCounter++;  // Assign and increment knight number
        knights.summon(knightNumber, target, King.this);

        if (knightCounter > summonMax) {
            knightCounter = 1;  // Reset knightCounter if it exceeds the limit
        }
        startCooldown("skill1", skill1CooldownDuration);
    }

    public void skill2() {
        isShieldActive = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isShieldActive = false; // Deactivate the shield after a set duration
            }
        }, shieldDuration);
        startCooldown("skill2", skill2CooldownDuration);
    }

    public int ultimate(Base target) {
        Random random = new Random();
        int baseDamage = 15;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (isFirstUse) {
            // First use logic: start a 5-minute countdown timer
            startFiveMinuteTimer(target);
            return 0; // No damage on first use
        }
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        double originalStrength = target.getStrength();
        target.setStrength(target.getStrength() - 0.5);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                target.setStrength(originalStrength); // Restore strength
            }
        }, 6000);
        target.setParalyzed(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                target.setParalyzed(false); // End paralyze after duration
            }
        }, 3000);
        startCooldown("ultimate", ultimateCooldownDuration);
        return damage;
    }

    private void startFiveMinuteTimer(Base target) {
        // Start a timer to end the game after 5 minutes if not won
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isGameOver) {
                    // If game is not yet over, the player loses
                    System.out.println("Time's up! You couldn't defeat the King in 5 minutes.");
                    target.setCurrHp(0); // Forcefully reduce player's HP to 0 (game over)
                    target.resetTimers();
                    King.this.resetTimers();
                    isGameOver = true;
                }
            }
        }, countdown); // 5 minutes = 300,000 milliseconds
    }

@Override
    public void encounter() {
        System.out.println("encounter");
        game.displayText(narration.getDialogue(43));

        Main.frame.getContentPane().removeAll();

        JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");

        game.setDialogueIndex(23);
        String[] speakers = {"Main Character", "King"};
            System.out.println("Starting dialogue..." + dialogueIndex);
            game.displayDialogue(game.getDialogueIndex(), 43, speakers, () -> {
                player.setCurrentChapter(3);

                exploreUI.startBossBattle();
            });      
    }

    public void loseScenario() {
        game.setDialogueIndex(23);
        String[] speakers = {"Main Character", "King"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(), 43, speakers, () -> {
            game.displayText(narration.getDialogue(43));
        });

        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exploreUI.startExplore();
    }

    public void winScenario() {
        System.out.println("win scenario");
        game.displayText(narration.getDialogue(44));

        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.frame.getContentPane().removeAll();
        JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");

        game.setDialogueIndex(44);
        String[] speakers = {"Main Character", "King"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(), 51, speakers, () -> {
            exploreUI.startExplore();
        });
    }


}
