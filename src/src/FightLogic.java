
// import java.util.Scanner;

// public class FightLogic extends Base {

//     public boolean fail(Playable character, Base enemy) {
//         Scanner scan = new Scanner(System.in);
//         boolean lose = true;
//         gameOver = false;

//         character.start();
//         enemy.start();
//         character.updateMana();
//         enemy.attack(character);

//         while (!gameOver && character.getCurrHp() > 0 && enemy.getCurrHp() > 0) {
//             // Skill selection
//             String skill;
//             boolean validSkill = false;

//             while (!validSkill) {
//                 character.displayChoices(character);
//                 skill = scan.nextLine();

//                 if (character.getCurrHp() <= 0) {
//                     System.out.println(character.getName() + " has been defeated! You Lose!");
//                     gameOver = true;
//                     break;
//                 }
//                 if (character.isParalyzed()) {
//                     System.out.println("You are paralyzed!");
//                 } else {
//                     switch (skill) {
//                         // case "1" ->
//                             validSkill = character.chooseSkill1(enemy);
//                         case "2" ->
//                             validSkill = character.chooseSkill2(enemy);
//                         case "3" ->
//                             validSkill = character.chooseUltimate(enemy);
//                         default ->
//                             System.out.println("Invalid choice! Please select a valid skill.");
//                     }
//                 }
//             }

//             if (enemy.getCurrHp() <= 0) {
//                 System.out.println("Congratulations! You Won against " + enemy.getName() + "\n");
//                 character.levelUp(enemy.getExpYield());
//                 gameOver = true;
//                 lose = false;
//             }
//         }
//         gameOver = true;
//         System.out.println("\n-----Game Over-----");
//         character.resetState();
//         enemy.resetState();
//         return lose;// Terminate the application
//     }
// }
