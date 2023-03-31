package seedu.commands;

import seedu.entities.Meal;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

/**
 * Command to delete meal from database
 */
public class DeleteMealCommand extends Command{
    private int index;

    public DeleteMealCommand(String commandWord, String userInput) throws LifeTrackerException {
        parseInput(commandWord, userInput);
    }

    /**
     * For parsing the index of meal to be deleted from database
     * @param commandWord command without arguments
     * @param commandDescriptor full command with arguments
     * @throws LifeTrackerException
     */
    private void parseInput(String commandWord, String commandDescriptor) throws LifeTrackerException{
        if (commandDescriptor.length() == commandWord.length()){
            throw new InvalidCommandException();
        }
        String indexStr = commandDescriptor.split(" ")[1];

        try {
            this.index = Integer.parseInt(indexStr) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    /** 
     * Removes meal from database if valid index was given
     * @param ui For handling i/o with user
     * @param foodStorage
     * @param mealStorage To remove meal from database
     * @param userStorage
     * @param exerciseStorage
     * @throws LifeTrackerException if invalid index was given
     */
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage,
                        MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        try{
            Meal deletedMeal = mealStorage.deleteMeal(this.index);
            ui.printMealDeleted(deletedMeal);
            LogFileHandler.logInfo(deletedMeal.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index);
        }
    }
}
