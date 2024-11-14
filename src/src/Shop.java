
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Shop {

    private List<Item> shopInv;
    private Playable player;
    private Inventory inventory;
    private ItemManager itemManager;

    public Shop(Playable character) {
        this.shopInv = new ArrayList<>();
        this.player = character;
        this.itemManager = new ItemManager();
        this.inventory = Inventory.getInstance();

        shopInv.add(new Rose());
        shopInv.add(new Clothes1());

        // Create potions using ItemManager methods
        shopInv.add(itemManager.createHealthPotion(Size.SMALL));
        shopInv.add(itemManager.createHealthPotion(Size.MEDIUM));
        shopInv.add(itemManager.createHealthPotion(Size.LARGE));
        shopInv.add(itemManager.createManaPotion(Size.SMALL));
        shopInv.add(itemManager.createManaPotion(Size.MEDIUM));
        shopInv.add(itemManager.createManaPotion(Size.LARGE));
        shopInv.add(itemManager.createStrengthPotion(StrengthItem.Rarity.COMMON));
        shopInv.add(itemManager.createStrengthPotion(StrengthItem.Rarity.UNCOMMON));
        shopInv.add(itemManager.createStrengthPotion(StrengthItem.Rarity.RARE));

    }

    public void displayShop(Playable player) {
        JFrame shopFrame = new JFrame("Shop");
        shopFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        shopFrame.setSize(500, 500);

        JPanel shopPanel = new JPanel();
        shopPanel.setLayout(new BorderLayout());

        // Add a label to display the player's currency at the top
        JLabel currencyLabel = new JLabel("Your Currency: " + player.getCurrency());
        shopPanel.add(currencyLabel, BorderLayout.NORTH);

        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new GridLayout(shopInv.size() + 1, 2));

        // Add item labels and buy buttons
        for (int i = 0; i < shopInv.size(); i++) {
            Item item = shopInv.get(i);
            JLabel itemLabel = new JLabel(item.getItemByName() + " - P" + item.getCost());
            JButton buyButton = new JButton("Buy");

            buyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (player.getCurrency() >= item.getCost()) {
                        player.setCurrency(player.getCurrency() - item.getCost());
                        inventory.addItem(item);
                        shopFrame.dispose();
                        JOptionPane.showMessageDialog(null, "You bought " + item.getItemByName() + "!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds!");
                    }
                    // Update the currency label here:
                    currencyLabel.setText("Your Currency: " + player.getCurrency());
                }
            });

            itemPanel.add(itemLabel);
            itemPanel.add(buyButton);
        }

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> shopFrame.dispose());
        itemPanel.add(closeButton);

        shopPanel.add(itemPanel, BorderLayout.CENTER);

        shopFrame.add(shopPanel);
        shopFrame.setVisible(true);
    }

    public void displayItems() {
        System.out.println("Welcome to the Shop! These are the available items: ");
        for (int i = 0; i < shopInv.size(); i++) {
            System.out.println((i + 1) + ". " + shopInv.get(i).getItemByName());
        }
    }

    public void buyItem(int itemNumber) {
        int itemIndex = itemNumber - 1;
        if (itemIndex < -1 || itemIndex >= shopInv.size()) {
            System.out.println("Invalid item selection.");
            return;
        }

        Item item = shopInv.get(itemIndex);
        if (player.getCurrency() >= item.getCost()) {
            player.setCurrency(player.getCurrency() - item.getCost());

            // shopInv.remove(itemIndex);
            System.out.println("You purchased: " + item.getItemByName());
            inventory.addItem(item);
            displayCurrency();
        } else {
            System.out.println("Not enough currency to buy item!");
        }
    }

    public void displayCurrency() {
        System.out.println("\n-------------------------------------");
        System.out.println("You have " + player.getCurrency() + " petals.");
        System.out.println("-------------------------------------\n");
    }

}
