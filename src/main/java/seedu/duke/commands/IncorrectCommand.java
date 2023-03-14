package seedu.duke.commands;

import seedu.duke.food.FoodList;

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
