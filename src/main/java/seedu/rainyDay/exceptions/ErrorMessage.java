package seedu.rainyDay.exceptions;

public enum ErrorMessage {
    UNRECOGNIZED_INPUT("Sorry! I do not understand your input!\n Please refer to the help table!"),
    WRONG_INPUT_FORMAT("Wrong input format!\nPlease refer to 'help' for correct user input!"),
    WRONG_DELETE_INDEX("Please ensure delete index is a number > 0 and <= "),
    NO_DELETE_INDEX("Please include a delete index!"),
    WRONG_ADD_FORMAT("'add' input not correct!\nPlease refer to 'help' for correct user inputs for 'add' commands"),
    WRONG_FILTER_FORMAT("Please refer to 'help' for correct user input!"),
    NO_STATEMENTS_MATCH_DESCRIPTION("We could not find any matches for your description in your report"),
    WRONG_VIEW_FORMAT("Wrong format for view"),
    WRONG_EDIT_FORMAT("Sorry, your edit command is invalid. Edit format is as follows: \n" +
            "edit [index] ADDCOMMAND\n" + "or\n" +
            "edit [index] {-in/ -out/ -d description/ -v $value/ -c category/ -date date\n"),
    MISSING_DETAILS("Please include some details for your command!"),
    WRONG_SHORTCUT_FORMAT("Wrong format for shortcut");


    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
