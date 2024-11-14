
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Drunkard extends Base {

    private MainChar player;
    private Base enemy;

    private BattleUI battleUI;
    private ExploreUI exploreUI;
    private int stop;
    private int dialogueIndex;

    private TimerTask attackTask;
    private boolean isAttacking = false;

    private Chapter1 ch1;
    private GameStageImplementation game = new GameStageImplementation();
    private Narration narration = new Narration();

    protected long paralyzeDuration = 6000;

    public Drunkard() { //2.5 strength
        super("The Drunkard", 500, 500, 2.1, "Wine Blast", "Happy Hour", "Drunken Rage", 75000, "Boozara");
        attackTimer = new Timer();
        starter = new Timer();
        this.player = MainChar.getInstance(name);
        this.exploreUI = new ExploreUI(player);
        ultimateCooldownDuration = 35000;
        skill2CooldownDuration = 20000; // 20 seconds

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
                System.out.println("The Drunkard's ultimate is now available!");
            }
        }, ultimateCooldownDuration);
    }

    @Override
    public void attack(Base target) {
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (target.getCurrHp() <= 0 || Drunkard.this.getCurrHp() <= 0) {
                    attackTimer.cancel(); // Stop attacking if target or Vain Man dies
                    return;
                }
                boolean skillUsed = false; // Track if a skill has been successfully used

                while (!skillUsed) {
                    Random random = new Random();
                    int num = random.nextInt(3);  // Randomly select a skill

                    if (num == 0) {  // Skill 1
                        int damageDealt = skill1();
                        System.out.println("The Drunkard used " + nameSkill1());
                        System.out.println("The Drunkard deals " + damageDealt + " damage to " + target.getName());
                        target.setCurrHp(target.currHp - damageDealt);
                        skillUsed = true; // Skill successfully used
                    } else if (num == 1) {  // Ultimate
                        if (!isUltimateOnCooldown && isUltimateAvailable) {
                            int damageDealt = ultimate(target);
                            System.out.println("The Drunkard used " + nameUltimate());
                            System.out.println("The Drunkard deals " + damageDealt + " damage and has paralyzed " + target.getName() + " for 6 seconds");
                            target.setCurrHp(target.currHp - damageDealt);
                            skillUsed = true; // Skill successfully used
                        }
                    } else {  // Skill 2
                        if (!isSkill2OnCooldown) {
                            int healing = skill2();
                            System.out.println("The Drunkard used " + nameSkill2());
                            System.out.println("The Drunkard heals " + healing + " to itself ");
                            skillUsed = true; // Skill successfully used
                        }
                    }
                }
                // Display stats after every attack
                displayStats(target, Drunkard.this);

                if (target.getCurrHp() > 0) {
                    displayChoices(target);
                } else {
                    System.out.println("You have 0 health! \nEnter any key to continue...");
                }
            }
        }, 5000, 5000); // 5000 initial delay, 5000 ms (5 seconds) interval
    }

    public int skill1() {
        Random random = new Random();
        int baseDamage = 20;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        return (int) ((baseDamage + additionalDamage) * strength);
    }

    public int skill2() {
        Random random = new Random();
        int baseHeal = 10;
        int num = random.nextInt(100);
        int additionalHeal = num / 10;
        if (num <= 9) {
            return 0;
        }
        int heal = (int) ((baseHeal + additionalHeal) + ((0.2) * currHp));
        setCurrHp(this.currHp + heal);
        startCooldown("skill2", skill2CooldownDuration);
        return heal;
    }

    public int ultimate(Base target) {
        Random random = new Random();
        int baseDamage = 50;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        target.setParalyzed(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                target.setParalyzed(false); // Restore original state
            }
        }, paralyzeDuration);
        startCooldown("ultimate", ultimateCooldownDuration);
        return damage;
    }

    @Override
    public void encounter() {
        System.out.println("encounter");
        Main.frame.getContentPane().removeAll();

        JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");

        game.setDialogueIndex(73);
        String[] speakers = {"Main Character", "Drunkard"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(),83 , speakers, () -> {
            player.setCurrentChapter(2);
            exploreUI.startBossBattle();
        });
    }
    //player loss
    public void loseScenario(){
        game.displayText(narration.getDialogue(83));

        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        exploreUI.startExplore();
    }

    // player win
    public void winScenario() {
        System.out.println("win scenario");

        try {
            Thread.sleep(2000); // Delay for 2 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.frame.getContentPane().removeAll();
        // JLabel backgroundLabel = game.initializeBackgroundNarration("images/gameplay bg.png");

        game.setDialogueIndex(84);
        String[] speakers = {"Lamplighter", "Main Character", "Cassius"};
        System.out.println("Starting dialogue..." + dialogueIndex);
        game.displayDialogue(game.getDialogueIndex(), 98, speakers, () -> {
            exploreUI.startExplore();
        });
    }

}
