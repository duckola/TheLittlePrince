
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class Inventory {

    private static Inventory instance;

    private int maxCapacity = 20;
    private JFrame frame; // Reference to the JFrame

    private Map<Item, Long> potionCooldowns = new HashMap<>();
    Map<String, Item> items = new HashMap<>();

    private Inventory() {
        maxCapacity = 20;
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    public Inventory(JFrame frame, int maxCapacity) {
        this.frame = frame; // Initialize the frame reference
        this.maxCapacity = maxCapacity;
    }

    public boolean addItem(Item item) {
        if (item != null && !isFull()) {
            String itemName = item.getItemByName();
            if (items.containsKey(itemName)) {
                items.get(itemName).addItemCount(1); // Increase count if stackable
                System.out.println(itemName + " count increased to " + items.get(itemName).getItemCount());
            } else {
                items.put(itemName, item);
                System.out.println(itemName + " has been added to your inventory.");
            }
            return true;
        }
        return false;
    }

    public boolean hasItem(String itemName) {
        boolean result = items.containsKey(itemName) && items.get(itemName).getItemCount() > 0;
        System.out.println("[DEBUG] Checking if " + itemName + " exists in inventory: " + result);
        return result;
    }

    public void removeItem(String itemName) {
        Item item = findItem(itemName);
        if (item != null) {
            items.remove(item);
            System.out.println(itemName + " has been removed from your inventory.");
        }
    }

    // Find an item by name
    public Item findItem(String itemName) {
        if (items.containsKey(itemName)) {
            System.out.println("Found " + itemName + " in the inventory.");
            return items.get(itemName);
        } else {
            System.out.println(itemName + " not found in the inventory.");
            return null;
        }
    }

    public void useItem(Item item, Playable player) {
        System.out.println("[DEBUG] Attempting to use item: " + item);

        if (items.containsKey(item.getItemByName())) {
            item.applyEffect(player);
            item.addItemCount(-1);
            System.out.println("[DEBUG] Used " + item.getItemByName() + ". Remaining count: " + item.getItemCount());

            if (item.getItemCount() <= 0) {
                items.remove(item.getItemByName());
                System.out.println("[DEBUG] " + item.getItemByName() + " removed from inventory as count reached zero.");
            }

            System.out.println(item.getItemByName() + " has been used. Remaining count: " + item.getItemCount());
        } else {
            System.out.println("[DEBUG] Item not found in inventory.");
        }
    }

    public boolean isFull() {
        return items.size() >= maxCapacity; // Check if current size reaches capacity
    }

    public boolean isPotionOnCooldown(String potionName) {
        Item potion = findItem(potionName); // Assuming you have a method to get an item by name
        Long cooldownEnd = potionCooldowns.get(potion);
        System.out.println("[DEBUG] Checking cooldown for " + potionName + ": false");

        return cooldownEnd != null && cooldownEnd > System.currentTimeMillis();

    }

    public long getPotionCooldownTime(String potionName) {
        Item potion = findItem(potionName);
        Long cooldownEnd = potionCooldowns.get(potion);
        if (cooldownEnd != null) {
            return (cooldownEnd - System.currentTimeMillis()) / 1000;
        } else {
            return 0;
        }
    }

    public void showInventory() {
        JFrame inventoryFrame = new JFrame("Inventory");
        inventoryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inventoryFrame.setSize(300, 300);

        // Create a list model for the inventory items
        DefaultListModel<String> inventoryModel = new DefaultListModel<>();
        for (Map.Entry<String, Item> entry : items.entrySet()) {
            inventoryModel.addElement(entry.getKey() + ": " + entry.getValue().getItemCount());
        }

        // Create a JList to display the items
        JList<String> itemList = new JList<>(inventoryModel);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow single item selection

        // Handle item selection (e.g., show item details or usage options)
        itemList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String itemName = inventoryModel.getElementAt(selectedIndex);
                    // Show item details or usage options here
                    System.out.println("Selected item: " + itemName);
                    // useItem(itemName,);
                }
            }
        });

        inventoryFrame.add(new JScrollPane(itemList));
        inventoryFrame.setVisible(true);
    }


    // public boolean hasManaPotion(String name, Size size) {
    //     for (Item item : items.values()) {
    //         if (item instanceof ManaPotion && item.getItemByName().equals(name) && ((ManaPotion) item).getSize() == size) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // public boolean hasHealthPotion(String name, Size size) {
    //     for (Item item : items.values()) {
    //         if (item instanceof HealthPotion && item.getItemByName().equals(name) && ((HealthPotion) item).getSize() == size) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }


    public boolean hasManaPotion() {
        for (Item item : items.values()) {
            if (item instanceof ManaPotion) {
                return true; // Found at least one ManaPotion
            }
        }
        return false; // No ManaPotions found
    }

    public boolean hasHealthPotion() {
        for (Item item : items.values()) {
            if (item instanceof HealthPotion) {
                return true; // Found at least one ManaPotion
            }
        }
        return false; // No ManaPotions found
    }

    public boolean hasStrengthPotion(String name, StrengthItem.Rarity rarity) {
        for (Item item : items.values()) {
            if (item instanceof StrengthItem && item.getItemByName().equals(name) && ((StrengthItem) item).getRarity() == rarity) {
                return true;
            }
        }
        return false;
    }

}
