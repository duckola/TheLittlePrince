
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartButtonListener implements ActionListener {

    private JFrame frame;
    private Narration narration;
    private StartGame startGame;

    public StartButtonListener(JFrame frame) {
        this.frame = frame; 
        this.narration = new Narration(); 
        this.startGame = new StartGame(frame,narration);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        startGame.showStoryline();
    }
}
