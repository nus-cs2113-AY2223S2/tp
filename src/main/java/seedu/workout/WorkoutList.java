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

    public void showWorkoutList() {
        try {
            if (!workoutList.isEmpty()) {
                System.out.println("Here are the list of dates for your workout: ");
                for (Workout workout : workoutList) {
                    System.out.println(workout.getDate());
                }
            } else {
                System.out.println("Haven't start your workout, please enter your workout");
            }

        } catch (NullPointerException e) {
            System.out.println("Haven't start your workout, please enter your workout");
        }
    }

    public int getLastIndex() {
        return workoutList.size() - 1;
    }

    public Workout getCurrentWorkout() {
        return workoutList.get(currentWorkoutIndex);
    }
}
