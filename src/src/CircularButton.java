
import javax.swing.*;
import java.awt.*;

public class CircularButton extends JButton {

    public CircularButton(String text) {
        super(text);
        setBorderPainted(true);
        setContentAreaFilled(true);
        setFocusPainted(true);
        setBackground(Color.YELLOW);
    }

    public JButton createRoundedButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Set button color
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); // Rounded corners

                // Paint text
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw a rounded border around the button
                g2.setColor(getForeground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }

            @Override
            public boolean contains(int x, int y) {
                // Make button hit area rounded
                return new Rectangle(0, 0, getWidth(), getHeight()).contains(x, y);
            }
        };

        button.setContentAreaFilled(false); // Remove default fill to allow custom paint
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(false);
        return button;
    }
}