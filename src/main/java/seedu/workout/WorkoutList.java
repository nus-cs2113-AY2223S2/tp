package seedu.workout;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutList {

    private ArrayList<Workout> workoutList = new ArrayList<>();
    public WorkoutList() {
    }

    public void addWorkout(Workout workout) {
        workoutList.add(workout);
    }
    public ArrayList<Workout> getWorkoutList() {
        return workoutList;
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
}
