
import java.awt.*;
import javax.swing.*;

public class GameGUI {

    public void display() {
        JFrame frame = new JFrame("Beyond the Stars: Trials of Survival");
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.BOLD, 20));
        startButton.addActionListener(new StartButtonListener(frame));
        backgroundPanel.add(startButton, gbc);

        gbc.gridy = 1; // Move down for the next button

        JButton settingsButton = new JButton("Settings");
        settingsButton.setFont(new Font("Serif", Font.BOLD, 20));
        settingsButton.addActionListener(new SettingsButtonListener());
        backgroundPanel.add(settingsButton, gbc);

        frame.add(backgroundPanel);
        frame.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {

    private final Image backgroundImage;

    public BackgroundPanel() {
        backgroundImage = new ImageIcon("../images/startscreen.jpg").getImage(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
