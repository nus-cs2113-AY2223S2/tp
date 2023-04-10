package seedu.fst.commands;

import seedu.fst.food.FoodList;

/**
 * Represents a Clear command.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_ACKNOWLEDGEMENT = "Clearing the food list as requested...";

    /**
     * Remove all the food items in the food list.
     * Returns a CommandResult object to display the message after executing the clear command.
     *
     * @param foodList
     * @return acknowledgement message to clear the food list
     */
    @Override
    public CommandResult execute(FoodList foodList) {
        foodList.clearFoodList();
        return new CommandResult(MESSAGE_CLEAR_ACKNOWLEDGEMENT);
    }

    /**
     * Returns a boolean that indicates the continuation of the program.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
