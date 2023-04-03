package seedu.commands;


import seedu.calorietracker.CalorieTracker;
import seedu.workout.Day;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;
import java.util.Date;
import java.util.HashMap;

public class Command {

    protected static boolean isDayEntered;
    protected static Date date;
    protected static boolean isWorkoutEntered;
    protected  WorkoutList workoutList = new WorkoutList();
    protected CalorieTracker calorieTracker;
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

    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) throws IllegalArgumentException{
        if (workoutList == null || calorieTracker == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;


    }
    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
