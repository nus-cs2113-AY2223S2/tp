package seedu.commands;

import seedu.database.ExerciseStorage;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.CalorieUi;
import seedu.ui.ExerciseUi;
import seedu.ui.GeneralUi;

public class ExerciseCommand extends Command {

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        boolean toContinue = true;
        User user = userStorage.getUser();
        CalorieUi calorieUi = new CalorieUi();
        ExerciseUi exerciseUi = new ExerciseUi();
    }
}
