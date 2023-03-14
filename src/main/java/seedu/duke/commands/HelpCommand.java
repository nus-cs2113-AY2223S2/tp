package seedu.duke.commands;

import seedu.duke.food.FoodList;


public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    private static final String SHOW_ALL_COMMANDS = "List of commands: 'bye', 'help', 'list', 'add', 'remove', 'find'"
            + "\nFor more detailed information on usage of specific command, type: help --COMMAND";
    private static final String DEFAULT_HELP_MESSAGE = "Refer to our user guide for more in-depth details on"
            + " how to use our system:"
            + "\nhttps://docs.google.com/document/d/1WKscnkYy9UqI_tsWmUHIMjgILJc6GQeFn0B1ce6qkQo/edit?usp=sharing";
    private static final String HELP_EXIT = "Command 'exit': This command is used to exit the program.";
    private static final String HELP_LIST = "Command 'list': This command lists all food products in the tracker."
            + "\nAppend the filter '--fresh' for listing unexpired food products and "
            + "'--expired' for listing expired food products.";
    private static final String HELP_ADD = "Command 'add': This command adds a food product to the food supply tracker."
            + "\nFormat: add -n PRODUCT_NAME -e EXPIRY_DATE"
            + "\nFormat for EXPIRY_DATE: DD/MM/YYYY";
    private static final String HELP_REMOVE = "Command 'remove': This command removes the food product from the"
            + " food supply tracker based on its index." + "\nFormat: remove INDEX";
    private static final String HELP_FIND = "Command 'find': This command finds the food product by its name."
            + "\nFormat: find PRODUCT_NAME";
    private static final String REPORT_INVALID_INPUT = "Opps! Invalid input entered: ";
    private String[] filters;

    public HelpCommand(String userInput) {
        filters = userInput.replaceAll(" ", "").split("--");
    }

    public String[] getFilters() {
        return filters;
    }

    @Override
    public CommandResult execute(FoodList foodList) {
        String printToUser = "";
        if (isHelpOrEmpty(filters[0]) && filters.length == 1) {
            printToUser = SHOW_ALL_COMMANDS + '\n' + DEFAULT_HELP_MESSAGE;
            return new CommandResult(printToUser);
        }

        for (String f : filters) {

            if (isHelpOrEmpty(f)) {
                continue;
            }

            switch (f) {
            case ExitCommand.COMMAND_WORD:
                printToUser = printToUser.concat('\n' + HELP_EXIT);
                break;
            case ListCommand.COMMAND_WORD:
                printToUser = printToUser.concat('\n' + HELP_LIST);
                break;
            case AddCommand.COMMAND_WORD:
                printToUser = printToUser.concat('\n' + HELP_ADD);
                break;
            case RemoveCommand.COMMAND_WORD:
                printToUser = printToUser.concat('\n' + HELP_REMOVE);
                break;
            case FindCommand.COMMAND_WORD:
                printToUser = printToUser.concat('\n' + HELP_FIND);
                break;
            default:
                printToUser = printToUser.concat('\n' + REPORT_INVALID_INPUT + f);
            }
        }
        return new CommandResult(printToUser);
    }

    private boolean isHelpOrEmpty(String word) {
        return (word.equals(COMMAND_WORD) || word.equals(""));
    }
}
