package seedu.workouttracker.workout;

import seedu.ui.Ui;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class WorkoutList {
    public static final int NO_CURRENT_WORKOUT = -1;
    public ArrayList<Workout> workoutArrayList;
    public int currentWorkoutIndex;

    public WorkoutList() {
        workoutArrayList = new ArrayList<>();
        currentWorkoutIndex = NO_CURRENT_WORKOUT;
    }

    public void addWorkout(Workout workout) {
        workoutArrayList.add(workout);
        currentWorkoutIndex = getLastIndex();
    }

    public void setCurrentWorkoutIndex(int currentWorkoutIndex) {
        this.currentWorkoutIndex = currentWorkoutIndex;
    }

<<<<<<< HEAD:src/main/java/seedu/workouttracker/workout/WorkoutList.java
    public void removeWorkout(Date date) {
        for (Workout workout : workoutList) {
            if (workout.getDate().equals(date)) {
                workoutList.remove(workout);
                System.out.println("Workout deleted successfully.");
                Ui.showSeparator();
                return;
            }
        }
        System.out.println("No workout found with the specified date.");
    }
    /**
     * This method will loop the workout list and print out the date in this list
     *
     */
    //@@ author ZIZI-czh
    public void showWorkoutList() {
        try {
            if (!workoutList.isEmpty()) {
                System.out.println("Here are the list of dates for your workout: ");
                for (Workout workout : workoutList) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
                    String formattedDate = dateFormat.format(workout.getDate());
                    System.out.println(formattedDate);
                }
                Ui.showSeparator();
            } else {
                //if there is no workout have been done
                System.out.println("Haven't start your workout, please enter your workout");
            }

        } catch (NullPointerException e) {
            System.out.println("Haven't start your workout, please enter your workout");
        }

    }
    public void displayWorkout(Date date) {
        for (Workout workout : workoutList) {
            if (workout.getDate().equals(date)) {
                System.out.println(workout.getExercises());
                Ui.showSeparator();
                return;
            }
        }
    }
=======
>>>>>>> 7ac8556 (Refactor the code edit Junit Test for list and delete calss):src/main/java/seedu/workout/WorkoutList.java
    public int getLastIndex() {
        return workoutArrayList.size() - 1;
    }

    public Workout getCurrentWorkout() {
        return workoutArrayList.get(currentWorkoutIndex);
    }
}
