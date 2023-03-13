package seedu.Commands;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;
import seedu.Ui.GeneralUi;

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

    public abstract void execute(GeneralUi ui ,FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
            throws LifeTrackerException;
}
