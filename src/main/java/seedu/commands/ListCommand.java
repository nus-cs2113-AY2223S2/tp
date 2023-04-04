package seedu.commands;

import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class ListCommand extends Command {
    private String argument;

    public ListCommand(String commandWord, String userInput) throws LifeTrackerException {
        String[] commandParts = userInput.split(" ");
        if (commandWord.length() == userInput.length() || userInput.split(" ").length < 2) {
            throw new MissingArgumentsException(commandWord, "[meals/foods/exercises]");
        } else if (commandParts.length > 2) {
            throw new ExtraArgumentsException();
        }
        this.argument = userInput.split(" ")[1];
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage,
                        MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        if (argument.equals("meals")) {
            ui.printAllMeals(mealStorage);
        } else if (argument.equals("foods")) {
            ui.printAllFoods(foodStorage);
        } else if (argument.equals("exercises")) {
            ui.printAllExercises(exerciseStorage);
        } else {
            throw new LifeTrackerException("You can only list foods/meals/exercises !");
        }
    }
}
