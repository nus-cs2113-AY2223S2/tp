package seedu.duke;

import static seedu.duke.ColorCode.ANSI_RESET;
import static seedu.duke.ColorCode.ANSI_GREEN;
import static seedu.duke.ColorCode.ANSI_BLUE;
import static seedu.duke.ColorCode.ANSI_RED;

import java.util.ArrayList;


public class Ui {
    public static final String LINE = "____________________________________________________________";
    public static final String LOGO =
            "    /|    //| |     // | |     //   ) )  //   / / //   ) )\n"
                    + "   //|   // | |    //__| |    //        //   / / ((\n"
                    + "  // |  //  | |   / ___  |   //  ____  //   / /    \\\\\\\\\n"
                    + " //  | //   | |  //    | |  //    / / //   / /       ) )\n"
                    + "//   |//    | | //     | | ((____/ / ((___/ / ((___ / /";
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
    public static final String CONFIRM_MESSAGE = "Are you sure you want this item to be permanently deleted?\n(Y/N)";

    public static final int NAME_COL_WIDTH = 15;
    public static final int UPC_COL_WIDTH = 12;
    public static final int QTY_COL_WIDTH = 8;

    public static final int PRICE_COL_WIDTH = 5;
    public static final String INVALID_EDIT_FORMAT = "Wrong/Incomplete Format! Please edit items in the following " +
            "format: " + "edit upc/[UPC] {n/[Name] qty/[Quantity] p/[Price]}";
    public static final String ITEM_NOT_FOUND = "Edit failed! Reason: Item not found in database. Please add item " +
            "first!";
    public static final String SUCCESS_EDIT = "Successfully edited the following item:";
    public static final String NO_SEARCH_RESULTS = "Unfortunately, no search results could be found. Try again?";
    public static final String MISSING_PRICE = "Please enter a number for the price!";

    private static final String NAME_HEADING = "Name";
    private static final String UPC_HEADING = "UPC";
    private static final String QTY_HEADING = "Quantity";
    private static final String PRICE_HEADING = "Price";

    private static final String TABLE_CORNER = "+";
    private static final String TABLE_ROW = "-";
    private static final String TABLE_LEFT = "| ";
    private static final String TABLE_RIGHT = " |";
    private static final String TABLE_MIDDLE = " | ";
    private static final String SUCCESS_REMOVE = "Successfully removed the following item: ";
    private static final String NOT_REMOVING = "Ok...You changed your mind really quickly.";
    private static final String INVALID_REPLY = "Invalid response, only yes (Y) or no (N) answer is allowed.\n" +
            "Please try again :(";
    private static final String INVALID_INDEX = "This index is invalid.\nPlease enter a number ";
    private static final String INVALID_REMOVE_FORMAT = "Wrong/Incomplete Format! Please remove items in the " +
            "following format(s):\n" + "Remove by UPC: remove f/item upc/[UPC]\n" +
            "Remove by item index: remove f/index [INDEX]";

    public Ui() {
        greetUser();
    }

    public static void printDoubleNeeded() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + MISSING_PRICE + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printEmptySearch() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + NO_SEARCH_RESULTS + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printSearchUPCItem(Item item) {
        System.out.println(Ui.LINE);
        System.out.println(ANSI_GREEN + "Here is your item: ");
        System.out.println(item.toString() + ANSI_RESET);
        System.out.println(Ui.LINE);
    }

    public static void printSearchItems(ArrayList<Item> items) {
        System.out.println(Ui.LINE);
        int counter = 0;
        for (Item item : items) {
            System.out.println(Ui.LINE);
            counter++;
            System.out.println(ANSI_GREEN + "Item Number: " + counter);
            System.out.println(item.toString() + ANSI_RESET);
            System.out.println(Ui.LINE);
        }
        System.out.println(Ui.LINE);
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

    public static String printTable(ArrayList<Item> items) {
        int[] columnWidths = {NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH, PRICE_COL_WIDTH};

        StringBuilder table = new StringBuilder();

        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            String qty = Integer.toString(item.getQuantity());
            String price = Double.toString(item.getPrice());

            int maxColHeight = findMaxColHeight(name, upc, qty, price, columnWidths);
            table.append(printRow(name, upc, qty, price, maxColHeight, columnWidths));
        }

        return table.toString();
    }

    private static String printHeadings(int[] columnWidths) {
        String[] headings = {NAME_HEADING, UPC_HEADING, QTY_HEADING, PRICE_HEADING};
        StringBuilder allHeadings = new StringBuilder();

        for (int i = 0; i < headings.length; i += 1) {
            String heading = headings[i];
            int width = columnWidths[i];
            String formattedHeading = String.format(TABLE_LEFT + "%-" + width + "s ", heading);
            allHeadings.append(formattedHeading);
        }

        allHeadings.append(TABLE_LEFT.replaceAll(" ", ""));
        allHeadings.append(System.lineSeparator());

        return allHeadings.toString();
    }

