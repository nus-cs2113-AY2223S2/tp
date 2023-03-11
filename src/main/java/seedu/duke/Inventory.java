package seedu.duke;
import java.util.ArrayList;
import java.util.HashSet;


import static seedu.duke.Ui.*;


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

    public static void printTable() {
        int[] columnWidths = {NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH, PRICE_COL_WIDTH};

        Ui.printTableSeparator(columnWidths);
        Ui.printHeadings(columnWidths);
        Ui.printTableSeparator(columnWidths);

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            String qty = Integer.toString(item.getQuantity());
            String price = Double.toString(item.getPrice());

            int maxColHeight = Ui.findMaxColHeight(name, upc, qty, price, columnWidths);
            printRow(name, upc, qty, price, maxColHeight, columnWidths);
        }
    }













}
