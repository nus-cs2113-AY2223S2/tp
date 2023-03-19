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
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.InvalidMealException;
import seedu.exceptions.LifeTrackerException;
import seedu.logger.LogFileHandler;
import seedu.parser.DateParser;
import seedu.ui.GeneralUi;

public class AddMealCommand extends Command {
    private String commandWord;
    private String userInput;
    private String dateString;
    private LocalDate date;
    private String mealTypeString;
    private MealTypes mealType;
    private String foodName;
    private int choice;
    private Meal meal;
    private ArrayList<Food> foods;
    private DateTimeFormatter dtf;

    public AddMealCommand(String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }


    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
                throws LifeTrackerException {
        foods = new ArrayList<Food>();
        dtf = mealStorage.getDateTimeFormatter();
        dateString = "";
        mealTypeString = "";
        mealType = null;

        getDetails(ui, foodStorage);
        
        meal = new Meal(foods, date, mealType);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
        LogFileHandler.logInfo(meal.toString());
    }



    private void getDetails(GeneralUi ui, FoodStorage foodStorage) throws LifeTrackerException {
        boolean toContinue = true;

        System.out.println("Enter date of meal:");
        dateString = ui.readLine();
        date = DateParser.parse(dateString, dtf);

        System.out.println(System.lineSeparator() + "Enter type of meal:");
        mealTypeString = ui.readLine();
        if ((mealType = MealTypes.fromString(mealTypeString)) == null) {
            throw new InvalidMealException(mealTypeString);
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
                throw new InvalidIndexException(choice);
            }

            foods.add(filteredFoods.get(choice-1));

            System.out.println(System.lineSeparator() +"Type 1 to add more food. Type any other number to quit");
            choice = ui.readInt();
            if (choice != 1) {
                toContinue = false;
            }
            
        } while (toContinue);

    }
}
