import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class VainMan extends Base { // 10 seconds after game starts
    protected boolean isUltimateAvailable = false; // New flag for ultimate availability
    protected boolean isUltimateOnCooldown = false;
    protected long ultimateCooldownDuration = 30000;
    protected long ultimateActiveDuration = 10000;

    protected boolean isSkill2OnCooldown = false;
    protected long skill2Duration = 10000; // 10 seconds
    protected long strengthReductionDuration = 4000;

    public VainMan() {   // strength = 1.4
        super("The Vain Man", 250, 250, 1.4, "Fame Famine", "Illusory Appeal", "Ego Boost",15000);
        attackTimer = new Timer();
        starter = new Timer();
    }

    @Override
    public void start(){
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
        attackTimer.scheduleAtFixedRate(new TimerTask() {
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
                        int damageDealt = skill1();
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
                            int damageDealt = skill2(target);
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
                    displayChoiceSkill(target);
                } else {
                    System.out.println("You have 0 health! \nEnter any key to continue...");
                }
            }
        }, 0, 5000); // 0 initial delay, 5000 ms (5 seconds) interval
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

    public void ultimate(){
        double originalStrength = getStrength();
        setStrength(originalStrength+2);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setStrength(originalStrength);
            }
        }, ultimateActiveDuration);

        isUltimateOnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateOnCooldown = false; // Cooldown ends after 15 seconds
            }
        }, ultimateCooldownDuration);
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

        isSkill2OnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill2OnCooldown = false; // End cooldown
            }
        }, skill2Duration);
        return damage;
    }
}