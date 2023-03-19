package seedu.duke;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private final ArrayList<Item> itemInventory = new ArrayList<>();
    private final HashMap<String, Item> upcCodes = new HashMap<>();
    private final Trie trie = new Trie();
    private final HashMap<String, ArrayList<Item>> itemNameHash = new HashMap<>();



    /**
     * Retrieves all the UPC Codes in the inventory currently
     *
     * @return upcCodes a hashmap of UPC code with its corresponding item in the inventory
     */
    public HashMap<String, Item> getUpcCodes() {
        return upcCodes;
    }

    public  ArrayList<Item> getItemInventory() {
        return itemInventory;
    }

    public Trie getTrie() {
        return trie;
    }

    public HashMap<String, ArrayList<Item>> getItemNameHash() {
        return itemNameHash;
    }


//    public static void filterCategory(String category) {
//        ArrayList<Item> filteredItems = new ArrayList<>();
//        for (Item item : itemInventory) {
//            if (item.getCategory().equals(category)) {
//                filteredItems.add(item);
//            }
//        }
//        if (filteredItems.isEmpty()) {
//            Ui.printEmptySearch();
//            return;
//        }
//        Ui.printSearchItems(filteredItems);
//    }

//    public static void filterTags(final String tag) {
//        ArrayList<Item> filteredItems = new ArrayList<>();
//        for (Item item : itemInventory) {
//            if (item.getTags().isEmpty()) {
//                continue;
//            }
//            for (String itemTag : item.getTags()) {
//                if (itemTag.equals(tag)) {
//                    filteredItems.add(item);
//                }
//            }
//        }
//        if (filteredItems.isEmpty()) {
//            Ui.printEmptySearch();
//            return;
//        }
//        Ui.printSearchItems(filteredItems);
//    }
//
//    public static void filterPrice(final double price, final String mode) {
//        ArrayList<Item> filteredItems = new ArrayList<>();
//        for (Item item : itemInventory) {
//            switch (mode) {
//            case "p/lt":
//                if (item.getPrice() < price) {
//                    filteredItems.add(item);
//                }
//                break;
//            case "p/gt":
//                if (item.getPrice() > price) {
//                    filteredItems.add(item);
//                }
//                break;
//            case "p/let":
//                if (item.getPrice() <= price) {
//                    filteredItems.add(item);
//                }
//                break;
//            case "p/get":
//                if (item.getPrice() >= price) {
//                    filteredItems.add(item);
//                }
//                break;
//            default:
//                break;
//            }
//        }
//        if (filteredItems.isEmpty()) {
//            Ui.printEmptySearch();
//            return;
//        }
//        Ui.printSearchItems(filteredItems);
//    }
//
//

}
