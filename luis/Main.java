import java.util.Scanner;

public class Main extends MainGame{
    public static void main(String [] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter your name, adventurer: ");
        String name = scan.nextLine();

        MainChar character = new MainChar(name);
        VainMan boss1 = new VainMan();
        MainGame game1 = new MainGame();
        if (game1.fail(character,boss1)){
            System.exit(0);
        }

        Drunkard boss2 = new Drunkard();
        MainGame game2 = new MainGame();
        if (game2.fail(character,boss2)){
            System.exit(0);
        }

        King boss3 = new King();
        MainGame game3 = new MainGame();
        if (game3.fail(character, boss3)){
            System.exit(0);
        }

        System.exit(0);
    }
}
