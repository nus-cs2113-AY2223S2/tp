package bagpacker.packingfunc;

import java.util.ArrayList;
import java.util.List;

/**
 * PackingList class contains methods to manipulate the users packing list
 */
public class PackingList {


    private static ArrayList<Item> itemList = new ArrayList<>();
    private static int targetIndex = -1;

    /**
     * Getter for itemList
     *
     * @return list of items
     */
    public static ArrayList<Item> getItemList() {
        return itemList;
    }
    /**
     * Adds a task to the list.
     */
    public void addItem(Item toAdd) {
        itemList.add(toAdd);
    }

    public static Item get(int targetIndex) {
        return itemList.get(targetIndex);
    }

}
