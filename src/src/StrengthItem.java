
public class StrengthItem extends Item{
    public enum Rarity{
        COMMON, UNCOMMON, RARE
    }
    
    private Rarity rarity;
    private double increaseAmount;

    public StrengthItem(Rarity rarity){
        super(rarity + " Strength Item", "Increases strength based on size", getCost(rarity));
        this.rarity = rarity;
        this.increaseAmount = getIncreaseAmount(rarity);
    }

    public static int getCost(Rarity rarity){
        switch(rarity){
            case COMMON: return 15;
            case UNCOMMON: return 25;
            case RARE: return 45;
            default: return 15;
        }
    }

    public static double getIncreaseAmount(Rarity rarity){
        switch(rarity){
            case COMMON: return 0.25;
            case UNCOMMON: return 0.5;
            case RARE: return 1.0;
            default: return 0.25;
        }
    }


    @Override
    public void applyEffect(Playable character){
        System.out.println("Increasing strength by " + Math.ceil(increaseAmount * 100) + "%");
        character.setStrength(getIncreaseAmount(rarity) + character.getStrength());
    }

    public Rarity getRarity() {
        return rarity;
    }

    
}