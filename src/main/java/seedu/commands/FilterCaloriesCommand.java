package seedu.commands;

import seedu.entities.Food;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;
import java.util.List;

public class FilterCaloriesCommand extends Command {
    private float caloriesLowerLimit;
    private float caloriesUpperLimit;

    private void showCaloriesFilteredFoods(FoodStorage foodStorage, List<Food> caloriesFilteredFoods)
            throws LifeTrackerException {

        if (caloriesFilteredFoods.size() == 0) {
            throw new LifeTrackerException(System.lineSeparator() + "No food found within calorie range");
        }
        System.out.printf("Here are foods with calories between %.2f and %.2f\n", 
                caloriesLowerLimit, caloriesUpperLimit);
        for (int i = 0; i < caloriesFilteredFoods.size(); i++) {
            System.out.printf("%d) %s" + System.lineSeparator(), i+1, caloriesFilteredFoods.get(i).toString());
        }
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {

        System.out.println("Please key in the lower calorie limit (float):");
        caloriesLowerLimit = ui.readFloat();
        System.out.println("Please key in the upper calorie limit (float):");
        caloriesUpperLimit = ui.readFloat();
        if (caloriesLowerLimit > caloriesUpperLimit) {
            throw new LifeTrackerException("Please enter valid lower and upper limits!");
        }

        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);
        showCaloriesFilteredFoods(foodStorage, caloriesFilteredFoods);
    }
}
