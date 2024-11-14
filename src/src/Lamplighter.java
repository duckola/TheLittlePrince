
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Lamplighter extends Playable {

    private static Lamplighter instance; // Single instance reference

    protected boolean isShieldActive = false;
    protected long shieldDuration = 10000;

    private int damage = 0;
    private int amount;
    private int petals = 100;

    public Lamplighter() {
        super("Lamplighter", 200, 100, 10, 200, 0, 100, 1, 0.7, "Flame Burst", "Molten Shield", "Blast and Absorb");
        skill1CooldownDuration = 4000;
        skill1ManaCost = 12;
        skill2CooldownDuration = 10000;
        skill2ManaCost = 30;
        ultimateCooldownDuration = 35000; //35000
        ultimateManaCost = 60;
    }

    public static synchronized Lamplighter getInstance() {
        if (instance == null) {
            instance = new Lamplighter();
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
        isShieldActive = false;
    }

    @Override
    public void levelUp(int addExp) {
        while (addExp > 0) {
            int expNeeded = maxExp - currExp; // Experience needed to reach next level
            if (addExp >= expNeeded) {
                // Enough experience to level up
                currLevel += 1;
                System.out.println(getName() + " Leveled Up! " + currLevel);
                strength += 0.1;
                maxHp += 30;
                currHp += 30;
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

    @Override
    public void setCurrHp(int hp) {
        if (hp < currHp && isShieldActive) {
            damage = currHp + hp;
            int upDamage = (int) Math.ceil(damage / 2.0);
            System.out.println("Lamplighter's shield absorbed " + (currHp - upDamage) + " damage! ");
            if (hp < 0) {
                currHp = 0;
            } else {
                currHp = (int) Math.floor(damage / 2.0);
            }
            return; // If the shield is active, no damage is applied
        }
        if (hp < 0) {
            currHp = 0;
        } else {
            currHp = Math.min(hp, maxHp);
        }
    }

    public int skill1() {
        setCurrMana(currMana - skill1ManaCost);
        Random random = new Random();
        int baseDamage = 13; //setback to 5
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        damage = (int) ((baseDamage + additionalDamage) * strength);
        startCooldown("skill1", skill1CooldownDuration);
        return damage;
    }

    public int skill2() {
        setCurrMana(currMana - skill2ManaCost);
        isShieldActive = true;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                isShieldActive = false; // Deactivate the shield after a set duration
            }
        }, shieldDuration);

        Random random = new Random();
        int baseDamage = 10; //setback to 5
        int num = random.nextInt(100);
        int additionalDamage = num / 10;
        if (num <= 9) {
            return 0;
        }
        damage = (int) (((baseDamage + additionalDamage) + (0.3 * (maxHp - currHp))) * strength);
        startCooldown("skill2", skill2CooldownDuration);
        return damage;
    }

    public int[] ultimate() {
        setCurrMana(currMana - ultimateManaCost);
        int[] healdmg = new int[2];

        Random random = new Random();
        int baseDamage = 150;
        int num1 = random.nextInt(100);
        int additionalDamage = (num1 / 10);
        damage = (int) (((baseDamage + additionalDamage) * strength));
        if (num1 <= 9) {
            damage = 0;
        }

        int baseHeal = 45;
        int num2 = random.nextInt(100);
        int additionalHeal = (num2 / 10);
        int heal = (baseHeal + additionalHeal);
        if (num2 <= 9) {
            heal = 0;
        }

        startCooldown("ultimate", ultimateCooldownDuration);
        healdmg[0] = damage;
        healdmg[1] = heal;
        return healdmg;
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
            displayStats(Lamplighter.this, target);
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
            System.out.println(getName() + " reflected " + damage + " damage");
            target.setCurrHp(target.currHp - damage);
            displayStats(Lamplighter.this, target);
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
            int[] healdmg = ultimate();
            damage = healdmg[0];
            int heal = healdmg[1];
            System.out.println(getName() + " deals " + damage + " damage and heals " + heal);
            setCurrHp(currHp + heal);
            target.setCurrHp(target.currHp - damage);
            displayStats(Lamplighter.this, target);
            return true; // Valid skill chosen
        }
        return false;
    }

    public int damageDealt() {
        return damage;
    }

}
