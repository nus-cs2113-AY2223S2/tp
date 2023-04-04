package seedu.duke.utils;

import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Ui {
    public static final String LINE = "---------------------------------------------------------------------------";
    public static final String DASHBOARDLOGO = "\n" +
            "██████╗░░█████╗░░██████╗██╗░░██╗██████╗░░█████╗░░█████╗░██████╗░██████╗░\n" +
            "██╔══██╗██╔══██╗██╔════╝██║░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██║░░██║███████║╚█████╗░███████║██████╦╝██║░░██║███████║██████╔╝██║░░██║\n" +
            "██║░░██║██╔══██║░╚═══██╗██╔══██║██╔══██╗██║░░██║██╔══██║██╔══██╗██║░░██║\n" +
            "██████╔╝██║░░██║██████╔╝██║░░██║██████╦╝╚█████╔╝██║░░██║██║░░██║██████╔╝\n" +
            "╚═════╝░╚═╝░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░";
    public static final String LOGO = "\n" +
            "███╗░░░███╗░█████╗░░██████╗░██╗░░░██╗░██████╗░██████╗████████╗░█████╗░░█████╗░██╗░░██╗\n" +
            "████╗░████║██╔══██╗██╔════╝░██║░░░██║██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██║░██╔╝\n" +
            "██╔████╔██║███████║██║░░██╗░██║░░░██║╚█████╗░╚█████╗░░░░██║░░░██║░░██║██║░░╚═╝█████═╝░\n" +
            "██║╚██╔╝██║██╔══██║██║░░╚██╗██║░░░██║░╚═══██╗░╚═══██╗░░░██║░░░██║░░██║██║░░██╗██╔═██╗░\n" +
            "██║░╚═╝░██║██║░░██║╚██████╔╝╚██████╔╝██████╔╝██████╔╝░░░██║░░░╚█████╔╝╚█████╔╝██║░╚██╗\n" +
            "╚═╝░░░░░╚═╝╚═╝░░╚═╝░╚═════╝░░╚═════╝░╚═════╝░╚═════╝░░░░╚═╝░░░░╚════╝░░╚════╝░╚═╝░░╚═╝";

    public static final String INVENTORYLOGO = "\n" +
            "██╗███╗░░██╗██╗░░░██╗███████╗███╗░░██╗████████╗░█████╗░██████╗░██╗░░░██╗\n" +
            "██║████╗░██║██║░░░██║██╔════╝████╗░██║╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
            "██║██╔██╗██║╚██╗░██╔╝█████╗░░██╔██╗██║░░░██║░░░██║░░██║██████╔╝░╚████╔╝░\n" +
            "██║██║╚████║░╚████╔╝░██╔══╝░░██║╚████║░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░\n" +
            "██║██║░╚███║░░╚██╔╝░░███████╗██║░╚███║░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░\n" +
            "╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░";

    public static final String GREET_MESSAGE = "Welcome to MagusStock. How may I assist you today?";
    public static final String EXIT_MESSAGE = "Hope you had an enjoyable experience. See you next time!";
    public static final String UNKNOWN_COMMAND = "I don't understand that command, please refer to the user guide " +
            "or enter 'help' for all available commands";
    public static final String INVALID_ADD = "Wrong/Incomplete Format! Please add new items in the following format: " +
            "\nFormat: add n/[name] upc/[UPC] qty/[quantity] p/[price] c/[category]\nREQUIRED fields: n/ upc/ qty/ " +
            "p/ \nOPTIONAL field: c/\nTip: Ensure that your UPC, quantity and price are all positive numbers and " +
            "within valid range";

    public static final String INVALID_AUTO_SAVE_INPUT = "Invalid input! Please enter either 'on' or 'off' to " +
            "enable/disable auto-save";
    public static final String AUTOSAVE_ON = "Auto-save has been enabled!";
    public static final String AUTOSAVE_OFF = "Auto-save has been disabled!";
    public static final String DUPLICATE_ADD = "Duplicate UPC found! Please add another item with a different UPC";
    public static final String SUCCESS_ADD = "Successfully added the item(s) into the system!";

    public static final String SUCCESS_LIST = "Here are the items in your inventory:";

    public static final String EMPTY_LIST = "There are no items in your inventory.";
    public static final String CONFIRM_MESSAGE = "Are you sure you want this item to be permanently deleted?\n(Y/N)";

    public static final String INVALID_SESSION_FILE = "INFO: A Session Inventory file was found but it is corrupted. " +
            "\n" + "      Please delete the corrupt .csv file.";
    public static final String RECOVERED_SESSION_FILE = "INFO: Session Inventory Data recovered." +
            " The inventory has been updated.";
    public static final String EMPTY_SESSION_FILE = "INFO: Empty/No Session Inventory file found.";

    public static final String INVALID_ALERT_FILE = "INFO: A Session Alerts file was found but it is corrupted. " +
            "\n" + "      Please delete the corrupt .csv file.";

    public static final String EMPTY_ALERT_FILE = "INFO: Empty/No Session Alerts file found.";

    public static final String RECOVERED_ALERT_FILE = "INFO: Session Alert Data recovered." +
            " The list of active alerts has been updated.";

    public static final int INVENTORY_ATTRIBUTE_COUNT = 6;
    public static final int HELP_ATTRIBUTE_COUNT = 2;
    public static final int ALERT_ATTRIBUTE_COUNT = 3;

    public static final int NAME_COL_WIDTH = 15;
    public static final int UPC_COL_WIDTH = 12;
    public static final int QTY_COL_WIDTH = 8;

    public static final int PRICE_COL_WIDTH = 8;

    public static final int INDEX_COL_WIDTH = 5;
    public static final int COMMAND_COL_WIDTH = 25;
    public static final int FORMAT_COL_WIDTH = 25;
    public static final String INVALID_EDIT_FORMAT = "Wrong/Incomplete Format! Please edit items in the following " +
            "format: \nFormat: edit upc/[UPC] n/[Name] qty/[Quantity] p/[Price] c/[Category]\nREQUIRED fields: upc/\n" +
            "OPTIONAL fields: n/ qty/ p/ c/\nTip: Ensure that your UPC, quantity and price are all positive numbers " +
            "and within valid range";
    public static final String ITEM_NOT_FOUND = "Command failed! Reason: Item not found in database. Please add item " +
            "first!";
    public static final String SUCCESS_EDIT = "Successfully edited the following item:";
    public static final String SUCCESS_RESTOCK = "Successfully restocked the following item:";
    public static final String SUCCESS_SELL = "Successfully sold the following item:";
    public static final String ITEM_NOT_EDITED = "Item Specified will not be updated.";
    public static final String WRONG_QUANTITY_INPUT = "For Quantity inputs: MUST BE a POSITIVE WHOLE NUMBER/ZERO.";
    public static final String WRONG_PRICE_INPUT = "For Price inputs: MUST BE a POSITIVE WHOLE " +
            "NUMBER/DECIMAL NUMBER/ZERO.";
    public static final String INVALID_RESTOCK_FORMAT = "Wrong/Incomplete Format! Please restock items in the " +
            "following format: " + "restock upc/[UPC] qty/[Quantity]";
    public static final String INVALID_ADD_QUANTITY_FORMAT = "Unable to restock item. REASON: Quantity inputs" +
            " SHOULD NOT contain NEGATIVE integers, ZERO(0), or STRING inputs!";
    public static final String INVALID_DEDUCT_QUANTITY_FORMAT = "Unable to sell item. REASON: Quantity inputs" +
            " SHOULD NOT contain NEGATIVE integers, ZERO(0), or STRING inputs!" + "\n" +
            "Also ensure that the desired" + " quantity to be deducted is LESS THAN current stock levels.";
    public static final String INVALID_SELL_FORMAT = "Wrong/Incomplete Format! Please restock items in the " +
            "following format: " + "sell upc/[UPC] qty/[Quantity]";
    public static final String NO_SEARCH_RESULTS = "Unfortunately, no search results could be found. Try again?";
    public static final String MISSING_PRICE = "Please enter a number for the price!";
    public static final String ITEM_ADDED_AT = "Item added at: ";
    public static final String AT = "At: ";
    public static final String CATEGORY_CHANGED_TO = "Category changed to: ";
    public static final String SOLD = "Sold ";
    public static final String ITEMS = " items";
    public static final String BOUGHT = "Bought ";
    public static final String RENAMED_TO = "Renamed to: ";
    public static final String PRICE_DECREASED_FROM = "Price decreased from $";
    public static final String PRICE_INCREASED_FROM = "Price increased from $";
    public static final String TO_DOLLAR_SIGN = " to $";

    private static final String NAME_HEADING = "Name";
    private static final String UPC_HEADING = "UPC";
    private static final String QTY_HEADING = "Quantity";
    private static final String PRICE_HEADING = "Price";

    private static final String CATEGORY_HEADING = "Category";
    private static final String INDEX_HEADING = "Index";
    private static final String COMMAND_HEADING = "Command";
    private static final String FORMAT_HEADING = "Command Format";

    private static final String TABLE_CORNER = "+";
    private static final String TABLE_ROW = "-";
    private static final String TABLE_LEFT = "| ";
    private static final String TABLE_RIGHT = " |";
    private static final String TABLE_MIDDLE = " | ";

    private static final String DOLLAR_SIGN = "$";
    private static final String SUCCESS_REMOVE = "Successfully removed the following item: ";
    private static final String NOT_REMOVING = "Ok...You changed your mind really quickly.";
    private static final String INVALID_REPLY = "Invalid response, only yes (Y) or no (N) answer is allowed.\n" +
            "Please try again :(";
    private static final String INVALID_INDEX = "This index is invalid.\nPlease enter a number ";

    private static final String INVALID_ALERT_KEYWORD = "Keyword after alert can only be \"add\", \"remove\" " +
            "\"or list\".";
    private static final String INVALID_ADD_ALERT =
            "Wrong/Incomplete Format! Please add new alerts in the following format: \n" +
                    "\"alert add upc/[UPC] min/[integer]\" to set an alert when stock falls below a minimum \n" +
                    " OR\n" +
                    "\"alert add upc/[UPC] max/[integer]\" to set an alert when stock exceeds a maximum. \n";

    private static final String EXISTING_MIN_ALERT = "This item already has a minimum alert. " +
            "Delete the existing one first.";

    private static final String EXISTING_MAX_ALERT = "This item already has a maximum alert. " +
            "Delete the existing one first.";

    private static final String INVALID_MIN_ALERT = "Minimum value to set an alert must be less than existing " + "" +
            "maximum alert value of this item.";

    private static final String INVALID_MAX_ALERT = "Maximum value to set an alert must be more than existing " + "" +
            "mimimum alert value of this item.";

    private static final String SUCCESS_ADD_ALERT = "Successfully added a new alert.";

    private static final String INVALID_REMOVE_ALERT =
            "Wrong/Incomplete Format! " + "Please remove new alerts in the " + "following format: \n" +
                    "\"alert remove upc/[UPC] level/min\" to remove an alert for minimum stock level \n" +
                    " OR\n" +
                    "\"alert remove upc/[UPC] level/max\" to remove an alert for maximum stock level. \n";
    private static final String SUCCESS_REMOVE_ALERT = "Successfully removed the alert.";

    private static final String NONEXISTENT_REMOVE_ALERT = "The alert that you are attempting to remove " +
            "does not exist.";

    private static final String INVALID_ALERT_TYPE = "Alert is not a valid type (min/max)";

    private static final String INVALID_CATEGORY_FORMAT = "Wrong/Incomplete Format! Please enter category commands " +
            "as shown below.\n" + "List all categories: cat list\n" + "List all items in a category: cat [Category]\n" +
            "List all items and all categories: cat table";
    private static final int CATEGORY_COL_WIDTH = 15;
    private static final int ITEMS_COL_WIDTH = 30;
    private static final String NO_CATEGORY_LIST = "Category list is empty. There are no items in the inventory.";
    private static final String INVALID_CATEGORY_FIND = "The category you are looking for does not exist.";
    private static final String INVALID_CATEGORY = "The category does not exist.";
    private static final String NEW_CATEGORY_ADDED = "A new category has been added.";
    private static final String NO_CHANGES_WERE_RECORDED = "An edit attempt was made, but no changes were recorded.";
    private static final int ORIGINAL_ITEM_INDEX = 0;


    public static void printLine() {
        System.out.println(LINE);
    }

    public static void printDoubleNeeded() {
        System.out.println(MISSING_PRICE);
    }

    public static void printEmptySearch() {
        printLine();
        System.out.println(NO_SEARCH_RESULTS);
        printLine();
    }

    public static void printSearchUPCItem(Item item) {
        printLine();
        System.out.println("Here is your item: ");
        ArrayList<Item> singleItem = new ArrayList<>();
        singleItem.add(item);
        System.out.println(printTable(singleItem));
        printLine();
    }

    public static void printSearchItems(ArrayList<Item> items) {
        printLine();
        System.out.println(printTable(items));
        printLine();
    }

    public static void printExitMessage() {
        printLine();
        System.out.println(EXIT_MESSAGE);
        printLine();
    }

    public static void greetUser() {
        printLine();
        System.out.println(LOGO);
        System.out.println(GREET_MESSAGE);
        printLine();
    }

    public static void printInvalidSessionFile() {
        System.out.println(INVALID_SESSION_FILE);
        printLine();
    }

    public static void printRecoveredSessionFile() {
        System.out.println(RECOVERED_SESSION_FILE);
        printLine();
    }

    public static void printEmptySessionFile() {
        System.out.println(EMPTY_SESSION_FILE);
        printLine();
    }

    public static void printInvalidAlertFile() {
        System.out.println(INVALID_ALERT_FILE);
        printLine();
    }

    public static void printEmptyAlertFile() {
        System.out.println(EMPTY_ALERT_FILE);
        printLine();
    }

    public static void printRecoveredAlertFile() {
        System.out.println(RECOVERED_ALERT_FILE);
        printLine();
    }

    public static void printUnknownCommand() {
        printLine();
        System.out.println(UNKNOWN_COMMAND);
        printLine();
    }

    public static void printInvalidAddCommand() {
        printLine();
        System.out.println(INVALID_ADD);
        printLine();
    }

    public static void printInvalidAutoSaveInput() {
        printLine();
        System.out.println(INVALID_AUTO_SAVE_INPUT);
        printLine();
    }

    public static void printAutoSaveEnabled() {
        printLine();
        System.out.println(AUTOSAVE_ON);
        printLine();
    }

    public static void printAutoSaveDisabled() {
        printLine();
        System.out.println(AUTOSAVE_OFF);
        printLine();
    }

    public static void printDuplicateAdd() {
        printLine();
        System.out.println(DUPLICATE_ADD);
        printLine();
    }

    public static void printSuccessAdd() {
        printLine();
        System.out.println(SUCCESS_ADD);
        printLine();
    }

    public static void printRaceCondition() {
        printLine();
        System.out.println("A rare race condition occurred. Please try restarting the program");
        System.out.println("If this happens often, check that other programs are not interfering with this one");
        printLine();
    }

    public static void printSuccessList() {
        printLine();
        System.out.println(INVENTORYLOGO);
        System.out.println(SUCCESS_LIST);
    }

    public static void printEmptyList() {
        printLine();
        System.out.println(EMPTY_LIST);
        printLine();
    }

    public static String printTable(HashMap<String, ArrayList<Item>> categoryHash) {
        int[] columnWidths = {CATEGORY_COL_WIDTH, ITEMS_COL_WIDTH};

        StringBuilder table = new StringBuilder();

        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));
        categoryHash.forEach((category, items)
                -> table.append((printRow(category, items, columnWidths))));
        return table.toString();
    }

    public static String printTable() {
        HashMap<String, String> commandsHashMap = new HashMap<>();
        CommandFormat commandFormat = new CommandFormat(commandsHashMap);
        commandsHashMap = commandFormat.getCommandsHashMap(commandsHashMap);
        int[] columnWidths = {COMMAND_COL_WIDTH, FORMAT_COL_WIDTH};

        StringBuilder table = new StringBuilder();

        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));
        commandsHashMap.forEach((format, description)
                -> table.append((printRow(description, format, columnWidths))));
        return table.toString();
    }

    public static String printTable(ArrayList<Item> items) {
        int[] columnWidths = {INDEX_COL_WIDTH, NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH, PRICE_COL_WIDTH,
            CATEGORY_COL_WIDTH,};

        StringBuilder table = new StringBuilder();

        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            String qty = Integer.toString(item.getQuantity());
            String price = String.format("%.2f", item.getPrice());
            String category = item.getCategory();
            String index = Integer.toString(items.indexOf(item));

            table.append(printRow(name, upc, qty, price, category, index, columnWidths));
        }
        return table.toString();
    }

    public static String printTable(HashMap<String, Integer> upcMap, Inventory inventory) {

        int[] columnWidths = {NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH};
        StringBuilder table = new StringBuilder();
        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));

        HashMap<String, Item> inventoryMap = inventory.getUpcCodes();

        upcMap.forEach((key, value)
                -> table.append(printRow(inventoryMap.get(key).getName(), key, value.toString(), columnWidths)));

        return table.toString();

    }

    private static String printHeadings(int[] columnWidths) {
        String[] headings = {};
        if (columnWidths.length == INVENTORY_ATTRIBUTE_COUNT) {
            headings = new String[] {INDEX_HEADING, NAME_HEADING, UPC_HEADING, QTY_HEADING, PRICE_HEADING,
                CATEGORY_HEADING};
        } else if (columnWidths.length == HELP_ATTRIBUTE_COUNT && columnWidths[0] == COMMAND_COL_WIDTH) {
            headings = new String[] {COMMAND_HEADING, FORMAT_HEADING};
        } else if (columnWidths.length == ALERT_ATTRIBUTE_COUNT) {
            headings = new String[] {"Name", "UPC", "Stock"};
        } else if (columnWidths.length == HELP_ATTRIBUTE_COUNT && columnWidths[0] == CATEGORY_COL_WIDTH) {
            headings = new String[] {CATEGORY_HEADING, NAME_HEADING + ": " + UPC_HEADING};
        }
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
            tableSeparator.append(TABLE_ROW.repeat(columnWidth + 2));
        }

        tableSeparator.append(TABLE_CORNER);
        tableSeparator.append(System.lineSeparator());
        return tableSeparator.toString();
    }

    private static String printRow(String description, String format, int[] columnWidths) {
        String[] descriptionLines = wrapText(description, COMMAND_COL_WIDTH);
        String[] formatLines = wrapText(format, FORMAT_COL_WIDTH);
        StringBuilder row = new StringBuilder();

        int rowHeight = findRowHeight(descriptionLines, formatLines);

        for (int i = 0; i < rowHeight; i += 1) {
            row.append(TABLE_LEFT);
            row.append(printAttribute(descriptionLines, COMMAND_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(formatLines, FORMAT_COL_WIDTH, i));
            row.append(TABLE_RIGHT);
            row.append(System.lineSeparator());

            if (i == rowHeight - 1) {
                row.append(printTableSeparator(columnWidths));
            }
        }
        return row.toString();
    }

    private static String printRow(String category, ArrayList<Item> items, int[] columnWidths) {
        String[] categoryLines = wrapText(category, CATEGORY_COL_WIDTH);
        ArrayList<String> itemLines = new ArrayList<>();
        StringBuilder row = new StringBuilder();

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            itemLines.add(name + ":" + upc);
        }
        String[] itemListLines = wrapText(itemLines.toString(), ITEMS_COL_WIDTH);
        int rowHeight = findRowHeight(categoryLines, itemListLines);

        for (int i = 0; i < rowHeight; i += 1) {
            row.append(TABLE_LEFT);
            row.append(printAttribute(categoryLines, CATEGORY_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(itemListLines, ITEMS_COL_WIDTH, i));
            row.append(TABLE_RIGHT);
            row.append(System.lineSeparator());

            if (i == rowHeight - 1) {
                row.append(printTableSeparator(columnWidths));
            }
        }
        return row.toString();
    }

    private static String printRow(String name, String upc, String qty, String price, String category, String index,
                                   int[] columnWidths) {
        String[] nameLines = wrapText(name, NAME_COL_WIDTH);
        String[] upcLines = wrapText(upc, UPC_COL_WIDTH);
        String[] qtyLines = wrapText(qty, QTY_COL_WIDTH);
        String[] catLines = wrapText(category, CATEGORY_COL_WIDTH);
        String[] priceLines = wrapText(DOLLAR_SIGN + price, PRICE_COL_WIDTH);
        String[] indexLines = wrapText(index, INDEX_COL_WIDTH);
        StringBuilder row = new StringBuilder();

        int rowHeight = findRowHeight(nameLines, upcLines, qtyLines, priceLines, catLines, indexLines);

        for (int i = 0; i < rowHeight; i += 1) {
            row.append(TABLE_LEFT);
            row.append(printAttribute(indexLines, INDEX_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(nameLines, NAME_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(upcLines, UPC_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(qtyLines, QTY_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(priceLines, PRICE_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(catLines, CATEGORY_COL_WIDTH, i));
            row.append(TABLE_RIGHT);
            row.append(System.lineSeparator());

            if (i == rowHeight - 1) {
                row.append(printTableSeparator(columnWidths));
            }
        }
        return row.toString();
    }

    private static String printRow(String name, String upc, String stock, int[] columnWidths) {
        String[] upcLines = wrapText(upc, UPC_COL_WIDTH);
        String[] stockLines = wrapText(stock, QTY_COL_WIDTH);
        String[] nameLines = wrapText(name, NAME_COL_WIDTH);
        StringBuilder row = new StringBuilder();

        int rowHeight = findRowHeight(upcLines, stockLines, nameLines);

        for (int i = 0; i < rowHeight; i += 1) {
            row.append(TABLE_LEFT);
            row.append(printAttribute(nameLines, NAME_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(upcLines, UPC_COL_WIDTH, i));
            row.append(TABLE_MIDDLE);
            row.append(printAttribute(stockLines, QTY_COL_WIDTH, i));
            row.append(TABLE_RIGHT);
            row.append(System.lineSeparator());

            if (i == rowHeight - 1) {
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

    /* Method below adapted from https://stackoverflow.com/questions/4055430/java-
    code-for-wrapping-text-lines-to-a-max-line-width */
    private static String[] wrapText(String input, int width) {
        if (input.contains("[")) {
            input = input.replace("[", "");
        }
        if (input.contains("]")) {
            input = input.replace("]", "");
        }
        String[] words = input.split("\\s+");
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder line = new StringBuilder();

        for (int i = 0; i < words.length; i += 1) {
            if (line.length() + words[i].length() <= width) {
                line = addWordWithoutWrap(line, words, lines, i, width);
            } else if (words[i].length() > width) {
                addWordWithWrap(words, lines, i, width);
            } else {
                lines.add(line.toString());
                line = new StringBuilder(words[i] + " ");
            }
        }

        if (line.length() > 0) {
            lines.add(line.toString());
        }
        return lines.toArray(new String[0]);
    }

    private static StringBuilder addWordWithoutWrap(StringBuilder line, String[] words, ArrayList<String> lines,
                                                    int current, int width) {
        line.append(words[current]);

        if (line.length() < width) {
            line.append(" ");
        }

        if (words[current].equals(",")) {
            for (int i = 0; i < width - line.length(); i++) {
                line.append(" ");
            }
        }

        if (current + 1 != words.length && line.length() + words[current + 1].length() > width) {
            lines.add(line.toString());
            line = new StringBuilder();
        }
        return line;
    }

    private static void addWordWithWrap(String[] words, ArrayList<String> lines, int current, int width) {
        int start = 0;
        while (start < words[current].length()) {
            int end = Math.min(start + width, words[current].length());
            lines.add(words[current].substring(start, end));
            start = end;
        }
    }

    private static int findRowHeight(String[]... rowHeights) {
        int maxAttributeHeight = 0;

        for (String[] rowHeight : rowHeights) {
            if (rowHeight.length > maxAttributeHeight) {
                maxAttributeHeight = rowHeight.length;
            }
        }
        return maxAttributeHeight;
    }

    /**
     * Informs the user that his/her edit command is of the wrong format, by the printing a string to show the correct
     * format instead.
     */
    public static void printInvalidEditCommand() {
        printLine();
        System.out.println(INVALID_EDIT_FORMAT);
        printLine();
    }

    /**
     * Prints a string to inform the user that the item with the specified UPC code cannot be found inside the database.
     */
    public static void printItemNotFound() {
        printLine();
        System.out.println(ITEM_NOT_FOUND);
        printLine();
    }

    /**
     * Calls a method to prints the updated version of the item in question, or else calls a method to print a string
     * to inform the user that the item in question is not updated due to an error in his or her inputs.
     *
     * @param oldItem     The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     * @throws EditErrorException The exception used to handle all errors related to the "Edit" command.
     */
    public static void printEditDetails(Item oldItem, Item updatedItem) throws EditErrorException {
        try {
            if (!updatedItem.isUpdatedFrom(oldItem)) {
                throw new EditErrorException();
            }
            printUpdatedItemDetails(oldItem, updatedItem);
            if (!Objects.equals(oldItem.getUpc(), updatedItem.getUpc())) {
                throw new AssertionError("Both items should be of same UPC Code.");
            }
        } catch (EditErrorException eee) {
            printItemNotUpdatedError();
        }
    }


    /**
     * Prints the updated attributes of the item as specified by the user. Shows both the previous attributes
     * and the updated attributes of the item.
     *
     * @param oldItem     The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     */
    private static void printUpdatedItemDetails(Item oldItem, Item updatedItem) {
        printLine();
        System.out.println(SUCCESS_EDIT + "\n");
        System.out.println("Before Update: ");
        System.out.println(oldItem.toString());
        System.out.println("\n" + "After Update: ");
        System.out.println(updatedItem.toString());
        printLine();
    }

    /**
     * Prints the updated quantity of an item specified by the user. Shows both the previous quantity
     * and the updated quantity of the item after restocking.
     *
     * @param oldItem     The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     */
    public static void printRestockDetails(Item oldItem, Item updatedItem) {
        printLine();
        System.out.println(SUCCESS_RESTOCK + "\n");
        System.out.println("Before Restocking: ");
        System.out.println("Item Name: " + oldItem.getName() + "\n" + "UPC Code: " + oldItem.getUpc() + "\n" +
                "Quantity Available: " + oldItem.getQuantity());
        System.out.println("\n" + "After Restocking: ");
        System.out.println("Item Name: " + updatedItem.getName() + "\n" + "UPC Code: " + updatedItem.getUpc() + "\n" +
                "Quantity Available: " + updatedItem.getQuantity());
        printLine();
    }

    /**
     * Prints the updated quantity of an item specified by the user. Shows both the previous quantity
     * and the updated quantity of the item after selling, as well as the price its sold at.
     *
     * @param oldItem     The item containing the old attributes.
     * @param updatedItem The same item but with new attributes as defined by the user.
     */
    public static void printSellDetails(Item oldItem, Item updatedItem) {
        printLine();
        System.out.println(SUCCESS_SELL + "\n");
        System.out.println("Before Selling: ");
        System.out.println("Item Name: " + oldItem.getName() + "\n" + "UPC Code: " + oldItem.getUpc() + "\n" +
                "Quantity Available: " + oldItem.getQuantity());
        System.out.println("\n" + "After Selling: ");
        System.out.println("Item Name: " + updatedItem.getName() + "\n" + "UPC Code: " + updatedItem.getUpc() + "\n" +
                "Quantity Available: " + updatedItem.getQuantity());
        System.out.println("\n" + "Sold " + (oldItem.getQuantity() - updatedItem.getQuantity())
                + " " + updatedItem.getName() + " at a price of $" + updatedItem.getPrice() +
                ".");
        printLine();
    }

    /**
     * Prints an error message to inform the user that the item is not updated.
     */
    private static void printItemNotUpdatedError() {
        printLine();
        System.out.println(ITEM_NOT_EDITED);
        System.out.println("REASON: Item's name/price/quantity is the same as user's input.");
        printLine();
    }

    /**
     * Prints an error message to inform the user that item is not updated due to wrong quantity/price input type.
     */
    public static void printInvalidPriceOrQuantityEditInput() {
        printLine();
        System.out.println(ITEM_NOT_EDITED);
        System.out.println("REASON:");
        System.out.println(WRONG_QUANTITY_INPUT);
        System.out.println(WRONG_PRICE_INPUT);
        printLine();
    }

    /**
     * Prints an error message to inform the user that the user command for restock is invalid.
     */
    public static void printInvalidRestockCommand() {
        printLine();
        System.out.println(INVALID_RESTOCK_FORMAT);
        printLine();

    }

    /**
     * Prints an error message to inform the user that the "restock" command contains negative values or strings.
     */
    public static void printInvalidAddQuantityInput() {
        printLine();
        System.out.println(INVALID_ADD_QUANTITY_FORMAT);
        printLine();
    }

    /**
     * Prints an error message to inform the user that the "sell" command contains negative values or strings.
     */
    public static void printInvalidDeductQuantityInput() {
        printLine();
        System.out.println(INVALID_DEDUCT_QUANTITY_FORMAT);
        printLine();
    }

    /**
     * Prints an error message to inform the user that the user command for selling is invalid.
     */
    public static void printInvalidSellCommand() {
        printLine();
        System.out.println(INVALID_SELL_FORMAT);
        printLine();
    }

    public static void printInvalidReply() {
        printLine();
        System.out.println(INVALID_REPLY);
        printLine();
    }

    public static void printNotRemoving() {
        printLine();
        System.out.println(NOT_REMOVING);
        printLine();
    }

    public static void printSuccessRemove(Item itemToRemove) {
        printLine();
        System.out.println(SUCCESS_REMOVE);
        System.out.println(itemToRemove.toString());
        printLine();
    }

    public static void printConfirmMessage(Item itemToRemove) {
        printLine();
        System.out.println(CONFIRM_MESSAGE);
        System.out.println(itemToRemove.toString());
        printLine();
    }

    public static void printInvalidIndex(Inventory inventory) {
        printLine();
        int listSize = inventory.getItemInventory().size();
        switch (listSize) {
        case 0:
            System.out.println(EMPTY_LIST);
            break;
        case 1:
            System.out.println(INVALID_INDEX + "0 to remove item successfully.");
            break;
        default:
            System.out.println(INVALID_INDEX + "between 0 to " + (listSize - 1) +
                    " to remove item successfully.");
            break;
        }
        printLine();
    }

    public static void printInvalidUpc(Inventory inventory) {
        printLine();
        int listSize = inventory.getItemInventory().size();
        if (listSize == 0) {
            System.out.println(EMPTY_LIST);
        } else {
            System.out.println("This UPC is invalid. Try again.");
        }
        printLine();
    }

    public static void printExistingMinAlert() {
        printLine();
        System.out.println(EXISTING_MIN_ALERT);
        printLine();
    }

    public static void printExistingMaxAlert() {
        printLine();
        System.out.println(EXISTING_MAX_ALERT);
        printLine();
    }

    public static void printInvalidAddAlertCommand() {
        printLine();
        System.out.println(INVALID_ADD_ALERT);
        printLine();
    }

    public static void printInvalidMinAlert() {
        printLine();
        System.out.println(INVALID_MIN_ALERT);
        printLine();
    }

    public static void printInvalidMaxAlert() {
        printLine();
        System.out.println(INVALID_MAX_ALERT);
        printLine();
    }

    public static void printSuccessAddAlert() {
        printLine();
        System.out.println(SUCCESS_ADD_ALERT);
        printLine();
    }

    public static void printInvalidAlertKeyword() {
        printLine();
        System.out.println(INVALID_ALERT_KEYWORD);
        printLine();
    }

    public static void printInvalidRemoveAlertCommand() {
        printLine();
        System.out.println(INVALID_REMOVE_ALERT);
        printLine();
    }

    public static void printSuccessRemoveAlertCommand() {
        printLine();
        System.out.println(SUCCESS_REMOVE_ALERT);
        printLine();
    }

    public static void printNonExistentRemoveAlert() {
        printLine();
        System.out.println(NONEXISTENT_REMOVE_ALERT);
        printLine();
    }

    public static String printInvalidAlertType() {
        StringBuilder sb = new StringBuilder("");
        sb.append(LINE);
        sb.append(INVALID_ALERT_TYPE);
        sb.append(LINE);
        return sb.toString();
    }

    private static String printAlerts(Inventory inventory, AlertList alertList) {

        StringBuilder alertTable = new StringBuilder();

        boolean hasMinAlerts = !alertList.getMinAlertUpcs().isEmpty();
        boolean hasMaxAlerts = !alertList.getMaxAlertUpcs().isEmpty();

        String minAlertTable = "";
        String maxAlertTable = "";

        if (hasMinAlerts) {
            minAlertTable = Ui.printTable(alertList.getMinAlertUpcs(), inventory);
            alertTable.append("Alerts for minimum stock level:" + System.lineSeparator());
            alertTable.append(minAlertTable);
        }

        if (hasMaxAlerts) {
            if (hasMinAlerts) {
                alertTable.append(System.lineSeparator());
            }
            maxAlertTable = Ui.printTable(alertList.getMaxAlertUpcs(), inventory);
            alertTable.append("Alerts for maximum stock level:" + System.lineSeparator());
            alertTable.append(maxAlertTable);
        }

        if (!hasMinAlerts && !hasMaxAlerts) {
            alertTable.append("No alerts to print.");
        }

        return alertTable.toString();
    }

    public static void printDashboard(Inventory inventory, AlertList alertList) {
        Item mostQuantityItem = inventory.getUpcCodes().get(inventory.getItemWithQuantityExtremes(true));
        Item leastQuantityItem = inventory.getUpcCodes().get(inventory.getItemWithQuantityExtremes(false));
        printLine();
        System.out.println(DASHBOARDLOGO);
        System.out.println("Overview:");
        printLine();
        System.out.println("Total number of items: " +
                inventory.getItemInventory().size());
        System.out.println("Total number of active alerts: " +
                alertList.getTotalAlertNumber());

        System.out.println("Total value of inventory: " +
                "$" + inventory.getTotalValue());
        if (!inventory.getItemInventory().isEmpty()) {
            System.out.println("Item with most quantity: " + mostQuantityItem.getName() +
                    " (" + mostQuantityItem.getQuantity() + ") ");
            System.out.println("Item with least quantity: " + leastQuantityItem.getName() +
                    " (" + leastQuantityItem.getQuantity() + ") ");
        }
        printLine();
        System.out.println("Current Session Configurations:");
        printLine();
        if (SessionManager.getAutoSave()) {
            System.out.println("AutoSave Mode: " + "TRUE");
        } else {
            System.out.println("AutoSave Mode: " + "FALSE");
        }
        System.out.println("Inventory Data File Status: " + SessionManager.inventoryDataFileExist());
        System.out.println("Alerts Data File Status: " + SessionManager.alertDataFileExist());
        printLine();
        System.out.println("List of active alerts:");

        String alertTable = printAlerts(inventory, alertList);

        System.out.println(alertTable);
        printLine();
    }

    /**
     * Prints out a corresponding string for an item when it is changed.
     *
     * @param editType The type of edit that was made to the item
     * @param oldItem  Item before change
     * @param newItem  Item after change
     */

    private static void printItemChange(Types.EditType editType, Item oldItem, Item newItem) {
        switch (editType) {
        case RECATEGORIZE:
            System.out.println(CATEGORY_CHANGED_TO + newItem.getCategory());
            break;
        case SOLD:
            System.out.print(SOLD + (oldItem.getQuantity() - newItem.getQuantity()));
            System.out.println(ITEMS);
            break;
        case BOUGHT:
            System.out.print(BOUGHT + (newItem.getQuantity() - oldItem.getQuantity()));
            System.out.println(ITEMS);
            break;
        case RENAME:
            System.out.println(RENAMED_TO + newItem.getName());
            break;
        case PRICE_DECREASE:
            System.out.print(PRICE_DECREASED_FROM + oldItem.getPrice());
            System.out.println(TO_DOLLAR_SIGN + newItem.getPrice());
            break;
        case PRICE_INCREASE:
            System.out.print(PRICE_INCREASED_FROM + oldItem.getPrice());
            System.out.println(TO_DOLLAR_SIGN + newItem.getPrice());
            break;
        default:
            break;
        }
    }

    /**
     * Prints out the history of an item in chronological order. Also prints out the end result if there were changes.
     *
     * @param results An ArrayList of instances of the item.
     */

    public static void printHistory(ArrayList<Item> results) {
        printLine();
        System.out.println(ITEM_ADDED_AT + results.get(ORIGINAL_ITEM_INDEX).getDateTimeString());
        System.out.println(results.get(ORIGINAL_ITEM_INDEX).toString());
        printLine();
        int changesMade = 0;
        for (int i = 1; i < results.size(); i++) {
            ArrayList<Types.EditType> edits = results.get(i - 1).getEditTypes(results.get(i));
            if (edits.isEmpty()) {
                System.out.println(NO_CHANGES_WERE_RECORDED);
                continue;
            }
            changesMade++;
            printLine();
            System.out.println(AT + results.get(i).getDateTimeString());
            for (Types.EditType editType : edits) {
                printItemChange(editType, results.get(i - 1), results.get(i));
            }
            printLine();
        }
        if (changesMade > 0) {
            printLine();
            System.out.println(results.get(results.size() - 1).toString());
            printLine();
        }
    }

    public static void printMinAlertWarning(String name, int alertLevel) {
        System.out.println("ALERT: The quantity of " + name +
                " is below the minimum level of " + alertLevel + ".");
        printLine();
    }

    public static void printMaxAlertWarning(String name, int alertLevel) {
        System.out.println("ALERT: The quantity of " + name +
                " is above the maximum level of " + alertLevel + ".");
        printLine();
    }

    public static void printInvalidCategoryCommand() {
        printLine();
        System.out.println(INVALID_CATEGORY_FORMAT);
        printLine();
    }

    public static void printCategory(HashMap<String, ArrayList<Item>> categoryHash) {
        printLine();
        System.out.println(printTable(categoryHash));
        printLine();
    }

    public static void printNoCategoryList() {
        printLine();
        System.out.println(NO_CATEGORY_LIST);
        printLine();
    }

    public static void printInvalidCategory() {
        printLine();
        System.out.println(INVALID_CATEGORY_FIND);
        printLine();
    }

    public static void printNewCategory() {
        printLine();
        System.out.println(INVALID_CATEGORY + NEW_CATEGORY_ADDED);
        printLine();
    }

    public static void printCategoryList(HashMap<String, ArrayList<Item>> categoryHash) {
        printLine();
        System.out.println("Here is the list of categories you have: ");
        categoryHash.forEach((cat, items) -> System.out.println(cat));
        printLine();
    }
}

