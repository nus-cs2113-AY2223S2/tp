package seedu.duke.ui;

/**
 * Provides enum variables for storing custom program error messages.
 */
public enum ErrorMessages {
    ERROR_FILE_READ("Unable to read the data file from hard disk. We will generate a new file for you."),
    ERROR_EXCESSIVE_FILTERS("You have a too specific filter, no such exercises " +
                                   "exists! Try generating with less filters or less number " +
                                   "of exercises"),

    ERROR_DIFFICULTY_INPUT("Incorrect difficulty level input, " +
            "please input one of the following levels : (easy/medium/hard)"),
    ERROR_EXERCISE_NUM_INPUT_STRING("Invalid input! " +
            "Please enter the number of exercises you want at the end of your generate command!"),
    ERROR_BODY_WORKOUT_TYPE_INPUT("Incorrect body workout type input"),
    ERROR_NO_ONGOING_EXERCISE("There is no current workout session!" +
            "Please start a session with the \"start\" command!"),
    ERROR_ONGOING_EXERCISE_GENERATE_COMMAND("Finish your exercise! Cannot generate new exercise"),
    ERROR_ONGOING_EXERCISE_HELP_COMMAND("Finish your exercise! Cannot print help messages!"),
    ERROR_ONGOING_EXERCISE_HISTORY_COMMAND("Finish your exercise!" +
            "You can look and feel good about your previous workout sessions " +
            "later!"),
    ERROR_ONGOING_EXERCISE_START_COMMAND("Exercise already in progress! Unable to start another exercise!"),
    ERROR_ONGOING_EXERCISE_TEST_SAMPLE("Finish your exercise! Testing of our features can come after that :)"),
    ERROR_FILTER_INPUT("Unknown filter input!"),
    ERROR_EMPTY_KEYWORD("Please key in a keyword for Fitness Duke to search!"),
    ERROR_INVALID_PLAN_ADDITION("Invalid add command!"),
    ERROR_INVALID_DATE_INPUT("Invalid date input!"),
    ERROR_INVALID_FILTER_INPUT("Invalid filter input!"),
    ERROR_INVALID_DELETE_COMMAND("Invalid delete command!"),
    ERROR_NUM_ACHIEVEMENT_NOT_FOUND("The number of achievements seem to be lost. Achievements might not"),
    ERROR_INVALID_PLAN("No such plan found!");

    public final String message;
    /**
     * Instantiates a new error message when user initialises a new instance of this enum.
     *
     * @param message A string containing the message.
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    /**
     * Gets the error message as a string.
     *
     * @return A string containing the message.
     */
    public String toString() {
        return message;
    }
}
