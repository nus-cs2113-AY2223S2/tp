package seedu.commands;


import seedu.calorietracker.CaloriesRecorder;
import seedu.calorietracker.FoodList;
import seedu.workout.Day;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.Date;
import java.util.HashMap;

public class Command {

    protected static boolean isDayEntered;
    protected static boolean isWorkoutEntered;
    protected WorkoutList workoutList = new WorkoutList();
    protected CaloriesRecorder caloriesRecorder;
    protected FoodList foodList;
    protected HashMap<Date, Day> workouts;
    protected Workout workoutForOneDay = new Workout();
    protected Day day;


    //@@ author ZIZI-czh
    public Command() {
        workouts = new HashMap<>();
        day = workoutList.getSingleWorkout();
    }

    //@@ author ZIZI-czh
    public static void setIsDayEntered(boolean isDayEnter) {
        isDayEntered = isDayEnter;
    }

    //@@ author ZIZI-czh
    public static void setIsWorkoutEntered(boolean isWorkoutEnter) {
        isWorkoutEntered = isWorkoutEnter;
    }

    public WorkoutList getWorkoutList() {
        return workoutList;
    }

    public void setData(WorkoutList workoutList, CaloriesRecorder caloriesRecorder, FoodList foodList)
            throws IllegalArgumentException {
        if (workoutList == null || caloriesRecorder == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
        this.workoutList = workoutList;
        //  this.calorieTracker = calorieTracker;
        this.caloriesRecorder = caloriesRecorder;
        this.foodList = foodList;
    }


    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
