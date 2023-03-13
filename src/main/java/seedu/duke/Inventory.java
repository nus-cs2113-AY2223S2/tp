package seedu.duke;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.trie.Trie;

import java.util.ArrayList;
import java.util.HashMap;

import static seedu.duke.Parser.in;

public class Inventory {
    private static ArrayList<Item> items = new ArrayList<>();
    private static HashMap<String, Item> upcCodes = new HashMap<>();
    private static Trie trie = new Trie();
    private static HashMap<String, ArrayList<Item>> itemNameHash = new HashMap<>();

    public static void filterCategory(String category) {
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getCategory().equals(category)) {
                filteredItems.add(item);
            }
        }
        if (filteredItems.isEmpty()) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printSearchItems(filteredItems);
    }

    public static void filterTags(String tag) {
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            if (item.getTags().isEmpty()) {
                continue;
            }
            for (String itemTag : item.getTags()) {
                if (itemTag.equals(tag)) {
                    filteredItems.add(item);
                }
            }
        }
        if (filteredItems.isEmpty()) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printSearchItems(filteredItems);
    }

    public static void filterPrice(double price, String mode) {
        ArrayList<Item> filteredItems = new ArrayList<>();
        for (Item item : items) {
            switch (mode) {
            case "p/lt":
                if (item.getPrice() < price) {
                    filteredItems.add(item);
                }
                break;
            case "p/gt":
                if (item.getPrice() > price) {
                    filteredItems.add(item);
                }
                break;
            case "p/let":
                if (item.getPrice() <= price) {
                    filteredItems.add(item);
                }
                break;
            case "p/get":
                if (item.getPrice() >= price) {
                    filteredItems.add(item);
                }
                break;
            default:
                break;
            }
        }
        if (filteredItems.isEmpty()) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printSearchItems(filteredItems);
    }

    /**
     * Add an item object into the inventory
     *
     * @param item The target item which the user wants to add to the inventory.
     */
    public static void addItem(Item item) {
        if (upcCodes.containsKey(item.getUpc())) {
            Ui.printDuplicateAdd();
        } else {
            upcCodes.put(item.getUpc(), item);
            items.add(item);
            Ui.printSuccessAdd();
            String itemName = item.getName().toLowerCase();
            if (itemNameHash.containsKey(itemName)) {
                itemNameHash.get(itemName).add(item);
            } else {
                itemNameHash.put(itemName, new ArrayList<>());
                itemNameHash.get(itemName).add(item);
            }
            trie.add(itemName);
        }
    }

    /**
     * Search for an item in the inventory by its unique UPC number and returns search query
     *
     * @param upc numeric UPC number which user provides for querying
     */
    public static void searchUPC(String upc) {
        if (!upcCodes.containsKey(upc)) {
            Ui.printEmptySearch();
            return;
        }
        Ui.printSearchUPCItem(upcCodes.get(upc));
    }

    /**
     * Search for an item in the inventory by keyword and returns search query
     *
     * @param keyword alphanumeric keyword which user provides for querying
     */
    public static void search(String keyword) {
        ArrayList<String> resultNames = trie.prefixFind(keyword);

        if (resultNames.size() == 0) {
            Ui.printEmptySearch();
            return;
        }
        System.out.println(Ui.LINE);
        ArrayList<Item> resultItems = new ArrayList<>();
        for (String name : resultNames) {
            for (Item item : itemNameHash.get(name)) {
                resultItems.add(item);
            }
        }
        Ui.printSearchItems(items);
        System.out.println(Ui.LINE);
    }

    /**
     * Searches for the item in the ArrayList and changes the item attributes according to the wishes of the user.
     *
     * @param editInfo The array of strings containing all edit information as defined by the user inputs.
     */
    public static void editItem(String[] editInfo) {
        try {
            Item updatedItem = retrieveItemFromArrayList(editInfo);
            Item oldItem = new Item(updatedItem.getName(), updatedItem.getUpc(), updatedItem.getQuantity().toString(),
                    updatedItem.getPrice().toString());
            for (int data = 1; data < editInfo.length; data += 1) {
                updateItemInfo(updatedItem, editInfo[data]);
            }
            String oldItemName = oldItem.getName().toLowerCase();
            String newItemName = updatedItem.getName().toLowerCase();
            if (oldItemName != newItemName && itemNameHash.get(oldItemName).size() == 1) {
                itemNameHash.remove(oldItemName);
                trie.remove(oldItemName);
                ArrayList<Item> newItemArrayList = new ArrayList<>();
                newItemArrayList.add(updatedItem);
                itemNameHash.put(newItemName, newItemArrayList);
            } else {
                itemNameHash.get(oldItemName).remove(oldItem);
                if (!itemNameHash.containsKey(newItemName)) {
                    itemNameHash.put(newItemName, new ArrayList<Item>());
                }
                itemNameHash.get(newItemName).add(updatedItem);
            }
            trie.add(newItemName);
            upcCodes.remove(oldItem.getUpc());
            upcCodes.put(updatedItem.getUpc(), updatedItem);
            Ui.printEditDetails(oldItem, updatedItem);
        } catch (EditErrorException eee) {
            Ui.printItemNotFound();
        }
    }

    /**
     * Based on the user input, the appropriate attribute (Price, Quantity, Name etc.) of the item will be targeted
     * and subsequently edited to the user's respective input.
     *
     * @param item The target item in the ArrayList in which the user wants to edit.
     * @param data The user input which contains the information to be used to update the item attributes.
     */
    private static void updateItemInfo(Item item, String data) {
        if (data.contains("n/")) {
            String newName = data.replaceFirst("n/", "");
            item.setName(newName);
        } else if (data.contains("qty/")) {
            String updatedQuantity = data.replaceFirst("qty/", "");
            Integer newQuantity = Integer.valueOf(updatedQuantity);
            item.setQuantity(newQuantity);
        } else if (data.contains("p/")) {
            String updatedPrice = data.replaceFirst("p/", "");
            Double newPrice = Double.valueOf(updatedPrice);
            item.setPrice(newPrice);
        } else {
            Ui.printInvalidEditCommand();
        }
    }

    /**
     * Cycles through the ArrayList to obtain the item required to be interacted with by the user.
     *
     * @param editInfo The array of strings that contain the user inputs.
     * @return Returns the variable of type "Item", which is the item in question to be interacted with by the user.
     * @throws EditErrorException Exception related to all errors generated by the edit command.
     */
    public static Item retrieveItemFromArrayList(String[] editInfo) throws EditErrorException {
        String upcCode = editInfo[0].replaceFirst("upc/", "");
        if (!upcCodes.containsKey(upcCode)) {
            throw new EditErrorException();
        }
        Item selectedItem = upcCodes.get(upcCode);
        return selectedItem;
    }

    /**
     * Temporary List Method created by Kai Wen for Edit Function Testing.
     */
    public static void listAll() {
        for (Item x : items) {
            System.out.println((items.indexOf(x) + 1) + ".");
            System.out.println("Item Name: " + x.getName());
            System.out.println("UPC Code: " + x.getUpc());
            System.out.println("Quantity Available: " + x.getQuantity());
            System.out.println("Item Price: " + x.getPrice());
            System.out.println(" ");
        }
    }

    public static void removeItemAtIndex(int index) {
        Item itemToRemove = items.get(index);
        Ui.printConfirmMessage();
        String confirmation = in.nextLine();
        switch (confirmation.toUpperCase()) {
        case "Y":
            String itemName = itemToRemove.getName().toLowerCase();
            String upcCode = itemToRemove.getUpc();
            int i = items.indexOf(itemToRemove);
            upcCodes.remove(upcCode);
            items.remove(i);
            if (itemNameHash.get(itemName).size() == 1) {
                itemNameHash.remove(itemName);
            } else {
                itemNameHash.get(itemName).remove(itemToRemove);
            }
            Ui.printSuccessRemove(itemToRemove);
            break;
        case "N":
            Ui.printNotRemoving();
            break;
        default:
            Ui.printInvalidReply();
            break;
        }
    }

    public static void listItems() {
        if (!items.isEmpty()) {
            Ui.printSuccessList();
            String table = Ui.printTable(items);
            System.out.println(table);
            System.out.println(Ui.LINE);
        } else {
            Ui.printInvalidList();
        }
    }

    /**
     * Removes item from the array list of items and hashmap of upc
     * using item's unique upc.
     *
     * @param upc UPC of item to be removed from the list.
     * @return index of item in the arraylist for testing; otherwise -1.
     */
    public static int removeByUpc(String upc) {
        String upcCode = upc.replaceFirst("upc/", "");
        searchUPC(upcCode);
        if (!upcCodes.containsKey(upcCode)) {
            return -1;
        }
        Item itemToRemove = upcCodes.get(upcCode);
        Ui.printConfirmMessage();
        String confirmation = in.nextLine();
        switch (confirmation.toUpperCase()) {
        case "Y":
            String itemName = itemToRemove.getName().toLowerCase();
            int i = items.indexOf(itemToRemove);
            upcCodes.remove(upcCode);
            items.remove(i);
            if (itemNameHash.get(itemName).size() == 1) {
                itemNameHash.remove(itemName);
            } else {
                itemNameHash.get(itemName).remove(itemToRemove);
            }
            Ui.printSuccessRemove(itemToRemove);
            return i;
        case "N":
            Ui.printNotRemoving();
            break;
        default:
            Ui.printInvalidReply();
            break;
        }
        return -1;
    }

    public static ArrayList<Item> getItemList() {
        return items;
    }
}
