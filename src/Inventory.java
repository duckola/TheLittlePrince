
import javax.swing.*;

public class Inventory {

    private int healthPotions;
    private int manaPotions;
    private JFrame frame;  // Reference to the JFrame

    public Inventory(JFrame frame) {
        this.frame = frame;  // Initialize the frame reference
        this.healthPotions = 0;  // Start with no potions
        this.manaPotions = 0;
    }

    // Method to add health potions
    public void addHealthPotion() {
        healthPotions++;
    }

    // Method to add mana potions
    public void addManaPotion() {
        manaPotions++;
    }

    // Method to use a health potion
    public boolean useHealthPotion(MainChar player) {
        if (healthPotions > 0) {
            healthPotions--;
            player.restoreHealth(50);  // Example: Restore 50 HP
            return true;
        } else {
            return false;  // No health potions left
        }
    }

    // Method to use a mana potion
    public boolean useManaPotion(MainChar player) {
        if (manaPotions > 0) {
            manaPotions--;
            player.restoreMana(50);  // Example: Restore 50 MP
            return true;
        } else {
            return false;  // No mana potions left
        }
    }

    // Getters for potion counts
    public int getHealthPotions() {
        return healthPotions;
    }

    public int getManaPotions() {
        return manaPotions;
    }

    // Show inventory in a dialog box
    public void showInventory() {
        StringBuilder inventoryMessage = new StringBuilder("Your Inventory:\n");
        inventoryMessage.append("Health Potions: ").append(getHealthPotions()).append("\n");
        inventoryMessage.append("Mana Potions: ").append(getManaPotions()).append("\n");

        // Show the inventory in a dialog box
        JOptionPane.showMessageDialog(frame, inventoryMessage.toString(), "Inventory", JOptionPane.INFORMATION_MESSAGE);
    }
}
