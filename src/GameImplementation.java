public class GameImplementation implements Game {

    @Override
    public void start() {
        GameGUI gameGUI = new GameGUI();
        gameGUI.display();
    }

    //     public void startGame() {
    //     loadChapter(1);  // Start with chapter 1
    // }

    // public void loadChapter(int chapterNumber) {
    //     switch (chapterNumber) {
    //         case 1:
    //             Chapter1 chapter1 = new Chapter1(frame, narration, this);  // Pass narration and the game instance
    //             chapter1.showStoryline();
    //             break;
    //         case 2:
    //             // Chapter2 chapter2 = new Chapter2(frame, narration, this);
    //             // chapter2.showStoryline();
    //             break;
    //         // Add additional chapters here
    //     }
    // }

    // public void completeChapter() {
    //     System.out.println("Chapter complete! Moving to the next chapter...");
    //     loadChapter(2);  // Move to the next chapter
    // }
}
