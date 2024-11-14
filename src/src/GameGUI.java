
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameGUI { 
    public void display() {
        
        // Create background panel and set layout
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setVisible(true); // Make the panel visible
        backgroundPanel.setLayout(new BorderLayout());

 
        // Panel for holding buttons at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make panel transparent to show background
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

        // Create Start Game Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.BOLD, 20));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        startButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        startButton.setFocusPainted(false); // No focus border
        startButton.setOpaque(true); // Ensure background color is painted
        startButton.addActionListener(new StartButtonListener());

        // Add hover effect to Start Game button
        startButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                startButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        // Create Tutorial Button
        JButton tutorialButton = new JButton("Tutorial");
        tutorialButton.setFont(new Font("Serif", Font.BOLD, 20));
        tutorialButton.setForeground(Color.WHITE);
        tutorialButton.setBackground(new Color(255, 215, 0)); // Soft pastel yellow
        tutorialButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        tutorialButton.setFocusPainted(false); // No focus border
        tutorialButton.setOpaque(true); // Ensure background color is painted
        tutorialButton.addActionListener(new SettingsButtonListener());

        // Add hover effect to Tutorial button
        tutorialButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tutorialButton.setBackground(new Color(255, 245, 0)); // Lighter shade on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                tutorialButton.setBackground(new Color(255, 215, 0)); // Reset to original color
            }
        });

        // Add buttons to buttonPanel
        buttonPanel.add(startButton);
        buttonPanel.add(tutorialButton);

        // Add buttonPanel to the bottom of backgroundPanel
        backgroundPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Set backgroundPanel as the content pane of the frame
        Main.frame.setContentPane(backgroundPanel);
        Main.frame.setVisible(true);
        
    }


}


class BackgroundPanel extends JPanel {

    private Image backgroundImage;
    private boolean visible = true; // Flag for visibility


    public void setVisible(boolean visible) {
        this.visible = visible;
        repaint(); // Trigger a repaint to reflect the change
    }
    
    public BackgroundPanel() {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream("images/title_screen.png");
            if (inputStream == null) {
                throw new IOException("Image not found in path: images/title_screen.png");
            }
            backgroundImage = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            backgroundImage = null; // Or set a default image
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null && visible) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this); // Set to fill the panel
        }
    }

}
