import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class KingKnights { // 10 seconds after game starts
    protected Timer attackTimer;
    protected Timer lifespanTimer;
    protected long lifespanDuration = 10000; // 10 seconds lifespan
    protected long attackInterval = 2000;
    protected double strength;

    public KingKnights(double strength) {
        attackTimer = new Timer();
        lifespanTimer = new Timer();
        this.strength = strength-1;
    }

    public void summon(int knightNumber, Base target, King king) {
        // Set a timer for lifespan (10 seconds)
        lifespanTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                attackTimer.cancel(); // Stop attacking after lifespan ends && animations of knights expiring
                king.decreaseSummoned(); // Decrease summoned count when knights expire
            }
        }, lifespanDuration);

        // Attack the target every 2 seconds
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (target.getCurrHp() <= 0) {
                    attackTimer.cancel(); // Stop attacking if the target dies
                    return;
                }

                int damageDealt = skill1(target);
                System.out.println("The King's knights "+knightNumber+" attacked "+ target.getName() +" and dealt " + damageDealt);
            }
        }, 0, attackInterval); // 0 initial delay, attacks every 2 seconds
    }


    public int skill1(Base target) {
        Random random = new Random();
        int baseDamage = 5;

        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        target.setCurrHp(target.currHp - damage);
        return damage;
    }
}
