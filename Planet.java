
import java.util.ArrayList;
import java.util.List;

// Planet Class
public class Planet {

    private String name;
    private String description;
    private List<NPC> npcs;
    private List<String> keys; // List of keys related to the planet

    public Planet(String name, String description) {
        this.name = name;
        this.description = description;
        this.npcs = new ArrayList<>();
        this.keys = new ArrayList<>();
    }

    public void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public void addKey(String key) {
        keys.add(key);
    }

    public List<NPC> getNPCs() {
        return npcs; // Get the list of NPCs on this planet
    }

    public List<String> getKeys() {
        return keys; // Get the list of keys related to the planet
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
