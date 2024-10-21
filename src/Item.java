
public abstract class Item {

    private String name;
    private String type;
    private boolean stackable;
    private int quantity;
    private String effect;

    public Item(String name, String type, boolean stackable, String effect) {
        this.name = name;
        this.type = type;
        this.stackable = stackable;
        this.quantity = 1;
        this.effect = effect;
    }

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return stackable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addQuantity(int amount) {
        this.quantity += amount;
    }

    public abstract void applyEffect(Player player);
}
