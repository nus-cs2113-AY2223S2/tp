package seedu.commands;

import seedu.database.ExerciseStorage;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.Food;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.GeneralUi;
import java.util.List;

public class FilterCaloriesCommand extends Command {


    int choice;

    private float caloriesLowerLimit;

    private float caloriesUpperLimit;

    private void showCaloriesFilteredFoods(FoodStorage foodStorage, List<Food> caloriesFilteredFoods)
            throws LifeTrackerException {

        if (caloriesFilteredFoods.size() == 0) {
            throw new LifeTrackerException(System.lineSeparator() + "No food found within calorie range");
        }
        System.out.println("Please select which food:");
        for (int i = 0; i < caloriesFilteredFoods.size(); i++) {
            System.out.printf("%d) %s" + System.lineSeparator(), i+1, caloriesFilteredFoods.get(i).toString());
        }
    }


    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {

        System.out.println("Please key in the lower limit (float):");
        String lower = ui.readLine();
        try {
            caloriesLowerLimit = Float.parseFloat(lower);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, input is not a float value");
        }
        System.out.println("Please key in the upper limit (float):");
        String upper = ui.readLine();
        try {
            caloriesUpperLimit = Float.parseFloat(upper);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, input is not a float value");
        }

        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);
        showCaloriesFilteredFoods(foodStorage, caloriesFilteredFoods);
    }
}
