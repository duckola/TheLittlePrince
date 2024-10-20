import java.util.Scanner;

public class Main extends MainGame{
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        MainGame game = new MainGame();
        System.out.print("Enter your name, adventurer: ");
        String name = scan.nextLine();
        MainChar character = new MainChar(name);
        VainMan enemy = new VainMan();
        game.gameStart(character,enemy);
    }
}
