package seedu.rainyDay.exceptions;

public enum ErrorMessage {
    UNRECOGNIZED_INPUT("Sorry! I do not understand your input!\nPlease type \"help\" for a list of supported " +
            "commands!"),
    WRONG_DELETE_INDEX("Sorry, your delete command is invalid. Please ensure delete index is a number > 0 and <= %s"),
    NO_DELETE_INDEX("Sorry, your delete command is invalid. Please include a delete index! \n" +
            "Delete format is as follows :\n" + "delete [index]"),
    WRONG_ADD_FORMAT("Sorry, your add command is invalid. Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_FILTER_FORMAT("Sorry, your filter command is invalid. Filter format is as follows: \n" +
            "filter {-in/-out} {-d description} {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_VIEW_FORMAT("Sorry, your view command is invalid. View format is as follows: \n" +
            "view {time} {-sort} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_EDIT_FORMAT("Sorry, your edit command is invalid. Edit format is as follows: \n" +
            "edit [index] {-in/ -out/ -d description/ -v $value/ -c category/ -date DD/MM/YYYY\n" +
            "Please refer to 'help' for more information!"),
    MISSING_DETAILS("Please include the necessary details for your command! Refer to 'help' for the " +
            "format of instructions!"),
    WRONG_SET_BUDGET_FORMAT("Wrong format to set budget goal!"),
    WRONG_SHORTCUT_FORMAT("Sorry, your shortcut command is invalid. Shortcut format is as follows: \n" +
            "shortcut [SHORTCUTNAME -maps ACTUALCOMMAND] \n" + "The shortcut should also be a single word.\n" +
            "Please refer to 'help' for more information!"),
    WRONG_IGNORE_INDEX("Sorry, your ignore command is invalid. Please ensure ignore index is a number > 0 and <= %s"),
    WRONG_IGNORE_FORMAT("Sorry, your ignore command is invalid. Please include a ignore index! \n" +
            "ignore format is as follows :\n" + "ignore/unignore [index]"),
    UNSUPPORTED_DESCRIPTION_NAME("Sorry, character \"-\" is not supported for description name\n\n" +
            "Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    UNSUPPORTED_CATEGORY_NAME("Sorry, character \"-\" is not supported for category name\n\n" +
            "Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    EMPTY_DESCRIPTION_NAME("Sorry, description name cannot be empty\n\n" +
            "Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    SHORTCUT_ALREADY_EXISTS("The shortcut already exists."),
    SHORTCUT_MAPS_ITSELF("The shortcut you are trying to configure should not map to itself."),
    SHORTCUT_NAME_VALID_COMMAND("The shortcut you are trying to configure has the same name as a valid command, " +
            "please name your shortcut something else.");


    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
