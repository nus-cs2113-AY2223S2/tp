package seedu.duke;
import java.util.ArrayList;
import java.util.HashSet;




public class Inventory {
    private static ArrayList<Item> items = new ArrayList<>();
    private static HashSet<String> upcCodes = new HashSet<>();
    //todo: trie and HashMap<String,ArrayList<int>> for searching

    public static void addItem(Item item){
        if (upcCodes.contains(item.getUpc())) {
            Ui.printDuplicateAdd();
        } else {
            upcCodes.add(item.getUpc());
            items.add(item);
            Ui.printSuccessAdd();
        }
    }
    public static void removeItemAtIndex(int index){
        items.remove(index);
    }

    public static void listItems() {
        if (!items.isEmpty()) {
            Ui.printSuccessList();
            Ui.printTable(items);
        } else {
            Ui.printInvalidList();
        }
    }
}
