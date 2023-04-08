package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;

import seedu.constants.DateConstants;
import seedu.definitions.MealTypes;
import seedu.entities.Food;
import seedu.entities.Meal;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.InvalidDateException;
import seedu.exceptions.InvalidIndexException;
import seedu.exceptions.InvalidMealException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.logger.LogFileHandler;
import seedu.parser.DateParser;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
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

    
    /** 
     * Executes adding meal to database
     * Supports two modes of adding meal to database
     * 1. add command without arguments: prompts user to input necessary information
     * 2. add command with arguments: parses arguments to get information
     * @param ui handles i/o with user
     * @param foodStorage for getting food information
     * @param mealStorage for storing meal information
     * @param userStorage
     * @param exerciseStorage
     * @throws LifeTrackerException
     */
    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
                        ExerciseStorage exerciseStorage)
                throws LifeTrackerException {
        foods = new ArrayList<Food>();
        dtf = DateConstants.PARSE_DTF;
        dateString = "";
        mealTypeString = "";
        mealType = null;
        if (commandWord.length() == userInput.length()) {
            getDetails(ui, foodStorage);
        } else {
            parseCommand(ui, foodStorage);
        }

        meal = new Meal(foods, date, mealType);
        mealStorage.saveMeal(meal);
        ui.printNewMealAdded(meal);
        ui.displayDayCalories(exerciseStorage, date, mealStorage);
        LogFileHandler.logInfo(meal.toString());
    }
    

    
    /** 
     * Prompts user for meal details
     * @param ui
     * @param foodStorage
     * @throws LifeTrackerException
     */
    private void getDetails(GeneralUi ui, FoodStorage foodStorage) throws LifeTrackerException {
        boolean toContinue = true;
        System.out.println("Enter date of meal (d/M/yyyy):");
        try {
            dateString = ui.readLine();
            if (dateString.matches("\\d{1,2}/")) {
                throw new InvalidDateException(dateString);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(dateString);
        }

        System.out.println(System.lineSeparator() + "Enter type of meal (Breakfast/Lunch/Dinner):");
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

    
    /** 
     * Parses command for meal information
     * @param ui
     * @param foodStorage
     * @throws LifeTrackerException
     */
    private void parseCommand(GeneralUi ui, FoodStorage foodStorage) throws LifeTrackerException {
        int dateIndex;
        int mealTypeIndex;
        int foodIndex;
        int choice;
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
        } else if (dateIndex > commandWord.length()+1) {
            throw new InvalidCommandException();
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

        try {
            if (dateString.matches("\\d{1,2}/")) {
                throw new InvalidDateException(dateString);
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(dateString);
        }
        mealType = MealTypes.fromString(mealTypeString);
        foodList = foodString.split(", ");

        for (int i = 0; i < foodList.length; i++) {
            foodList[i] = foodList[i].trim();
            List<Food> filteredFoods = foodStorage.getFoodsByName(foodList[i]);
            if (filteredFoods.size() == 0) {
                System.out.println(System.lineSeparator() + "No food found with " + foodList[i]);
                continue;
            }
            System.out.println(System.lineSeparator() + "These are the food with " + foodList[i]);
            System.out.println("Please select which food:");
            for (int j = 0; j < filteredFoods.size(); j++) {
                System.out.printf("%d) %s" + System.lineSeparator(), j + 1, filteredFoods.get(j).toString());
            }

            choice = ui.readInt();
            try {
                foods.add(filteredFoods.get(choice - 1));
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidIndexException(choice);
            }
            System.out.println("You chose: " + filteredFoods.get(choice - 1) + System.lineSeparator());
        }
    }
}
