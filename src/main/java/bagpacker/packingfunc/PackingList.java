package bagpacker.packingfunc;

import java.util.ArrayList;

/**
 * PackingList class contains methods to manipulate the users packing list
 */
public class PackingList {


    private static ArrayList<Item> itemList = new ArrayList<>();

    private static int targetIndex = -1;
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
     * Adds quantity to quantity already packed
     */
    public void packItem(Item toPack, int quantity) {
        toPack.setPacked(quantity);
    }

    /**
     * Subtracts quantity from quantity already packed
     */
    public void unpackItem(Item toPack, int quantity) {
        toPack.setPacked(quantity);
    }

    public static Item get(int targetIndex) {
        return itemList.get(targetIndex);
    }

    /**
     * Returns size of packing list
     */
    public int size(){
        return itemList.size();
    }


}
