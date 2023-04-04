package seedu.commands;

import seedu.entities.Food;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import java.util.List;

public class FilterCaloriesCommand extends Command {
    private float caloriesLowerLimit;
    private float caloriesUpperLimit;

    public FilterCaloriesCommand(String command, String type) throws LifeTrackerException {
        String[] split = type.split(" ");
        if (command.length() == type.length() || split.length < 2) {
            throw new MissingArgumentsException(command, "requires  both lower limit and upper limit");
        } else if (split.length > 3) {
            throw new ExtraArgumentsException();
        }
        this.caloriesLowerLimit = Float.parseFloat(split[1]);
        this.caloriesUpperLimit = Float.parseFloat(split[2]);
    }
    private void showCaloriesFilteredFoods(FoodStorage foodStorage, List<Food> caloriesFilteredFoods)
            throws LifeTrackerException {

        if (caloriesFilteredFoods.size() == 0) {
            throw new LifeTrackerException(System.lineSeparator() + "No food found within calorie range");
        }
        System.out.printf("Here are foods that contain calories within the %.2f and %.2f range\n",
                caloriesLowerLimit, caloriesUpperLimit);
        for (int i = 0; i < caloriesFilteredFoods.size(); i++) {
            System.out.printf("%d) %s" + System.lineSeparator(), i+1, caloriesFilteredFoods.get(i).toString());
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {

        System.out.println("This is the lower calorie limit (float):");
        System.out.println(caloriesLowerLimit);
        System.out.println("This is the upper calorie limit (float):");
        System.out.println(caloriesUpperLimit);
        if (caloriesLowerLimit > caloriesUpperLimit) {
            throw new LifeTrackerException("Please enter valid lower and upper limits!");
        }

        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);
        showCaloriesFilteredFoods(foodStorage, caloriesFilteredFoods);
    }
}
