
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartButtonListener implements ActionListener {

    private JFrame frame;
    private Narration narration;
    private Chapter1 chapter1;
    public StartButtonListener(JFrame frame) {
        this.frame = frame; 
        this.narration = new Narration(); 
        this.chapter1 = new Chapter1(frame,narration);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chapter1.showStoryline();
    }
}


