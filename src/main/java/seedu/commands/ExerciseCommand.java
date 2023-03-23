package seedu.commands;

import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.CalorieUi;
import seedu.ui.GeneralUi;

public class ExerciseCommand extends Command{
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
            throws LifeTrackerException {
        User user = userStorage.getUser();
        CalorieUi calorieUi = new CalorieUi();
    }
}
