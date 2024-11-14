public class Clothes1 extends Item{
    private int additionalHp;

    public Clothes1(){
        super("Vitality Cloak", "Increases maximum HP.", 30);
        additionalHp = 20;
    }

    @Override
    public void applyEffect(Playable character) {

        System.out.println("Max HP increasing to 20");
        character.setMaxHp(character.getMaxHp() + additionalHp);
    }
}
