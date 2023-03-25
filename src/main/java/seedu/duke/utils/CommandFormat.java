package seedu.duke.utils;

import java.util.HashMap;

public class CommandFormat {
    public static final String ADD_FORMAT = "add n/[Name] upc/[UPC] qty/[Quantity] p/[Price]";
    public static final String EDIT_FORMAT = "edit upc/[UPC] {n/[Name] qty/[Quantity] p/[Price]}";
    public static final String REMOVE_FORMAT = "remove f/item upc/[UPC]\nremove f/index [Index]";
    public static final String HELP_FORMAT = "help";
    public static final String SEARCH_KEYWORD_FORMAT = "search [Keyword]";
    public static final String SEARCH_UPC_FORMAT = "searchupc [UPC]";
    public static final String FILTER_FORMAT = "filter f/[Filter type] {p/[Price type] [Category or Price or Tag]}";
    public static final String LIST_FORMAT = "list";
    public static final String ALERT_ADD_FORMAT = "alert add upc/[UPC] {min/[Minimum] or max/[Maximum]}";

    public static final String ALERT_REMOVE_FORMAT = "alert remove upc/[UPC] level/[Min or Max]";
    public static final String CATEGORY_FORMAT = "cat n/[Name] c/[Category]";
    public static final String TAG_FORMAT = "tag a/[Add/Remove] n/[Name] [Tag]";

    public static final String ADD_DESCRIPTION = "add:\nadds an item to the inventory list";
    public static final String EDIT_DESCRIPTION = "edit:\nedits relevant information of an item";
    public static final String REMOVE_DESCRIPTION = "remove:\nremoves an item from the inventory list";
    public static final String HELP_DESCRIPTION = "help:\nshows you this list of commands";
    public static final String LIST_DESCRIPTION = "list:\nshows you the inventory list of items";
    public static final String SEARCH_KEYWORD_DESCRIPTION = "search:\n search existing items by keywords";
    public static final String SEARCH_UPC_DESCRIPTION = "searchupc:\nsearch existing items by UPC";
    public static final String FILTER_DESCRIPTION = "filter:\nfilters items by category or price or tag";
    public static final String ALERT_ADD_DESCRIPTION = "alert add:\nadds new alert for an item to "
            + "track its stock count";
    public static final String ALERT_REMOVE_DESCRIPTION = "alert remove:\nremoves existing alert for an item";
    public static final String CATEGORY_DESCRIPTION = "cat:\nputs an item into the specified category";
    public static final String TAG_DESCRIPTION = "tag:\nadds or removes a tag of an existing item";

    protected HashMap<String, String> commandsHashMap;
    public CommandFormat(HashMap<String, String> commandsHashMap) {
        this.commandsHashMap = getCommandsHashMap(commandsHashMap);
    }

    public HashMap<String, String> getCommandsHashMap(HashMap<String, String> commands) {
        commands.put(ADD_FORMAT, ADD_DESCRIPTION);
        commands.put(REMOVE_FORMAT, REMOVE_DESCRIPTION);
        commands.put(EDIT_FORMAT, EDIT_DESCRIPTION);
        commands.put(LIST_FORMAT, LIST_DESCRIPTION);
        commands.put(SEARCH_UPC_FORMAT, SEARCH_UPC_DESCRIPTION);
        commands.put(SEARCH_KEYWORD_FORMAT, SEARCH_KEYWORD_DESCRIPTION);
        commands.put(FILTER_FORMAT, FILTER_DESCRIPTION);
        commands.put(CATEGORY_FORMAT, CATEGORY_DESCRIPTION);
        commands.put(TAG_FORMAT, TAG_DESCRIPTION);
        commands.put(ALERT_ADD_FORMAT, ALERT_ADD_DESCRIPTION);
        commands.put(ALERT_REMOVE_FORMAT, ALERT_REMOVE_DESCRIPTION);
        commands.put(HELP_FORMAT, HELP_DESCRIPTION);
        return commands;
    }
}
