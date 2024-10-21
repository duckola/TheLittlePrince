import java.util.Scanner;

public class Main extends MainGame{
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        MainGame game = new MainGame();
        System.out.print("Enter your name, adventurer: ");
        String name = scan.nextLine();

        MainChar character = new MainChar(name);
        VainMan boss1 = new VainMan();
        if (!game.gameStart(character,boss1)){
            System.exit(0);
        }

        game.stopOperations(character, boss1);

        Drunkard boss2 = new Drunkard();
        if (!game.gameStart(character,boss2)){
            System.exit(0);
        }

        game.stopOperations(character, boss2);
    }
}
