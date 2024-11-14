
import java.util.Timer;
import java.util.TimerTask;

public class Playable extends Base {

    protected int skill1ManaCost;
    protected int skill2ManaCost;
    protected int ultimateManaCost;

    protected boolean canUseSkill2 = false;
    protected boolean canUseUltimate = false;

    protected Timer skill2start;
    protected Timer ultimateStart;

    private int damageDealt = 0;
    private int amount;
    private int petals = 100;

    private String name;

    private int chapter = 1;

    public Playable(String name, int maxHp,
            int maxMana, int maxExp,
            int currHp, int currExp,
            int currMana, int currLevel,
            double strength, String skill1Name,
            String skill2Name, String ultimateName) {
        super(name, maxHp, maxMana, maxExp, currHp, currExp, currMana, currLevel, strength, skill1Name, skill2Name, ultimateName);
        this.name = name;
    }

    public void levelUp(int addExp) {
    }

    @Override
    public void start() {
        skill2start = new Timer();
        skill2start.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseSkill2 = true; // Skill 2 becomes available after 5 seconds
                System.out.println("Your Skill 2 is now ava ilable!");
            }
        }, skill2CooldownDuration); // 5 seconds delay

        // Set a 10-second delay for Ultimate
        ultimateStart = new Timer();
        ultimateStart.schedule(new TimerTask() {
            @Override
            public void run() {
                canUseUltimate = true; // Ultimate becomes available after 10 seconds
                System.out.println("Your Ultimate is now available!");
            }
        }, ultimateCooldownDuration); // 10 seconds delay
    }

    public boolean chooseSkill1(Base target) {
        return false;
    }

    public boolean chooseSkill2(Base target) {
        return false;
    }

    public boolean chooseUltimate(Base target) {
        return false;
    }

    @Override
    public void resetState() {
        // Reset any cooldowns, flags, or statuses here
        resetTimers();     // Assuming this method cancels and clears any active timers
        resetCooldowns();  // Assuming this method resets cooldowns
        resetHealthMana(); // Assuming this method resets health/mana if needed
    }

    public void resetCooldowns() {
        // Reset skill cooldowns
        isSkill1OnCooldown = false;
        isSkill2OnCooldown = false;
        isUltimateOnCooldown = false;
        // Reset skill availability
        canUseSkill2 = false;  // Skill 2 needs to become available after its delay
        canUseUltimate = false;
        // Any other reset logic for the character
    }

    @Override
    public void resetTimers() {
        if (skill2start != null) {
            skill2start.cancel(); // Cancel the attack timer
        }
        if (ultimateStart != null) {
            ultimateStart.cancel(); // Cancel the attack timer
        }
    }

    public void resetHealthMana() {
        setCurrHp(maxHp);  // Reset health
        setCurrMana(maxMana);  // Reset mana
    }

    public boolean isSkillOnCooldown() {
        return false;
    }

    public int damageDealt() {
        return damageDealt;
    }

    public void setMaxHp(int amount) {
    }

    public int getCurrency() { // currency in this game
        return petals;
    }

    public void setCurrency(int petals) {
        this.petals = petals;
    }

    public int addCurrency(int amount) {
        petals += amount;
        return petals;
    }

    public String getName(){
        return  name;
    }

    public int heal(int amount) {
        currHp += amount;
        if (currHp > maxHp) {
            currHp = maxHp;
        }
        return amount;
    }

    public int restoreMana(int amount) {
        currMana += amount;
        if (currMana > maxMana) {
            currMana = maxMana;
        }
        return amount;
    }

    public int increaseStrength(int amount) {
        strength += amount;
        return amount;
    }

    public int amountIncreased() {
        return amount;
    }

    public int getCurrentChapter() {
        return chapter;
    }

    public void setCurrentChapter(int chapter) {
        this.chapter = chapter;
    }

    public void incrementChapter() {
        chapter++;
    }


}
