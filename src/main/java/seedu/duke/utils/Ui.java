package seedu.duke.utils;

import seedu.duke.commands.CategoryCommand;
import seedu.duke.exceptions.EditErrorException;
import seedu.duke.objects.AlertList;
import seedu.duke.objects.Inventory;
import seedu.duke.objects.Item;
import seedu.duke.types.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Ui {
    private static final String LINE = "__________________________________________________________________________";
    private static final String DASHBOARDLOGO = "\n" +
            "██████╗░░█████╗░░██████╗██╗░░██╗██████╗░░█████╗░░█████╗░██████╗░██████╗░\n" +
            "██╔══██╗██╔══██╗██╔════╝██║░░██║██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔══██╗\n" +
            "██║░░██║███████║╚█████╗░███████║██████╦╝██║░░██║███████║██████╔╝██║░░██║\n" +
            "██║░░██║██╔══██║░╚═══██╗██╔══██║██╔══██╗██║░░██║██╔══██║██╔══██╗██║░░██║\n" +
            "██████╔╝██║░░██║██████╔╝██║░░██║██████╦╝╚█████╔╝██║░░██║██║░░██║██████╔╝\n" +
            "╚═════╝░╚═╝░░╚═╝╚═════╝░╚═╝░░╚═╝╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═════╝░";
    private static final String LOGO = "\n" +
            "███╗░░░███╗░█████╗░░██████╗░██╗░░░██╗░██████╗░██████╗████████╗░█████╗░░█████╗░██╗░░██╗\n" +
            "████╗░████║██╔══██╗██╔════╝░██║░░░██║██╔════╝██╔════╝╚══██╔══╝██╔══██╗██╔══██╗██║░██╔╝\n" +
            "██╔████╔██║███████║██║░░██╗░██║░░░██║╚█████╗░╚█████╗░░░░██║░░░██║░░██║██║░░╚═╝█████═╝░\n" +
            "██║╚██╔╝██║██╔══██║██║░░╚██╗██║░░░██║░╚═══██╗░╚═══██╗░░░██║░░░██║░░██║██║░░██╗██╔═██╗░\n" +
            "██║░╚═╝░██║██║░░██║╚██████╔╝╚██████╔╝██████╔╝██████╔╝░░░██║░░░╚█████╔╝╚█████╔╝██║░╚██╗\n" +
            "╚═╝░░░░░╚═╝╚═╝░░╚═╝░╚═════╝░░╚═════╝░╚═════╝░╚═════╝░░░░╚═╝░░░░╚════╝░░╚════╝░╚═╝░░╚═╝";

    private static final String INVENTORYLOGO = "\n" +
            "██╗███╗░░██╗██╗░░░██╗███████╗███╗░░██╗████████╗░█████╗░██████╗░██╗░░░██╗\n" +
            "██║████╗░██║██║░░░██║██╔════╝████╗░██║╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
            "██║██╔██╗██║╚██╗░██╔╝█████╗░░██╔██╗██║░░░██║░░░██║░░██║██████╔╝░╚████╔╝░\n" +
            "██║██║╚████║░╚████╔╝░██╔══╝░░██║╚████║░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░\n" +
            "██║██║░╚███║░░╚██╔╝░░███████╗██║░╚███║░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░\n" +
            "╚═╝╚═╝░░╚══╝░░░╚═╝░░░╚══════╝╚═╝░░╚══╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░";

    private static final String GREET_MESSAGE = "Welcome to MagusStock. How may I assist you today?";
    private static final String EXIT_MESSAGE = "Hope you had an enjoyable experience. See you next time!";
    private static final String UNKNOWN_COMMAND = "I don't understand that command, please refer to the user guide " +
            "or enter 'help' for all available commands";
    private static final String INVALID_ADD = "Wrong/Incomplete Entry For Add! Please refer to UG for more " +
            "information\nSample Format: \"add n/[name] upc/[UPC] qty/[quantity] p/[price] c/[category]\"";

    private static final String INVALID_AUTO_SAVE_INPUT = "Invalid input! Please enter either 'on' or 'off' to " +
            "enable/disable auto-save";
    private static final String AUTOSAVE_ON = "Auto-save has been enabled!";
    private static final String AUTOSAVE_OFF = "Auto-save has been disabled!";
    private static final String DUPLICATE_ADD = "Duplicate UPC found! Please add another item with a different UPC";
    private static final String SUCCESS_ADD = "Successfully added the item(s) into the system!";

    private static final String INVALID_LIST = "Wrong/Incomplete Format! Please list items in the following format : " +
            "\nSample Format: \"list\"";

    private static final String SUCCESS_LIST = "Here are the items in your inventory:";

    private static final String EMPTY_LIST = "There are no items in your inventory.";
    private static final String INVALID_SESSION_FILE = "INFO: A Session Inventory file was found but it is corrupted. "
            + "\nPlease delete the corrupt .csv file.";
    private static final String RECOVERED_SESSION_FILE = "INFO: Session Inventory Data recovered." +
            " The inventory has been updated.";
    private static final String EMPTY_SESSION_FILE = "INFO: Empty/No Session Inventory file found.";

    private static final String INVALID_ALERT_FILE = "INFO: A Session Alerts file was found but it is corrupted. " +
            "\n" + "      Please delete the corrupt .csv file.";

    private static final String EMPTY_ALERT_FILE = "INFO: Empty/No Session Alerts file found.";

    private static final String RECOVERED_ALERT_FILE = "INFO: Session Alert Data recovered." +
            " The list of active alerts has been updated.";

    private static final int INVENTORY_ATTRIBUTE_COUNT = 6;
    private static final int HELP_ATTRIBUTE_COUNT = 2;
    private static final int ALERT_ATTRIBUTE_COUNT = 3;

    private static final int NAME_COL_WIDTH = 15;
    private static final int UPC_COL_WIDTH = 12;
    private static final int QTY_COL_WIDTH = 8;

    private static final int PRICE_COL_WIDTH = 8;

    private static final int INDEX_COL_WIDTH = 5;
    private static final int COMMAND_COL_WIDTH = 25;
    private static final int FORMAT_COL_WIDTH = 25;
    private static final String QUANTITY_AVAILABLE_LABEL = "Quantity Available: ";
    private static final String UPC_CODE_LABEL = "UPC Code: ";
    private static final String ITEM_NAME_LABEL = "Item Name: ";
    private static final String INVALID_EDIT_FORMAT = "Wrong/Incomplete Entry For Edit! Please refer to UG for more " +
            "information.\nSample Format: \"edit upc/[UPC] n/[Name] qty/[Quantity] p/[Price] c/[Category]\"";
    private static final String ITEM_NOT_FOUND = "Command failed! Reason: Item not found in database. " +
            "Please add item first!";
    private static final String SUCCESS_EDIT = "Successfully edited the following item:";
    private static final String SUCCESS_RESTOCK = "Successfully restocked the following item:";
    private static final String SUCCESS_SELL = "Successfully sold the following item:";
    private static final String ITEM_NOT_EDITED = "Item Specified will not be updated.";
    private static final String WRONG_QUANTITY_INPUT = "For Quantity inputs: MUST BE A POSITIVE WHOLE NUMBER/ZERO.";
    private static final String WRONG_PRICE_INPUT = "For Price inputs: MUST BE A POSITIVE WHOLE " +
            "NUMBER/DECIMAL NUMBER/ZERO.";
    private static final String INVALID_RESTOCK_FORMAT = "Wrong/Incomplete Entry For Restock! Please refer to UG for " +
            "more information\nSample Format: \"restock upc/[UPC] qty/[Quantity]\"";
    private static final String INVALID_ADD_QUANTITY_FORMAT = "Unable to restock item. REASON: Quantity inputs" +
            " SHOULD NOT contain NEGATIVE integers, ZERO(0), or STRING inputs!" + "\n" +
            "Also ensure that the desired quantity to be added does not cause current stock levels to exceed MAX" +
            "\n" + "quantity limit of 99,999,999.";
    private static final String INVALID_DEDUCT_QUANTITY_FORMAT = "Unable to sell item. REASON: Quantity inputs" +
            " SHOULD NOT contain NEGATIVE integers, DECIMALS, ZERO(0), or STRING inputs!" + "\n" +
            "Also ensure that the desired" + " quantity to be deducted is LESS THAN current stock levels.";
    private static final String INVALID_SELL_FORMAT = "Wrong/Incomplete Entry For Sell! Please refer to UG for more" +
            " information.\nSample Format: \"sell upc/[UPC] qty/[Quantity]\"";
    private static final String NO_SEARCH_RESULTS = "Unfortunately, no search results could be found. Try again?";
    private static final String MISSING_PRICE = "Please enter a number for the price!";
    private static final String ITEM_ADDED_AT = "Item added at: ";
    private static final String AT = "At: ";
    private static final String CATEGORY_CHANGED_TO = "Category changed to: ";
    private static final String SOLD = "Sold ";
    private static final String ITEMS = " items";
    private static final String BOUGHT = "Bought ";
    private static final String RENAMED_TO = "Renamed to: ";
    private static final String PRICE_DECREASED_FROM = "Price decreased from $";
    private static final String PRICE_INCREASED_FROM = "Price increased from $";
    private static final String TO_DOLLAR_SIGN = " to $";

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
    private static final String INVALID_INDEX = "This index is invalid.\nPlease enter number ";

    private static final String INVALID_ALERT_KEYWORD = "Keyword after alert can only be \"add\", \"remove\" ";

    private static final String INVALID_ALERT_PARAMETER = "Wrong/Incomplete Format! Please refer to the user guide" +
            " for the correct alert parameters." + "\nSample Format: \n To add alert: " +
            "\"alert add upc/[UPC] min/[integer]\"" +
            " OR " +
            "\"alert add upc/[UPC] max/[integer]\"" +
            "\n To remove alert: " +
            "\"alert remove upc/[UPC] level/min\"" +
            " OR " +
            "\"alert remove upc/[UPC] level/max\"";
    private static final String INVALID_ADD_ALERT =
            "Wrong/Incomplete Entry For Add Alert! Please refer to UG for more information \nSample Format:\n" +
                    "\"alert add upc/[UPC] min/[integer]\"" +
                    " OR\n" +
                    "\"alert add upc/[UPC] max/[integer]\"";

    private static final String EXISTING_MIN_ALERT = "This item already has a minimum alert. " +
            "Delete the existing one first.";

    private static final String EXISTING_MAX_ALERT = "This item already has a maximum alert. " +
            "Delete the existing one first.";

    private static final String INVALID_MIN_ALERT = "Minimum value to set an alert must be less than existing " + "" +
            "maximum alert value of this item.";

    private static final String INVALID_MAX_ALERT = "Maximum value to set an alert must be more than existing " + "" +
            "minimum alert value of this item.";

    private static final String SUCCESS_ADD_ALERT = "Successfully added a new alert.";

    private static final String INVALID_REMOVE_ALERT =
            "Wrong/Incomplete Entry For Remove Alert! Please refer to UG for more information\nSample Format:\n" +
                    "\"alert remove upc/[UPC] level/min\"" +
                    " OR\n" +
                    "\"alert remove upc/[UPC] level/max\"";
    private static final String SUCCESS_REMOVE_ALERT = "Successfully removed the alert.";

    private static final String NONEXISTENT_REMOVE_ALERT = "The alert that you are attempting to remove " +
            "does not exist.";
    private static final String INVALID_ALERT_TYPE = "Alert is not a valid type (min/max)";
    private static final int CATEGORY_COL_WIDTH = 15;
    private static final int ITEMS_COL_WIDTH = 45;
    private static final String NO_CATEGORY_LIST = "Category list is empty. There are no items in the inventory.";
    private static final String INVALID_CATEGORY_FIND = "The category you are looking for does not exist.";
    private static final String INVALID_CATEGORY = "The category does not exist.";
    private static final String NEW_CATEGORY_ADDED = "A new category has been added.";
    private static final String NO_CHANGES_WERE_RECORDED = "An edit attempt was made, but no changes were recorded.";
    private static final int ORIGINAL_ITEM_INDEX = 0;
    private static final String INVALID_UPC = "This UPC is invalid. Try again.";
    private static final String CATEGORY_LISTING = "Here is the list of categories you have: ";
    private static final String RARE_RACE_CONDITION_OCCURRED = "A rare race condition occurred. "
            + "Please try restarting the program";
    private static final String CHECK_OTHER_PROGRAMS = "If this happens often, check that other programs"
            + " are not interfering with this one";
    private static final String YOUR_ITEM = "Here is your item: ";
    private static final String STOCK_HEADING = "Stock";


    /**
     * Prints out line separator.
     */
    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out a warning message that the price entered is not a number.
     */
    public static void printDoubleNeeded() {
        System.out.println(MISSING_PRICE);
    }

    /**
     * Prints out a message informing that no search results can be found.
     */
    public static void printEmptySearch() {
        printLine();
        System.out.println(NO_SEARCH_RESULTS);
        printLine();
    }

    /**
     * Prints out item that was searched for using UPC.
     * @param item The item that was searched for using UPC.
     */
    public static void printSearchUPCItem(Item item) {
        printLine();
        System.out.println(YOUR_ITEM);
        ArrayList<Item> singleItem = new ArrayList<>();
        singleItem.add(item);
        System.out.println(printTable(singleItem));
        printLine();
    }

    /**
     * Prints out table of items that was filtered or searched for.
     * @param items List of items to be filtered or searched.
     */
    public static void printSearchItems(ArrayList<Item> items) {
        printLine();
        System.out.println(printTable(items));
        printLine();
    }

    /**
     * Prints out exit message when the user exits the program.
     */
    public static void printExitMessage() {
        printLine();
        System.out.println(EXIT_MESSAGE);
        printLine();
    }

    /**
     * Prints out MagusStock logo and greeting message.
     */
    public static void greetUser() {
        printLine();
        System.out.println(LOGO);
        System.out.println(GREET_MESSAGE);
        printLine();
    }

    /**
     * Prints out a message informing that the session inventory file is corrupted.
     */
    public static void printInvalidSessionFile() {
        System.out.println(INVALID_SESSION_FILE);
        printLine();
    }

    /**
     * Prints out a message informing that the session inventory file has been recovered.
     */
    public static void printRecoveredSessionFile() {
        System.out.println(RECOVERED_SESSION_FILE);
        printLine();
    }

    /**
     * Prints out a message informing that the session inventory file is empty.
     */
    public static void printEmptySessionFile() {
        System.out.println(EMPTY_SESSION_FILE);
        printLine();
    }

    /**
     * Prints out a warning message to inform the user that the AlertData.csv file is corrupted.
     */
    public static void printInvalidAlertFile() {
        System.out.println(INVALID_ALERT_FILE);
        printLine();
    }

    /**
     * Prints out a message that the AlertData.csv file is empty or does not exist.
     */
    public static void printEmptyAlertFile() {
        System.out.println(EMPTY_ALERT_FILE);
        printLine();
    }

    /**
     * Prints out a warning message that the AlertData.csv file has been recovered.
     */
    public static void printRecoveredAlertFile() {
        System.out.println(RECOVERED_ALERT_FILE);
        printLine();
    }

    /**
     * Prints out a warning message that the command is not understood.
     */
    public static void printUnknownCommand() {
        printLine();
        System.out.println(UNKNOWN_COMMAND);
        printLine();
    }

    /**
     * Prints out a warning message that the format of the add command is invalid.
     */
    public static void printInvalidAddCommand() {
        printLine();
        System.out.println(INVALID_ADD);
        printLine();
    }

    /**
     * Prints out a warning message that the format of the autosave commmand is invalid.
     */
    public static void printInvalidAutoSaveInput() {
        printLine();
        System.out.println(INVALID_AUTO_SAVE_INPUT);
        printLine();
    }

    /**
     * Prints out a message informing the user that autosave has been enabled.
     */
    public static void printAutoSaveEnabled() {
        printLine();
        System.out.println(AUTOSAVE_ON);
        printLine();
    }

    /**
     * Prints out a message informing the user that autosave has been disabled.
     */
    public static void printAutoSaveDisabled() {
        printLine();
        System.out.println(AUTOSAVE_OFF);
        printLine();
    }

    /**
     * Prints out a message informing the user that an item with a duplicate UPC is being added.
     */
    public static void printDuplicateAdd() {
        printLine();
        System.out.println(DUPLICATE_ADD);
        printLine();
    }

    /**
     * Prints out a message informing the user that item has been successfully added.
     */
    public static void printSuccessAdd() {
        printLine();
        System.out.println(SUCCESS_ADD);
        printLine();
    }

    /**
     * Prints out a warning message when race condition occurs.
     */
    public static void printRaceCondition() {
        printLine();
        System.out.println(RARE_RACE_CONDITION_OCCURRED);
        System.out.println(CHECK_OTHER_PROGRAMS);
        printLine();
    }

    /**
     * Prints out logo indicating the inventory, and that the list of inventory items has been successfully printed.
     */
    public static void printSuccessList() {
        printLine();
        System.out.println(INVENTORYLOGO);
        System.out.println(SUCCESS_LIST);
    }

    /**
     * Prints out message that there are no items in the inventory.
     */
    public static void printEmptyList() {
        printLine();
        System.out.println(EMPTY_LIST);
        printLine();
    }

    /**
     * Prints out message that the command format for listing out inventory items is incorrect.
     */
    public static void printInvalidList() {
        printLine();
        System.out.println(INVALID_LIST);
        printLine();
    }

    /**
     * Creates a string containing a table of all categories in the inventory and their associated items.
     * @param categoryHash Hash map of categories and their associated items.
     * @return String containing the table of categories and their associated items.
     */
    public static String printTable(HashMap<String, ArrayList<Item>> categoryHash) {
        int[] columnWidths = {CATEGORY_COL_WIDTH, ITEMS_COL_WIDTH};

        StringBuilder table = new StringBuilder();

        table.append(printTableSeparator(columnWidths));
        table.append(printHeadings(columnWidths));
        table.append(printTableSeparator(columnWidths));
        categoryHash.forEach((category, items)
                -> table.append((printRow(CategoryCommand.capitaliseCategory(category), items, columnWidths))));
        return table.toString();
    }

    /**
     * Creates a string containing a table of all commands in the program and the format for executing the commands.
     * @return String containing a table of all commands and their format.
     */
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


    /**
     * Creates a string containing a table of all items in the inventory, including the index, name, UPC, quantity,
     * price and category of each item.
     *
     * @param items The ArrayList containing all items in the inventory.
     * @return String containing the table of inventory items and their associated details.
     */
    public static String printTable(ArrayList<Item> items) {
        int[] columnWidths = {INDEX_COL_WIDTH, NAME_COL_WIDTH, UPC_COL_WIDTH, QTY_COL_WIDTH,
            PRICE_COL_WIDTH, CATEGORY_COL_WIDTH};

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

    /**
     * Creates a string containing a table of alerts, including the name and UPC of the item that the alert is set for,
     * as well as the quantity at which the alert is triggered.
     *
     * @param upcMap    The hash map containing the UPC and quantity of the alerts to be printed.
     * @param inventory The inventory of items that contains the names of items.
     * @return String containing the table of alert UPCs, names and quantities.
     */
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

    /**
     * Prints out the headings for a table.
     *
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing the headings for a table.
     */
    private static String printHeadings(int[] columnWidths) {
        String[] headings = {};
        if (columnWidths.length == INVENTORY_ATTRIBUTE_COUNT) {
            headings = new String[] {INDEX_HEADING, NAME_HEADING, UPC_HEADING, QTY_HEADING, PRICE_HEADING,
                                     CATEGORY_HEADING};
        } else if (columnWidths.length == HELP_ATTRIBUTE_COUNT && columnWidths[0] == COMMAND_COL_WIDTH) {
            headings = new String[] {COMMAND_HEADING, FORMAT_HEADING};
        } else if (columnWidths.length == ALERT_ATTRIBUTE_COUNT) {
            headings = new String[] {NAME_HEADING, UPC_HEADING, STOCK_HEADING};
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

    /**
     * Prints out a sequence of - and + characters to form the horizontal row separators of a table.
     *
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing one horizontal row separator for a table.
     */
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

    /**
     * Prints out one row of a table when listing all possible commands.
     * @param description The description of the command to be printed in the row.
     * @param format The format of the command to be printed in the row.
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing one row of the table.
     */
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

    /**
     * Prints out one row of a table when listing all items sorted by their category.
     * @param category The category to be printed in the row.
     * @param items The list of items belonging to the category to be printed in the row.
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing one row of the table.
     */
    private static String printRow(String category, ArrayList<Item> items, int[] columnWidths) {
        String[] categoryLines = wrapText(category, CATEGORY_COL_WIDTH);
        ArrayList<String> itemLines = new ArrayList<>();
        StringBuilder row = new StringBuilder();

        for (Item item : items) {
            String name = item.getName();
            String upc = item.getUpc();
            name = name.replaceAll(" ", "_");
            itemLines.add(name + ":_" + upc);
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

    /**
     * Prints out one row of a table when listing out all the items in the inventory.
     *
     * @param name         The name of the item to be printed.
     * @param upc          The UPC of the item to be printed.
     * @param qty          The quantity of the item to be printed.
     * @param price        The price of the item to be printed.
     * @param category     The category of the item to be printed.
     * @param index        The index of the item to be printed.
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing one row of the table.
     */
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

    /**
     * Prints out one row of a table when listing all alerts.
     *
     * @param name         The name of the item that has an alert.
     * @param upc          The UPC of the item that has an alert.
     * @param stock        The minimum or maximum quantity of an item that is specified by the alert.
     * @param columnWidths The array of integers that stores the width of each column in the table.
     * @return String containing one row of the table.
     */
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

    /**
     * Prints out one attribute of the object in concern in a row (e.g. the name of an item).
     *
     * @param attributeLines The array of strings containing the object attribute to be printed.
     * @param columnWidth    The width of the column that the attribute is printed in.
     * @param rowNumber      The number of the current row that the attribute is being printed on.
     * @return String containing the substring of the attribute that fits within columnWidth.
     */
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


    /**
     * Splits a string into portions based on the width of the table column that the string will be printed in.
     * This allows for printing out a string with text wrapping.
     * Adapted from &lt;a href = "https://stackoverflow.com/questions/4055430/java-code-for-wrapping-text-lines-to-a-
     * max-line-width&rt;StackOverflow: Java code for wrapping text lines to a max line width&lt;/a&rt;
     *
     * @param input The string to be split.
     * @param width The width of the table column.
     * @return Array of strings containing the portions of the string.
     */
    private static String[] wrapText(String input, int width) {
        if (!input.contains("/")) {
            if (input.contains("[")) {
                input = input.replace("[", "");
            }
            if (input.contains("]")) {
                input = input.replace("]", "");
            }
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

    /**
     * Adds a word that does not require wrapping to a StringBuilder object.
     *
     * @param line    The StringBuilder object that contains previously-added words.
     * @param words   The array of strings containing whitespace-separated words.
     * @param lines   The ArrayList that existing words in line will be added to if the word to be added results in
     *                the length of line exceeding the column width.
     * @param current The array index indicating the word to be added from the words array.
     * @param width   The width of the table column that the word will be printed in.
     * @return StringBuilder object containing the words to be printed.
     */
    private static StringBuilder addWordWithoutWrap(StringBuilder line, String[] words, ArrayList<String> lines,
                                                    int current, int width) {
        if (words[current].contains("_")) {
            words[current] = words[current].replaceAll("_", " ");
        }
        line.append(words[current]);

        if (words[current].contains(",")) {
            int addSpace = width - line.length();
            for (int i = 0; i < addSpace; i++) {
                line.append(" ");
            }
        }
        if (line.length() < width) {
            line.append(" ");
        }

        if (current + 1 != words.length && line.length() + words[current + 1].length() > width) {
            lines.add(line.toString());
            line = new StringBuilder();
        }
        return line;
    }

    /**
     * Adds a word that requires wrapping to the next row to an ArrayList.
     *
     * @param words   The array of strings containing whitespace-separated words.
     * @param lines   The ArrayList that the wrapped word will be added to.
     * @param current The array index indicating the word to be added from the words array.
     * @param width   The width of the table column that the word will be printed in.
     */
    private static void addWordWithWrap(String[] words, ArrayList<String> lines, int current, int width) {
        int start = 0;
        if (words[current].contains("_")) {
            words[current] = words[current].replaceAll("_", " ");
        }
        while (start < words[current].length()) {
            int end = Math.min(start + width, words[current].length());
            lines.add(words[current].substring(start, end));
            start = end;
        }
    }

    /**
     * Finds the height of a row in a table.
     *
     * @param rowHeights The array of strings containing the row height required for each attribute.
     * @return The integer value representing the required height of the row.
     */
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
        System.out.println(ITEM_NAME_LABEL + oldItem.getName() + "\n" + UPC_CODE_LABEL + oldItem.getUpc() + "\n" +
                QUANTITY_AVAILABLE_LABEL + oldItem.getQuantity());
        System.out.println("\n" + "After Restocking: ");
        System.out.println(ITEM_NAME_LABEL + updatedItem.getName() + "\n" + UPC_CODE_LABEL + updatedItem.getUpc() +
                "\n" + QUANTITY_AVAILABLE_LABEL + updatedItem.getQuantity());
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
        System.out.println(ITEM_NAME_LABEL + oldItem.getName() + "\n" + UPC_CODE_LABEL + oldItem.getUpc() + "\n" +
                QUANTITY_AVAILABLE_LABEL + oldItem.getQuantity());
        System.out.println("\n" + "After Selling: ");
        System.out.println(ITEM_NAME_LABEL + updatedItem.getName() + "\n" + UPC_CODE_LABEL + updatedItem.getUpc() +
                "\n" + QUANTITY_AVAILABLE_LABEL + updatedItem.getQuantity());
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

    /**
     * Prints out message to inform the user that item has been successfully removed.
     *
     * @param itemToRemove The item to be removed.
     */
    public static void printSuccessRemove(Item itemToRemove) {
        printLine();
        System.out.println(SUCCESS_REMOVE);
        System.out.println(itemToRemove.toString());
        printLine();
    }

    /**
     * Prints out message to inform user that the index for removing items is invalid.
     * @param inventory Inventory containing the item to be removed.
     */
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

    /**
     * Prints out message to inform user that the UPC of the item being removed is invalid.
     * @param inventory Inventory containing the item to be removed.
     */
    public static void printInvalidUpc(Inventory inventory) {
        printLine();
        int listSize = inventory.getItemInventory().size();
        if (listSize == 0) {
            System.out.println(EMPTY_LIST);
        } else {
            System.out.println(INVALID_UPC);
        }
        printLine();
    }

    /**
     * Prints out warning message that a minimum alert already exists.
     */
    public static void printExistingMinAlert() {
        printLine();
        System.out.println(EXISTING_MIN_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that a maximum alert already exists.
     */
    public static void printExistingMaxAlert() {
        printLine();
        System.out.println(EXISTING_MAX_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that the format for adding an alert is incorrect.
     */
    public static void printInvalidAddAlertCommand() {
        printLine();
        System.out.println(INVALID_ADD_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that the format for adding a minimum alert is incorrect.
     */
    public static void printInvalidMinAlert() {
        printLine();
        System.out.println(INVALID_MIN_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that the format for adding a maximum alert is incorrect.
     */
    public static void printInvalidMaxAlert() {
        printLine();
        System.out.println(INVALID_MAX_ALERT);
        printLine();
    }

    /**
     * Prints out message that an alert was successfully added.
     */
    public static void printSuccessAddAlert() {
        printLine();
        System.out.println(SUCCESS_ADD_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that an invalid keyword was specified after the "add" command.
     */
    public static void printInvalidAlertKeyword() {
        printLine();
        System.out.println(INVALID_ALERT_KEYWORD);
        printLine();
    }

    /**
     * Prints out warning message that the format for the alert command is incorrect.
     */
    public static void printInvalidAlertParameter() {
        printLine();
        System.out.println(INVALID_ALERT_PARAMETER);
        printLine();
    }

    /**
     * Prints out warning message that the format for removing an alert is incorrect.
     */
    public static void printInvalidRemoveAlertCommand() {
        printLine();
        System.out.println(INVALID_REMOVE_ALERT);
        printLine();
    }

    /**
     * Prints out message that an alert was successfully removed.
     */
    public static void printSuccessRemoveAlertCommand() {
        printLine();
        System.out.println(SUCCESS_REMOVE_ALERT);
        printLine();
    }

    /**
     * Prints out warning message that the alert the user is attempting to remove does not exist.
     */
    public static void printNonExistentRemoveAlert() {
        printLine();
        System.out.println(NONEXISTENT_REMOVE_ALERT);
        printLine();
    }

    /**
     * Creates error message that the alert type is invalid (neither minimum nor maximum).
     *
     * @return String containing the error message to be printed.
     */
    public static String printInvalidAlertType() {
        StringBuilder sb = new StringBuilder("");
        sb.append(LINE);
        sb.append(INVALID_ALERT_TYPE);
        sb.append(LINE);
        return sb.toString();
    }

    /**
     * Creates a string containing all alerts, if any.
     *
     * @param inventory The inventory of items containing the item names that have alerts associated with them.
     * @param alertList The AlertList object containing the two hash maps that store minimum and maximum alerts.
     * @return String containing the table to be printed out, or an error message if there are no alerts to print.
     */
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

    /**
     * Prints dashboard of information relating to inventory and system configurations.
     *
     * @param inventory The inventory containing all items.
     * @param alertList The AlertList object containing the two hash maps that store minimum and maximum alerts.
     */
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
        System.out.println("Inventory Data File Status: " + SessionManager.inventoryDataFileExist(inventory));
        System.out.println("Alerts Data File Status: " + SessionManager.alertDataFileExist(inventory));
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

    /**
     * Prints out a warning message when the quantity of an item goes below its minimum alert level.
     *
     * @param name       Name of the item in concern.
     * @param alertLevel The minimum alert level set for the item.
     */
    public static void printMinAlertWarning(String name, int alertLevel) {
        System.out.println("ALERT: The quantity of " + name +
                " is below the minimum level of " + alertLevel + ".");
        printLine();
    }

    /**
     * Prints out a warning message when the quantity of an item exceeds its maximum alert level.
     *
     * @param name       Name of the item in concern.
     * @param alertLevel The maximum alert level set for the item.
     */
    public static void printMaxAlertWarning(String name, int alertLevel) {
        System.out.println("ALERT: The quantity of " + name +
                " is above the maximum level of " + alertLevel + ".");
        printLine();
    }

    /**
     * Prints out a formatted table of categories.
     * @param categoryHash Hash map of categories and their associated items.
     */
    public static void printCategory(HashMap<String, ArrayList<Item>> categoryHash) {
        printLine();
        System.out.println(printTable(categoryHash));
        printLine();
    }

    /**
     * Prints out a formatted list of categories.
     * @param categoryHash List of categories.
     */
    public static void printCategory(ArrayList<Item> categoryHash) {
        printLine();
        System.out.println(printTable(categoryHash));
        printLine();
    }

    /**
     * Prints out a warning message that there are no categories to be printed.
     */
    public static void printNoCategoryList() {
        printLine();
        System.out.println(NO_CATEGORY_LIST);
        printLine();
    }

    /**
     * Prints out error message that the category does not exist.
     */
    public static void printInvalidCategory() {
        printLine();
        System.out.println(INVALID_CATEGORY_FIND);
        printLine();
    }

    /**
     * Prints warning message that the category being added is invalid.
     */
    public static void printNewCategory() {
        printLine();
        System.out.println(INVALID_CATEGORY + " " + NEW_CATEGORY_ADDED);
        printLine();
    }

    /**
     * Prints out list of all categories.
     * @param categoryHash Hash map of categories and their associated items.
     */
    public static void printCategoryList(HashMap<String, ArrayList<Item>> categoryHash) {
        printLine();
        System.out.println(CATEGORY_LISTING);
        categoryHash.forEach((cat, items) -> System.out.println(CategoryCommand.capitaliseCategory(cat)));
        printLine();
    }
}

