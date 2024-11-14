
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class VainMan extends Base { // 10 seconds after game starts

    protected long ultimateActiveDuration = 10000;
    protected long strengthReductionDuration = 4000;

    private TimerTask attackTask;
    private boolean isAttacking = false;

    private MainChar player;
    private ExploreUI exploreUI;
    private int dialogueIndex = 24;
    private int damageDealt;

    private Chapter1 ch1;
    private GameStageImplementation game = new GameStageImplementation();
    private Narration narration = new Narration();

    public VainMan() {   // strength = 1.4
        super("The Vain Man", 250, 250, 1.4, "Fame Famine", "Illusory Appeal", "Ego Boost", 10000, "Asteroid B-326 ");
        attackTimer = new Timer();
        starter = new Timer();
        this.player = MainChar.getInstance(name);
        this.exploreUI = new ExploreUI(player);
        ultimateCooldownDuration = 30000;
        skill2CooldownDuration = 10000;
    }

    public String getName() {
        return name;
    }

    public int getDamageDealt() {
        return damageDealt;
    }

    public void cancelAttack() {
        if (attackTask != null) {
            attackTask.cancel();
            isAttacking = false;
        }
    }

    @Override
    public void start() {
        starter.schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateAvailable = true; // Ultimate is now available
                System.out.println("The Vain Man's ultimate is now available!");
            }
        }, ultimateCooldownDuration);
    }

    @Override
    public void attack(Base target) {
        if (!isAttacking) {
            isAttacking = true;
            attackTask = new TimerTask() {

                @Override
                public void run() {

                    if (target.getCurrHp() <= 0 || VainMan.this.getCurrHp() <= 0) {
                        attackTimer.cancel(); // Stop attacking if target or Vain Man dies
                        return;
                    }

                    boolean skillUsed = false; // Track if a skill has been successfully used
                    while (!skillUsed) {
                        Random random = new Random();
                        int num = random.nextInt(3);  // Randomly select a skill

                        if (num == 0) {  // Skill 1
                            damageDealt = skill1();
                            System.out.println("The Vain Man used " + nameSkill1());
                            System.out.println("The Vain Man deals " + damageDealt + " damage to " + target.getName());
                            target.setCurrHp(target.currHp - damageDealt);
                            skillUsed = true; // Skill successfully used
                        } else if (num == 1) {  // Ultimate
                            if (!isUltimateOnCooldown && isUltimateAvailable) {
                                ultimate();
                                System.out.println("The Vain Man used " + nameUltimate());
                                System.out.println("The Vain Man has boosted his strength.");
                                skillUsed = true; // Skill successfully used
                            }
                        } else {  // Skill 2
                            if (!isSkill2OnCooldown) {
                                damageDealt = skill2(target);
                                System.out.println("The Vain Man used " + nameSkill2());
                                System.out.println("The Vain Man deals " + damageDealt + " damage to " + target.getName() + " and reduced their strength.");
                                target.setCurrHp(target.currHp - damageDealt);
                                skillUsed = true; // Skill successfully used
                            }
                        }
                    }
                    // Display stats after every attack
                    displayStats(target, VainMan.this);

                    if (target.getCurrHp() > 0) {
                        displayChoices(target);
                    } else {
                        System.out.println("You have 0 health! \nEnter any key to continue...");
                    }
                }
            };
            new Timer().scheduleAtFixedRate(attackTask, 5000, 5000);
        }
    }

    public int skill1() {
        Random random = new Random();
        int baseDamage = 30;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        return (int) ((baseDamage + additionalDamage) * strength);
    }

    public int skill2(Base target) {
        Random random = new Random();
        int baseDamage = 15;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        double originalStrength = target.getStrength();
        target.setStrength(originalStrength - 0.3);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                target.setStrength(originalStrength); // Restore original strength
            }
        }, strengthReductionDuration);
        startCooldown("skill2", skill2CooldownDuration);
        return damage;
    }

    public void ultimate() {
        double originalStrength = getStrength();
        setStrength(originalStrength + 2);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setStrength(originalStrength);
            }
        }, ultimateActiveDuration);
        startCooldown("ultimate", ultimateCooldownDuration);
    }

    @Override
    public void encounter() {
        System.out.println("encounter");
        Main.frame.getContentPane().removeAll();

        JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");

        game.setDialogueIndex(23);
        String[] speakers = {"Lamplighter", "Main Character", "Cassius"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(), 43, speakers, () -> {
            exploreUI.startBossBattle();
        });
    }

    public void loseScenario() {
        game.displayText(narration.getDialogue(43));

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
        String[] speakers = {"Lamplighter", "Main Character", "Cassius"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(), 51, speakers, () -> {
            Chapter2 ch2 = new Chapter2(player);
            ch2.startChapter2();
        });
    }

}
