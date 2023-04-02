package seedu.commands;

import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class HelpCommand extends Command {

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        System.out.println("Here are the list of available commands");
        System.out.println("[view]: View your personal information.");
        System.out.println("[update]: Update your personal information.");
        System.out.println("[add]: Add a meal. ");
        System.out.println("Usage: add /on [date] /type [MealType] /foods [foods]");
        System.out.println("[list]: List either all the foods in the database or all previous added meals. ");
        System.out.println("Usage: list foods / list meals");
        System.out.println("[delete]: Deletes a previously added meal. ");
        System.out.println("Usage: delete [index]");
        System.out.println("[filter]: Filters food by calorie content.");
        System.out.println("[nutrition]: Find the nutrients of a specific kind of food.");
        System.out.println("[exercise]: Input a completed exercise. ");
        System.out.println("Usage: exercise /type [exercise name] /description [exercise description] " +
                "/calories [calories burnt] /on [date]");
        System.out.println("[track]: Returns your caloric intake from previous days.");
        System.out.println("[examples]: Displays examples for inputs");
        System.out.println("[bye]: Exits the program.");
        System.out.println("For any other questions please visit out User Guide.");
    }
}