package seedu.duke.objects;

import seedu.duke.utils.trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;


public class Inventory {
    private final ArrayList<Item> itemInventory = new ArrayList<>();
    private final HashMap<String, Item> upcCodes = new HashMap<>();
    private final Trie trie = new Trie();
    private final HashMap<String, ArrayList<Item>> itemNameHash = new HashMap<>();
    private final HashMap<String, ArrayList<Item>> upcCodesHistory = new HashMap<>();
    private AlertList alertList = new AlertList();
    private final HashMap<String, ArrayList<Item>> categoryHash = new HashMap<>();


    /**
     * Retrieves all the UPC Codes in the inventory currently.
     *
     * @return upcCodes a hashmap of UPC code with its corresponding item in the inventory
     */
    public HashMap<String, Item> getUpcCodes() {
        return upcCodes;
    }

    /**
     * Retrieves all the items in the inventory currently
     *
     * @return itemInventory an arraylist of items in the inventory
     */
    public ArrayList<Item> getItemInventory() {
        return itemInventory;
    }

    public double getTotalValue() {
        double totalCost = 0;
        for (Item item : itemInventory) {
            totalCost += item.getPrice() * item.getQuantity();
        }
        return Math.round(totalCost * 100.0) / 100.0;
    }

    /**
     * Returns the item with the most/least quantity in the inventory.
     *
     * @param findMaximum true if the maximum quantity is to be found, false if the minimum quantity is to be found
     * @return itemUPC the UPC code of the item with the most/least quantity
     */
    public String getItemWithQuantityExtremes(boolean findMaximum) {
        int extremumQuantity = findMaximum ? 0 : Integer.MAX_VALUE;
        String itemUPC = "";
        for (Item item : itemInventory) {
            int quantity = item.getQuantity();
            if ((findMaximum && quantity > extremumQuantity) ||
                    (!findMaximum && quantity < extremumQuantity)) {
                extremumQuantity = quantity;
                itemUPC = item.getUpc();
            }
        }
        return itemUPC;
    }


    public Trie getTrie() {
        return trie;
    }

    public AlertList getAlertList() {
        return alertList;
    }

    public void setAlertList(AlertList alertList) {
        this.alertList = alertList;
    }

    /**
     * Retrieves all the items Name Hash in the inventory currently.
     *
     * @return itemInventory an arraylist of items in the inventory
     */
    public HashMap<String, ArrayList<Item>> getItemNameHash() {
        return itemNameHash;
    }

    public HashMap<String, ArrayList<Item>> getUpcCodesHistory() {
        return upcCodesHistory;
    }

    public HashMap<String, ArrayList<Item>> getCategoryHash() {
        return categoryHash;
    }

}
