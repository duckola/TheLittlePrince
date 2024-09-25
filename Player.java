
import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Quest> acceptedQuests;
    private List<String> inventory; // List to hold inventory items

    public Player(String name) {
        this.name = name;
        this.acceptedQuests = new ArrayList<>();
        this.inventory = new ArrayList<>(); // Initialize the inventory
    }

    public String getName() {
        return name;
    }

    public void acceptQuest(Quest quest) {
        acceptedQuests.add(quest); // Add the quest to the player's list
    }

    public void completeQuest(Quest quest) {
        quest.completeQuest(); // Complete the specified quest
    }

    public boolean hasCompletedCurrentPlanet() {
        // Logic to determine if the player has completed all quests on the current planet
        for (Quest quest : acceptedQuests) {
            if (!quest.isCompleted()) {
                return false; // If any quest is not completed, return false
            }
        }
        return true; // All quests completed
    }

    public void addToInventory(String item) {
        inventory.add(item); // Add item to the inventory
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (String item : inventory) {
                System.out.println("- " + item);
            }
        }
    }
}
