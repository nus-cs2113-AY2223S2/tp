package seedu.Commands;

import java.util.ArrayList;
import java.util.List;
import seedu.Database.FoodStorage;
import seedu.Database.MealStorage;
import seedu.Database.UserStorage;
import seedu.Entities.Food;
import seedu.Entities.Meal;
import seedu.Exceptions.LifeTrackerException;
import seedu.Ui.GeneralUi;

public class AddMealCommand extends Command {
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
                throws LifeTrackerException {
        String date;
        String foodName;
        boolean toContinue = true;
        int choice;
        Meal meal;
        ArrayList<Food> foods = new ArrayList<Food>();

        System.out.println("Enter date of meal:");
        date = ui.readLine();

        do {
            System.out.println("Enter food:");
            foodName = ui.readLine();


            List<Food> filteredFoods = foodStorage.getFoodsByName(foodName);
            if (filteredFoods.size() == 0) {
                throw new LifeTrackerException("\nNo food found with " + foodName);
            }

            System.out.println("\nThese are the food with " + foodName);
            System.out.println("Please select which food:");
            for (int i = 0; i < filteredFoods.size(); i++) {
                System.out.printf("%d) %s\n", i+1, filteredFoods.get(i).toString());
            }

            choice = ui.readInt();
            if (choice <= 0 || choice > filteredFoods.size()) {
                throw new LifeTrackerException("\nInvalid index!");
            }

            foods.add(filteredFoods.get(choice-1));

            System.out.println("\nType 1 to add more food. Type any other number to quit");
            choice = ui.readInt();
            if (choice != 1) {
                toContinue = false;
            }
            
        } while (toContinue);
        
        meal = new Meal(foods, date);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
    }
}
