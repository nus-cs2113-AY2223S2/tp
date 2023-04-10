package seedu.fst.commands;

import seedu.fst.food.FoodList;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

//@@author ZhongXiangWong
/**
 * Represents a Help Command
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    private static final String SHOW_ALL_COMMANDS = "List of commands: 'add', 'list', 'remove', 'find', "
            + "'update', 'clear', 'exit'."
            + "\nFor more detailed information on usage of specific command, type: help --COMMAND";
    private static final String DEFAULT_HELP_MESSAGE = "Refer to our user guide for more in-depth details on"
            + " how to use Food Supply Tracker:"
            + "\nhttps://ay2223s2-cs2113-w13-3.github.io/tp/UserGuide.html";

    private static final String HELP_ADD = "Command 'add': This command adds a food product to the food supply tracker."
            + "\nFormat: add --n PRODUCT_NAME --e EXPIRY_DATE {--c category --q QUANTITY --u UNIT}"
            + "\n   Parameters cannot contain any punctuations and identifiers must follow the above format."
            + "\n   Identifier within brackets are optional."
            + "\n\nFormat for EXPIRY_DATE: DD/MM/YYYY"
            + "\n\nList of valid CATEGORY as follows:"
            + "\n   fruit, meat, vegetable, dairy, grain, seafood, beverage, others"
            + "\n   Any category provided that does not fall into the above-mentioned categories will be grouped under "
            + "'others'."
            + "\n\nQUANTITY must be specified when UNITS is used."
            + "\nList of valid UNIT as follows:"
            + "\n   mg, g, lg, ml, l, serving, servings, packet, packets, box, boxes, unit, units"
            + "\n   Any unit provided that does not fall into the above-mentioned units will be"
            + " quantified as 'unit(s)'.";

    private static final String HELP_LIST = "Command 'list': This command lists all food products in the tracker."
            + "\nFood products will be listed according to the order of expiry dates.";

    private static final String HELP_REMOVE = "Command 'remove': This command removes the food product from the"
            + " food supply tracker based on its index."
            + "\nFormat: remove INDEX";

    private static final String HELP_FIND = "Command 'find': This command filters the list according to the flags " +
            "applied."
            + "\nFormat: find {PRODUCT_NAME} {--fresh} {--expired} {--flag ATTRIBUTE_NAME}"
            + "\nList of valid flags as follows:"
            + "\n   --fresh: list non-expired items"
            + "\n   --expired: list expired items"
            + "\n   --u: filter by unit"
            + "\n   --q: filter by quantity"
            + "\n   --c: filter by category"
            + "\nIt is possible to have multiple flags (all flags will be accounted for. i.e. with more flags, the"
            + " list can only get smaller or remain the same).";
    private static final String HELP_UPDATE = "Command 'update': This command allows users to update the name, "
            + "expiry date, category, quantity and units based on the index in the food list."
            + "\nList of valid flags as follows:"
            + "\n   --n: update by name"
            + "\n   --e: update by expiry date"
            + "\n   --u: update by unit"
            + "\n   --q: update by quantity"
            + "\n   --c: update by category"
            + "\nIt is possible to have multiple flags.";
    private static final String HELP_CLEAR = "Command 'clear': This command clears the entire food list.";
    private static final String HELP_EXIT = "Command 'exit': This command will save the food list in an external file"
            + "before exiting the program.";
    private static final String REPORT_INVALID_INPUT = "Opps! The command '%s' is not valid.";

    private final HashSet<String> filters = new LinkedHashSet<>();

    /**
     * This constructor takes in inputs and split it with the according to the regex '--' and store them in a
     * LinkedHashSet named filters. This method checks for repeat commands words and ignore them.
     *
     * @param userInput the input from the user for the 'help' command
     */
    public HelpCommand(String userInput) {
        filters.addAll(List.of(userInput.replaceAll(" ", "").split("--")));
    }

    public HashSet<String> getFilters() {
        return filters;
    }

    /**
     * This method loops through all the filtered command words and pass them to the method <code>appendMessage()</code>
     * (unless the command word is help or empty, in which case will be ignored).
     * After all the filtered words are processed, this method will  append the default help message at the end.
     *
     * @param foodList the food list
     * @return a CommandResult object to display the successful message
     */
    @Override
    public CommandResult execute(FoodList foodList) {
        String printToUser = "";
        if (filters.size() > 1) {
            for (String f : filters) {
                if (!isHelpOrEmpty(f)) {
                    printToUser = appendMessage(printToUser, f);
                }
            }
        }
        printToUser = addNewLine(printToUser);
        printToUser = printToUser.concat(SHOW_ALL_COMMANDS + '\n' + DEFAULT_HELP_MESSAGE);
        return new CommandResult(printToUser);
    }

    /**
     * This method decides whether a filtered command is valid or not and append it to the message. Valid commands will
     * show help messages for their usage while invalid command will be reported back to the user as non-valid commands.
     *
     * @param printToUser the message to append
     * @param f           the command word
     * @return a CommandResult object to display the successful message
     */
    private String appendMessage(String printToUser, String f) {
        printToUser = addNewLine(printToUser);
        switch (f) {
        case AddCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_ADD);
            break;
        case ListCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_LIST);
            break;
        case RemoveCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_REMOVE);
            break;
        case FindCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_FIND);
            break;
        case UpdateCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_UPDATE);
            break;
        case ClearCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_CLEAR);
            break;
        case ExitCommand.COMMAND_WORD:
            printToUser = printToUser.concat(HELP_EXIT);
            break;
        default:
            printToUser = printToUser.concat(String.format(REPORT_INVALID_INPUT, f));
            break;
        }
        assert !printToUser.equals("");
        return printToUser;
    }

    /**
     * This method checks for empty string or 'help' as command words. As these words hold no meaning,
     * they should be ignored.
     *
     * @param word the command word to be checked
     * @return true or false
     */
    private boolean isHelpOrEmpty(String word) {
        return (word.equals(COMMAND_WORD) || word.equals(""));
    }

    /**
     * This method is used to make the CL output more readable. It adds a new line after each block of text.
     *
     * @param printToUser the text to be printed to the CLI.
     * @return the text with the addition of a new line.
     */
    private String addNewLine(String printToUser) {
        if (!printToUser.equals("")) {
            printToUser = printToUser.concat("\n\n\n");
        }
        return printToUser;
    }
}
