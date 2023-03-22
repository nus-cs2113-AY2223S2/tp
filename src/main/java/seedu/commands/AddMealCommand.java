package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import seedu.exceptions.MissingArgumentsException;
import seedu.logger.LogFileHandler;
import seedu.parser.DateParser;
import seedu.ui.GeneralUi;

public class AddMealCommand extends Command {
    String commandWord;
    String userInput;
    String dateString = "";
    LocalDate date = null;
    String mealTypeString = "";
    MealTypes mealType = null;
    String foodName;
    int choice;
    Meal meal;
    ArrayList<Food> foods;
    DateTimeFormatter dtf;

    public AddMealCommand(String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage)
            throws LifeTrackerException {
        foods = new ArrayList<Food>();
        dtf = mealStorage.getDateTimeFormatter();


        if (commandWord.length() == userInput.length()) {
            getDetails(ui, foodStorage);
        } else {
            parseCommand(ui, foodStorage);
        }

        meal = new Meal(foods, date, mealType);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
        LogFileHandler.logInfo("User added this meal" + System.lineSeparator() + meal.toString());
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
                System.out.printf("%d) %s" + System.lineSeparator(), i + 1, filteredFoods.get(i).toString());
            }

            choice = ui.readInt();
            if (choice <= 0 || choice > filteredFoods.size()) {
                throw new InvalidIndexException(choice);
            }

            foods.add(filteredFoods.get(choice - 1));

            System.out.println(System.lineSeparator() + "Type 1 to add more food. Type any other number to quit");
            choice = ui.readInt();
            if (choice != 1) {
                toContinue = false;
            }

        } while (toContinue);
    }

    private void parseCommand(GeneralUi ui, FoodStorage foodStorage) throws LifeTrackerException {
        int dateIndex;
        int mealTypeIndex;
        int foodIndex;
        String dateString;
        String mealTypeString;
        String foodString;
        String[] foodList;
        String dateIdentifier = "/on";
        String mealTypeIdentifier = "/type";
        String foodIdentifier = "/foods";

        dateIndex = userInput.indexOf(dateIdentifier);
        if (dateIndex == -1) {
            throw new MissingArgumentsException(commandWord, dateIdentifier);
        }
        mealTypeIndex = userInput.indexOf(mealTypeIdentifier);
        if (mealTypeIndex == -1) {
            throw new MissingArgumentsException(commandWord, mealTypeIdentifier);
        }
        foodIndex = userInput.indexOf(foodIdentifier);
        if (foodIndex == -1) {
            throw new MissingArgumentsException(commandWord, foodIdentifier);
        }

        dateString = userInput.substring(dateIndex+dateIdentifier.length(), mealTypeIndex-1).trim();
        mealTypeString = userInput.substring(mealTypeIndex+mealTypeIdentifier.length(), foodIndex-1).trim();
        foodString = userInput.substring(foodIndex+foodIdentifier.length());

        date = DateParser.parse(dateString, dtf);
        mealType = MealTypes.fromString(mealTypeString);
        foodList = foodString.split(", ");

        for (int i = 0; i < foodList.length; i++) {
            List<Food> filteredFoods = foodStorage.getFoodsByName(foodList[i]);
            if (filteredFoods.size() == 0) {
                System.out.println("Could not parse: " + foodList[i] + ". Skipping...");
                continue;
            }
            foods.add(filteredFoods.get(0));
        }
    }
}
