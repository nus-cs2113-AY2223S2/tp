package seedu.workout;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutList {
    public static final int NO_CURRENT_WORKOUT = -1;
    public ArrayList<Workout> workoutList;
    public int currentWorkoutIndex;
    public WorkoutList() {
        workoutList = new ArrayList<>();
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
        currentWorkoutIndex = getLastIndex();
    }

    public void setCurrentWorkoutIndex(int currentWorkoutIndex) {
        this.currentWorkoutIndex = currentWorkoutIndex;
    }
    public void removeWorkout(Date date) {
        for (Workout workout : workoutList) {
            if (workout.getDate().equals(date)) {
                workoutList.remove(workout);
                System.out.println("Workout deleted successfully.");
                return;
            }
        }

        System.out.println("No workout found with the specified date.");
    }
    public void displayWorkout(Date date) {
        for (Workout workout : workoutList) {
            if (workout.getDate().equals(date)) {
                System.out.println(workout.workoutExercises);
                return;
            }
        }
    }
    public int getLastIndex() {
        return workoutList.size() - 1;
    }
    public Workout getCurrentWorkout() {
        return workoutList.get(currentWorkoutIndex);
    }
}