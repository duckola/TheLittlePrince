
import java.util.Timer;
import java.util.TimerTask;

public class Mob extends Base {

    private BossType bossType;

    private int minDamage;
    private int maxDamage;
    private int damageDealt;
    private String name;
    private String enemyName;

    //for mobs
    public Mob(String name, int level, int expYield, int minDamage, int maxDamage, String planet) {
        super(name, // Set appropriate HP and stats based on level
                calculateMaxHp(level),
                calculateMaxHp(level),
                1.0, // No mana for mobs
                "",
                "",
                "",
                0, // No experience for defeating mobs
                "");
        this.minDamage = minDamage; // Adjust for each mob type
        this.maxDamage = maxDamage; // Adjust for each mob type
        this.expYield = expYield;
        this.planet = planet;
        this.name = name;
        this.enemyName = name;
        starter = new Timer();
        attackTimer = new Timer();
        ultimateCooldownDuration = 30000;
    }

    //for bosses
    public Mob(String name, int hp, int attack, int defense, BossType bossType) {
        this.bossType = bossType;

    }

    public String getName(){
        return name;
    }
    // Getter for bossType
    public BossType getBossType() {
        return bossType;
    }

    private static int calculateMaxHp(int level) {
        // Define logic to calculate HP based on level (e.g., 10 * level)
        return 10 * level; // Replace with your calculation
    }

    @Override
    public void attack(Base target) {
        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(target.getCurrHp() <= 0 || Mob.this.getCurrHp() <= 0){
                    attackTimer.cancel();
                    return;
                }
                boolean skillUsed = false;
                while(!skillUsed) {
                    damageDealt = (int) (Math.random() * (maxDamage - minDamage + 1)) + minDamage;
                    target.setCurrHp(target.getCurrHp() - damageDealt);
                    System.out.println(Mob.this.getName() + " attacks for " + damageDealt + " damage!");
                    skillUsed = true;
                }
            }
        }, 5000, 5000);
    }

    public void start() {
        starter.schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateAvailable = true; // Ultimate is now available
                System.out.println("The " + name + "'s ultimate is now available!");
            }
        }, ultimateCooldownDuration);
    }

    public int damageDealt() {
        return damageDealt;
    }

    public String getEnemyName(){
        return enemyName;
    }
}
