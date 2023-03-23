package seedu.commands;

import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.parser.DateParser;
import seedu.ui.GeneralUi;

import java.util.List;

public class FilterCaloriesCommand extends Command {

    private float caloriesLowerLimit;

    private float caloriesUpperLimit;

    public FilterCaloriesCommand(float caloriesLowerLimit, float caloriesUpperLimit) {
        this.caloriesLowerLimit = caloriesLowerLimit;
        this.caloriesUpperLimit = caloriesUpperLimit;
    }

    private void showCaloriesFilteredFoods(FoodStorage foodStorage) throws LifeTrackerException {
        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);

        if (caloriesFilteredFoods.size() == 0) {
            throw new LifeTrackerException(System.lineSeparator() + "No food found within calorie range");
        }
        System.out.println("Please select which food:");
        for (int i = 0; i < caloriesFilteredFoods.size(); i++) {
            System.out.printf("%d) %s" + System.lineSeparator(), i+1, caloriesFilteredFoods.get(i).toString());
        }
    }


    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage) throws LifeTrackerException {

    }
}
