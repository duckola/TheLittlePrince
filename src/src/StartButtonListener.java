
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StartButtonListener implements ActionListener {

    private Narration narration;
    private Chapter1 chapter1;

    public StartButtonListener() {
        this.narration = new Narration(); 
        this.chapter1 = new Chapter1();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chapter1.showStoryline();

        BackgroundPanel backgroundPanel = (BackgroundPanel) Main.frame.getContentPane();
        backgroundPanel.setVisible(false);

    }
}


