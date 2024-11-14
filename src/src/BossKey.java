
public class BossKey extends Item {

    private BossType bossType;

    private String name;
    public BossKey(BossType bossType) {
        super("Key", false, null); // Inherit from Item with specific values
        this.bossType = bossType;
        this.name = "Key to " + bossType.toString(); // Set name based on boss type
    }
}
