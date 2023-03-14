package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Meal;
import seedu.Exceptions.InvalidCommandException;
import seedu.Exceptions.LifeTrackerException;
import seedu.Ui.GeneralUi;

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
        } catch (IndexOutOfBoundsException e) {
            throw new LifeTrackerException("Invalid Index!");
        }
    }
}
