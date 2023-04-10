package seedu.duke.commands;

import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.utils.trie.Trie;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an abstract command class for all commands.
 */
public abstract class Command {
    protected static final String MIN_KEYWORD = "min";
    protected static final String MAX_KEYWORD = "max";
    protected static HashMap<String, ArrayList<Item>> categoryHash;
    protected final Inventory inventory;
    protected ArrayList<Item> itemInventory;
    protected HashMap<String, ArrayList<Item>> itemNameHash;
    protected HashMap<String, Item> upcCodes;
    protected Trie itemsTrie;

    protected Command(Inventory inventory) {
        this.inventory = inventory;
        this.itemInventory = inventory.getItemInventory();
        this.itemNameHash = inventory.getItemNameHash();
        this.upcCodes = inventory.getUpcCodes();
        this.itemsTrie = inventory.getTrie();
        this.categoryHash = inventory.getCategoryHash();
    }

    public abstract void run();
}
