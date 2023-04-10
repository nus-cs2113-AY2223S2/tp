package seedu.fst.commands;

import seedu.fst.exceptions.FSTException;
import seedu.fst.food.FoodList;

/**
 * Represents a List command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String SHOW_FOODLIST_MESSAGE = "Below are the food list: \n";
    public static final String REPORT_NUMBER_OF_FOOD_FRONT = "\nYou now have ";
    public static final String REPORT_NUMBER_OF_FOOD_BACK = " food products in your lists.";

    /**
     * Returns the formatted food list in String
     *
     * @param foodList
     * @return list of food items in String
     */
    @Override
    public String outputToUser(FoodList foodList){
        String printToUser = "";
        int numberOfFood = foodList.getNumberOfFood();

        if (numberOfFood > 0) {
            assert !foodList.getFoodList().isEmpty() :
                "The list of food to be printed have to be a food list type and" +
                "Food list cannot be empty.";
            printToUser = printToUser.concat(SHOW_FOODLIST_MESSAGE + foodList);
        }

        printToUser = printToUser.concat(REPORT_NUMBER_OF_FOOD_FRONT + numberOfFood + REPORT_NUMBER_OF_FOOD_BACK);
        return printToUser;
    }

    /**
     * Returns a CommandResult object to display the food list after executing the list command.
     *
     * @param foodList
     * @return list of food items to be printed
     * @throws FSTException
     */
    @Override
    public CommandResult execute(FoodList foodList) throws FSTException {
        String printToUser = outputToUser(foodList);
        return new CommandResult(printToUser);
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
