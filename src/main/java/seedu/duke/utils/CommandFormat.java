package seedu.duke.utils;

import java.util.HashMap;

public class CommandFormat {
    public static final String ADD_FORMAT = "add n/[Name] upc/[UPC] qty/[Quantity] p/[Price] c/[Category]";
    public static final String EDIT_FORMAT = "edit upc/[UPC] {n/[Name] qty/[Quantity] p/[Price]}";
    public static final String REMOVE_FORMAT = "remove f/upc [UPC], remove f/index [Index]";
    public static final String RESTOCK_FORMAT = "restock upc/[UPC] qty/[Quantity]";
    public static final String SELL_FORMAT = "sell upc/[UPC] qty/[Quantity]";
    public static final String HELP_FORMAT = "help";
    public static final String SEARCH_KEYWORD_FORMAT = "search [Keywords]";
    public static final String SEARCH_UPC_FORMAT = "searchupc [UPC]";
    public static final String FILTER_FORMAT = "filter f/category [category], filter f/[gt/get/lt/let] [price]";
    public static final String LIST_FORMAT = "list";
    public static final String ALERT_ADD_FORMAT = "alert add upc/[UPC] {min/[Minimum] or max/[Maximum]}";

    public static final String ALERT_REMOVE_FORMAT = "alert remove upc/[UPC] level/[Min or Max]";
    public static final String CATEGORY_FORMAT = "cat [list or table]";
    public static final String HISTORY_FORMAT = "history [UPC]";
    public static final String EXIT_FORMAT = "exit or bye";

    public static final String DASHBOARD_FORMAT = "db";
    public static final String ADD_DESCRIPTION = "add: adds an item to the inventory list";
    public static final String EDIT_DESCRIPTION = "edit: edits relevant information of an item";
    public static final String REMOVE_DESCRIPTION = "remove: removes an item from the inventory list";
    public static final String RESTOCK_DESCRIPTION = "restock: increases the quantity of an item";
    public static final String SELL_DESCRIPTION = "sell: decreases the quantity of an item";
    public static final String HELP_DESCRIPTION = "help: shows you this list of commands";
    public static final String LIST_DESCRIPTION = "list: shows you the inventory list of items";
    public static final String SEARCH_KEYWORD_DESCRIPTION = "search: search existing items by keywords";
    public static final String SEARCH_UPC_DESCRIPTION = "searchupc: search existing items by UPC";
    public static final String FILTER_DESCRIPTION = "filter: filters items by category or price or tag";
    public static final String ALERT_ADD_DESCRIPTION = "alert add: adds new alert for an item to "
            + "track its stock count";
    public static final String ALERT_REMOVE_DESCRIPTION = "alert remove: removes existing alert for an item";
    public static final String CATEGORY_DESCRIPTION = "cat: list all categories or list table " +
            "of categories and items respectively";
    public static final String HISTORY_DESCRIPTION = "history: shows the historical commands executed for an item";
    public static final String DASHBOARD_DESCRIPTION = "db: displays the dashboard of Magus-Stock";
    public static final String EXIT_DESCRIPTION = "exit: exits the MagusStock program";


    protected HashMap<String, String> commandsHashMap;

    public CommandFormat(HashMap<String, String> commandsHashMap) {
        this.commandsHashMap = getCommandsHashMap(commandsHashMap);
    }

    public HashMap<String, String> getCommandsHashMap(HashMap<String, String> commands) {
        commands.put(ADD_FORMAT, ADD_DESCRIPTION);
        commands.put(REMOVE_FORMAT, REMOVE_DESCRIPTION);
        commands.put(EDIT_FORMAT, EDIT_DESCRIPTION);
        commands.put(RESTOCK_FORMAT, RESTOCK_DESCRIPTION);
        commands.put(SELL_FORMAT, SELL_DESCRIPTION);
        commands.put(LIST_FORMAT, LIST_DESCRIPTION);
        commands.put(SEARCH_UPC_FORMAT, SEARCH_UPC_DESCRIPTION);
        commands.put(SEARCH_KEYWORD_FORMAT, SEARCH_KEYWORD_DESCRIPTION);
        commands.put(FILTER_FORMAT, FILTER_DESCRIPTION);
        commands.put(CATEGORY_FORMAT, CATEGORY_DESCRIPTION);
        commands.put(ALERT_ADD_FORMAT, ALERT_ADD_DESCRIPTION);
        commands.put(ALERT_REMOVE_FORMAT, ALERT_REMOVE_DESCRIPTION);
        commands.put(HELP_FORMAT, HELP_DESCRIPTION);
        commands.put(HISTORY_FORMAT, HISTORY_DESCRIPTION);
        commands.put(DASHBOARD_FORMAT, DASHBOARD_DESCRIPTION);
        commands.put(EXIT_FORMAT, EXIT_DESCRIPTION);
        return commands;
    }
}
