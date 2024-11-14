import java.awt.Color;

import javax.swing.JFrame;

public class Main {
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Beyond the Stars: Trials of Survival");
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // Prevent resizing
        frame.setVisible(true);

        
        GameImplementation game = new GameImplementation();
        game.start();
    }
}