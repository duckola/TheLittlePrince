
import java.util.ArrayList;
import java.util.List;

// NPC Class
public class NPC {

    private String name;
    private String dialogue;
    private List<Quest> quests;

    public NPC(String name, String dialogue) {
        this.name = name;
        this.dialogue = dialogue;
        this.quests = new ArrayList<>();
    }

    public void interact(Player player) {
        System.out.println(name + ": " + dialogue);
        for (int i = 0; i < quests.size(); i++) {
            System.out.println((i + 1) + ". " + quests.get(i).getDescription());
        }
        System.out.println("Choose a quest to accept:");
        // Add logic to let the player select a quest
    }

    public void addQuest(Quest quest) {
        quests.add(quest);
    }

    public List<Quest> getQuests() {
        return quests; // Get the list of quests for this NPC
    }
}
