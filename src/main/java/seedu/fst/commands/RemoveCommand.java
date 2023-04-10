package seedu.fst.commands;

import seedu.fst.exceptions.FSTException;
import seedu.fst.exceptions.IllegalValueException;
import seedu.fst.food.FoodList;

/**
 * Represents a RemoveCommand Class
 */
public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String BLANK_INDEX_MESSAGE_1 = "Please enter an integer to remove a food item.\n" +
            "You now have ";
    public static final String BLANK_INDEX_MESSAGE_2 = " food items in your list.";
    public static final String INCORRECT_INDEX_MESSAGE_1 = "Incorrect value entered.\n" +
            "Your now have ";
    public static final String INVALID_INPUT_MESSAGE = "Please use a reasonable value :<";
    public String index;

    public RemoveCommand(String index) {
        this.index = index;
    }

    /**
     * This constructor creates a boolean object that checks if the index provided is an integer.
     * Code referred from: https://www.baeldung.com/java-check-string-number
     * 
     * @return Returns true if the index is an integer and false otherwise
     */
    public boolean isInteger() {
        try {
            Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * This constructor creates a boolean object that checks if the index provided does not exceed the integer limit.
     *
     * @param deleteNumber Input string that is provided by the user
     * @return Returns true if the number is within the integer limit and false if otherwise
     */
    boolean isNumberValid(int deleteNumber) {
        if ((deleteNumber > 9999) || (deleteNumber < 0)) {
            return false;
        }
        return true;
    }

    /**
     * This method firstly checks whether the value of the index provided by the user exists and is a valid number.
     * Then, it will proceed to check if the index provided is within the size of the food list.
     * After the checks, it will proceed to retrieve the food item to be deleted from the food list
     * and eventually remove it from the list.
     *
     * @param foodlist food list containing the list of food items
     * @return feedback to user if the food item has been successfully deleted otherwise an error message
     * @throws IllegalValueException if the index provided does not exist or beyond the bounds of the food list
     */
    public CommandResult execute(FoodList foodlist) throws FSTException {
        if (index.isBlank()) {
            String blankIntegerMessage = BLANK_INDEX_MESSAGE_1 + foodlist.getNumberOfFood() + BLANK_INDEX_MESSAGE_2;
            throw new IllegalValueException(blankIntegerMessage);
        }

        if (!isInteger()) {
            String notIntegerMessage = BLANK_INDEX_MESSAGE_1 + foodlist.getNumberOfFood() + BLANK_INDEX_MESSAGE_2;
            return new CommandResult(notIntegerMessage);
        }

        int deleteItem = Integer.parseInt(index.trim()) - 1;
        if (!isNumberValid(deleteItem)) {
            return new CommandResult(INVALID_INPUT_MESSAGE);
        }

        if (deleteItem >= foodlist.getNumberOfFood() || deleteItem < 0) {
            String incorrectIndexFormat = INCORRECT_INDEX_MESSAGE_1 + foodlist.getNumberOfFood()
                    + BLANK_INDEX_MESSAGE_2;
            throw new IllegalValueException(incorrectIndexFormat);
        }

        String foodName = foodlist.getFood(deleteItem).getName();
        foodlist.removeFood(deleteItem);
        int itemsLeft = foodlist.getNumberOfFood();
        System.out.println("Removed '" + foodName + "' from the food supply list.");
        return new CommandResult("There is/are now " + itemsLeft + " item(s) in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
