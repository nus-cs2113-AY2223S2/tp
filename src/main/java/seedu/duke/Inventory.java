package seedu.duke;

import seedu.duke.trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final ArrayList<Item> itemInventory = new ArrayList<>();
    private final HashMap<String, Item> upcCodes = new HashMap<>();
    private final Trie trie = new Trie();
    private final HashMap<String, ArrayList<Item>> itemNameHash = new HashMap<>();


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


    public Trie getTrie() {
        return trie;
    }

    /**
     * Retrieves all the items Name Hash in the inventory currently.
     *
     * @return itemInventory an arraylist of items in the inventory
     */
    public HashMap<String, ArrayList<Item>> getItemNameHash() {
        return itemNameHash;
    }


}
