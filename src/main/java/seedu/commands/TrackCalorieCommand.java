package seedu.commands;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.constants.DateConstants;
import seedu.entities.Exercise;
import seedu.entities.Meal;
import seedu.exceptions.LifeTrackerException;
import seedu.parser.DateParser;
import seedu.storage.ExerciseStorage;
import seedu.storage.FoodStorage;
import seedu.storage.MealStorage;
import seedu.storage.UserStorage;
import seedu.ui.GeneralUi;

public class TrackCalorieCommand extends Command {
    private String userInput;

    public TrackCalorieCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(GeneralUi ui, FoodStorage foodStorage, MealStorage mealStorage, UserStorage userStorage,
            ExerciseStorage exerciseStorage) throws LifeTrackerException {
        ArrayList<Meal> meals = mealStorage.getMeals();
        ArrayList<Exercise> exercises = exerciseStorage.getExercises();
        List<Meal> filteredMeals;
        List<Exercise> filteredExercises;
        LocalDate startDate;
        LocalDate endDate;
        double caloriesConsumed;
        double caloriesBurnt;
        DateTimeFormatter dtf = DateConstants.PARSE_DTF;
        String startDateIdentifier = "/start";
        String endDateIdentifier = "/end";
        String startDateString;
        String endDateString;
        int startDateIndex;
        int endDateIndex;
        int nextSpaceIndex;
        
        startDateIndex = userInput.indexOf(startDateIdentifier);
        if (startDateIndex == -1) {
            startDate = getStartingDate(meals, exercises);
        } else {
            nextSpaceIndex = getIndex(
                userInput.indexOf(" ", startDateIndex+startDateIdentifier.length()+1), 
                userInput.length()
            );
            startDateString = userInput.substring(startDateIndex+startDateIdentifier.length(), nextSpaceIndex).trim();
            startDate = DateParser.parse(startDateString, dtf);
        }

        endDateIndex = userInput.indexOf(endDateIdentifier);
        if (endDateIndex == -1) {
            endDate = getEndingDate(meals, exercises);
        } else {
            nextSpaceIndex = getIndex(
                userInput.indexOf(" ", endDateIndex+endDateIdentifier.length()+1), 
                userInput.length()
            );
            endDateString = userInput.substring(endDateIndex+endDateIdentifier.length(), nextSpaceIndex).trim();
            endDate = DateParser.parse(endDateString, dtf);
        }

        if (startDate == null || endDate == null) {
            throw new LifeTrackerException("No Meals and Exercises Found!");
        } else if (startDate.isAfter(endDate)) {
            throw new LifeTrackerException(startDate + " cannot be later than " + endDate);
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

    private int getIndex(int valOne, int valTwo) {
        if (valOne == -1) {
            return valTwo;
        } else {
            return valOne;
        }
    }
    
}
