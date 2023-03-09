package seedu.duke;
import java.util.ArrayList;
import java.util.HashSet;

public class Inventory {
    private static ArrayList<Item> items = new ArrayList<>();
    private static HashSet<String> upcCodes = new HashSet<>();
    //todo: trie and HashMap<String,ArrayList<int>> for searching
    private static void addItem(Item item){
        items.add(item);
    }
    private static void removeItemAtIndex(int index){
        items.remove(index);
    }

}
