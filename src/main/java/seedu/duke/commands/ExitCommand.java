package seedu.duke.commands;

import seedu.duke.food.FoodList;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Food Supply Tracker as requested ...";

    @Override
    public CommandResult execute(FoodList foodList) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
