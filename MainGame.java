import java.util.Scanner;

public class MainGame extends Base{
    public void gameStart(MainChar character, Base enemy){
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        System.out.println("Do you wish to explore?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        String choice = scan.nextLine();
        boolean validChoice = false;
        while (!validChoice) {
            switch (choice) {
                case "1" -> {
                    System.out.println("-----You have entered Asteroid-326-----");
                    System.out.println("-----Prepare to fight the Vain Man-----");
                    validChoice = true;
                }
                case "2" -> {
                    validChoice = true;
                    gameOver = true;
                }
                default ->{
                    System.out.println("Enter a valid choice!");
                    choice = scan.nextLine();
                }
            }
        }
        boolean hasAttacked = false;
        boolean isRegenMana = false;

        while (!gameOver && character.getCurrHp() > 0 && enemy.getCurrHp() > 0) {
            // Skill selection
            String skill;
            boolean validSkill = false;
            while (!validSkill) {
                character.displayChoiceSkill(character);
                skill = scan.nextLine();

                if (character.getCurrHp() <= 0) {
                    character.attackTimer.cancel(); // Cancel character's attack timer
                    enemy.attackTimer.cancel();
                    System.out.println(character.getName() + " has been defeated! You Lose!");
                    gameOver = true;
                    break;
                } else {
                    switch (skill) {
                        case "1" -> validSkill = character.chooseSkill1(enemy);
                        case "2" -> validSkill = character.chooseSkill2();
                        case "3" -> validSkill = character.chooseUltimate(enemy);
                        default -> System.out.println("Invalid choice! Please select a valid skill.");
                    }
                }
                if(!isRegenMana) {
                    character.updateMana();
                    isRegenMana = true;
                }
            }
            if (!hasAttacked){
                enemy.attack(character);
                hasAttacked = true;
            }
            if (enemy.getCurrHp() <= 0) {
                character.attackTimer.cancel(); // Cancel character's attack timer
                enemy.attackTimer.cancel();
                System.out.println("Congratulations! You Won against "+enemy.getName()+"\n");
                character.levelUp(enemy.getExpYield());
                gameOver = true;
            }
        }

        if(gameOver){
            System.out.println("\n-----Round Finished-----");
        }
        System.exit(0); // Terminate the application
    }
}
