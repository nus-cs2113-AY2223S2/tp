package seedu.rainyDay.exceptions;

public enum ErrorMessage {
    UNRECOGNIZED_INPUT("Sorry! I do not understand your input!\n Please refer to the help table!"),
    WRONG_DELETE_INDEX("Sorry, your delete command is invalid. Please ensure delete index is a number > 0 and <= \n" +
            "Please refer to 'help' for more information!"),
    NO_DELETE_INDEX("Sorry, your delete command is invalid. Please include a delete index! \n" +
            "Delete format is as follows :\n" + "delete [index]"),
    WRONG_ADD_FORMAT("Sorry, your add command is invalid. Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date date} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_FILTER_FORMAT("Sorry, your filter command is invalid. Filter format is as follows: \n" +
            "filter {-in} {-out} {-d description} {-c category} {-date date} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_VIEW_FORMAT("Sorry, your view command is invalid. View format is as follows: \n" +
            "view {time} {-sort} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_EDIT_FORMAT("Sorry, your edit command is invalid. Edit format is as follows: \n" +
            "edit [index] ADDCOMMAND\n" + "or\n" +
            "edit [index] {-in/ -out/ -d description/ -v $value/ -c category/ -date date\n" +
            "Please refer to 'help' for more information!"),
    MISSING_DETAILS("Please include some details for your command!"),
<<<<<<< HEAD
    WRONG_SHORTCUT_FORMAT("Wrong format for shortcut"),
    WRONG_SET_BUDGET_FORMAT("Wrong format to set budget goal!");

=======
    WRONG_SHORTCUT_FORMAT("Sorry, your shortcut command is invalid. Shortcut format is as follows: \n" +
            "shortcut [SHORTCUTNAME -maps ACTUALCOMMAND] \n" +
            "Please refer to 'help' for more information!");
>>>>>>> master

    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
