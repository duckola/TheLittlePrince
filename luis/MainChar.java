import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainChar extends Base{
    protected int currency = 100;
    protected boolean isSkill1OnCooldown = false;
    protected long skill1Duration = 3000;
    protected int skill1ManaCost = 10;
    protected long cdReduction = 0;

    protected boolean isSkill2OnCooldown = false;
    protected long skill2Duration = 10000;
    protected int skill2ManaCost = 30;

    protected boolean isUltimateOnCooldown = false;
    protected long ultimateDuration = 30000;
    protected int ultimateManaCost = 70;

    protected boolean canUseSkill2 = false;
    protected boolean canUseUltimate = false;

    protected Timer skill2start = new Timer();
    protected Timer ultimateStart = new Timer();

    public MainChar(String name) {
        super(name, 150, 100, 10, 150, 0, 100, 1, 1.0, "Blade Strike", "Swords Dance", "Judgement");
        attackTimer = new Timer();
    }

    @Override
    public void start(){
        skill2start = new Timer();
        skill2start.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseSkill2 = true; // Skill 2 becomes available after 5 seconds
                System.out.println("Your Skill 2 is now available!");
            }
        }, skill2Duration); // 5 seconds delay

        // Set a 10-second delay for Ultimate
        ultimateStart = new Timer();
        ultimateStart.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseUltimate = true; // Ultimate becomes available after 10 seconds
                System.out.println("Your Ultimate is now available!");
            }
        }, ultimateDuration); // 10 seconds delay
    }


    public int skill1(Base target) {
        setCurrMana(this.currMana - skill1ManaCost);
        Random random = new Random();
        int baseDamage = 5; //setback to 5

        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }

        double initialDamage = (baseDamage + additionalDamage);
        initialDamage *= this.strength;
        int damage = (int) initialDamage;
        target.setCurrHp(target.currHp - damage);

        isSkill1OnCooldown = true;
        long cooldownDuration = (cdReduction > 0) ? cdReduction : skill1Duration;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isSkill1OnCooldown = false; // End cooldown
                cdReduction =  0;
            }
        }, cooldownDuration);
        return damage;
    }


    public void skill2( ){
        setCurrMana(this.currMana - skill2ManaCost);
        this.strength += 1; // Temporary strength increase

        cdReduction = skill1Duration/2;

        isSkill2OnCooldown = true; // Start cooldown
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                strength -= 1; // Reset strength boost
                cdReduction = 0;
                isSkill2OnCooldown = false; // End cooldown
            }
        }, skill2Duration);
    }


    public int ultimate(Base target){
        setCurrMana(this.currMana - ultimateManaCost);
        int baseDamage = 50;

        int missingHp = target.maxHp - target.currHp;
        int additionalDamage = (int) (0.50 * missingHp);
        int damage = (baseDamage + additionalDamage);
        target.setCurrHp(target.currHp - damage);

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

    @Override
    public void resetState() {
        // Reset any cooldowns, flags, or statuses here
        resetCooldowns();  // Assuming this method resets cooldowns
        resetTimers();     // Assuming this method cancels and clears any active timers
        resetHealthMana(); // Assuming this method resets health/mana if needed
    }

    public void resetCooldowns() {
        // Reset skill cooldowns
        isUltimateOnCooldown = false;
        isSkill2OnCooldown = false;
        // Any other reset logic for the character
    }

    public void resetTimers() {
        if (attackTimer != null) {
            attackTimer.cancel();
            attackTimer = null;
        }
        if (skill2start != null) {
            skill2start.cancel(); // Cancel the attack timer
        }
        if (ultimateStart != null) {
            ultimateStart.cancel(); // Cancel the attack timer
        }
    }
    public void resetHealthMana() {
        setCurrHp(this.maxHp);  // Reset health
        setCurrMana(this.maxMana);  // Reset mana
    }
}