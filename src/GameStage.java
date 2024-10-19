
import javax.swing.JButton;

public interface GameStage {

    void showStoryline();
    void showStage();
    void displayNarration(String narrationText);
    void displayChoices(JButton... choices);
    void displaySpeakerName(String speakerName);
    void displayDialogue(String dialogue);
    void createDialogBox(); // Method to create the dialog box
    void setupMainFrame(); // Method to setup the main frame
    void blackScreen();
    void straightDialogue();
    void typeWriterEffect(String text, int delay);
}
