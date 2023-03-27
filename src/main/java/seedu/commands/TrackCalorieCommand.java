package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.entities.Meal;
import seedu.exceptions.LifeTrackerException;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class TrackCalorieCommand extends Command {
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
            ExerciseStorage exerciseStorage) throws LifeTrackerException {
        ArrayList<Meal> meals = mealStorage.getMeals();
        LocalDate date;
        double totalCalories = 0;
        DateTimeFormatter dtf = mealStorage.getDateTimeFormatter();

        if (meals.size() == 0) {
            System.out.println("No Meals Added thus far!");
            return;
        }

        date = meals.get(0).getDate();
        for (Meal meal : meals) {
            if (!date.equals(meal.getDate())) {
                System.out.printf("%s: %.0f calories consumed\n", date.format(dtf), totalCalories);
                date = meal.getDate();
                totalCalories = meal.getTotalCalories();
            } else {
                totalCalories += meal.getTotalCalories();
            }
        }

        System.out.printf("%s: %.0f calories consumed\n", date.format(dtf), totalCalories);
    }
    
}
