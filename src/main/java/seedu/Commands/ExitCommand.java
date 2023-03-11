package seedu.Commands;

import java.io.IOException;

import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Exceptions.LifeTrackerException;
import seedu.Output.UI;

public class ExitCommand extends Command{
    @Override
    public void execute(UI ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage) 
            throws LifeTrackerException {
        setIsExit(true);
        ui.printGoodbye();
        try {
            mealStorage.write();
            userStorage.write();
        } catch (IOException e) {
            throw new LifeTrackerException("Error saving databases!");
        }
    }
}
