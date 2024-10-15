
import javax.swing.*;

public abstract class GameStage {

    private JFrame frame;
    private Narration narration;

    public GameStage(JFrame frame, Narration narration) {
        this.frame = frame;
        this.narration = narration;
    }

    public abstract void showStage();
    public abstract  void displayNarration(String narrationText);
    public abstract void displayChoices(JButton... buttons);


}
