

public class Chapter2 {

    private String playeName;
    private GameStageImplementation game;
    private final Narration narration;
    private ExploreUI explore;
    private Playable player;

    public Chapter2(Playable player) {
        this.game = new GameStageImplementation(); // Check for null initialization
        this.player = player;
        this.narration = new Narration();  // Ensure this object is not null    
    }

    public void startChapter2() {

        Main.frame.getContentPane().removeAll();

        game.showChapter("images/chapter2.png");
        game.pause();
        game.displayPlanetName("images/planet2.png");
        game.pause();
        
        introNarration();
    }
    
    private void introNarration(){
        game.typeWriterEffect(narration.getDialogue(53), 30, () -> {

            game.typeWriterEffect(narration.getDialogue(54), 30, () -> {
                princeEncounter();
            });
        });
    }

    private void princeEncounter(){
        game.setDialogueIndex(55);
        game.displayDialogue(55, 58, new String[]{"Main Charater: ", "Little Prince"}, () -> {
            game.typeWriterEffect(narration.getDialogue(58), 30, () -> {
                game.setDialogueIndex(59);
                game.displayDialogue(59, 60, new String[]{"Main Charater: "}, () -> {
                    game.typeWriterEffect(narration.getDialogue(60), 30, () -> {
                        ExploreUI exploreUI = new ExploreUI(player);
                        player.setCurrentChapter(2);
                        startExploring();
                    });
                });
            });
        });
    }

    private void startExploring() {
        explore.startExplore();
    }

}
