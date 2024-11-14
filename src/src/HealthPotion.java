public class HealthPotion extends Item{
    // public enum Size{
    //     SMALL, MEDIUM, LARGE;
    // }
    private Size size;
    private int restoreAmount;

    public HealthPotion(Size size){
        super(size + " Health Potion", "Restores health based on size", getCost(size));
        this.size = size;
        this.restoreAmount = getRestoreAmount(size);
    }

    public static int getCost(Size size){
        switch(size){
            case SMALL: return 10;
            case MEDIUM: return 20;
            case LARGE: return 30;
            default: return 10;
        }
    }

    public static int getRestoreAmount(Size size){
        switch(size){
            case SMALL: return 15;
            case MEDIUM: return 25;
            case LARGE: return 35;
            default: return 15;
        }
    }

    @Override
    public void applyEffect(Playable character){
        System.out.println("Restoring " + restoreAmount + " HP!");
        character.setCurrMana(character.getCurrHp() + restoreAmount);
    }

    public Size getSize() {
        return size;
    }
}