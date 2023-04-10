package seedu.fst.commands;

import seedu.fst.food.FoodList;

/**
 * Represents an Exit command.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Food Supply Tracker as requested ...";

    /**
     * Returns a CommandResult object to display the message after executing the exit command.
     *
     * @param foodList
     * @return acknowledgement message to exit program
     */
    @Override
    public CommandResult execute(FoodList foodList) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    /**
     * Returns a boolean that indicates the exiting of the programme.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
