
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainChar extends Playable {

    private static MainChar instance; // Single instance reference

    private Inventory inventory;

    protected long cdReduction = 1000;
    protected long skill2ActiveDuration = 6000;
    private  int damageDealt = 0;
    private int amount;
    private int petals = 100;
    private String name;
    private int chapter = 1;


    private MainChar(String name) {
        super(name, 150, 100, 10, 150, 0, 100, 1, 1.0, "Blade Strike", "Swords Dance", "Judgement");
        skill1CooldownDuration = 3000;
        skill1ManaCost = 10;
        skill2CooldownDuration = 15000;
        skill2ManaCost = 30;
        ultimateCooldownDuration = 30000;
        ultimateManaCost = 50;
        this.amount = amount;
        this.petals = petals;
        this.inventory = inventory;
        this.name = name;    
    }

    public static synchronized MainChar getInstance(String name) {
        if (instance == null) {
            instance = new MainChar(name);
        }
        return instance;
    }
    
    // public MainChar(String name) {
    //     super(name, 150, 100, 10, 150, 0, 100, 1, 1.0, "Blade Strike", "Swords Dance", "Judgement");
    //     skill1CooldownDuration = 3000;
    //     skill1ManaCost = 10;
    //     skill2CooldownDuration = 15000;
    //     skill2ManaCost = 30;
    //     ultimateCooldownDuration = 30000;
    //     ultimateManaCost = 50;
    //     this.amount = amount;
    //     this.petals = petals;
    //     this.inventory = inventory;
    //     this.name = name;
    // }

    // public int getCurrentChapter(){
    //     return chapter;
    // }

    // public void setCurrentChapter(int chapter){
    //     this.chapter = chapter;
    // }

    // public void incrementChapter(){
    //     chapter++;
    // }
    
    @Override
    public void levelUp(int addExp) {
        while (addExp > 0) {
            int expNeeded = maxExp - currExp; // Experience needed to reach next level
            if (addExp >= expNeeded) {
                // Enough experience to level up
                currLevel += 1;
                System.out.println(name + " Leveled Up! " + currLevel);
                strength += 0.15;
                maxHp += 20;
                currHp += 20;
                maxMana += 15;
                addExp -= expNeeded; // Deduct the exp needed for this level-up
                currExp = 0; // Reset current experience
                maxExp = (int) Math.ceil(maxExp * 1.3); // Increase required exp for next level
            } else {
                // Not enough to level up, just add to current experience
                currExp += addExp;
                addExp = 0;
            }
        }
    }

    public int skill1() {
        damageDealt = 0;
        setCurrMana(currMana - skill1ManaCost);
        Random random = new Random();
        int baseDamage = 15; //setback to
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((baseDamage + additionalDamage) * strength);
        damageDealt = damage;
        startCooldown("skill1", skill1CooldownDuration);
        return damage;
    }

    public void skill2() {
        damageDealt = 0;
        setCurrMana(currMana - skill2ManaCost);
        strength += 1; // Temporary strength increase
        long originalCD = skill1CooldownDuration;
        skill1CooldownDuration = cdReduction;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                strength -= 1;
                skill1CooldownDuration = originalCD;
            }
        }, skill2ActiveDuration);
        startCooldown("skill2", skill2CooldownDuration);
    }

    public int ultimate(Base target) {
        damageDealt = 0;
        setCurrMana(currMana - ultimateManaCost);
        Random random = new Random();
        int baseDamage = 100;
        int num = random.nextInt(100);
        int missingHp = target.maxHp - target.currHp;
        int additionalDamage = (num / 10);
        if (num <= 9) {
            return 0;
        }
        int damage = (int) ((((baseDamage + additionalDamage) + (0.30 * missingHp)) * strength));
        damageDealt = damage;
        startCooldown("ultimate", ultimateCooldownDuration);
        return damage;
    }

    @Override
    public boolean chooseSkill1(Base target) {
        if (isSkill1OnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else if (currMana < skill1ManaCost) {
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " used " + skill1Name);
            int damage = skill1();
            System.out.println(getName() + " deals " + damage);
            target.setCurrHp(target.currHp - damage);
            displayStats(MainChar.this, target);
            return true; // Valid skill chosen
        }
        return false;
    }

    @Override
    public boolean chooseSkill2(Base target) {
        if (!canUseSkill2) {
            System.out.println("Skill 2 is not available yet!");
        } else if (isSkill2OnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else if (currMana < skill2ManaCost) {
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " used " + skill2Name);
            skill2();
            System.out.println(getName() + " is pumping up!");
            return true; // Valid skill chosen
        }
        return false;
    }

    @Override
    public boolean chooseUltimate(Base target) {
        if (!canUseUltimate) {
            System.out.println("Ultimate is not available yet!");
        } else if (isUltimateOnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else if (currMana < ultimateManaCost) {
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " used " + ultimateName);
            int damage = ultimate(target);
            System.out.println(getName() + " deals " + damage);
            target.setCurrHp(target.currHp - damage);
            displayStats(MainChar.this, target);
            return true; // Valid skill chosen
        }
        return false;
    }

    public boolean oneHit(Base target){
        int damage = 99999;
        target.setCurrHp(target.getCurrHp() - damage);
        displayStats(MainChar.this, target);
        damageDealt = damage;
        return true;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
    public int damageDealt(){
        return damageDealt;
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

    public int amountIncreased(){
        return amount;
    }
    public boolean isSkillOnCooldown() {
        return isSkill1OnCooldown || isSkill2OnCooldown || isUltimateOnCooldown;
    }

    public int getCurrency(){ // currency in this game
        return petals;
    }

    public void setCurrency(int petals){
        this.petals = petals;
    }

    public int addCurrency(int amount){
        petals += amount;
        return  petals;
    }

	public void setMaxHp(int amount) {
        maxHp += amount;
	}
}

