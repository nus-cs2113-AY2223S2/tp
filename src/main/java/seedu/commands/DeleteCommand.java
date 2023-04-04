package seedu.commands;

import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.exceptions.InvalidIndexException;
import seedu.logger.LogFileHandler;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

/**
 * Command to delete meal from database
 */
public class DeleteCommand extends Command{
    private String itemType;
    private int index;

    public DeleteCommand(String commandWord, String userInput) throws LifeTrackerException {
        parseInput(userInput);
    }

    /**
     * For parsing the index of meal to be deleted from database
     * @param commandDescriptor full command with arguments
     * @throws LifeTrackerException
     */
    private void parseInput(String commandDescriptor) throws LifeTrackerException{

        String[] commandParts = commandDescriptor.split(" ");
        if (commandParts.length == 1) {
            throw new MissingArgumentsException("delete", "[/meal, /exercise]");
        } else if (commandParts.length > 3) {
            throw new ExtraArgumentsException();
        } else if (!commandParts[1].equals("/meal") && !commandParts[1].equals("/exercise")) {
            throw new InvalidCommandException();
        }
        this.itemType = commandParts[1].substring(1);
        try {
            this.index = Integer.parseInt(commandParts[2]) - 1;
        } catch (Exception e) {
            throw new LifeTrackerException("Please enter an index to delete!");
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
        if (this.itemType.equals("meal")) {
            try{
                Meal deletedMeal = mealStorage.deleteMeal(this.index);
                ui.printMealDeleted(deletedMeal);
                LogFileHandler.logInfo(deletedMeal.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(index + 1);
            }
        } else if (this.itemType.equals("exercise")) {
            try{
                Exercise deletedExercise = exerciseStorage.deleteExercise(this.index);
                ui.printExerciseDeleted(deletedExercise);
                LogFileHandler.logInfo(deletedExercise.toString());
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(index + 1);
            }
        } else {
            throw new InvalidCommandException();
        }
    }
}
