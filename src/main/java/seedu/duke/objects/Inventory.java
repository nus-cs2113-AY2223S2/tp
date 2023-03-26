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

    private final AlertList alertList = new AlertList();


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

    public String getItemWithMostQuantity() {
        int maxQuantity = 0;
        String itemUPC = "";
        for (Item item : itemInventory) {
            if (item.getQuantity() > maxQuantity) {
                maxQuantity = item.getQuantity();
                itemUPC = item.getUpc();
            }
        }
        return itemUPC;
    }

    public String getItemWithLeastQuantity() {
        int minQuantity = Integer.MAX_VALUE;
        String itemUPC = "";
        for (Item item : itemInventory) {
            if (item.getQuantity() < minQuantity) {
                minQuantity = item.getQuantity();
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

}
