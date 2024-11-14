
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Fox extends Playable {

    private static Fox instance; // Single instance reference
    private boolean isFoxAcquired = false;
    protected boolean isDodging = false;
    protected long dodgingDuration = 5000;
    private final GameStageImplementation game = new GameStageImplementation();

    private int damage = 0;
    private int amount;
    private int petals = 100;

    public Fox() {
        super("Fox", 100, 100, 10, 100, 0, 100, 1, 1.3, "Mystic Claws", "Swift Strike", "Foxfire Barrage");
        skill1CooldownDuration = 7000;
        skill1ManaCost = 15;
        skill2CooldownDuration = 13000;
        skill2ManaCost = 40;
        ultimateCooldownDuration = 30000;
        ultimateManaCost = 55;
    }

    public static synchronized Fox getInstance() {
        if (instance == null) {
            instance = new Fox();
        }
        return instance;
    }

    @Override
    public void resetTimers() {
        if (skill2start != null) {
            skill2start.cancel(); // Cancel the attack timer
        }
        if (ultimateStart != null) {
            ultimateStart.cancel(); // Cancel the attack timer
        }
        isDodging = false;
    }

    @Override
    public void levelUp(int addExp) {
        while (addExp > 0) {
            int expNeeded = maxExp - currExp; // Experience needed to reach next level
            if (addExp >= expNeeded) {
                // Enough experience to level up
                currLevel += 1;
                System.out.println(getName() + " Leveled Up! " + currLevel);
                strength += 0.2;
                maxHp += 10;
                currHp += 10;
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

        setCurrMana(currMana - skill1ManaCost);
        Random random = new Random();
        int baseDamage = 20;
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        damage = (int) ((baseDamage + additionalDamage) * strength);
        startCooldown("skill1", skill1CooldownDuration);
        return damage;
    }

    @Override
    public void setCurrHp(int hp) {
        if (isDodging) {
            System.out.println("Fox completely dodged the attack!");
            return; // If dodging, no damage is applied
        }
        if (hp < 0) {
            currHp = 0;
        } else {
            currHp = Math.min(hp, maxHp);
        }
    }

    public int skill2() {
        setCurrMana(currMana - skill2ManaCost);
        isDodging = true;

        Random random = new Random();
        int baseDamage = 20; //setback to 5
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        damage = (int) ((baseDamage + additionalDamage) * strength);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isDodging = false; // Deactivate the shield after a set duration
            }
        }, dodgingDuration);
        startCooldown("skill2", skill2CooldownDuration);
        return damage;
    }

    public int ultimate() {
        setCurrMana(currMana - ultimateManaCost);
        int baseDamage = 26; // setback to 5 as per your requirement
        int totalDamage = 0;
        startCooldown("ultimate", ultimateCooldownDuration);

        for (int i = 0; i < 5; i++) { // Loop for 5 hits per second
            Random random = new Random();
            int num = random.nextInt(100);
            int additionalDamage = num / 10;

            if (num > 9) { // Only apply damage if `num` is greater than 9
                damage = (int) ((baseDamage + additionalDamage) * strength);
                totalDamage += damage;
                System.out.println("Fox deals " + damage); // Print the damage dealt
            }
        }
        return totalDamage;
    }

    @Override
    public boolean chooseSkill1(Base target) {
        if (isSkill1OnCooldown) {
            System.out.println("Skill is on cooldown!");
        } else if (currMana < skill1ManaCost) {
            System.out.println("Insufficient Mana!");
        } else {
            System.out.println(getName() + " used " + skill1Name);
            damage = skill1();
            System.out.println(getName() + " deals " + damage);
            target.setCurrHp(target.currHp - damage);
            displayStats(Fox.this, target);
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
            damage = skill2();
            System.out.println(getName() + " swiftly attacked and dealt " + damage);
            target.setCurrHp(target.currHp - damage);
            displayStats(Fox.this, target);
            return true;
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
            damage = ultimate();
            System.out.println(getName() + " dealt a total of " + damage + " damage");
            target.setCurrHp(target.currHp - damage);
            displayStats(Fox.this, target);
            return true; // Valid skill chosen
        }
        return false;
    }

    public int damageDealt() {
        return damage;
    }

    public void foxAcquired() {
        isFoxAcquired = true;
        Main.frame.getContentPane().removeAll();
        game.setDialogueIndex(61);
        game.displayDialogue(61, 73, new String[]{"Main Character:", "Fox"}, null);
    }

    public boolean isFoxAcquired(){
        return isFoxAcquired;
    }
}
