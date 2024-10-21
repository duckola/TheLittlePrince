import java.util.Timer;
import java.util.TimerTask;
public class Base{
    protected String name;
    protected int maxHp;
    protected int maxExp;
    protected int maxMana;

    protected int currHp;
    protected int currExp;
    protected int currMana;
    protected int currLevel;
    protected double strength;

    protected String skill1Name = " ";
    protected String skill2Name = " ";
    protected String ultimateName = " ";

    protected int expYield;
    protected Timer attackTimer;

    public Base(){

    }

    // 4 parameters for Boss Enemies
    public Base(String name, int currHp, int maxHp, double strength, String skill1Name,
                String skill2Name, String ultimateName, int expYield){
        this.name = name;
        this.currHp = currHp;
        this.maxHp = maxHp;
        this.strength = strength;
        this.skill1Name = skill1Name;
        this.skill2Name = skill2Name;
        this.ultimateName = ultimateName;
        this.expYield = expYield;
        attackTimer = new Timer();
    }

    // Main Character
    public Base(String name, int maxHp,
                int maxMana, int maxExp, 
                int currHp, int currExp, 
                int currMana, int currLevel, 
                double strength, String skill1Name,
                String skill2Name, String ultimateName){
        this.name = name;
        this.maxHp = maxHp;
        this.maxMana = maxMana;
        this.maxExp = maxExp;
        this.currHp = currHp;
        this.currExp = currExp;
        this.currMana = currMana;
        this.currLevel = currLevel;
        this.strength = strength;
        this.skill1Name = skill1Name;
        this.skill2Name = skill2Name;
        this.ultimateName = ultimateName;
        attackTimer = new Timer();
    }

    // Support Character

    
    public int getCurrHp(){
        return currHp;
    }

    public int getCurrExp(){
        return currExp;
    }

    public int getCurrMana(){
        return currMana;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public int getMaxExp(){
        return maxExp;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public String getName(){
        return name;
    }

    public int getCurrLevel(){
        return currLevel;
    }

    public double getStrength(){
        return strength;
    }

    public void setStrength(double strength){
        this.strength = strength;
    }

    public String nameSkill1(){
        return skill1Name;
    }
    
    public String nameSkill2(){
        return skill2Name;
    }

    public String nameUltimate(){
        return ultimateName;
    }

    public int getExpYield() {
        return expYield;
    }

    public void attack(Base target){

    }
    public void setReduceHp(int damage){
        if(currHp - damage <= 0){
            currHp = 0;
        } else {
            currHp -= damage;
        }
    }

    public void setAddHp(int damage){
        if(currHp + damage >= maxHp){
            currHp = maxHp;
        } else {
            currHp += maxHp;
        }
    }

     public void setAddMana(int addMana){    
        if(currMana + addMana >= maxMana){
            currMana = maxMana;
        } else {
            currMana += addMana;
        }
    }

    public void updateMana() {
        Timer manaTimer = new Timer();
        manaTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Only increase mana if it's not already full
                if (currMana < maxMana) {
                    currMana = Math.min(currMana + 2, maxMana); // Increases mana by 2, capped at maxMana
                }
            }
        }, 0, 1000); // 0 initial delay, 1000 ms (1 second) interval
    }

    public void setReduceMana(int reduceMana){
        if(currMana - reduceMana <= 0){
            currMana = 0;
        } else {
            currMana -= reduceMana;
        }
    }

    public void levelUp(int addExp){
        while(addExp > 0){
            if(currExp + addExp >= maxExp){
                currLevel += 1;
                System.out.println(name + " Leveled Up! " + currLevel);
                strength += 0.2;
                maxHp += 15;
                currHp += 15;
                maxMana += 10;
                addExp -= (maxExp - currExp);
                currExp = 0;
                maxExp = (int) Math.ceil(maxExp*1.3);
            } else {
                currExp += addExp;
                addExp = 0;
            }
        }
    }

    public void clear(){  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void displayStats(Base team, Base enemy){
     // Display updated stats
        System.out.println("\n-----Character Stats-----");
        System.out.println("HP: " + team.getCurrHp());
        System.out.println("Mana: " + team.getCurrMana());
        System.out.println("-------------------------");
        System.out.println("-----Enemy Stats-----");
        System.out.println("HP: " + enemy.getCurrHp());
        System.out.println("---------------------");
    }

    public boolean gameOver(Base character, Base enemy){
        return character.getCurrHp() <= 0 || enemy.getCurrHp() <= 0;
    }

    public void displayChoiceSkill(Base character){
        System.out.println("\nEnter a skill:");
        System.out.println("1. "+ character.nameSkill1());
        System.out.println("2. "+ character.nameSkill2());
        System.out.println("3. "+ character.nameUltimate()+"\n");
    }
    public void stopOperations(MainChar character, Base enemy) {
        if (character.attackTimer != null) {
            character.attackTimer.cancel(); // Stop character's timer
            character.attackTimer = null;
        }
        if (enemy.attackTimer != null) {
            enemy.attackTimer.cancel(); // Stop enemy's timer
            enemy.attackTimer = null;
        }
    }
}
