package seedu.duke;

import static seedu.duke.ColorCode.*;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO =
            "    /|    //| |     // | |     //   ) )  //   / / //   ) )\n"
                    + "   //|   // | |    //__| |    //        //   / / ((        \n"
                    + "  // |  //  | |   / ___  |   //  ____  //   / /    \\\\\\\\      \n"
                    + " //  | //   | |  //    | |  //    / / //   / /       ) )   \n"
                    + "//   |//    | | //     | | ((____/ / ((___/ / ((___ / /    ";
    public static final String GREET_MESSAGE = "Welcome to MagusStock. How may I assist you today?";
    public static final String EXIT_MESSAGE = "Hope you had an enjoyable experience. See you next time!";
    public static final String UNKNOWN_COMMAND = "I don't understand that command, please refer to the user guide " +
            "for all available commands";
    public static final String INVALID_ADD = "Wrong/Incomplete Format! Please add new items in the following format: " +
            "add n/[name] upc/[UPC] qty/[quantity] p/[price]";
    public static final String DUPLICATE_ADD = "Duplicate item found! Please add another item with a different UPC";
    public static final String SUCCESS_ADD = "Successfully added the item(s) into the system!";
    public static final String SUCCESS_LIST = "Here are the items in your inventory:";

    public static final String INVALID_LIST = "There are no items in your inventory.";

    public static final int NAME_COL_WIDTH = 15;
    public static final int UPC_COL_WIDTH = 12;
    public static final int QTY_COL_WIDTH = 8;

    private static final String NAME_HEADING = "Name";
    private static final String UPC_HEADING = "UPC";
    private static final String QTY_HEADING = "Quantity";

    private static final String PRICE_HEADING = "Price";

    public static final int PRICE_COL_WIDTH = 5;

    private static final String TABLE_CORNER = "+";
    private static final String TABLE_ROW = "-";
    private static final String TABLE_LEFT = "| ";
    private static final String TABLE_RIGHT = " |";
    private static final String TABLE_MIDDLE = " | ";

    public Ui() {
        greetUser();
    }

    public static void printExitMessage() {
        System.out.println(LINE);
        System.out.println(EXIT_MESSAGE);
        System.out.println(LINE);
    }

    public static void greetUser() {
        System.out.println(LINE);
        System.out.println(LOGO);
        System.out.println(GREET_MESSAGE);
        System.out.println(LINE);
    }

    public static void printUnknownCommand() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + UNKNOWN_COMMAND + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printInvalidAddCommand() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + INVALID_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printDuplicateAdd() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + DUPLICATE_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printSuccessAdd() {
        System.out.println(LINE);
        System.out.println(ANSI_GREEN + SUCCESS_ADD + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printSuccessList() {
        System.out.println(LINE);
        System.out.println(ANSI_GREEN + SUCCESS_LIST + ANSI_RESET);
    }

    public static void printInvalidList() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + INVALID_LIST + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printTable(ArrayList<Item> items) {
        int[] columnWidths = {NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH, PRICE_COL_WIDTH};

        printTableSeparator(columnWidths);
        printHeadings(columnWidths);
        printTableSeparator(columnWidths);

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            String qty = Integer.toString(item.getQuantity());
            String price = Double.toString(item.getPrice());

            int maxColHeight = findMaxColHeight(name, upc, qty, price, columnWidths);
            printRow(name, upc, qty, price, maxColHeight, columnWidths);
        }

        System.out.println(LINE);
    }

    private static void printHeadings(int[] columnWidths) {
        String[] headings = {NAME_HEADING, UPC_HEADING, QTY_HEADING, PRICE_HEADING};
        for (int i = 0; i < headings.length; i += 1) {
            String heading = headings[i];
            int width = columnWidths[i];
            System.out.printf(TABLE_LEFT + "%-" + width + "s ", heading);
        }
        System.out.println(TABLE_LEFT);
    }

    private static void printTableSeparator(int[] columnWidths) {
        for (int columnWidth : columnWidths) {
            System.out.print(TABLE_CORNER);
            for (int j = 0; j < columnWidth + 2; j++) {
                System.out.print(TABLE_ROW);
            }
        }

        System.out.println(TABLE_CORNER);
    }

    private static void printRow(String name, String upc, String qty, String price,
                                int maxRowHeight, int[] columnWidths) {
        String[] nameLines = wrapText(name, NAME_COL_WIDTH);
        String[] upcLines = wrapText(upc, UPC_COL_WIDTH);
        String[] qtyLines = wrapText(qty, QTY_COL_WIDTH);
        String[] priceLines = wrapText(price, PRICE_COL_WIDTH);

        for (int i = 0; i < maxRowHeight; i += 1) {

            System.out.print(TABLE_LEFT);
            printAttribute(nameLines, NAME_COL_WIDTH, i);
            System.out.print(TABLE_MIDDLE);
            printAttribute(upcLines, UPC_COL_WIDTH, i);
            System.out.print(TABLE_MIDDLE);
            printAttribute(qtyLines, QTY_COL_WIDTH, i);
            System.out.print(TABLE_MIDDLE);
            printAttribute(priceLines, PRICE_COL_WIDTH, i);
            System.out.println(TABLE_RIGHT);

            if (i == maxRowHeight - 1) {
                printTableSeparator(columnWidths);
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

    /*Method below adapted from https://stackoverflow.com/questions/4055430/java-
  code-for-wrapping-text-lines-to-a-max-line-width*/
    private static String[] wrapText(String input, int width) {
        String[] words = input.split(" ");
        ArrayList<String> lines = new ArrayList<>();

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

        return lines.toArray(new String[0]);
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

}

