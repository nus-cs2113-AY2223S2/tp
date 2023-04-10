package seedu.commands;

import seedu.entities.Food;
import seedu.exceptions.ExtraArgumentsException;
import seedu.exceptions.InvalidArgumentsException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.exceptions.NegativeFieldInfoException;
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

        try {
            this.caloriesLowerLimit = Float.parseFloat(split[1]);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(command, "lower calorie value");
        }
        try {
            this.caloriesUpperLimit = Float.parseFloat(split[2]);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentsException(command, "upper calorie value");
        }

        if (caloriesLowerLimit < 0) {
            throw new NegativeFieldInfoException(command, "lower calorie value");
        }

        if (caloriesUpperLimit < 0) {
            throw new NegativeFieldInfoException(command, "upper calorie value");
        }
        
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

        if (caloriesLowerLimit > caloriesUpperLimit) {
            throw new LifeTrackerException("Lower calorie limit should not be greater than upper calorie limit!");
        }
        System.out.println("This is the lower calorie limit (float):");
        System.out.println(caloriesLowerLimit);
        System.out.println("This is the upper calorie limit (float):");
        System.out.println(caloriesUpperLimit);

        List<Food> caloriesFilteredFoods = foodStorage.getFoodsByCalories(caloriesLowerLimit, caloriesUpperLimit);
        showCaloriesFilteredFoods(foodStorage, caloriesFilteredFoods);
    }
}
