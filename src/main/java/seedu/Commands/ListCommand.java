package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;
import seedu.Exceptions.InvalidArgumentsException;
//import seedu.Output.UI;
import seedu.Ui.GeneralUi;

public class ListCommand extends Command {
    private String argument;

    public ListCommand(String commandWord, String userInput) throws InvalidArgumentsException {
        if (commandWord.length() == userInput.length() || userInput.split(" ").length < 2) {
            throw new InvalidArgumentsException(commandWord);
        }

        this.argument = userInput.split(" ")[1];

        if (!this.argument.equals("meals") && !this.argument.equals("foods")) {
            throw new InvalidArgumentsException(commandWord);
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage,
                        MealStorage mealStorage, UserStorage userStorage) throws LifeTrackerException {
        if (argument.equals("meals")) {
            ui.printAllMeals(mealStorage);
        } else {
            ui.printAllFoods(foodStorage);
        }
    }
}
