package seedu.duke;
import java.util.ArrayList;
import java.util.HashSet;
import java.lang.StringBuilder;



public class Inventory {
    private static ArrayList<Item> items = new ArrayList<>();
    private static HashSet<String> upcCodes = new HashSet<>();
    //todo: trie and HashMap<String,ArrayList<int>> for searching





    public static final int NAME_COL_WIDTH = 15;
    public static final int UPC_COL_WIDTH = 12;
    public static final int QTY_COL_WIDTH = 8;

    public static final int PRICE_COL_WIDTH = 5;

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
        int[] columnWidths = {NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH, PRICE_COL_WIDTH}; //name upc quantity price

        Ui.printTableSeparator(columnWidths);
        Ui.printHeadings(columnWidths);
        Ui.printTableSeparator(columnWidths);

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            String qty = Integer.toString(item.getQuantity());
            String price = Double.toString(item.getPrice());

            int maxColHeight = findMaxColHeight(name, upc, qty, price, columnWidths);

            printRow(name, upc, qty, price, maxColHeight, columnWidths);

        }
    }

    private static void printRow(String name, String upc, String qty, String price, int maxRowHeight, int[] columnWidths) {
       // String[] row = {name, upc, qty, price}; //not needed

        String[] nameLines = wrapText(name, NAME_COL_WIDTH);
        String[] upcLines = wrapText(upc, UPC_COL_WIDTH);
        String[] qtyLines = wrapText(qty, QTY_COL_WIDTH);
        String[] priceLines = wrapText(price, PRICE_COL_WIDTH);

        for (int i = 0; i < maxRowHeight; i += 1) {

            System.out.print("| ");
            printAttribute(nameLines, NAME_COL_WIDTH, i);
            System.out.print(" | ");
            printAttribute(upcLines, UPC_COL_WIDTH, i);
            System.out.print(" | ");
            printAttribute(qtyLines, QTY_COL_WIDTH, i);
            System.out.print(" | ");
            printAttribute(priceLines, PRICE_COL_WIDTH, i);
            System.out.println(" |");


            if (i == maxRowHeight - 1) {
                Ui.printTableSeparator(columnWidths);
            }
        }
        }

        private static void printAttribute(String[] attributeLines, int columnWidth, int rowNumber) {
            if (rowNumber < attributeLines.length) {
                String paddedString = String.format("%1$-" + columnWidth + "s", attributeLines[rowNumber]);
                System.out.print(paddedString);
            } else {
                System.out.print(" ".repeat(columnWidth));
            }

        }




    private static int findMaxColHeight(String name, String upc, String qty, String price, int[] columnWidths) {

        int nameLength = name.length();
        int upcLength = upc.length();
        int qtyLength = qty.length();
        int priceLength = price.length();

        int[] attributeWidths = {nameLength, upcLength, qtyLength, priceLength};
        int max = 1;

        for (int i = 0; i < attributeWidths.length; i += 1) {
            int colHeight = attributeWidths[i] / columnWidths[i];

            if (attributeWidths[i] % columnWidths[i] != 0) {
                colHeight += 1;
            }
            if (colHeight > max) {
                max = colHeight;
            }
        }

        return max;

    }

    /*Method below adapted from https://stackoverflow.com/questions/4055430/java-
    code-for-wrapping-text-lines-to-a-max-line-width*/
    private static String[] wrapText(String input, int width) {
        String[] words = input.split(" ");
        ArrayList<String> lines = new ArrayList<String>();

        StringBuilder line = new StringBuilder();

        for (String word : words) {
            if (line.length() + word.length() + 1 <= width) {
                line.append(word).append(" ");
            }

            if (word.length() > width) {
                int start = 0;
                while (start < word.length()) {
                    int end = Math.min(start + width, word.length());
                    lines.add(word.substring(start, end));
                    start = end;
                }
            } else {
                lines.add(line.toString());
                line = new StringBuilder(word + " ");
            }
        }
       // lines.add(line.toString()); do not remove
        return lines.toArray(new String[0]);


    }



}
