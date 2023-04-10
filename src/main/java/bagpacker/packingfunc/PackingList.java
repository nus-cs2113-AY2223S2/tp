package bagpacker.packingfunc;
import java.util.ArrayList;

/**
 * PackingList class contains methods to manipulate the users packing list
 */
public class PackingList {
    private static ArrayList<Item> itemList = new ArrayList<>();

    /**
     * Checks if item of the same name as itemName is found in the current packing list and returns true
     *
     * @param itemName name of item to return
     * @return the true if found, else null
     */
    public static boolean itemFinder(String itemName) {
        for (Item item : itemList) {
            if (itemName.equals(item.getItemName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if items contain keyword in their names in the current packing list
     * @param keyword String of keyword to be searched
     * @return true if item name containing keyword is found
     */
    public static boolean keywordFinder(String keyword) {
        for (Item item : itemList) {
            if (item.getItemName().contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * getExistingItem returns the object of type Item of the same name as input itemName
     * <p>
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

    /**
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
    /**
     * Edits the itemToEdit's total quantity to the newTotalQuantity
     *
     * @param itemToEdit name of the item to edit total quantity
     * @param newTotalQuantity the total quantity to change to
     */
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
     * @param toAdd item to be added to the packing list
     */
    public void addItem(Item toAdd) {
        itemList.add(toAdd);
    }

    /**
     * Removes an item from the list.
     * @param toDelete item to be deleted from the packing list
     */
    public void deleteItem(Item toDelete) {
        itemList.remove(toDelete);
    }

    /**
     * Packs an item by a certain quantity
     * @param toPack the item to pack
     * @param quantity value to increase the packed quantity of the given item toPack by
     */
    public void packItem(Item toPack, int quantity) {
        toPack.setPacked(quantity);
    }

    /**
     * Marks an item as unpacked in the list.
     * @param toUnpack the item to unpack
     * @param quantity value to decrease the packed quantity of the given item toPack by
     */
    public void unpackItem(Item toUnpack, int quantity) {
        toUnpack.setUnpacked(quantity);
    }

    /**
     * Returns the target item.
     * @param targetIndex position of item in packingList
     * @return item
     */
    public static Item get(int targetIndex) {
        return itemList.get(targetIndex);
    }
    /**
     * Returns the target item.
     * @param itemName position of item in packingList
     * @return item
     */
    public static Item getItemByName(String itemName) {
        for (Item item : PackingList.getItemList()) {
            if (itemName.equals(item.getItemName())) {
                return item;
            }
        }
        return null;
    }
    /**
     * Returns size of packing list
     */
    public int size() {
        return itemList.size();
    }


}
