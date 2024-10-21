
public abstract class Skill {

    protected String name;
    protected int manaCost;
    protected int damage;

    public Skill(String name, int manaCost, int damage) {
        this.name = name;
        this.manaCost = manaCost;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getDamage() {
        return damage;
    }

    public abstract void useSkill(Player player, Character target);
}
