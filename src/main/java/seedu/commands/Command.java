package seedu.commands;


import seedu.calorietracker.CalorieTracker;
import seedu.workout.Workout;
import seedu.workout.WorkoutList;

import java.util.ArrayList;

public class Command {
    protected static ArrayList<Workout> workoutArrayList;

    protected static WorkoutList workoutList = new WorkoutList();
    protected CalorieTracker calorieTracker;

    public Command() {
    }
   /* public static WorkoutList getWorkoutList() {
        return workoutList;
    }*/


    public void setData(WorkoutList workoutList, CalorieTracker calorieTracker) throws IllegalArgumentException {
        if (workoutList == null || calorieTracker == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
    }
    public void setData(WorkoutList workoutList) {
        if (workoutList == null) {
            throw new IllegalArgumentException("WorkoutList cannot be null.");
        }
        this.workoutList = workoutList;
        this.calorieTracker = calorieTracker;
    }

    public String execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }
}
