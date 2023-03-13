package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;
import seedu.Output.UI;

public class DisplayFoodCommand extends Command{
    @Override
    public void execute(UI ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage) throws LifeTrackerException {
        ui.printAllFoods(foodStorage);
    }
}
