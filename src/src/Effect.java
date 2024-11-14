class Effect {
    private double amount;
    private long duration;
    private Playable player;

    public Effect(double amount, long duration) {
        this.amount = amount;
        this.duration = duration;
    }
    // public void apply(MainChar player, String potionName) {
    //     int amount = player.getCurrLevel() * 10;
    //     switch (potionName) {
    //         case "Health Potion":
    //             player.heal(amount * player.getMaxHp() / 100);
    //             break;
    //         case "Mana Potion":
    //             player.restoreMana(amount * player.getMaxMana() / 100);
    //             break;
    //         case "Strength Potion":
    //             player.increaseStrength((int) (player.getStrength() * 0.1));
    //             break;
    //         default:
    //             System.out.println("Invalid item effect.");
    //     }
    // }
}
