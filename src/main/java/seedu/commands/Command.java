package seedu.commands;

import seedu.database.ExerciseStorage;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.GeneralUi;

public abstract class Command {
    private boolean isExit;

    public Command(){
        setIsExit(false);
    }

    public void setIsExit(boolean exit) {
        this.isExit = exit;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(GeneralUi ui , FoodStorage foodStorage,
                                 MealStorage mealStorage, UserStorage userStorage, ExerciseStorage exerciseStorage)
            throws LifeTrackerException;
}
