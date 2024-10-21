
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
        gbc.insets = new Insets(0, 50, 50, 50);
        gbc.anchor = GridBagConstraints.CENTER;

        // Create Start Game Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        startButton.setFocusPainted(false); // No focus border
        startButton.setOpaque(true); // Ensure background color is painted
        startButton.addActionListener(new StartButtonListener(frame));

        // Add hover effect to Start Game button
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        backgroundPanel.add(startButton, gbc);

        gbc.gridx = 4; // Move X-POSITION for the next button

        // Create Settings Button
        JButton settingsButton = new JButton("Tutorial");
        settingsButton.setFont(new Font("Serif", Font.BOLD, 20));
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        settingsButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        settingsButton.setFocusPainted(false); // No focus border
        settingsButton.setOpaque(true); // Ensure background color is painted
        settingsButton.addActionListener(new SettingsButtonListener());

        // Add hover effect to Settings button
        settingsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingsButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingsButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        backgroundPanel.add(settingsButton, gbc);

        frame.add(backgroundPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {

    private final Image backgroundImage;

    public BackgroundPanel() {
        backgroundImage = new ImageIcon("../images/princeAndFox.jpg").getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
