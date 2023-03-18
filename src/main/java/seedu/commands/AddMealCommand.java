package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import seedu.database.FoodStorage;
import seedu.database.MealStorage;
import seedu.database.UserStorage;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.ui.GeneralUi;

public class AddMealCommand extends Command {

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
                throws LifeTrackerException {
        String dateString = "";
        LocalDate date;
        String mealTypeString = "";
        MealTypes mealType = null;
        String foodName;
        boolean toContinue = true;
        int choice;
        Meal meal;
        ArrayList<Food> foods = new ArrayList<Food>();
        DateTimeFormatter dtf = mealStorage.getDateTimeFormatter();

        System.out.println("Enter date of meal:");
        try {
            dateString = ui.readLine();
            date = LocalDate.parse(dateString, dtf);
        } catch (DateTimeParseException e) {
            throw new LifeTrackerException(System.lineSeparator() + "Invalid Date: " + dateString + 
                    "! Please format using d/m/yyyy");
        }

        System.out.println(System.lineSeparator() + "Enter type of meal:");
        mealTypeString = ui.readLine();
        if ((mealType = MealTypes.fromString(mealTypeString)) == null) {
            throw new LifeTrackerException(System.lineSeparator() + "Invalid meal type: " + mealTypeString +
                    "! Supported meal types: " + MealTypes.getSupportedMealTypes());
        }

        do {
            System.out.println(System.lineSeparator() + "Enter food:");
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
        
        meal = new Meal(foods, date, mealType);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
        LogFileHandler.logInfo(meal.toString());
    }
}
