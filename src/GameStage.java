
import javax.swing.JButton;

public interface GameStage {
    void showStoryline();
    void showStage();
    void displayNarration(String narrationText);
    void displayChoices(JButton... choices);
    void blackScreen();
    void straightDialogue();
    void typeWriterEffect(String text, int delay);
}
