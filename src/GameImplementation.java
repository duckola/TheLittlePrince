public class GameImplementation implements Game {

    @Override
    public void start() {
        GameGUI gameGUI = new GameGUI();
        gameGUI.display();
    }
}
