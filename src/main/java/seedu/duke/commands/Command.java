package seedu.duke.commands;
import seedu.duke.food.FoodList;

public abstract class Command {

    protected Command() {
    }

    public CommandResult execute(FoodList foodList) {
        return new CommandResult("Duke is running");
    }

    public boolean isExit() {
        return false;
    }

}
