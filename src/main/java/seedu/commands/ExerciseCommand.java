package seedu.commands;

import seedu.entities.User;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
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
