package seedu.fst.commands;

import seedu.fst.food.FoodList;

/**
 * Represents an IncorrectCommand Class
 */
public class IncorrectCommand extends Command {
    public static final String INCORRECT_COMMAND = "Oops! " +
            "Incorrect command format. Type 'help' to see more!!";

    public IncorrectCommand() {
    }

    @Override
    public CommandResult execute(FoodList foodList) {
        return new CommandResult(INCORRECT_COMMAND);
    }

}
