package seedu.todolist.constants;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Enum that holds all the flags allowed in commands.
 */
public enum Flags {
    // Command flags that do not take arguments
    COMMAND_EXIT("exit", true),
    COMMAND_TAG_LIST("taglist", true),
    COMMAND_PROGRESS("progress", true),
    COMMAND_HELP("help", true),
    COMMAND_RESET("reset", true),

    // Command flags that can take arguments
    COMMAND_LIST("list", true),
    COMMAND_ADD("add", false),
    COMMAND_MARK("mark", true),
    COMMAND_UNMARK("unmark", true),
    COMMAND_DELETE("delete", true),
    COMMAND_FULL_INFO("info", true),
    COMMAND_CONFIG("config", true),

    // Commands that edit task parameters
    COMMAND_EDIT_DESCRIPTION("desc", true),
    COMMAND_EDIT_DEADLINE("due", true),
    COMMAND_EDIT_EMAIL("email", true),
    COMMAND_EDIT_TAGS("tags", true),
    COMMAND_EDIT_REPEAT("rep", true),
    COMMAND_EDIT_PRIORITY("prio", true),

    // Argument flags
    DESCRIPTION("-desc", false),
    DEADLINE("-due", false),
    EMAIL("-email", false),
    PRIORITY("-prio", false),
    REPEAT("-rep", false),
    TAG("-tags", false),
    EDIT("-edit", false),
    EDIT_ADD("-add", false),
    EDIT_DELETE("-del", true),
    RESET("-reset", true),
    SORT("-sort", false),

    // Filter flags
    FILTER_DONE("-done", false),
    FILTER_OVERDUE("-overdue", false),
    FILTER_BEFORE("-before", false),
    FILTER_AFTER("-after", false),
    FILTER_ALL("-all", true),

    // Config flags
    CONFIG_CHECK_FREQ("-chkfreq", false),
    CONFIG_REPEAT_FREQ("-repfreq", false);

    public static final HashSet<Flags> FILTER_FLAGS = new HashSet<>(Arrays.asList(
            Flags.FILTER_DONE, Flags.FILTER_OVERDUE, Flags.FILTER_BEFORE, Flags.FILTER_AFTER, Flags.FILTER_ALL,
            Flags.DESCRIPTION, Flags.EMAIL, Flags.REPEAT, Flags.TAG, Flags.PRIORITY
    ));
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

    /**
     * Converts the given string into the corresponding Flags object.
     *
     * @param name The string to convert into a Flags object.
     * @return A Flags object if there is a flag matching the given string, null otherwise.
     */
    public static Flags fromString(String name) {
        return map.get(name);
    }
}
