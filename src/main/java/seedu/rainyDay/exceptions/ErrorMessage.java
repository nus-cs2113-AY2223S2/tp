package seedu.rainyDay.exceptions;

public enum ErrorMessage {
    UNRECOGNIZED_INPUT("Sorry! I do not understand your input!\nPlease type \"help\" for a list of supported " +
            "commands!"),
    EMPTY_FINANCIAL_REPORT("Unable to execute the operation without transaction data! \n" +
            "Please add transactions first!"),
    WRONG_DELETE_INDEX("Sorry, your delete command is invalid. Please ensure [INDEX] is a number and" +
            " is within the boundary > 0 and <= %s"),
    ADD_FORMAT("Add format is as follows: \n" +
            "add [-in/-out] [description] $[value] {-c category} {-date DD/MM/YYYY} \n" +
            "Please refer to 'help' for more information!"),
    FILTER_FORMAT("Filter format is as follows: \n" +
            "filter {-in/-out} {-d DESCRIPTION} {-c CATEGORY} {-date DD/MM/YYYY} \n" +
            "At least one of these flags must be present and in the order above.\n" +
            "Please refer to 'help' for more information!"),
    NO_DELETE_INDEX("Sorry, your delete command is invalid. Please include a delete index! \n" +
            "Delete format is as follows :\n" + "delete [INDEX]"),
    WRONG_ADD_FORMAT("Sorry, your add command is invalid."),
    WRONG_FILTER_FORMAT("Sorry, your filter command is invalid. Filter format is as follows: \n" +
            "filter {-in/-out} {-d DESCRIPTION} {-c CATEGORY} {-date DD/MM/YYYY} \n" +
            "At least one of these flags must be present and in the order above.\n" +
            "Please refer to 'help' for more information!"),
    WRONG_VIEW_FORMAT("Sorry, your view command is invalid. View format is as follows: \n" +
            "view {TIME} {-sort} \n" +
            "Please refer to 'help' for more information!"),
    WRONG_EDIT_FORMAT("Sorry, your edit command is invalid. Edit format is as follows: \n" +
            "edit [INDEX] [-in/-out/-d DESCRIPTION/-v $VALUE/-c CATEGORY/-date DD/MM/YYYY]\n" +
            "Please refer to 'help' for more information!"),
    EDIT_FORMAT("Edit format is as follows: \n" +
            "edit [INDEX] [-in/-out/-d DESCRIPTION/-v $VALUE/-c CATEGORY/-date DD/MM/YYYY]\n" +
            "Please refer to 'help' for more information!"),
    WRONG_EDIT_INDEX("Sorry, your edit command is invalid. Please ensure [INDEX] is a number " +
            "and is within the boundary > 0 and <= %s"),
    WRONG_SET_BUDGET_FORMAT("Sorry, your setbudget command is invalid. setbudget format is as follows: \n" +
            "setbudget [VALUE] \n" +
            "Please refer to 'help' for more information!"),
    WRONG_SHORTCUT_FORMAT("Sorry, your shortcut command is invalid. Shortcut format is as follows: \n" +
            "shortcut [SHORTCUTNAME -maps ACTUALCOMMAND] \n" + "The shortcut should also be a single word.\n" +
            "Please refer to 'help' for more information!"),
    WRONG_IGNORE_INDEX("Sorry, your ignore command is invalid. Please ensure [INDEX] is a number " +
            "and is within the boundary > 0 and <= %s"),
    WRONG_IGNORE_FORMAT("Sorry, your ignore command is invalid. Please include a ignore index! \n" +
            "ignore format is as follows :\n" + "ignore/unignore [INDEX]"),
    UNSUPPORTED_DESCRIPTION_NAME("Sorry, character \"-\" is not supported for description name\n\n"),
    UNSUPPORTED_CATEGORY_NAME("Sorry, character \"-\" is not supported for category name\n\n"),
    EMPTY_DESCRIPTION_NAME("Sorry, description name cannot be empty\n\n"),
    SHORTCUT_ALREADY_EXISTS("The shortcut already exists."),
    SHORTCUT_MAPS_ITSELF("The shortcut you are trying to configure should not map to itself."),
    SHORTCUT_NAME_VALID_COMMAND("The shortcut you are trying to configure has the same name as a valid command, " +
            "please name your shortcut something else."),
    SHORTCUT_MAPS_SHORTCUT("A configured shortcut should not map to another shortcut"),
    SHORTCUT_DOES_NOT_EXIST("The shortcut does not exist."),
    INVALID_YEAR("Year provided needs to be in the form YYYY, where year is a number!\n\n"),
    INVALID_MONTH("Month provided needs to be in the form MM or M, where month is a number from 1 to 12!\n\n"),
    INVALID_DAY("Day provided needs to be in the form DD or D, where day is a number from 1 to 31!\n\n"),
    INVALID_DATE_FORMAT("Date provided needs to be in the form of DD/MM/YYYY or D instead of DD and M " +
            "instead of MM, where D, M, and Y are numbers!\n\n"),
    EMPTY_CATEGORY_NAME("Category name cannot be empty\n\n"),
    INVALID_DATE("Date provided does not exist, please ensure that you have keyed the correct date\n\n"),
    NO_DATE_PROVIDED("Please provide a date after the \"-date\" flag\n\n"),
    FAILED_FILE_OPERATION("File operation failed"),
    INVALID_SAVED_FINANCIAL_REPORT("Your saved data for financialReport is invalid"),
    INVALID_SAVED_SHORTCUT_COMMANDS("Your saved data for shortcutCommands is invalid"),
    INVALID_SAVED_BUDGET_GOAL("Your saved data for budgetGoal is invalid"),
    INVALID_SAVED_FINANCIAL_STATEMENTS("Your saved data for financialStatements is invalid"),
    INVALID_SAVED_REPORT_OWNER("Your saved data for reportOwner is invalid"),
    INVALID_SAVED_DESCRIPTION("Your saved data for financialStatements has an invalid description"),
    INVALID_SAVED_FLOW_DIRECTION("Your saved data for financialStatements has an invalid flowDirection"),
    INVALID_SAVED_VALUE("Your saved data for financialStatements has an invalid value"),
    INVALID_SAVED_CATEGORY("Your saved data for financialStatements has an invalid category"),
    INVALID_SAVED_DATE("Your saved data for financialStatements has an invalid date"),
    INVALID_SAVED_IS_IGNORED("Your saved data for financialStatements has an invalid isIgnored field"),
    CSV_EXPORT_ERROR("Error exporting to CSV, please close the CSV file if you currently have it open."),
    CSV_EMPTY_STATEMENT("Your financial statements are empty, export to CSV will not be performed."),
    INVALID_VALUE("Unsupported amount provided, note that amount has to be a number > 0 and < 21,474,836.47\n\n");
    private final String error;

    ErrorMessage(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error;
    }
}
