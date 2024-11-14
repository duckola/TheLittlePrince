    // Custom JPanel with rounded corners

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
    class RoundedPanel extends JPanel {

        private Color backgroundColor;
        private int cornerRadius;

        public RoundedPanel(Color backgroundColor, int cornerRadius) {
            this.backgroundColor = backgroundColor;
            this.cornerRadius = cornerRadius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(backgroundColor);
            g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            super.paintComponent(g);
        }
    }