    private static String printTableSeparator(int[] columnWidths) {
        StringBuilder tableSeparator = new StringBuilder();

        for (int columnWidth : columnWidths) {
            tableSeparator.append(TABLE_CORNER);

            for (int j = 0; j < columnWidth + 2; j++) {
                tableSeparator.append(TABLE_ROW);
            }
        }

        tableSeparator.append(TABLE_CORNER);
        tableSeparator.append(System.lineSeparator());
        return tableSeparator.toString();
    }

    private static String printRow(String name, String upc, String qty, String price,
                                   int maxRowHeight, int[] columnWidths) {
        String[] nameLines = wrapText(name, NAME_COL_WIDTH);
        String[] upcLines = wrapText(upc, UPC_COL_WIDTH);
        String[] qtyLines = wrapText(qty, QTY_COL_WIDTH);
        String[] priceLines = wrapText(price, PRICE_COL_WIDTH);

        StringBuilder row = new StringBuilder();

        for (int i = 0; i < maxRowHeight; i += 1) {

            row.append(TABLE_LEFT);
            row.append(printAttribute(nameLines, NAME_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(upcLines, UPC_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(qtyLines, QTY_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(priceLines, PRICE_COL_WIDTH, i));
            row.append(TABLE_RIGHT);
            row.append(System.lineSeparator());

            if (i == maxRowHeight - 1) {
                row.append(printTableSeparator(columnWidths));
            }
        }

        return row.toString();
    }

    private static String printAttribute(String[] attributeLines, int columnWidth, int rowNumber) {
        StringBuilder attribute = new StringBuilder();

        if (rowNumber < attributeLines.length) {
            String paddedAttribute = String.format("%1$-" + columnWidth + "s", attributeLines[rowNumber]);
            attribute.append(paddedAttribute);
        } else {
            String paddedSpace = new String(new char[columnWidth]).replace('\0', ' ');
            attribute.append(paddedSpace);
        }

        return attribute.toString();

    }

    /*Method below adapted from https://stackoverflow.com/questions/4055430/java-
  code-for-wrapping-text-lines-to-a-max-line-width*/
    private static String[] wrapText(String input, int width) {
        String[] words = input.split("\\s+");
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

    /**
     * Informs the user that his/her edit command is of the wrong format, by the printing a string to show the correct
     * format instead.
     */
    public static void printInvalidEditCommand() {
        System.out.println(LINE);
        System.out.println(INVALID_EDIT_FORMAT);
        System.out.println(LINE);
    }

    /**
     * Prints a string to inform the user that the item with the specified UPC code cannot be found inside the database.
     */
    public static void printItemNotFound() {
        System.out.println(LINE);
        System.out.println(ITEM_NOT_FOUND);
        System.out.println(LINE);
    }

    /**
     * Prints the updated version of the item in question in order to inform the user of the changes made by him or her.
     *
     * @param oldItem     The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     */
    public static void printEditDetails(Item oldItem, Item updatedItem) {
        System.out.println(LINE);
        System.out.println(ANSI_BLUE + SUCCESS_EDIT + ANSI_RESET + "\n");
        System.out.println(ANSI_RED + "Before Update: " + ANSI_RESET);
        System.out.println("Item Name: " + oldItem.getName() + "\n" + "UPC Code: " + oldItem.getUpc() + "\n" +
                "Quantity Available: " + oldItem.getQuantity() + "\n" + "Item Price: " + oldItem.getPrice());
        System.out.println("\n" + ANSI_GREEN + "After Update: " + ANSI_RESET);
        System.out.println("Item Name: " + updatedItem.getName() + "\n" + "UPC Code: " + updatedItem.getUpc() + "\n" +
                "Quantity Available: " + updatedItem.getQuantity() + "\n" + "Item Price: " + updatedItem.getPrice());
        System.out.println(LINE);
    }

    public static void printInvalidReply() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + INVALID_REPLY + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printNotRemoving() {
        System.out.println(LINE);
        System.out.println(ANSI_GREEN + NOT_REMOVING + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printSuccessRemove(Item itemToRemove) {
        System.out.println(LINE);
        System.out.println(ANSI_BLUE + SUCCESS_REMOVE + ANSI_RESET);
        System.out.println(itemToRemove.toString());
        System.out.println(LINE);
    }
    public static void printConfirmMessage() {
        System.out.println(LINE);
        System.out.println(ANSI_BLUE + CONFIRM_MESSAGE + ANSI_RESET);
        System.out.println(LINE);
    }

    public static void printInvalidIndex() {
        System.out.println(LINE);
        int listSize = Inventory.getItemList().size();
        switch (listSize) {
        case 0:
            System.out.println(ANSI_RED + INVALID_LIST + ANSI_RESET);
            break;
        case 1:
            System.out.println(ANSI_RED + INVALID_INDEX + "0 to remove item successfully." + ANSI_RESET);
            break;
        default:
            System.out.println(ANSI_RED + INVALID_INDEX + "between 0 to " + (listSize-1) +
                    " to remove item successfully." + ANSI_RESET);
            break;
        }

        System.out.println(LINE);
    }

    public static void printInvalidRemove() {
        System.out.println(LINE);
        System.out.println(ANSI_RED + INVALID_REMOVE_FORMAT + ANSI_RESET);
        System.out.println(LINE);
    }


}

