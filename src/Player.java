
import java.util.HashMap;
import java.util.Map;

public class Player extends Character {

    private int mana;
    private int experience;
    private int petals; // Currency
    private Map<String, Skill> skills;
    private Inventory inventory;
    private boolean hasFox; // Track if the player has unlocked the Fox companion

    public Player(String name, int health, int mana) {
        super(name, health);
        this.mana = mana;
        this.experience = 0;
        this.petals = 100;
        this.skills = new HashMap<>();
        this.inventory = new Inventory(10);
        this.hasFox = false;

        // Add default skill (unlocked at the start)
        skills.put("Rose Petal Strike", new DamageSkill("Rose Petal Strike", 0, 8, 1,
                "The player strikes with a flourish, as if scattering rose petals, embodying beauty and grace."));
    }

    @Override
    public void attack(Character enemy) {
        Skill skill = skills.get("Rose Petal Strike");
        if (skill != null && mana >= skill.getManaCost()) {
            int damage = skill.getDamage();
            enemy.takeDamage(damage);
            mana -= skill.getManaCost();
            System.out.println(name + " attacks " + enemy.getName() + " with " + skill.getName() + " for " + damage + " damage.");
        } else {
            System.out.println("Not enough mana or skill not found.");
        }
    }

    public void heal(int amount) {
        health += amount;
        System.out.println(name + " heals for " + amount + ". Health: " + health);
    }

    public int getMana() {
        return mana;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean hasFox() {
        return hasFox;
    }

    public void unlockFox() {
        this.hasFox = true;
        System.out.println("Fox companion unlocked! You can now use Fox's Trick.");
    }

    public void addItemToInventory(String itemName) {
        inventory.addItem(new ConsumableItem(itemName, "A useful item.")); // Example item
    }

    public void useHealingSkill(Player target) {
        if (skills.containsKey("Rose's Embrace")) {
            Skill healingSkill = skills.get("Rose's Embrace");
            int healAmount = (int) (health * 0.20); // Heal 20% of max health
            target.heal(healAmount);
            System.out.println(name + " uses " + healingSkill.getName() + " and heals " + target.getName() + " for " + healAmount + " health.");
        }
    }

    public void reduceMana(int amount) {
        mana -= amount;
        if (mana < 0) {
            mana = 0; // Prevent mana from going below 0
        }
        System.out.println(name + " now has " + mana + " mana.");
    }

    public Skill getSkill(String skillName) {
        return skills.get(skillName);
    }

    void restoreMana(int i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
