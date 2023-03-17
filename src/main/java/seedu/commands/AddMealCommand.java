package seedu.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.ui.GeneralUi;

public class AddMealCommand extends Command {

    private static final Logger logger = Logger.getLogger(AddMealCommand.class.getName());

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
                throw new LifeTrackerException(System.lineSeparator() + "No food found with " + foodName);
            }

            System.out.println(System.lineSeparator() + "These are the food with " + foodName);
            System.out.println("Please select which food:");
            for (int i = 0; i < filteredFoods.size(); i++) {
                System.out.printf("%d) %s" + System.lineSeparator(), i+1, filteredFoods.get(i).toString());
            }

            choice = ui.readInt();
            if (choice <= 0 || choice > filteredFoods.size()) {
                throw new LifeTrackerException(System.lineSeparator() + "Invalid index!");
            }

            foods.add(filteredFoods.get(choice-1));

            System.out.println(System.lineSeparator() +"Type 1 to add more food. Type any other number to quit");
            choice = ui.readInt();
            if (choice != 1) {
                toContinue = false;
            }
            
        } while (toContinue);
        
        meal = new Meal(foods, date);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
        LogFileHandler.logInfo(meal.toString());
    }
}
