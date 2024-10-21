import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainChar extends Base{
    protected int currency = 100;

    protected boolean isSkill1OnCooldown = false;
    protected long skill1Duration = 1500;
    protected int skill1ManaCost = 10;

    protected boolean isSkill2OnCooldown = false;
    protected long skill2Duration = 5000;
    protected int skill2ManaCost = 30;

    protected boolean isUltimateOnCooldown = false;
    protected long ultimateDuration = 10000;
    protected int ultimateManaCost = 70;

    protected boolean canUseSkill2 = false;
    protected boolean canUseUltimate = false;

    protected Timer attackTimer;
    public MainChar(String name){
        super(name,100,100,10,100,0,100,1,1.0,"Blade Strike","Swords Dance","Judgement");
        attackTimer = new Timer();
        // Set a 5-second delay for Skill 2
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canUseSkill2 = true; // Skill 2 becomes available after 5 seconds
                System.out.println("You Skill 2 is now available!");
            }
        }, 5000); // 5 seconds delay

        // Set a 10-second delay for Ultimate
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canUseUltimate = true; // Ultimate becomes available after 10 seconds
                System.out.println("Your Ultimate is now available!");
            }
        }, 10000); // 10 seconds delay
    }


    public int skill1(Base target) {
        setReduceMana(skill1ManaCost);
        Random random = new Random();
        int baseDamage = 500; //setback to 5

        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }

        int damage = (int)((baseDamage + additionalDamage) * strength);
        target.setReduceHp(damage);

        isSkill1OnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill1OnCooldown = false; // End cooldown
            }
        }, skill1Duration);
        return damage;
    }


    public void skill2( ){
        setReduceMana(skill2ManaCost);
        this.strength += 5; // Temporary strength increase

        isSkill2OnCooldown = true; // Start cooldown

        // Reset cooldown after skill2CooldownDuration
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                strength -= 5; // Reset strength boost
                isSkill2OnCooldown = false; // End cooldown
            }
        }, skill2Duration);
    }


    public int ultimate(Base target){
        setReduceMana(ultimateManaCost);
        int baseDamage = 50;

        int missingHp = target.maxHp - target.currHp;
        int additionalDamage = (int) (0.50 * missingHp);
        int damage = (baseDamage + additionalDamage);
        target.setReduceHp(damage);

        isUltimateOnCooldown = true;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isUltimateOnCooldown = false; // End cooldown
            }
        }, ultimateDuration);
        return damage;
    }

    public boolean chooseSkill1(Base target){
        if(getCurrHp() == 0){
            System.out.println("You have no health");
        } else if (isSkill1OnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else  if (this.currMana < skill1ManaCost){
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " deals " + skill1(target));
            displayStats(MainChar.this, target);
            return  true; // Valid skill chosen
        }
        return false;
    }

    public boolean chooseSkill2(){
        if(getCurrHp() == 0){
            System.out.println("You have no health");
        } else if (!canUseSkill2) {
            System.out.println("Skill 2 is not available yet!");
        } else if (isSkill2OnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else  if (this.currMana < skill2ManaCost){
            System.out.println("Insufficient Mana!");
        } else {
            skill2();
            System.out.println(getName() + " is pumping up!");
            return true; // Valid skill chosen
        }
        return false;
    }

    public boolean chooseUltimate(Base target){
        if(getCurrHp() == 0){
            System.out.println("You have no health");
        } else if (!canUseUltimate) {
            System.out.println("Ultimate is not available yet!");
        } else if (isUltimateOnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else  if (this.currMana < ultimateManaCost){
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " deals " + ultimate(target));
            displayStats(MainChar.this, target);
            return  true; // Valid skill chosen
        }
        return false;
    }
}