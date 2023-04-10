package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.constants.DateConstants;
import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.exceptions.InvalidCommandException;
import seedu.exceptions.LifeTrackerException;
import seedu.exceptions.MissingArgumentsException;
import seedu.parser.DateParser;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class TrackCalorieCommand extends Command {
    private String commandWord;
    private String userInput;
    private LocalDate startDate;
    private LocalDate endDate;
    private DateTimeFormatter dtf;


    public TrackCalorieCommand(String commandWord, String userInput) {
        this.commandWord = commandWord;
        this.userInput = userInput;
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
            ExerciseStorage exerciseStorage) throws LifeTrackerException {
        ArrayList<Meal> meals = mealStorage.getMeals();
        ArrayList<Exercise> exercises = exerciseStorage.getExercises();
        String[] userInputArgs = userInput.split("\\s+");
        if (userInputArgs.length == 1){
            throw new MissingArgumentsException(commandWord, "[all] / [/start, /end]");
        } else if (userInputArgs[1].equals("all") && userInputArgs.length == 2){
            this.startDate = getStartingDate(meals, exercises);
            this.endDate = getEndingDate(meals, exercises);
            getMealsWithinDates(mealStorage, exerciseStorage, ui, startDate, endDate);
        } else if (userInputArgs.length > 5){
            throw new InvalidCommandException();
        } else {
            parseUserInput();
            getMealsWithinDates(mealStorage, exerciseStorage, ui, startDate, endDate);
        }
    }

    private void parseUserInput() throws LifeTrackerException {
        dtf = DateConstants.PARSE_DTF;
        int startDateIndex;
        int endDateIndex;
        String startDateIdentifier = "/start";
        String endDateIdentifier = "/end";

        startDateIndex = userInput.indexOf(startDateIdentifier);
        if (startDateIndex == -1) {
            throw new MissingArgumentsException(commandWord, startDateIdentifier);
        }
        endDateIndex = userInput.indexOf(endDateIdentifier);
        if (endDateIndex == -1) {
            throw new MissingArgumentsException(commandWord, endDateIdentifier);
        }
        String startDateString = userInput.substring(startDateIndex + startDateIdentifier.length(), endDateIndex-1)
                .trim();
        String endDateString = userInput.substring(endDateIndex + endDateIdentifier.length()).trim();
        this.startDate = DateParser.parse(startDateString, dtf);
        this.endDate = DateParser.parse(endDateString, dtf);
    }

    private void getMealsWithinDates(MealStorage mealStorage, ExerciseStorage exerciseStorage, GeneralUi ui,
                                     LocalDate startDate, LocalDate endDate)
            throws LifeTrackerException {
        List<Meal> filteredMeals;
        List<Exercise> filteredExercises;

        double caloriesConsumed;
        double caloriesBurnt;
        DateTimeFormatter dtf = DateConstants.PARSE_DTF;
        if (startDate == null || endDate == null) {
            System.out.println("No Meals and Exercises Found!");
            return;
        }
        if (startDate.isAfter(endDate)){
            throw new LifeTrackerException("Oops! End date cannot be before Start date!");
        }
        System.out.println("Showing your history from " + startDate + " to " + endDate);
        while (startDate.compareTo(endDate) <= 0) {
            caloriesConsumed = caloriesBurnt = 0;
            filteredMeals = mealStorage.getMealByDate(startDate);
            filteredExercises = exerciseStorage.getExercisesByDate(startDate);

            if (filteredMeals.size() == 0 && filteredExercises.size() == 0) {
                startDate = startDate.plusDays(1);
                continue;
            }

            ui.printLine();
            System.out.printf("Date: %s\n", startDate.format(dtf));

            if (filteredMeals.size() > 0) {
                for (Meal meal : filteredMeals) {
                    caloriesConsumed += meal.getTotalCalories();
                }
                System.out.println("Calories consumed: " + caloriesConsumed);
            }
            if (filteredExercises.size() > 0) {
                for (Exercise exercise : filteredExercises) {
                    caloriesBurnt += exercise.getCaloriesBurnt();
                }
                System.out.println("Calories burnt: " + caloriesBurnt);
            }

            System.out.println("Net calories: " + (caloriesConsumed - caloriesBurnt));

            startDate = startDate.plusDays(1);
        }
    }

    private LocalDate getStartingDate(ArrayList<Meal> meals, ArrayList<Exercise> exercises) {
        if (meals.size() == 0 && exercises.size() > 0) {
            return exercises.get(0).getDate();
        } else if (meals.size() > 0 && exercises.size() == 0) {
            return meals.get(0).getDate();
        } else if (meals.size() > 0 && exercises.size() > 0) {
            if (meals.get(0).getDate().compareTo(exercises.get(0).getDate()) < 0) {
                return meals.get(0).getDate();
            } else {
                return exercises.get(0).getDate();
            }
        } else {
            return null;
        }
    }

    private LocalDate getEndingDate(ArrayList<Meal> meals, ArrayList<Exercise> exercises) {
        if (meals.size() == 0 && exercises.size() > 0) {
            return exercises.get(exercises.size()-1).getDate();
        } else if (meals.size() > 0 && exercises.size() == 0) {
            return meals.get(meals.size()-1).getDate();
        } else if (meals.size() > 0 && exercises.size() > 0) {
            if (meals.get(meals.size()-1).getDate().compareTo(exercises.get(exercises.size()-1).getDate()) > 0) {
                return meals.get(meals.size()-1).getDate();
            } else {
                return exercises.get(exercises.size()-1).getDate();
            }
        } else {
            return null;
        }
    }
    
}
