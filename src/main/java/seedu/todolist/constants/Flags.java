package seedu.todolist.constants;

import java.util.HashMap;

/**
 * Enum that holds all the flags allowed in commands.
 */
public enum Flags {
    // Command flags that do not take arguments
    COMMAND_EXIT("exit", true),
    COMMAND_LIST("list", true),
    COMMAND_TAG_LIST("taglist", true),
    COMMAND_PROGRESS("progress", true),
    COMMAND_HELP("help", true),

    // Command flags that take arguments
    COMMAND_ADD("add", false),
    COMMAND_MARK("mark", false),
    COMMAND_UNMARK("unmark", false),
    COMMAND_DELETE("delete", false),
    COMMAND_FULL_INFO("info", false),
    COMMAND_FIND_TAG("findbytag", false),
    COMMAND_FIND_PRIORITY("findbypriority", false),
    COMMAND_CONFIG("config", true),

    // Flags for commands that edit task parameters
    COMMAND_EDIT_DESCRIPTION("desc", false),
    COMMAND_EDIT_DEADLINE("due", false),
    COMMAND_EDIT_EMAIL("email", false),
    COMMAND_EDIT_TAGS("tags", false),
    COMMAND_EDIT_REPEAT("rep", false),
    COMMAND_EDIT_PRIORITY("prio", false),

    // Argument flags
    DESCRIPTION("-desc", false),
    DEADLINE("-due", false),
    EMAIL("-email", false),
    PRIORITY("-prio", false),
    REPEAT("-rep", false),
    TAG("-tags", false),
    EDIT("-edit", false),
    EDIT_DELETE("-del", true),
    RESET("-reset", true),
    SORT("-sort", false),

    // Filter flags
    FILTER_DONE("-done", false),
    FILTER_OVERDUE("-overdue", false),

    // Config flags
    CONFIG_CHECK_FREQ("-chkfreq", false),
    CONFIG_REPEAT_FREQ("-repfreq", false);

    private static final HashMap<String, Flags> map = new HashMap<>();
    private final String name;
    private final boolean canBeEmpty;

    /**
     * Constructs the flags enum.
     *
     * @param name Allowed flag in a command.
     * @param canBeEmpty Whether this flag expects an argument after it.
     *                   True means an argument can still be provided after the flag, but it may not be used.
     */
    Flags(String name, boolean canBeEmpty) {
        this.name = name;
        this.canBeEmpty = canBeEmpty;
    }

    public String getName() {
        return name;
    }

    public boolean canBeEmpty() {
        return canBeEmpty;
    }

    static {
        for (Flags flag : Flags.values()) {
            map.put(flag.name, flag);
        }
    }

    public static Flags fromString(String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        return null;
    }
}
