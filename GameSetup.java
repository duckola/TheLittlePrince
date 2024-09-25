public class GameSetup {
    public static void main(String[] args) {
        // Create Planets
        Planet kingsPlanet = new Planet("King's Planet", "A world ruled by a king who believes he controls everything.");
        Planet vainMansPlanet = new Planet("Vain Man's Planet", "A tiny world dominated by a man obsessed with his reflection.");
        Planet drunkardsPlanet = new Planet("Drunkard's Planet", "A desolate planet where a man drinks to forget his shame.");
        Planet businessmansPlanet = new Planet("Businessman's Planet", "A planet filled with ledgers and ticking clocks.");
        Planet lamplightersPlanet = new Planet("Lamplighter's Planet", "A planet where the lamplighter must keep up with its rapid rotation.");
        Planet geographersPlanet = new Planet("Geographer's Planet", "A world covered in books and maps, yet never explored.");

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

        // Continue with game logic...
    }
}
