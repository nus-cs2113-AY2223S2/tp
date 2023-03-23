package seedu.commands;

import java.util.List;

import seedu.database.ExerciseStorage;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.Food;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.LifeTrackerException;
import seedu.ui.GeneralUi;

public class NutritionCommand extends Command {
    String foodName;
    int choice;

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
            throws LifeTrackerException {
        boolean toContinue = true;
        do {
            System.out.println(System.lineSeparator() + "What food would you like to see the nutrients for?");
            foodName = ui.readLine();

            List<Food> filteredFoods = foodStorage.getFoodsByName(foodName);
            if (filteredFoods.size() == 0) {
                throw new LifeTrackerException(System.lineSeparator() + "No food found with " + foodName);
            }

            System.out.println(System.lineSeparator() + "These are the food with " + foodName);
            System.out.println("Please select which food:");
            for (int i = 0; i < filteredFoods.size(); i++) {
                System.out.printf("%d) %s" + System.lineSeparator(), i + 1, filteredFoods.get(i).toString());
            }

            choice = ui.readInt();
            if (choice <= 0 || choice > filteredFoods.size()) {
                throw new InvalidIndexException(choice);
            }

            Food foodToBeAnalysed = (filteredFoods.get(choice - 1));
            float protein = foodToBeAnalysed.getProtein();
            System.out.println("Protein: " + protein);
            float totalFat = foodToBeAnalysed.getTotalFat();
            System.out.println("Total Fat: " + totalFat);
            float saturatedFat = foodToBeAnalysed.getSaturatedFat();
            System.out.println("Saturated Fat: " + saturatedFat);
            float dietaryFibre = foodToBeAnalysed.getDietaryFibre();
            System.out.println("Dietary Fibre: " + dietaryFibre);
            float carbohydrates = foodToBeAnalysed.getCarbohydrates();
            System.out.println("Carbohydrates: " + carbohydrates);
            float sugar = foodToBeAnalysed.getSugar();
            System.out.println("Sugar: " + sugar);
            float sodium = foodToBeAnalysed.getSodium();
            System.out.println("Sodium: " + sodium);

            System.out.println(System.lineSeparator() + "Type 1 to add more food. Type any other number to quit");
            choice = ui.readInt();
            if (choice != 1) {
                toContinue = false;
            }
        } while (toContinue);
    }
}
