package seedu.fst.commands;

import seedu.fst.exceptions.FSTException;
import seedu.fst.food.FoodList;

/**
 * Represents a Command class
 */
public abstract class Command {

    protected Command() {
    }

    public String outputToUser (FoodList foodList){
        String output ="";
        return output;
    }
    public CommandResult execute(FoodList foodList) throws FSTException {
        return new CommandResult("FoodSupplyTracker is running");
    }

    public boolean isExit() {
        return false;
    }

}
