
import java.util.Scanner;

public class Main {

    private static Player player;

    public static void main(String[] args) {
        // Initialize the player
        player = new Player("Traveler");

        // Create Planets
        Planet kingsPlanet = new Planet("King's Planet", "A world ruled by a king who believes he controls everything.");
        Planet vainMansPlanet = new Planet("Vain Man's Planet", "A tiny world dominated by a man obsessed with his reflection.");
        Planet drunkardsPlanet = new Planet("Drunkard's Planet", "A desolate planet where a man drinks to forget his shame.");
        Planet businessmansPlanet = new Planet("Businessman's Planet", "A planet filled with ledgers and ticking clocks.");
        Planet lamplightersPlanet = new Planet("Lamplighter's Planet", "A planet where the lamplighter must keep up with its rapid rotation.");
        Planet geographersPlanet = new Planet("Geographer's Planet", "A world covered in books and maps, yet never explored.");

        // Create NPCs and add quests
        createNPCs(kingsPlanet, vainMansPlanet, drunkardsPlanet, businessmansPlanet, lamplightersPlanet, geographersPlanet);

        // Start the game
        startGame(kingsPlanet); // Start the game on the King's Planet
    }

    private static void createNPCs(Planet kingsPlanet, Planet vainMansPlanet, Planet drunkardsPlanet,
            Planet businessmansPlanet, Planet lamplightersPlanet, Planet geographersPlanet) {
        // Create NPCs
        NPC king = new NPC("The King", "I rule over everything!");
        king.addQuest(new Quest("The Royal Decree: Collect three star fragments."));
        king.addQuest(new Quest("A Lesson in Leadership: Organize a meeting with villagers."));
        kingsPlanet.addNPC(king);

        NPC vainMan = new NPC("The Vain Man", "Look at me!");
        vainMan.addQuest(new Quest("Mirror, Mirror: Find a rare mirror for me."));
        vainMan.addQuest(new Quest("The Contest of Beauty: Compete in a beauty contest."));
        vainMansPlanet.addNPC(vainMan);

        NPC drunkard = new NPC("The Drunkard", "I drink to forget.");
        drunkard.addQuest(new Quest("A Bottle of Memories: Retrieve a special drink."));
        drunkard.addQuest(new Quest("The Broken Promise: Help confront his past."));
        drunkardsPlanet.addNPC(drunkard);

        NPC businessman = new NPC("The Businessman", "I own the stars!");
        businessman.addQuest(new Quest("Counting Stars: Help count his stars."));
        businessman.addQuest(new Quest("The Ledger of Life: Solve a logic puzzle."));
        businessmansPlanet.addNPC(businessman);

        NPC lamplighter = new NPC("The Lamplighter", "I light the stars!");
        lamplighter.addQuest(new Quest("The Cycle of Light: Assist in a timed challenge."));
        lamplighter.addQuest(new Quest("Finding Balance: Teach about rest."));
        lamplightersPlanet.addNPC(lamplighter);

        NPC geographer = new NPC("The Geographer", "I document everything!");
        geographer.addQuest(new Quest("Mapping the Unknown: Explore uncharted areas."));
        geographer.addQuest(new Quest("A Journey of Experience: Convince to explore."));
        geographersPlanet.addNPC(geographer);
    }

    private static void startGame(Planet startingPlanet) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Little Prince RPG!");
        System.out.println("You are on " + startingPlanet.getName() + ": " + startingPlanet.getDescription());

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Interact with NPCs");
            System.out.println("2. Check your inventory");
            System.out.println("3. Move to the next planet (if requirements are met)");
            System.out.println("4. Exit game");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    interactWithNPCs(startingPlanet, scanner);
                    break;
                case 2:
                    player.displayInventory();
                    break;
                case 3:
                    if (player.hasCompletedCurrentPlanet()) {
                        System.out.println("You may now move to the next planet!");
                        // Logic to move to the next planet goes here
                    } else {
                        System.out.println("You must complete all quests on this planet first.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void interactWithNPCs(Planet planet, Scanner scanner) {
        System.out.println("NPCs on " + planet.getName() + ":");
        for (NPC npc : planet.getNPCs()) {
            npc.interact(player);
        }
    }
}
