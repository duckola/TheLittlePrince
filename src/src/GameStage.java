
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public interface GameStage {

    void showChapter(String chapterName);

    void showDialogue(JTextArea storyTextArea, int dialogueIndex);

    void displayChoices(JButton... choices);

    
    // void straightDialogue();
    void typeWriterEffect(String text, int delay);

    void typeWriterEffect(JTextArea textArea, String text);

    void typeWriterEffect(String text, int delay, Runnable onComplete);

    JLabel initializeBackgroundNarration(String planetNamePath);

    JButton createButton(String text);

    Border createButtonBorder();

    void dialogue(JTextArea storyTextArea, String dialogueText, int index);

    void showNextDialogue(JTextArea storyTextArea);

    void displayDialogue(int startIndex, int endIndex, String[] speakerNames, Runnable onComplete);

    void displayPlanetName(String planetName);

}
