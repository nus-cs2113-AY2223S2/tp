package bagpacker.packingfunc;

import java.util.ArrayList;
import java.util.List;

/**
 * PackingList class contains methods to manipulate the users packing list
 */
public class PackingList {
    private static List<Item> allItems = new ArrayList<>();

    /**
     * Adds a task to the list.
     */
    public void addItem(Item toAdd) {
        allItems.add(toAdd);
    }

    public Item get(int itemIndex) {
        return allItems.get(itemIndex);
    }

    /**
     * Returns the number of {@link Item}s in the packing list.
     * @return int of packing list size
     */
    public int size() {
        return allItems.size();
    }


}
