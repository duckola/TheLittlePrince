public class Rose extends Item{
    private int restoreAmount;

    public Rose(){
        super("Rose", "Restore 10 HP", 8);
        this.restoreAmount = 10;
    }

    @Override
    public void applyEffect(Playable character){
        System.out.println("Restoring " + restoreAmount + " HP.");
        character.setCurrHp(character.getCurrHp() + restoreAmount);
        
    }
}
    