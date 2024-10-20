import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Drunkard extends Base {
    protected Timer attackTimer;

    protected long initialUltimateDelay = 35000; // 30 seconds after game starts
    protected boolean isUltimateAvailable = false; // New flag for ultimate availability
    protected boolean isUltimateOnCooldown = false;
    protected long ultimateDuration = 30000;
    protected boolean isUltimateActive = false;

    protected long initialSkill2Delay = 20000; // 30 seconds after game starts
    protected boolean isSkill2Available = false;
    protected boolean isSkill2OnCooldown = false;
    protected long skill2Duration = 10000; // 10 seconds

    public Drunkard() {
        super("The Drunkard", 500, 500, 2.5, "Barrel Throw", "Happy Hour", "Drunken Rage",30000);
        attackTimer = new Timer();
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateAvailable = true; // Ultimate is now available
                System.out.println("The Drunkard's ultimate is now available!");
            }
        }, initialUltimateDelay);
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
                        int damageDealt = skill1(target);
                        System.out.println("The Drunkard used " + nameSkill1());
                        System.out.println("The Drunkard deals " + damageDealt + " damage to " + target.getName());
                        skillUsed = true; // Skill successfully used
                    } else if (num == 1) {  // Ultimate
                        if (!isUltimateOnCooldown) {
                            ultimate();
                            System.out.println("The Drunkard used " + nameUltimate());
                            System.out.println("The Drunkard has boosted his strength.");
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
                    displayChoiceSkill(target);
                } else {
                    System.out.println("You have 0 health! \nEnter any key to continue...");
                }
            }
        }, 0, 5000); // 0 initial delay, 5000 ms (5 seconds) interval
    }


    public int skill1(Base target) {
        Random random = new Random();
        int baseDamage = 20;

        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        target.setReduceHp(damage);
        return damage;
    }

    public int skill2() { // equal ang weight nila sa skill so not considered as ultimate
        Random random = new Random();
        int baseHeal = 20;

        int num = random.nextInt(100);
        int additionalHeal = num / 10;
        if (num <= 9) {
            return 0;
        }
        int heal = (int) ((baseHeal + additionalHeal) * ((0.30)*maxHp));
        setAddHp(heal);

        isSkill2OnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill2OnCooldown = false; // End cooldown
            }
        }, skill2Duration);
        return heal;
    }

    public void ultimate(){
        isUltimateActive = true; // Activate the boost
        double originalStrength = getStrength();
        setStrength(originalStrength+2);

        isUltimateOnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                setStrength(originalStrength);
                isUltimateOnCooldown = false; // Cooldown ends after 15 seconds
            }
        }, ultimateDuration);
    }
}