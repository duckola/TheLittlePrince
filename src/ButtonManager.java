
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ButtonManager {

    private JPanel buttonPanel;
    private JFrame frame;

    public ButtonManager(JFrame frame) {
        this.frame = frame;
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.BLACK);
    }

    public void displayButtons(List<JButton> buttons) {
        buttonPanel.removeAll();
        for (JButton button : buttons) {
            button.setFont(new Font("Serif", Font.PLAIN, 18));
            button.setForeground(Color.WHITE);
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.revalidate();
        frame.repaint();
    }

    public void displayMainButtons() {
        List<JButton> mainButtons = List.of(
                createButton("Explore the World", this::explore),
                createButton("Check Inventory", this::checkInventory),
                createButton("Shop", this::shop)
        );
        displayButtons(mainButtons);
    }

    public void displayFightButtons() {
        List<JButton> fightButtons = List.of(
                createButton("Skill 1", this::useSkill1),
                createButton("Skill 2", this::useSkill2),
                createButton("Skill 3", this::useSkill3)
        );
        displayButtons(fightButtons);
    }

    private JButton createButton(String text, Runnable action) {
        JButton button = new JButton(text);
        button.addActionListener(e -> action.run());
        return button;
    }

    // Example placeholders for button actions
    private void explore() {
        System.out.println("Exploring the world...");
    }

    private void checkInventory() {
        System.out.println("Checking inventory...");
    }

    private void shop() {
        System.out.println("Opening shop...");
    }

    private void useSkill1() {
        System.out.println("Using Skill 1...");
    }

    private void useSkill2() {
        System.out.println("Using Skill 2...");
    }

    private void useSkill3() {
        System.out.println("Using Skill 3...");
    }
}
