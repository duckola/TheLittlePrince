

public class Item {

    private String name;
    private boolean stackable;
    private int itemCount;
    private Effect effect;
    private long cooldownDuration;
    private String type; // e.g., "Potion", "Weapon", "Armor"

    //for shop
    private String effectString;
    private int cost;


    //for shop
    public Item(String name, String effectString, int cost) {
        this.name = name;
        this.effectString = effectString;
        this.cost = cost;
        this.itemCount = 1; // Default quantity
    }

    //for item use
    public Item(String name, boolean stackable, Effect effect) {
        this.name = name;
        this.stackable = stackable;
        this.itemCount = 1; // Default quantity
        this.effect = effect;
    }
    
    public String getItemByName() {
        return name;
    }

    public long getCooldownDuration() {
        return cooldownDuration;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void addItemCount(int amount) {
        this.itemCount += amount;
    }

    public void applyEffect(Playable player) {
    }

    //for shop
    public String getEffect() {
        return effectString;
    }

    public int getCost() {
        return cost;
    }

    public void describe() {
    }

}