import java.util.Scanner;

public class MainGame extends Base{
    public boolean gameStart(MainChar character, Base enemy){
        Scanner scan = new Scanner(System.in);
        boolean gameOver = false;
        boolean leave = false;
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
                    leave = true;
                }
                default ->{
                    System.out.println("Enter a valid choice!");
                    choice = scan.nextLine();
                }
            }
        }

        if(leave){
            System.out.println("\n-----Leaving Round-----");
            return false;
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
                System.out.println("Congratulations! You Won against "+enemy.getName()+"\n");
               // character.levelUp(enemy.getExpYield());
                gameOver = true;
            }
        }
        System.out.println("\n-----Game Over-----");
        resetCharacterState(character);
        resetEnemyState(enemy);
        return true;// Terminate the application
    }
}
