
public class Enemy extends Base {

    private String enemyType; // Type of enemy, e.g., "Goblin", "Dragon", etc.

    // Constructor for Enemy with additional type information
    public Enemy(String name, int maxHp, int strength, String enemyType, int expYield) {
        super(name, maxHp, maxHp, strength, "Enemy Attack 1", "Enemy Attack 2", "Enemy Ultimate", expYield);
        this.enemyType = enemyType; // Set the enemy type
    }

    // Example method for a basic attack on a MainChar target
    public int basicAttack(MainChar target) {
        int damage = (int) (strength * 0.8); // Example damage calculation
        target.setReduceHp(damage); // Reduce the target's HP
        System.out.println(getName() + " attacks " + target.getName() + " for " + damage + " damage!");
        return damage;
    }

    // Example method to perform a skill attack (you can customize the damage logic)
    public int skillAttack(MainChar target) {
        int damage = (int) (strength * 1.2); // Skill attack might do more damage
        target.setReduceHp(damage);
        System.out.println(getName() + " uses a skill on " + target.getName() + " for " + damage + " damage!");
        return damage;
    }

    // Getter for the enemy type
    public String getEnemyType() {
        return enemyType;
    }

    // Override the toString method to include enemy type
    @Override
    public String toString() {
        return enemyType + " " + getName() + " [HP: " + getCurrHp() + "/" + getMaxHp() + "]";
    }
}
