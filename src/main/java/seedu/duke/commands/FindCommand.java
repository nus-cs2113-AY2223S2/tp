package seedu.duke.commands;

import seedu.duke.food.FoodList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    String term;

    public FindCommand(String term) {
        this.term = term;
    }

    @Override
    public CommandResult execute(FoodList foodList) {
        FoodList result = foodList.findFood(term);
        if (result.getNumberOfFood() > 0) {
            System.out.println(result);
            return new CommandResult("Found " + result.getNumberOfFood() + " of food items");
        } else {
            return new CommandResult("No food found for such term");
        }
    }
}
