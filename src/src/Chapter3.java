
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class Chapter3 {

    private String playeName;
    private GameStageImplementation game;
    private final Narration narration;
    private ExploreUI explore;
    private Playable player;

    public Chapter3(Playable player) {
        this.game = new GameStageImplementation(); // Check for null initialization
        this.player = player;
        this.narration = new Narration();  // Ensure this object is not null    
    }

    public void startChapter3() {
        try {
            game.showChapter("images/planet2/png");
            game.displayPlanetName("Planet 2: Boozara [Perpetual Celebration of Delusions]");
            game.initializeBackgroundNarration("../images/chpater2bg.png");

            // Check if the dialogue is being typed out
            game.setDialogueIndex(52);
            game.displayDialogue(52, 53, new String[]{"Speaker: "}, () -> {
                game.typeWriterEffect(narration.getDialogue(53), 30);
            });
        } catch (Exception e) {
            // Catch any errors in Chapter2 setup
            JOptionPane.showMessageDialog(null, "An error occurred in Chapter 2: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
