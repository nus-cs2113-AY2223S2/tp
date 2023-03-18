package seedu.commands;

import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.Meal;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.ui.GeneralUi;

public class DeleteMealCommand extends Command{
    private int index;

    public DeleteMealCommand(String commandWord, String userInput) throws LifeTrackerException {
        parseInput(commandWord, userInput);
    }
    private void parseInput(String commandWord, String commandDescriptor) throws LifeTrackerException{
        if (commandDescriptor.length() == commandWord.length()){
            throw new InvalidCommandException();
        }
        String indexStr = commandDescriptor.split(" ")[1];

        try {
            this.index = Integer.parseInt(indexStr);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage,
                MealStorage mealStorage, UserStorage userStorage) throws LifeTrackerException {
        try{
            Meal deletedMeal = mealStorage.deleteMeal(this.index);
            ui.printMealDeleted(deletedMeal);
            LogFileHandler.logInfo(deletedMeal.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException(index);
        }
    }
}
