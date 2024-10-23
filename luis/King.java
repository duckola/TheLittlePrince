import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class King extends Base {
    protected boolean isSkill1OnCooldown = false;
    protected long skill1CooldownDuration = 5000;
    protected int summonMax = 2;
    protected int summoned = 0;
    private int knightCounter = 1;

    protected boolean isUltimateOnCooldown = false;
    protected boolean isUltimateAvailable = false;
    protected long ultimateCooldownDuration = 60000;
    private boolean isFirstUse = true;
    private boolean isGameOver = false;
    protected long countdown = 300000; // 5 minutes = 300,000 milliseconds

    protected boolean isSkill2OnCooldown = false;
    protected long skill2CooldownDuration = 25000; // 10 seconds
    protected boolean isShieldActive = false;
    protected long shieldDuration = 6000;

    public King() { //2.5 strength
        super("The King", 1000, 1000, 3.0, "Royal Decree", "Royal Protection", "Absolute Authority",150000);
        attackTimer = new Timer();
        starter = new Timer();
    }

    @Override
    public void start(){
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
                            if(isFirstUse){
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
                    displayChoiceSkill(target);
                } else {
                    System.out.println("You have 0 health! \nEnter any key to continue...");
                }
            }
        }, 0, 5000); // 0 initial delay, 5000 ms (5 seconds) interval
    }

    @Override
    public void setCurrHp(int hp){
        if (isShieldActive) {
            System.out.println("The King's shield absorbed the damage! ");
            return; // If the shield is active, no damage is applied
        }
        if (hp < 0) {
            currHp = 0;
        } else currHp = Math.min(hp, maxHp);
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

        isSkill1OnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill1OnCooldown = false; // End cooldown
            }
        }, skill1CooldownDuration);
    }

    public void skill2() {
        isShieldActive = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isShieldActive = false; // Deactivate the shield after a set duration
            }
        }, shieldDuration);

        isSkill2OnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill2OnCooldown = false; // End cooldown
            }
        }, skill2CooldownDuration);
    }

    public int ultimate(Base target){
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

        isUltimateOnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateOnCooldown = false; // Cooldown ends after 15 seconds
            }
        }, ultimateCooldownDuration);
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
}
