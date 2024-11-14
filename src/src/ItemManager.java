
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ItemManager {

    private Map<String, Item> items = new HashMap<>();

    public ItemManager() {
        items.put("Rose", new Rose()); // Add the Rose object to the map
        items.put("Clothes1", new Clothes1()); // Add the Rose object to the map
        items.put("Drunkard Key", new BossKey(BossType.DRUNKARD));
        items.put("VainMan Key", new BossKey(BossType.VAINMAN));
        items.put("King Key", new BossKey(BossType.KING));    }
    
    public Size getRandomSize() {
        Random random = new Random();
        return Size.values()[random.nextInt(Size.values().length)];
    }



    public StrengthItem.Rarity getRandomRarity() {
        Random random = new Random();
        return StrengthItem.Rarity.values()[random.nextInt(StrengthItem.Rarity.values().length)];
    }

    public Item createHealthPotion(Size size) {
        Effect healthEffect = new Effect(HealthPotion.getRestoreAmount(size), 1000);
        return new Item("Health Potion (" + size.name() + ")", true, healthEffect);
    }

    public Item createManaPotion(Size size) {
        Effect manaEffect = new Effect(ManaPotion.getRestoreAmount(size), 1000);
        return new Item("Mana Potion (" + size.name() + ")", true, manaEffect);
    }

    public Item createStrengthPotion(StrengthItem.Rarity rarity) {
        // Implement logic to determine strength bonus based on rarity
        Effect strengthEffect = new Effect(StrengthItem.getIncreaseAmount(rarity), 5000);
        return new Item("Strength Potion (" + rarity.name() + ")", true, strengthEffect);
    }

    public Item getItem(String name) {
        return items.get(name);
    }
}
