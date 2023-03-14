package seedu.duke.commands;

/**
 * The code is taken from https://github.com/se-edu/addressbook-level2/tree/157fcf19c6b73289dc4cc7b2dd1152bc2b8e197a
 * <p>
 * Represents the results of a command execution.
 */
public class CommandResult {
    /**
     * The feedback message to be shown to the user. Contains a description of the execution result
     */
    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public void printResult() {
        System.out.println(feedbackToUser);
    }

}
