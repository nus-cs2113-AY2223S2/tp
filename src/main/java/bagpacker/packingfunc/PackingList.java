package bagpacker.packingfunc;
import bagpacker.commands.Command;

import java.util.ArrayList;

/**
 * PackingList class contains methods to manipulate the users packing list
 */
public class PackingList {
    private static ArrayList<Item> itemList = new ArrayList<>();

    /***
     * Checks if item of the same name as itemName is found in the current packing list
     *
     * @param itemName
     * @return true if item of same name as itemName is found else false
     */
    public static boolean itemFinder(String itemName) {
        for (Item item : itemList) {
            if (itemName.equals(item.getItemName())) {
                return true;
            }
        }
        return false;
    }

    /***
     * getExistingItem returns the object of type Item of the same name as input itemName
     *
     * this should only be called after checking that item does exist in the itemList
     *
     * @param itemName
     * @return item of the name itemName
     */
    public static Item getExistingItem(String itemName) {
        Item existingItem = null;
        for (int i = 0; i < itemList.size(); i++) {
            if (itemName.equals(itemList.get(i).getItemName())) {
                existingItem = itemList.get(i);
            }
        }
        assert(existingItem != null);
        return existingItem;
    }
    /***
     * Adds the added quantity to the current total quantity of an item in the itemList
     *
     * @param itemName name of the item to add total quantity to be packed
     * @param addQty the quantity to add to the old total
     */
    public static void addToItemQuantity(String itemName, int addQty) {
        Item itemToAdd = getExistingItem(itemName);
        int totalQuantity = itemToAdd.getTotalQuantity();
        itemToAdd.setTotalQuantity(totalQuantity + addQty);
    }

    public static void editTotalQuantity(Item itemToEdit, int newTotalQuantity) {
        itemToEdit.setTotalQuantity(newTotalQuantity);
    }

    public static void setItemList(ArrayList<Item> itemList) {
        PackingList.itemList = itemList;
    }

    /**
     * Getter for itemList
     *
     * @return list of items
     */
    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * Adds an item to the list.
     */
    public void addItem(Item toAdd) {
        itemList.add(toAdd);
    }

    /**
     * Removes an item from the list.
     */
    public void deleteItem(Item toDelete) {
        itemList.remove(toDelete);
    }

    /**
     * Marks an item as packed in the list.
     */
    public void packItem(Item toPack, int quantity) {
        toPack.setPacked(quantity);
    }

    /**
     * Marks an item as unpacked in the list.
     */
    public void unpackItem(Item toPack, int quantity) {
        toPack.setUnpacked(quantity);
    }

    public static Item get(int targetIndex) {
        return itemList.get(targetIndex);
    }

    /**
     * Returns size of packing list
     */
    public int size() {
        return itemList.size();
    }


}
